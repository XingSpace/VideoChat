package com.leichui.shortviedeo.Fragment;


import android.os.Bundle;

import com.yiw.circledemo.adapter.CircleAdapter;

public class MyBFragment extends BFragment  {

    public static MyBFragment newInstance(String userId) {
        MyBFragment newFragment = new MyBFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        newFragment.setArguments(bundle);
        return newFragment;

    }

    protected void myBMethod(){
        isMyBFragment = true;
        userId = getActivity().getIntent().getStringExtra("userId");
        BFragment_HEADVIEW_SIZE = 0;
    }
    public void createAdapter(){
        mAdapter = new CircleAdapter(getContext(),0);
    }
}
