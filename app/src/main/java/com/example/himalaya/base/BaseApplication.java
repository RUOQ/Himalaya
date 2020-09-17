package com.example.himalaya.base;

import android.app.Application;
import android.os.Handler;

import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.player.XMediaPlayerConstants;

/*
 *author:The GodFather
 *Date:2020/9/9
 *description:
 */public class BaseApplication extends Application {
    private String TAG = "BaseApplication";
    private static Handler sHandler = null;

    public void onCreate(){
      super.onCreate();
      ConstantsOpenSdk.isDebug = true;
      XMediaPlayerConstants.isDebug = true;
      CommonRequest mXimalaya = CommonRequest.getInstanse();

      if(DTransferConstants.isRelease) {
          String mAppSecret = "e0ec17c9c71d40fd55ed4aa135ca5428";
          mXimalaya.setAppkey("bad32c876d2cef409195b54e3e218996");
          mXimalaya.setPackid("com.example.himalaya");
          mXimalaya.init(this ,mAppSecret);
         LogUtil.d(TAG,"初始化接口了");
      } else {
          String mAppSecret = "0a09d7093bff3d4947a5c4da0125972e";
          mXimalaya.setAppkey("f4d8f65918d9878e1702d49a8cdf0183");
          mXimalaya.setPackid("com.ximalaya.qunfeng");
          mXimalaya.init(this ,mAppSecret);
      }
      LogUtil.init(this.getPackageName(),false);

      sHandler = new Handler();
  }

   public static Handler getsHandler(){
        return sHandler;
   }
 }
