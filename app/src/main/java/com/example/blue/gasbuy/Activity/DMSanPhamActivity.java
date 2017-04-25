package com.example.blue.gasbuy.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.Fragment.SanPhamfragment;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;
import com.example.blue.gasbuy.SaveLoadPreferences;

import java.util.ArrayList;
import java.util.List;

public class DMSanPhamActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<SanPham> arrSanpham = new ArrayList<>();

    private SanPhamfragment sanPhamfragment;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm_san_pham);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        setSanPham();
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        getSupportActionBar().setTitle(R.string.cua_hang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    //Gán sự kiện cho nút backhome
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            case R.id.app_bar_view:
                SaveLoadPreferences saveLoadPreferences=new SaveLoadPreferences(this);
boolean b=saveLoadPreferences.loadBoolean(SaveLoadPreferences.KEY_VIEW);
                if (b) {
                    item.setIcon(R.drawable.ic_view_module);
                   saveLoadPreferences.seveBoolean(SaveLoadPreferences.KEY_VIEW,false);
                } else {
                    item.setIcon(R.drawable.ic_view_list);
                    saveLoadPreferences.seveBoolean(SaveLoadPreferences.KEY_VIEW,true);
                }
                mSectionsPagerAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // gán nút search lên ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        SaveLoadPreferences saveLoadPreferences=new SaveLoadPreferences(this);
        boolean b=saveLoadPreferences.loadBoolean(SaveLoadPreferences.KEY_VIEW);
        MenuItem item=menu.findItem(R.id.app_bar_view);
        if(!b){
            item.setIcon(R.drawable.ic_view_module);
        }
        else {
            item.setIcon(R.drawable.ic_view_list);
        }

        return true;
    }

    // thêm dữ liệu cho sản phẩm
    private void setSanPham() {
        DatabaseManager data = new DatabaseManager(this);
        arrSanpham = data.getAllDataDM();
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * @param position Điểm fragment tương ứng với vị tri
         * @return fragment
         */
        @Override
        public Fragment getItem(int position) {
            sanPhamfragment = new SanPhamfragment();

            return sanPhamfragment;
        }

        // return số lượng cột của bảng
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public void notifyDataSetChanged() {
            Log.e("setdata","thanh cong");
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        // return tên cột
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Bếp Gas";
                case 1:
                    return "Bình Gas ";
                case 2:
                    return "Van Gas ";
            }
            return null;
        }

    }
}
