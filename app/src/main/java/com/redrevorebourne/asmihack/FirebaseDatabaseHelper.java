package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private final DatabaseReference mReferenceModule;
    private final DatabaseReference mReferenceApps;
    private final DatabaseReference mReferenceEmployees;
    private List<Module> modules = new ArrayList<>();
    private List<App> apps = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();


    public interface DataStatus{
        void DataIsLoadedModule(List<Module> modules, List<String> keys);
        void DataIsLoadedApp(List<App> apps, List<String> keys);
        void DataIsLoadedEmployee(List<Employee> apps, List<String> keys);
//        void DataIsLoadedTop(List<Module> modules, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceModule = mDatabase.getReference("ModulesStore");
        mReferenceApps = mDatabase.getReference("Apps");
        mReferenceEmployees = mDatabase.getReference("Employees");

//        mReferenceTops = mDatabase.getReference("Tops");
    }

    public void readModules(final DataStatus dataStatus){
        mReferenceModule.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modules.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Log.d(TAG, "onDataChange: " + keyNode.getKey());
                    Module module = keyNode.getValue(Module.class);
                    modules.add(module);
                }

                dataStatus.DataIsLoadedModule(modules, keys);
                Log.d(TAG, "onDataChange: data is loaded modules");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public void readApps(final DataStatus dataStatus) {
        mReferenceApps.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                apps.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Log.d(TAG, "onDataChange: " + keyNode.getKey());
                    App app = keyNode.getValue(App.class);
                    apps.add(app);
                }

                dataStatus.DataIsLoadedApp(apps, keys);
                Log.d(TAG, "onDataChange: data is loaded apps");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public void readEmployees(final DataStatus dataStatus) {
        mReferenceEmployees.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employees.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Log.d(TAG, "onDataChange: " + keyNode.getKey());
                    Employee employee = keyNode.getValue(Employee.class);
                    employees.add(employee);
                }

                dataStatus.DataIsLoadedEmployee(employees, keys);
                Log.d(TAG, "onDataChange: data is loaded apps");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    /*
    public void addCompany(CompanyFront company, final DataStatus dataStatus){
        String key = mReferenceCompanyFront.push().getKey();
        mReferenceCompanyFront.child(key).setValue(company).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void updateCompany(String key, CompanyFront company, final DataStatus dataStatus){
        mReferenceCompanyFront.child(key).setValue(company).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteCompany(String key,final DataStatus dataStatus){
        mReferenceCompanyFront.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsDeleted();
            }
        });
    }
    */
}
