package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Delayed;

public class EditableActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private int pages;
    private Toolbar toolbar;

    private FragmentStateAdapter pagerAdapter;
    private List<Integer> contentLayoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_app);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
//        setSupportActionBar(toolbar);

//        contentLayoutId = getIntent().getIntegerArrayListExtra("ids");
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        String items = getIntent().getStringExtra("items");
        Log.d(TAG, "onCreate: " + items);
        pages = Integer.parseInt(items);

        contentLayoutId = getIntent().getIntegerArrayListExtra("ids");
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);

//        Log.d(TAG, "onCreate: " + name);


        contentLayoutId = new ArrayList<>();
        contentLayoutId.add(R.layout.employees_fragment);
        contentLayoutId.add(R.layout.graph_fragment);
        contentLayoutId.add(R.layout.add_new_fragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.module_store) {
                    Intent intent = new Intent(EditableActivity.this, WebStoreListActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });


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
        viewPager.beginFakeDrag();
        viewPager.fakeDragBy(1000);
        viewPager.endFakeDrag();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_item) {
            new FirebaseDatabaseHelper().addApps(new App(getIntent().getStringExtra("name"), getIntent().getStringExtra("description"), "https://firebasestorage.googleapis.com/v0/b/redrevorebourne.appspot.com/o/logo_crop.png?alt=media&token=2ba5c686-6464-42e1-9fe7-7ac4ac1b75f8", Integer.toString(contentLayoutId.size()), contentLayoutId), new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void DataIsLoadedModule(List<Module> modules, List<String> keys) {

                }

                @Override
                public void DataIsLoadedApp(List<App> apps, List<String> keys) {

                }

                @Override
                public void DataIsLoadedEmployee(List<Employee> apps, List<String> keys) {

                }

                @Override
                public void DataIsInserted() {
                    finish();
                }

                @Override
                public void DataIsUpdated() {

                }

                @Override
                public void DataIsDeleted() {

                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            return contentLayoutId.size();
        }

        public List<Integer> getContentLayoutId() {
            return contentLayoutId;
        }

//        public void setContentLayoutId(List<Integer> contentLayoutId) {
//            this.contentLayoutId = contentLayoutId;
//        }
    }

}
