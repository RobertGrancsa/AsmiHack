
import firebase_admin
from firebase_admin import db
import math
import pandas_datareader as web
import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from sklearn import preprocessing
from keras.models import Sequential
from keras.layers import Dense, LSTM
import matplotlib.pyplot as plt

plt.style.use('fivethirtyeight')

# Configure firebase database connection
cred_obj = firebase_admin.credentials.Certificate('/home/robert-nst/Desktop/ASMI/redrevorebourne-firebase-adminsdk-6v36m-d1eb4ea52e.json') 
default_app = firebase_admin.initialize_app(cred_obj, {
	'databaseURL':'https://redrevorebourne-default-rtdb.europe-west1.firebasedatabase.app/'
	})

ref = db.reference("/User/modules/finance/")

# Retrieve known dataset from Firebase Realtime Database
dataset = []

for i in range(1, 13):
    dataset.append(ref.child("2021").child(str(i)).get())
for i in range(1, 9):
    dataset.append(ref.child("2022").child(str(i)).get())

# Get the number of values to train the model on
training_data_len = math.ceil(len(dataset) * .8)

# Scale the data

df = pd.DataFrame({'Profit': dataset})
x = df['Profit'].values.reshape(-1, 1) # to return a numpy array

scaler = preprocessing.MinMaxScaler()
scaled_data = scaler.fit_transform(x)

# Create the training data set:

# Create the scaled training data set
train_data = scaled_data[0 : training_data_len , :]

# Split the data into x_train and y_train data sets
x_train = []
y_train = []

for i in range(14, len(train_data)):
    x_train.append(train_data[i - 14 : i, 0])
    y_train.append(train_data[i, 0])

# Convert the x_train and y_train to numpy arrays
x_train = np.array(x_train)
y_train = np.array(y_train)

# Reshape the data
x_train = np.reshape(x_train, (x_train.shape[0], x_train.shape[1], 1))
print(x_train.shape)

# Build the LSTM model
model = Sequential()
model.add(LSTM(50, return_sequences=True, input_shape = (x_train.shape[1], 1)))
model.add(LSTM(50, return_sequences=False))
model.add(Dense(25))
model.add(Dense(1))

# Compile the model
model.compile(optimizer='adam', loss='mean_squared_error')

# Train the model
model.fit(x_train, y_train, batch_size=1, epochs=30)

# create the testing data set
# create a new array containing scaled values

test_data = scaled_data[training_data_len - 14: , :]

# create the data sets x_test and y_test
x_test = []
y_test = x[training_data_len:, :]
for i in range(14, len(test_data)):
    x_test.append(test_data[i-14:i, 0])

# convert the data to a numpy array
x_test = np.array(x_test)

# reshape the data
x_test = np.reshape(x_test, (x_test.shape[0], x_test.shape[1], 1))

# get the models predicted scores
predictions = model.predict(x_test)
predictions = scaler.inverse_transform(predictions)

for x in predictions:
    dataset.append(x[0])

for i in range (0, 24):
    dataset[i] = float(dataset[i])

known_dataset = []
predicted_dataset = []

for i in range (0, 21):
    known_dataset.append(dataset[i])
for j in range (20, 24):
    predicted_dataset.append(dataset[j])

plt.title("ProfitTracking")
plt.xlabel("Month")
plt.ylabel("Profit")
plt.plot(range(0, 21), known_dataset, color="blue")
plt.plot(range(20, 24), predicted_dataset, color="red")
plt.savefig('prediction.jpg')
