package com.redrevorebourne.asmihack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class StoreRecyclerView {
    private Context mContext;
    private StoreRecyclerView.ModuleAdapter mModuleAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Module> module){
        mContext = context;
        mModuleAdapter = new StoreRecyclerView.ModuleAdapter(module);
        mModuleAdapter.setHasStableIds(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new StoreRecyclerView.ModuleAdapter(module));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    class ModuleItemView extends RecyclerView.ViewHolder{

        private TextView moduleName;
        private TextView moduleRating;
        private TextView moduleDownloads;
        private TextView moduleAuthor;
        private ImageView imageModule;
        private RelativeLayout backgroundModule;


        public ModuleItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.store_item_layout, parent, false));

            moduleName = itemView.findViewById(R.id.moduleName);
            moduleRating = itemView.findViewById(R.id.moduleRatings);
            moduleDownloads = itemView.findViewById(R.id.moduleDownloads);
            moduleAuthor = itemView.findViewById(R.id.moduleAuthor);
            backgroundModule = itemView.findViewById(R.id.moduleBackground);
            imageModule = itemView.findViewById(R.id.imageView);
        }
        public void bind(Module module){
            moduleName.setText(module.getName());
            moduleRating.setText(module.getRating());
            moduleAuthor.setText(module.getAuthor());
            moduleDownloads.setText(module.getDownloadNumber());

            Picasso.get().load(module.getPhotoUrl()).into(imageModule);

            backgroundModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO insert class name here
                    Intent intent = new Intent(mContext, ModuleActivity.class);
                    intent.putExtra("name", module.getName());
                    intent.putExtra("rating", module.getRating());
                    intent.putExtra("downloads", module.getDownloadNumber());
                    intent.putExtra("author", module.getAuthor());
                    intent.putExtra("description", module.getDescription());
                    intent.putExtra("url", module.getPhotoUrl());

                    mContext.startActivity(intent);
                }
            });
        }
    }

    class ModuleAdapter extends RecyclerView.Adapter<ModuleItemView>{
        private List<Module> moduleList;

        public ModuleAdapter(List<Module> moduleList) {
            this.moduleList = moduleList;
        }

        @NonNull
        @Override
        public ModuleItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ModuleItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ModuleItemView holder, int position) {
            holder.bind(moduleList.get(position));
        }

        @Override
        public long getItemId(int position) {
            if (position < moduleList.size()){
                moduleList.get(position).getName();
            }
            return RecyclerView.NO_ID;
        }

        @Override
        public int getItemCount() {
            return moduleList.size();
        }
    }
}

