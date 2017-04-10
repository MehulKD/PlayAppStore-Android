package com.playappstore.playappstore.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.playappstore.playappstore.R;
import com.playappstore.playappstore.view.Fragment.FavouriteFragment;
import com.playappstore.playappstore.view.Fragment.FindFragment;
import com.playappstore.playappstore.view.Fragment.SettingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("hello world again.");


//        messageView = (TextView) findViewById(R.id.messageView);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                messageView.setText("12");
                Fragment selectedFragment = null;
                switch (tabId) {
                    case R.id.tab_find:
                        selectedFragment = FindFragment.newInstance();
                        break;
                    case R.id.tab_favorites:
                        selectedFragment = FavouriteFragment.newInstance();
                        break;
                    case R.id.tab_setting:
                        selectedFragment =  SettingFragment.newInstance();
                        break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                }

                //return true;
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
//                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });


        //Manually displaying the first fragment - one time only
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout, FindFragment.newInstance());
//        transaction.commit();
    }

}
