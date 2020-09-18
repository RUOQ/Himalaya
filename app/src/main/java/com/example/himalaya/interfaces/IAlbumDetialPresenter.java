package com.example.himalaya.interfaces;

/*
 *author:The GodFather
 *Date:2020/9/18
 *description:
 */public interface IAlbumDetialPresenter {

    /*
     * 下拉刷新*/
    void pull2RefreshMore();
//  上拉加载更多
    void loadMore();
    //获取专辑详情
    /*@param albumId
    * @param page*/
    void getAlbumDetail(int albumId,int page);
     /*
     * 注册UI通知的接口
     * @param detailViewCallback*/
    void registerViewCallback(IAlbumDetailViewCallback detailViewCallback);
    /*
     * 注消UI通知的接口
     * @param detailViewCallback*/
    void unRegisterViewCallback(IAlbumDetailViewCallback detailViewCallback);
}
