package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Delayed;

public class EditableActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private int pages;

    private FragmentStateAdapter pagerAdapter;
    private List<Integer> contentLayoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_app);

        contentLayoutId = getIntent().getIntegerArrayListExtra("ids");
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        String items = getIntent().getStringExtra("items");
        Log.d(TAG, "onCreate: " + items);
        pages = Integer.parseInt(items);


        contentLayoutId = new ArrayList<>();
        contentLayoutId.add(R.layout.employees_fragment);
        contentLayoutId.add(R.layout.add_new_fragment);
        contentLayoutId.add(R.layout.graph_fragment);
        contentLayoutId.add(R.layout.editable_fragment);
        contentLayoutId.add(R.layout.editable_fragment);


//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        // Replace the contents of the container with the new fragment
//        ft.replace(R.id.editableFragment, new EditableFragment());
//        // or ft.add(R.id.your_placeholder, new FooFragment());
//        // Complete the changes added above
//        ft.commit();

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        viewPager.setPageTransformer(new ZoomOutPageTransformer());
    }

    class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new EditableFragment(contentLayoutId.get(position), this);
        }

        @Override
        public int getItemCount() {
            return pages;
        }

        public List<Integer> getContentLayoutId() {
            return contentLayoutId;
        }

//        public void setContentLayoutId(List<Integer> contentLayoutId) {
//            this.contentLayoutId = contentLayoutId;
//        }
    }

}
