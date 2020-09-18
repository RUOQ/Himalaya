package com.example.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

/*
 *author:The GodFather
 *Date:2020/9/18
 *description:
 */public interface IAlbumDetailViewCallback {
     /*专辑内容加载出来
     * @param tracks*/

     void onDetailListLoaded(List<Track> tracks);

   /*
   * album传UI使用
   * @param  album*/
     void onAlbumLoaded(Album album);
}
