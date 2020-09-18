package com.example.himalaya.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.example.himalaya.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/*
 *author:The GodFather
 *Date:2020/9/11
 *description:
 */public class IndicatorAdapter extends CommonNavigatorAdapter {
     private static final String  TAG = "IndicatorAdapter";
     private final String[] mTitles;
    private OnIndicatorTabClickListener onTabClickListener;

    public IndicatorAdapter(Context context) {
       mTitles = context.getResources().getStringArray(R.array.indicator_name);

       for(String a:mTitles){
          Log.d(TAG,a);
       }
    }

    @Override
    public int getCount() {
        if(mTitles!= null){
            return mTitles.length;
        }
        return 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        //创建view
        ColorTransitionPagerTitleView colorTransitionPagerTitleView =new ColorTransitionPagerTitleView(context);
        //一般情况下颜色设置为灰色
        colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#000000"));
        //选中情况下的颜色设置为黑色
        colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#ff0000"));
        colorTransitionPagerTitleView.setTextSize(18);
        //设置要显示的内容
        colorTransitionPagerTitleView.setText(mTitles[index]);
        //设置title的点击事件，这里的话，如果点击了title则选中下面对应的index里面
        //contentPager随着title切换
        colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换viewpager的内容，如果index不一样的话
                //TODO：
                if(onTabClickListener!=null){
                    onTabClickListener.onTabClick(index);
                }
            }
        });
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator =new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        indicator.setColors(Color.parseColor("#ffffff"));
        return indicator;
    }

    public void setOnIndicatorTabClickListener(OnIndicatorTabClickListener listener){
        this.onTabClickListener = listener;
    }
    public interface OnIndicatorTabClickListener{
        void onTabClick(int index);
    }
}
