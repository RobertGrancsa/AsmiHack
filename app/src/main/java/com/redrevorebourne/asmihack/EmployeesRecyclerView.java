package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeesRecyclerView {
    private Context mContext;
    private EmployeeAdapter employeeAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Employee> employees){
        mContext = context;
        employeeAdapter = new EmployeeAdapter(employees);
        employeeAdapter.setHasStableIds(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new EmployeeAdapter(employees));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    class EmployeeItemView extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView title;
        private TextView salary;
        private TextView team;

        public EmployeeItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.employee_layout, parent, false));

            name = itemView.findViewById(R.id.employeeName);
            title = itemView.findViewById(R.id.employeeRole);
            team = itemView.findViewById(R.id.team);
            salary = itemView.findViewById(R.id.salary);
        }
        public void bind(Employee employee) {
            name.setText(employee.getName());
            title.setText(employee.getPosition());
            team.setText(employee.getTeam());
            salary.setText(employee.getSalary());
//
//            Log.d(TAG, "bind: " + app.getName());
//
//            appBackground.setOnClickListener(view -> {
//                Intent intent = new Intent(mContext, EditableActivity.class);
//                intent.putExtra("name", app.getName());
//                intent.putExtra("items", app.getNumberOfPages());
//
//                mContext.startActivity(intent);
//            });
        }
    }


    class EmployeeAdapter extends RecyclerView.Adapter<EmployeesRecyclerView.EmployeeItemView>{
        private final List<Employee> employeeList;

        public EmployeeAdapter(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }

        @NonNull
        @Override
        public EmployeesRecyclerView.EmployeeItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EmployeesRecyclerView.EmployeeItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull EmployeesRecyclerView.EmployeeItemView holder, int position) {
            holder.bind(employeeList.get(position));
        }

        @Override
        public long getItemId(int position) {
            if (position < employeeList.size()){
                employeeList.get(position).getName();
            }
            return RecyclerView.NO_ID;
        }

        @Override
        public int getItemCount() {
            return employeeList.size();
        }
    }
}

