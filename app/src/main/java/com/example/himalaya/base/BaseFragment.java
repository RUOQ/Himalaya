package com.example.himalaya.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/*
 *author:The GodFather
 *Date:2020/9/12
 *description:
 */public abstract class BaseFragment extends Fragment {
     @NonNull
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                              @NonNull Bundle savedInstanceState){
         View rootView =null;
         if(rootView==null) {
             rootView = onSubViewLoaded(inflater, container);
         }
         return rootView;
     }


   /*  @Override
    public View onCreateView(String name, Context context, AttributeSet attrs){
         LayoutInflater layoutInflater = LayoutInflater.from(context);
         View rootView = onSubViewLoaded(layoutInflater);
         return rootView;
     }*/
     protected abstract  View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container);
}
