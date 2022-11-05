package com.redrevorebourne.asmihack;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WebStoreListActivity extends Activity {
    private RecyclerView recyclerViewModules;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store_list);

        recyclerViewModules = findViewById(R.id.recyclerViewStore);

        new FirebaseDatabaseHelper().readModules(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoadedModule(List<Module> modules, List<String> keys) {
                new StoreRecyclerView().setConfig(recyclerViewModules, WebStoreListActivity.this, modules);
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
