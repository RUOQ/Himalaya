package com.example.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

/*
 *author:The GodFather
 *Date:2020/9/15
 *description:通知UI更新
 */public interface IRecommendViewCallback {
     /*
     * 获取推荐内容*/
     void onRecommentListLoaded(List<Album> result);
//     /*加载更多*/
//     void onLoaderMore(List<Album> result);
//     /*下拉加载更多的结果*/
//     void onRefreshMore(List<Album> result);
     //网络错误
     void onNetworkError();
     //数据为空
     void onEmpty();
     //正在加载
     void onLoading();
}
