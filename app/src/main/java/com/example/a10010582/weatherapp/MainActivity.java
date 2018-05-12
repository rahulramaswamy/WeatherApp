package com.example.a10010582.weatherapp;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        initTabHost();
    }
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment(), "Enter Location");
        viewPagerAdapter.addFragments(new CurrentForecast(), "Current Forecast");
        viewPagerAdapter.addFragments(new WeeklyForecast(), "Weekly Forecast");

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void initTabHost() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        ArrayList<String> tabNames = new ArrayList<>();
        tabNames.add("Home");
        tabNames.add("Current Forecast");
        tabNames.add("Weekly Forecast");
        for (int i = 0; i < tabNames.size(); i++) {
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames.get(i));
            tabSpec.setIndicator(tabNames.get(i));
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }
    public class FakeContent implements TabHost.TabContentFactory{
        Context context;
        public FakeContent(Context context){
            this.context = context;
        }
        @Override
        public View createTabContent(String s) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String s) {
        int item = tabHost.getCurrentTab();
        viewPager.setCurrentItem(item);
        viewPager.getAdapter().notifyDataSetChanged();
    }
}
