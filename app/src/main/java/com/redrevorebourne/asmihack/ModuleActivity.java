package com.redrevorebourne.asmihack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

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

        //imageModule = findViewById(R.id.imageModules);
        //imageModule = set

        moduleRate = findViewById(R.id.rateBox);
        moduleRate.setText(getIntent().getStringExtra("rating"));

        moduleNumberDownloads = findViewById(R.id.moduleDownloads);
        moduleNumberDownloads.setText(getIntent().getStringExtra("downloads"));

        moduleDescription = findViewById(R.id.moduleDescription);
        moduleDescription.setText(getIntent().getStringExtra("description"));

        moduleContact = findViewById(R.id.moduleContact);
        moduleContact.setText(getIntent().getStringExtra("description"));

        moduleURL = findViewById(R.id.moduleURL);
        moduleURL.setText(getIntent().getStringExtra("url"));
    }
}
