package com.example.himalaya.presenters;

import com.example.himalaya.interfaces.IAlbumDetailViewCallback;
import com.example.himalaya.interfaces.IAlbumDetialPresenter;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

/*
 *author:The GodFather
 *Date:2020/9/18
 *description:
 */public class AlbumDetailPresenter implements IAlbumDetialPresenter {
      private List<IAlbumDetailViewCallback> mCallbacks = new ArrayList<>();
    private Album mTargetAlbum = null;

    private AlbumDetailPresenter(){

     }
     private static AlbumDetailPresenter sInstance = null;
     public static AlbumDetailPresenter getInstance() {
         if (sInstance == null) {
             synchronized (AlbumDetailPresenter.class) {
                 if (sInstance == null) {
                     sInstance = new AlbumDetailPresenter();
                 }
             }
         }
         return sInstance;
     }
    @Override
    public void pull2RefreshMore() {

    }

    @Override
    public void loadMore() {
    }

    @Override
    public void getAlbumDetail(int albumId, int page) {

    }

    @Override
    public void registerViewCallback(IAlbumDetailViewCallback detailViewCallback) {
     //先判断有没有为
     if(!mCallbacks.contains(detailViewCallback)){
         mCallbacks.add(detailViewCallback);
         if(mTargetAlbum != null){
             detailViewCallback.onAlbumLoaded(mTargetAlbum);
         }
     }
    }

    @Override
    public void unRegisterViewCallback(IAlbumDetailViewCallback detailViewCallback) {
      mCallbacks.remove(detailViewCallback);
    }

    public void setTargetAlbum(Album targetAlbum){
         this.mTargetAlbum = targetAlbum;
    }
}
