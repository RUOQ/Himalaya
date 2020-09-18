package com.example.himalaya;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himalaya.base.BaseActivity;
import com.example.himalaya.interfaces.IAlbumDetailViewCallback;
import com.example.himalaya.interfaces.IAlbumDetialPresenter;
import com.example.himalaya.presenters.AlbumDetailPresenter;
import com.example.himalaya.views.RoundRectImageView;
import com.squareup.picasso.Picasso;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

/*
 *author:The GodFather
 *Date:2020/9/18
 *description:
 */public class DetailActivity extends BaseActivity implements IAlbumDetailViewCallback {

   private TextView mAlbumTitle ;
   private TextView mAlbumAuthor;
   private ImageView mLargeCover;
   private RoundRectImageView mSmallCover;
    private AlbumDetailPresenter mAlbumDetailPresenter;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail);
        //设置状态栏为透明
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        
        initView();

        mAlbumDetailPresenter = AlbumDetailPresenter.getInstance();
        mAlbumDetailPresenter.registerViewCallback(this);
    }

    private void initView() {
        mLargeCover = this.findViewById(R.id.iv_large_cover);
        mSmallCover = this.findViewById(R.id.iv_small_cover);
        mAlbumTitle  = this.findViewById(R.id.tv_album_title);
        mAlbumAuthor = this.findViewById(R.id.tv_album_author);
    }

    @Override
    public void onDetailListLoaded(List<Track> tracks) {

    }

    @Override
    public void onAlbumLoaded(Album album) {

        if(mAlbumTitle != null){
            mAlbumTitle.setText(album.getAlbumTitle());
        }
        if(mAlbumAuthor  != null){
            mAlbumAuthor.setText(album.getAnnouncer().getNickname());
        }
        if(mLargeCover != null){
            Picasso.get()
                    .load(album.getCoverUrlLarge())
                    .into(mLargeCover);
        }
        if(mSmallCover != null){
            Picasso.get()
                    .load(album.getCoverUrlSmall())
                    .into(mSmallCover);
        }

    }
}
