package com.example.himalaya;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.himalaya.adapters.IndicatorAdapter;
import com.example.himalaya.adapters.MainContentAdapter;
import com.example.himalaya.utils.LogUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;


public class MainActivity extends AppCompatActivity {
    private static final String Tag="MainActivity";
    private MagicIndicator magicIndicator;
    private ViewPager contentPager;
    private IndicatorAdapter indicatorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        
    }

    private void initEvent() {
        indicatorAdapter.setOnIndicatorTabClickListener(new IndicatorAdapter.OnIndicatorTabClickListener() {
            @Override
            public void onTabClick(int index) {
                LogUtil.d(Tag,"click index --->"+index);
                if(contentPager != null){
                    contentPager.setCurrentItem(index);

                }
            }
        });
    }

    @SuppressLint("ResourceType")
    private void initView() {
        magicIndicator = this.findViewById(R.id.main_indicator);

        ContextCompat.getColor(this, R.color.main_color);
        //封装了一层通用导航器 common Navigator
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        indicatorAdapter = new IndicatorAdapter(this);
        commonNavigator.setAdapter(indicatorAdapter);
        //viewpager
        contentPager = this.findViewById(R.id.content_pager);
        contentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //创建内容适配器
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainContentAdapter mainContentAdapter = new MainContentAdapter(fragmentManager);

        contentPager.setAdapter(mainContentAdapter);
        //把viewpager和indicator绑定到一起
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator,contentPager);
    }
}
