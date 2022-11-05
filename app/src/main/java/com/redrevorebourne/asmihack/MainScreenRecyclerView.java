package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class MainScreenRecyclerView {
    private Context mContext;
    private MainScreenRecyclerView.AppAdapter mAppAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public void setConfig(RecyclerView recyclerView, Context context, List<App> apps){
        mContext = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAppAdapter = new MainScreenRecyclerView.AppAdapter(apps);
        mAppAdapter.setHasStableIds(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MainScreenRecyclerView.AppAdapter(apps));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    class AppItemView extends RecyclerView.ViewHolder{

        private ImageView appImage;
        private TextView appName;
        private LinearLayout appBackground;


        public AppItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.start_image_layout, parent, false));

            appImage = itemView.findViewById(R.id.appImage);
            appName = itemView.findViewById(R.id.appName);
            appBackground = itemView.findViewById(R.id.backgroundApp);

//            companyTicker = itemView.findViewById(R.id.companyTicker);
//            backgroundCompany = itemView.findViewById(R.id.backgroundCompany);
//            buttonPlay = itemView.findViewById(R.id.buttonPlay);
        }
        public void bind(App app) {
//            DatabaseReference storageRef = firebaseDatabase.getReference();
//            DatabaseReference getImage = databaseReference.child(app.getPhotoURL());

            appName.setText(app.getName());
            Picasso.get().load(app.getPhotoURL()).into(appImage);

            Log.d(TAG, "bind: " + app.getName());

            appBackground.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, EditableActivity.class);
                intent.putExtra("name", app.getName());
                intent.putExtra("items", app.getNumberOfPages());

                mContext.startActivity(intent);
            });
        }
    }


    class AppAdapter extends RecyclerView.Adapter<AppItemView>{
        private List<App> appList;

        public AppAdapter(List<App> appList) {
            this.appList = appList;
        }

        @NonNull
        @Override
        public AppItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AppItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AppItemView holder, int position) {
            holder.bind(appList.get(position));
        }

        @Override
        public long getItemId(int position) {
            if (position < appList.size()){
                appList.get(position).getName();
            }
            return RecyclerView.NO_ID;
        }

        @Override
        public int getItemCount() {
            return appList.size();
        }
    }
}
