package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class WebStoreListActivity extends Activity {
    private RecyclerView recyclerViewModules;
    private List<Module> moduleList;
    private SearchView searchBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store_list);

        searchBar = findViewById(R.id.searchView);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase(Locale.ROOT);
                List<Module> moduleTemp = new ArrayList<>();

                for (int i = 0; i < moduleList.size(); i++) {
                    String name = moduleList.get(i).getName().toLowerCase(Locale.ROOT);
                    if (name.contains(newText)) {
                        moduleTemp.add(moduleList.get(i));
                    }
                }

                if (moduleTemp.isEmpty()) {
                    Snackbar snackbar = Snackbar
                            .make(recyclerViewModules, "No module named " + newText + " found", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    new StoreRecyclerView().setConfig(recyclerViewModules, WebStoreListActivity.this, moduleTemp);
                }
                return false;
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerViewModules = findViewById(R.id.recyclerViewStore);

        new FirebaseDatabaseHelper().readModules(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoadedModule(List<Module> modules, List<String> keys) {
                moduleList = modules;
                Log.d(TAG, "DataIsLoadedModule: " + modules.toString());
                new StoreRecyclerView().setConfig(recyclerViewModules, WebStoreListActivity.this, modules);
            }

            @Override
            public void DataIsLoadedApp(List<App> apps, List<String> keys) {

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

//    private void sortListBy(List<Module> modules){
//        Spinner spinnerSort = findViewById(R.id.sortBy);
//        Spinner spinnerWay = findViewById(R.id.upDown);
//
//        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sortListBy(modules);
//                new StoreRecyclerView().setConfig(recyclerViewModules, WebStoreListActivity.this, modules);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//                // sometimes you need nothing here
//            }
//        });
//        spinnerWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sortListBy(modules);
//                new CompanyRecyclerView().setConfig(mRecyclerView, MainActivity.this, modules);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//                // sometimes you need nothing here
//            }
//        });
//
//        Collections.sort(modules, new Comparator<CompanyFront>(){
//            public int compare(CompanyFront obj1, CompanyFront obj2) {
//                // ## Ascending order
//                Object selectedItem = spinnerSort.getSelectedItem();
//                Object selectedItem1 = spinnerWay.getSelectedItem();
//                Log.d(ContentValues.TAG, "compare: " + selectedItem1);
//                Log.d(ContentValues.TAG, "compare: " + spinnerWay.getItemIdAtPosition(1));
//                if (spinnerWay.getItemAtPosition(0).equals(selectedItem1)) {
//                    if (spinnerSort.getItemAtPosition(0).equals(selectedItem)) {
//                        return obj1.getCompany_name().compareToIgnoreCase(obj2.getCompany_name());
//                    } else if (spinnerSort.getItemAtPosition(2).equals(selectedItem))  {
//                        return Float.valueOf(obj1.getTotal()).compareTo(Float.valueOf(obj2.getTotal()));
//                    } else {
//                        return obj1.getStock_symbol().compareToIgnoreCase(obj2.getStock_symbol());
//                    }
//                } else {
//                    if (spinnerSort.getItemAtPosition(0).equals(selectedItem)) {
//                        return obj2.getCompany_name().compareToIgnoreCase(obj1.getCompany_name());
//                    } else if (spinnerSort.getItemAtPosition(2).equals(selectedItem)) {
//                        return Float.valueOf(obj2.getTotal()).compareTo(Float.valueOf(obj1.getTotal()));
//                    } else {
//                        return obj2.getStock_symbol().compareToIgnoreCase(obj1.getStock_symbol());
//                    }
//                }
//
//            }
//        });
//    }
}
