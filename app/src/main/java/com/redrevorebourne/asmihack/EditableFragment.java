package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EditableFragment extends Fragment {
    private int layoutIds;
    private EditableActivity.ScreenSlidePagerAdapter ref;
    public EditableFragment(int layoutIds, EditableActivity.ScreenSlidePagerAdapter ref) {
        this.layoutIds = layoutIds;
        this.ref = ref;
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
                break;
            case R.layout.add_new_fragment:
                MaterialButton button = view.findViewById(R.id.addNewModule);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO make here thing go up
                        Log.d(TAG, "onClick: Hello");
//                        ref.getContentLayoutId().size;
                    }
                });
                break;
            case R.layout.employees_fragment:
                RecyclerView recyclerView = view.findViewById(R.id.employeesRecyclerView);

                new FirebaseDatabaseHelper().readEmployees(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoadedModule(List<Module> modules, List<String> keys) {

                    }

                    @Override
                    public void DataIsLoadedApp(List<App> apps, List<String> keys) {

                    }

                    @Override
                    public void DataIsLoadedEmployee(List<Employee> apps, List<String> keys) {
                        new EmployeesRecyclerView().setConfig(recyclerView, getActivity(), apps);
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
                break;
            case R.layout.graph_fragment:
                ImageView image = view.findViewById(R.id.graph);

                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/redrevorebourne.appspot.com/o/prediction.jpg?alt=media&token=872f9c9f-3faf-4410-9c36-ee1f21f6a77f").into(image);
                break;
        }
    }
}
