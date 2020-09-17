package com.example.himalaya.interfaces;

/*
 *author:The GodFather
 *Date:2020/9/15
 *description:
 */public interface IRecommendPresenter {
/*
* 获取推荐内容
* */
  void getRecommendList();
  /*
  * 下拉刷新*/
  void pull2RefreshMore();
//  上拉加载更多
    void loadMore();
   /*这个方法用于Ui注册方法的回调
   * @param callback
   * */
    void registerViewCallback(IRecommendViewCallback callback);
    /*
    * 取消UI的回调*/
    void unRegisterViewCallback(IRecommendViewCallback callback);
}
