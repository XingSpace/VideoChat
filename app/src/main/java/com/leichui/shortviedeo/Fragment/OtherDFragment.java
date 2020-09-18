package com.leichui.shortviedeo.Fragment;

import android.os.Bundle;

import com.leichui.shortviedeo.fragment.DFragment;

public class OtherDFragment extends DFragment {


    public static OtherDFragment newInstance(String userId) {
        OtherDFragment newFragment = new OtherDFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        newFragment.setArguments(bundle);
        return newFragment;

    }

    @Override
    public void otherInit() {
        super.otherInit();
        String userId = getActivity().getIntent().getStringExtra("userId");
        setMyUserId(userId);
    }
}
