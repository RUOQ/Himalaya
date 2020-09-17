package com.example.himalaya.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.example.himalaya.R;
import com.squareup.picasso.Picasso;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

/*
 *author:The GodFather
 *Date:2020/9/15
 *description:
 */public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.InnerHolder>{

     private List<Album> mData = new ArrayList<>();
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend,parent,false);
        return new InnerHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecommendListAdapter.InnerHolder holder, int position) {
     //这里设置数据
        holder.itemView.setTag(position);
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        //返回要显示的个数
        if(mData!=null)
        {
            return mData.size();
        }
        return 0;

    }

    public void setData(List<Album> albumList) {
        if(mData!=null) {
//        mData.clear();
        mData.addAll(albumList);
        }
        //更新一下UI
        notifyDataSetChanged();
    }


    public class InnerHolder extends  RecyclerView.ViewHolder{

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(Album album) {
            //找到控件
            //专辑封面
            ImageView  albumCoverIv = itemView.findViewById(R.id.album_cover);
            //标题
            TextView albumTitleTv = itemView.findViewById(R.id.album_title_tv);
           //描述
            TextView albumDesrcTv = itemView.findViewById(R.id.album_description_tv);
            //播放数量
            TextView albumPlayCountTv = itemView.findViewById(R.id.album_play_count);
            //专辑内容数量
            TextView albumContentCountTv = itemView.findViewById(R.id.album_play_count);

            albumTitleTv.setText(album.getAlbumTitle());
            albumDesrcTv.setText(album.getAlbumIntro());
            albumPlayCountTv.setText(album.getPlayCount()+"");
            albumContentCountTv.setText(album.getIncludeTrackCount()+"");

            Picasso.get()
                    .load(album.getCoverUrlLarge())
                    .into(albumCoverIv);
        }
    }
}