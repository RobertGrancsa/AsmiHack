package com.redrevorebourne.asmihack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class StartScreenActivity extends Activity {
    private RecyclerView recyclerViewApps;
    private BottomNavigationView bottomAppBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        bottomAppBar = findViewById(R.id.bottomNavigationView);

        bottomAppBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.store_item) {
                    Intent intent = new Intent(StartScreenActivity.this, WebStoreListActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

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
