package com.redrevorebourne.asmihack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class ModuleActivity extends Activity {
    private TextView moduleName;
    private ImageView moduleImage;
    private TextView moduleRate;
    private TextView moduleNumberDownloads;
    private TextView moduleDescription;
    private TextView moduleContact;
    private TextView moduleURL;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        moduleName = findViewById(R.id.moduleName);
        moduleName.setText(getIntent().getStringExtra("name"));

        moduleImage = findViewById(R.id.imageModules);
        Picasso.get().load(getIntent().getStringExtra("url")).into(moduleImage);
        //imageModule = set

        moduleRate = findViewById(R.id.rateBox);
        moduleRate.setText(getIntent().getStringExtra("rating"));

        moduleNumberDownloads = findViewById(R.id.numberDownloadsBox);
        moduleNumberDownloads.setText(getIntent().getStringExtra("downloads"));

        moduleDescription = findViewById(R.id.moduleDescriptionAct);
        moduleDescription.setText(getIntent().getStringExtra("description"));

        moduleContact = findViewById(R.id.moduleContact);
        moduleContact.setText(getIntent().getStringExtra("description"));

//        moduleURL = findViewById(R.id.moduleURL);
//        moduleURL.setText(getIntent().getStringExtra("url"));
    }
}
