package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import static com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import java.util.Locale;

public class MainActivity extends Activity {
    private FirebaseAuth mAuth;
    private TextView emailbox;
    private TextView passwordbox;
    private static boolean counter = false;

    private GoogleSignInClient googleSignInClient;
    String TAG = "SignUp_Activity";
    private final int RESULT_CODE_SINGIN=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signInGoogle = findViewById(R.id.loginGoogle);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailbox = findViewById(R.id.emailBox);
        passwordbox = findViewById(R.id.passwordBox);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(view -> signIn(emailbox.getText().toString(), passwordbox.getText().toString()));

        TextView moveToRegister = findViewById(R.id.moveToRegister);

        moveToRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

            startActivity(intent);
        });

        signInGoogle.setOnClickListener(view -> {
            GoogleSignInOptions gso = new
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            googleSignInClient = GoogleSignIn.getClient(this,gso);

            Intent singInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(singInIntent,RESULT_CODE_SINGIN);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //just to verify the code
        if (requestCode == RESULT_CODE_SINGIN) {
            Task<GoogleSignInAccount> task;
            task = getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        //we use try catch block because of Exception.
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(MainActivity.this,"Signed in with Google successfully",Toast.LENGTH_LONG).show();

            //SignIn successful now show authentication
            FirebaseGoogleAuth(account);
        }

        catch (ApiException e) {
            e.printStackTrace();
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this,"Signing in with Google failed",Toast.LENGTH_LONG).show();
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        //checking the Authentication Credential and checking the task is successful
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_LONG).show();
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                updateUI(firebaseUser);
            } else {
                Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                updateUI(null);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }

        counter = true;
    }

    private void reload() { }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(MainActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, StartScreenActivity.class);

            startActivity(intent);
            finish();
        }
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        if ((email.equals("") || password.equals("")) && counter) {
            Toast.makeText(MainActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        }
        // [END sign_in_with_email]
    }
}

