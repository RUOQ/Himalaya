package com.example.himalaya.utils;


import com.example.himalaya.base.BaseFragment;
import com.example.himalaya.fragments.HistoryFragment;
import com.example.himalaya.fragments.RecommendFragment;
import com.example.himalaya.fragments.SubcriptionFragment;

import java.util.HashMap;
import java.util.Map;

/*
 *author:The GodFather
 *Date:2020/9/12
 *description:
 */public class FragmentCreator {

    private final static int INDEX_RECOMMEND = 0;
    private final static int INDEX_SUBSCRIPTION = 1;
    private final static int INDEX_HISTORY = 2;

    public final static int PAGE_COUNT = 3;
     private static Map<Integer, BaseFragment> sCache =new HashMap<>();
     public static BaseFragment getFragment(int index){
         BaseFragment baseFragment = sCache.get(index);
         if(baseFragment!=null){
             return baseFragment;
         }
         switch(index){
             case INDEX_RECOMMEND:
                 baseFragment =new RecommendFragment();
                 break;
             case INDEX_SUBSCRIPTION:
                 baseFragment =new SubcriptionFragment();
                 break;
             case INDEX_HISTORY:
                 baseFragment = new HistoryFragment();
                 break;
         }
         sCache.put(index,baseFragment);
         return  baseFragment;
     }
}
