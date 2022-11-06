package com.redrevorebourne.asmihack;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StartScreenActivity extends Activity {
    private RecyclerView recyclerViewApps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerViewApps = findViewById(R.id.recyclerViewStart);
        recyclerViewApps.setLayoutManager(new GridLayoutManager(this, 2));

        new FirebaseDatabaseHelper().readApps(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoadedModule(List<Module> modules, List<String> keys) {

            }

            @Override
            public void DataIsLoadedApp(List<App> apps, List<String> keys) {
                new MainScreenRecyclerView().setConfig(recyclerViewApps, StartScreenActivity.this, apps);
            }

            @Override
            public void DataIsLoadedEmployee(List<Employee> apps, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
