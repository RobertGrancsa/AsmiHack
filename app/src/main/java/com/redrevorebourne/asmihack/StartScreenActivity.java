package com.redrevorebourne.asmihack;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class StartScreenActivity extends Activity {
    private RecyclerView recyclerViewApps;
    private BottomNavigationView bottomAppBar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        bottomAppBar = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.floatingActionButton);

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(StartScreenActivity.this);

//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);

                dialog.setContentView(R.layout.app_dialog);
                
                Button button = dialog.findViewById(R.id.buttonCreateApp);
                EditText appName = dialog.findViewById(R.id.appNameDialog);
                EditText appDesc = dialog.findViewById(R.id.appDescDialog);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StartScreenActivity.this, EditableActivity.class);
                        intent.putExtra("name", appName.getText());
                        intent.putExtra("items", "1");
                        List<Integer> ids = new ArrayList<>();
                        ids.add(R.layout.add_new_fragment);
                        intent.putExtra("ids", ids.toArray());

                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                dialog.show();
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
