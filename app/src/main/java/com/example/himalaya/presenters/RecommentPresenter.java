package com.example.himalaya.presenters;

import androidx.annotation.Nullable;

import com.example.himalaya.interfaces.IRecommendPresenter;
import com.example.himalaya.interfaces.IRecommendViewCallback;
import com.example.himalaya.utils.Constants;
import com.example.himalaya.utils.LogUtil;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *author:The GodFather
 *Date:2020/9/15
 *description:
 */public class RecommentPresenter implements IRecommendPresenter {
    private static final String TAG ="RecommentPresenter" ;

    private List<IRecommendViewCallback> mCallbacks =new ArrayList<>();

    //构造器私有
     private RecommentPresenter(){

    }
    private static   RecommentPresenter sInstance =null;
    //懒汉式单例模式
    public static  RecommentPresenter getInstance(){
        if(sInstance ==null){
            synchronized (RecommentPresenter.class){
                if(sInstance==null){
                    sInstance =new RecommentPresenter();
                }
            }
        }
        return sInstance;
    }
    /*
    *获取推荐内容，其实是猜你喜欢
    * 获取专辑*/

    @Override
    public void getRecommendList() {
        //获取推荐内容
        //封装参数
        updateLoading();
        Map<String,String> map =new HashMap<>();
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMAND_COUNT+"");
        //这个参数标识一页数据显示返回多少条
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(@Nullable GussLikeAlbumList gussLikeAlbumList) {
                LogUtil.d(TAG,"运行在"+Thread.currentThread().getName());
                if(gussLikeAlbumList != null){
                    List<Album> albumList =gussLikeAlbumList.getAlbumList();
                    //数据回来，我们更新UI
                    //upRecommendUI(albumList);
                    handlerRecommendResult(albumList);
                }
            }

            @Override
            public void onError(int i, String s) {
                //数据出错
                LogUtil.d(TAG,"error-->"+i);
                LogUtil.d(TAG,"errorMsg-->"+s);
                handleError();
            }
        });

    }

    private void handleError() {
        if(mCallbacks != null){
            for(IRecommendViewCallback callback :mCallbacks){
                callback.onNetworkError();
            }
        }
    }

    private void handlerRecommendResult(List<Album> albumList) {
        //通知UI更新
        if(albumList != null){
            /*测试，让推荐界面显示为空*/
          //  albumList.clear();
            if(albumList.size() == 0 ){
                for(IRecommendViewCallback callback : mCallbacks){
                    callback.onEmpty();
                }
            }else{
                for(IRecommendViewCallback callback:mCallbacks){
                    callback.onRecommentListLoaded(albumList);
                }
            }

        }
    }

    private void updateLoading(){
      for(IRecommendViewCallback callback:mCallbacks){
          callback.onLoading();
      }
    }
    @Override
    public void pull2RefreshMore() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void registerViewCallback(IRecommendViewCallback callback) {
     if(!mCallbacks.contains(callback) && mCallbacks!=null){
         mCallbacks.add(callback);
     }
    }

    @Override
    public void unRegisterViewCallback(IRecommendViewCallback callback) {
        if(mCallbacks != null){
            mCallbacks.remove(mCallbacks);
        }
    }
}
