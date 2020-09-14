package com.benefitsoft.recorder.Flagment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benefitsoft.recorder.R;
import com.benefitsoft.recorder.TestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Main extends Fragment {
    private Unbinder unbinder;

    @OnClick(R.id.btn_start)
    public void btnStart() {
        Intent intent = new Intent(getActivity(), TestActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_bottom, R.anim.out_top);  //아래에서 위로 올라가는...
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}