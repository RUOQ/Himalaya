package com.example.himalaya.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.himalaya.DetailActivity;
import com.example.himalaya.R;
import com.example.himalaya.adapters.RecommendListAdapter;
import com.example.himalaya.base.BaseFragment;
import com.example.himalaya.interfaces.IRecommendViewCallback;
import com.example.himalaya.presenters.AlbumDetailPresenter;
import com.example.himalaya.presenters.RecommentPresenter;
import com.example.himalaya.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;

/*
 *author:The GodFather
 *Date:2020/9/12
 *description:
 */public class RecommendFragment extends BaseFragment implements IRecommendViewCallback, UILoader.OnRetryClickListener, RecommendListAdapter.onRecommendItemClickListener {
    private static final String TAG ="RecommendFragment" ;
    private RecyclerView recyclerView ;

    private RecommendListAdapter recommendListAdapter;
    private RecommentPresenter mRecommendPresenter;
    private UILoader mUiLoader;

    protected View onSubViewLoaded(final LayoutInflater layoutInflater, ViewGroup container) {

        mUiLoader = new UILoader(getContext()) {
            @Override
            protected View getSuccessView(ViewGroup container ){
                return createSuccessView(layoutInflater,container);
            }

        };
        //View加载完成
        View rootView = layoutInflater.inflate(R.layout.fragment_recomment,container,false);

        //通过单例模式获取到逻辑层的对象
        mRecommendPresenter = RecommentPresenter.getInstance();
        //先要设置通知接口的注册
        mRecommendPresenter.registerViewCallback(this);
        //获取推荐列表
        mRecommendPresenter.getRecommendList();
        //跟父类解绑
        if(mUiLoader.getParent() instanceof ViewGroup){
           ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
        }

        mUiLoader.setOnRetryClickListener(this);

        //返回view,给界面显示
        return mUiLoader;
    }



    private View createSuccessView(LayoutInflater layoutInflater, ViewGroup container) {
        View rootView = layoutInflater.inflate(R.layout.fragment_recomment,container,false);
        recyclerView =rootView.findViewById(R.id.recommend_list);
        /*
         * 1.找到对应的控件
         * 2.设置布局管理器*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(Rect outRect,View view ,RecyclerView parent,RecyclerView.State state){
                outRect.top = UIUtil.dip2px(view.getContext(),5);
                outRect.bottom = UIUtil.dip2px(view.getContext(),5);
                outRect.left =UIUtil.dip2px(view.getContext(),5);
                outRect.right=UIUtil.dip2px(view.getContext(),5);

            }
        });
        //3.设置适配器
        recommendListAdapter = new RecommendListAdapter();
        recyclerView.setAdapter(recommendListAdapter);
        recommendListAdapter.setOnRecommendItemClickListener(this);
        return rootView;
    }





    @Override
    public void onRecommentListLoaded(List<Album> result) {
      //当我们获取到推荐内容的时候，这个方法会被调用
        //把数据设置给了适配器，并更新UI
        recommendListAdapter.setData(result);
        mUiLoader.updateStatus(UILoader.UIStatus.SUCCESS);
    }

    @Override
    public void onNetworkError() {
        mUiLoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR);
    }

    @Override
    public void onEmpty() {
        mUiLoader.updateStatus(UILoader.UIStatus.EMPTY);
    }

    @Override
    public void onLoading() {
        mUiLoader.updateStatus(UILoader.UIStatus.LOADING);
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        //取消接口的注册，避免OOM(内存泄漏）
        if(mRecommendPresenter !=null){
            mRecommendPresenter.unRegisterViewCallback(this);
        }

    }
    @Override
    public void onRetryClick(){
     //表示网络不佳的时候,用户点击了重试
        //重新获取数据
      if(mRecommendPresenter != null){
          mRecommendPresenter.getRecommendList();
      }
    }

    //item被点击,跳转到详情界面
    @Override
    public  void onItemClick(int position,Album album){
        //根据位置拿到数据库
        AlbumDetailPresenter.getInstance().setTargetAlbum(album);
        //intent被点击跳转到详情页面
        Intent intent =new Intent(getContext(), DetailActivity.class);
        startActivity(intent);
    }
}
