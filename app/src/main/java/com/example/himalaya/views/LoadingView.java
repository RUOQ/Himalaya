package com.example.himalaya.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.himalaya.R;

/*
 *author:The GodFather
 *Date:2020/9/17
 *description:
 */public class LoadingView extends androidx.appcompat.widget.AppCompatImageView {
    //旋转角度
    private int rotateDegree  = 0;
    private boolean mNeedRotate = false;
     public LoadingView(@NonNull Context context) {
        this(context,null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置图片
        setImageResource(R.mipmap.loading);
    }
    @Override
    protected void onAttachedToWindow(){
         super.onAttachedToWindow();
         mNeedRotate = true;
         post(new Runnable() {
             @Override
             public void run() {
                 rotateDegree += 30;
                 //判断是否满足转满一圈,若是直接置为0,否则直接赋值
                 rotateDegree = rotateDegree<=360?rotateDegree:0;
                 invalidate();
                 //是否继续旋转
                 if(mNeedRotate){
                     postDelayed(this,50);
                 }

             }
         });
         //绑定到window的时候
    }
    @Override
    protected void onDetachedFromWindow(){
        super.onDetachedFromWindow();
        mNeedRotate = false;
        //从window解绑的时候
    }
    @Override
    protected void onDraw(Canvas canvas){
        /*
        * @param 旋转角度、x坐标，y坐标
        * */canvas.rotate(rotateDegree,getWidth()/2,getHeight()/2);
        super.onDraw(canvas);
    }
}
