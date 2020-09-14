package com.benefitsoft.recorder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.benefitsoft.recorder.Flagment.Main;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public Fragment mFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //툴바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //

        //매니페스트에서 세로 고정하면 에러남.. 여기서 해줌
        try {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //

        mFragment = new Main();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container, mFragment);
        mFragmentTransaction.commit();
    }
}