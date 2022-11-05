package com.redrevorebourne.asmihack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

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
        private RelativeLayout backgroundCompany;


        public ModuleItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.store_item_layout, parent, false));

            moduleName = itemView.findViewById(R.id.moduleName);
            moduleRating = itemView.findViewById(R.id.moduleRatings);
            moduleDownloads = itemView.findViewById(R.id.moduleDownloads);
            moduleAuthor = itemView.findViewById(R.id.moduleAuthor);
        }
        public void bind(Module module){
            moduleName.setText(module.getName());
            moduleRating.setText(module.getRating());
//            moduleAuthor.setText(module.getAuthor());
            moduleDownloads.setText(module.getDownloadNumber());
//            companyName.setText(module.getName());
//            companyName.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//            companyTicker.setText(module.getFiletype());
//            MediaPlayer mediaPlayer = new MediaPlayer();
//            buttonPlay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mediaPlayer.isPlaying()) {
//                        mediaPlayer.stop();
//                        mediaPlayer.release();
//                        buttonPlay.setIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_round_play_arrow_24));
//                        return;
//                    }
//                    Uri myUri = module.getUrl().getResult();
//                    mediaPlayer.setAudioAttributes(
//                            new AudioAttributes.Builder()
//                                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//                                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                                    .build()
//                    );
//                    try {
//                        mediaPlayer.setDataSource(mContext, myUri);
//                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
//                        mediaPlayer.start();
//                        Log.d(TAG, "onClick: tryin to play");
//                        buttonPlay.setIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_round_pause_24));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Log.e(TAG, "onClick: error :(", e);
//                    }
//                }
//            });
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

