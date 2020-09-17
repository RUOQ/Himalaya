package com.example.himalaya.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.himalaya.R;
import com.example.himalaya.base.BaseApplication;

/*
 *author:The GodFather
 *Date:2020/9/16
 *description:
 */
public abstract class UILoader extends FrameLayout {

    private View loadingView;
    private View successView;
    private View networkErrorView;
    private View mEmptyView;
    private OnRetryClickListener mOnRetryClickListener =null;

    public enum UIStatus{
        LOADING,SUCCESS,NETWORK_ERROR,EMPTY,NONE
    }
    public UIStatus mCurrentStatus =UIStatus.NONE;
    public UILoader(@NonNull Context context) {
        this(context,null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void updateStatus(UIStatus status){
        mCurrentStatus = status;
        //更新UI一定要在主线程上
        BaseApplication.getsHandler().post(new Runnable() {
            @Override
            public void run() {
                switchUIByCurrentStatus();
            }
        });
    }
  /*
  * 初始化UI
  * 把相关的view加载进来*/
    private void init() {
        switchUIByCurrentStatus();
    }

    private void switchUIByCurrentStatus() {
        //加载中
        if(loadingView == null) {
            loadingView = getLoadingView();
            addView(loadingView);
        }
        //设置状态是否可见
        loadingView.setVisibility(mCurrentStatus ==UIStatus.LOADING?VISIBLE:GONE);
        //成功
        if(successView == null) {
            successView = getSuccessView(this);
            addView(successView);
        }
        //设置状态是否可见
        successView.setVisibility(mCurrentStatus ==UIStatus.SUCCESS?VISIBLE:GONE);

        //网络错误页面
        if(networkErrorView == null) {
            networkErrorView = getNetworkErrorView();
            addView(networkErrorView);
        }
        //设置状态是否可见
        networkErrorView.setVisibility(mCurrentStatus ==UIStatus.NETWORK_ERROR?VISIBLE:GONE);
        //数据为空
        if(mEmptyView == null) {
            mEmptyView = getEmptyView();
            addView(mEmptyView);
        }
        //设置状态是否可见
        mEmptyView.setVisibility(mCurrentStatus ==UIStatus.EMPTY?VISIBLE:GONE);
    }

    protected View getEmptyView(){
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_empty_view,this,false);
    };

    protected  View getNetworkErrorView(){
        //通过加载视图，找到控件
        View networkErrorView =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_error_view,this,false);
        //设置监听器，完成点击事件
        networkErrorView.findViewById(R.id.network_error_icon).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                //TODO:重新获取数据
                if(mOnRetryClickListener != null){
                    mOnRetryClickListener.onRetryClick();
                }
            }
        });
         return networkErrorView;
    };

    protected  View getSuccessView(ViewGroup container){
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_success_view,this,false);

    };

    private View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_loading_view,this,false);
    }

    public interface OnRetryClickListener{
        void onRetryClick();
    }
    
    public void setOnRetryClickListener(OnRetryClickListener listener){
        this.mOnRetryClickListener = listener;
    }
}
