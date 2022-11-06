package com.redrevorebourne.asmihack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class EditableFragment extends Fragment {
    private int layoutIds;
    public EditableFragment(int layoutIds) {
        this.layoutIds = layoutIds;
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(layoutIds, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        switch (layoutIds) {
            case R.layout.editable_fragment:
                MaterialButton button = view.findViewById(R.id.addNewModule);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        
                    }
                });
                break;
            case R.layout.add_new_fragment:
                break;
            case R.layout.other_fragment:
                break;
        }
    }
}
