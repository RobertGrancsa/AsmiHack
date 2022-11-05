package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

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

        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        String items = getIntent().getStringExtra("items");
        Log.d(TAG, "onCreate: " + items);
        pages = Integer.parseInt(items);

        getIntent().getIntArrayExtra("ids");

        contentLayoutId = new ArrayList<>();
        contentLayoutId.add(R.layout.other_fragment);
        contentLayoutId.add(R.layout.editable_fragment);
        contentLayoutId.add(R.layout.editable_fragment);
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

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new EditableFragment(contentLayoutId.get(position));
        }

        @Override
        public int getItemCount() {
            return pages;
        }
    }

}
