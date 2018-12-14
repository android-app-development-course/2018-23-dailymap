package com.dailymap.view;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.dailymap.R;
import com.dailymap.adapter.MyViewPagerAdapter;
import com.dailymap.pages.homepagelink.HomePageFragment;
import com.dailymap.pages.MineFragment;
import com.dailymap.pages.SharePageFragment;
import com.dailymap.pages.TravelNoteFragment;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation_bar)
    protected BottomNavigationBar mBottomNavigationBar;

    @BindView(R.id.viewPager)
    protected ViewPager viewPager;

    private List<Fragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initview();
        initBottomNavigationBar();
        initviewpager();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initviewpager() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomePageFragment());
        mFragments.add(new SharePageFragment());
        mFragments.add(new TravelNoteFragment());
        mFragments.add(new MineFragment());

        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), mFragments));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initview() {
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
    }


    /**
     * 初始化底部导航栏
     */
    private void initBottomNavigationBar()
    {
        mBottomNavigationBar.clearAll();

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setActiveColor(R.color.text_blue);

        TextBadgeItem messageBadgeItem = new TextBadgeItem().setBorderWidth(4).setAnimationDuration(200).setBackgroundColor(Color.RED).setText("3").setHideOnSelect(true);

        mBottomNavigationBar.addItem(
                new BottomNavigationItem(R.drawable.find, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.find, "狸友圈"))
                .addItem(new BottomNavigationItem(R.drawable.message, "游记").setBadgeItem(messageBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.user, "我的")).setFirstSelectedPosition(0).initialise();


        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(int position)
            {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position)
            {

            }

            @Override
            public void onTabReselected(int position)
            {

            }
        });
    }
}
