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
    private DatabaseReference mReferenceModule;
//    private DatabaseReference mReferenceCompany;
//    private DatabaseReference mReferenceTops;
    private List<Module> modules = new ArrayList<>();


    public interface DataStatus{
        void DataIsLoadedModule(List<Module> modules, List<String> keys);
//        void DataIsLoadedTop(List<Module> modules, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceModule = mDatabase.getReference("ModulesStore");
//        mReferenceCompany = mDatabase.getReference("Companies");
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
