package com.example.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.bottomnavigation.bean.BottomTab;
import com.example.bottomnavigation.bean.TabItem;
import com.example.bottomnavigation.fragment.CloudFragment;
import com.example.bottomnavigation.fragment.ExchangeFragment;
import com.example.bottomnavigation.fragment.HomeFragment;
import com.example.bottomnavigation.fragment.UserCenterFragment;
import com.example.bottomnavigation.view.BottomTabNavigation;
import com.example.bottomnavigation.view.TabItemView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private UserCenterFragment userCenterFragment;
    private ExchangeFragment exchangeFragment;
    private CloudFragment cloudFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        BottomTabNavigation navigation = findViewById(R.id.bottom_navigation);
        navigation.addOnTabSelectedListener(new BottomTabNavigation.TabSelectedListener() {
            @Override
            public void tabSelected(TabItemView itemView) {
                displayFragment((String) itemView.getTag());
            }

            @Override
            public void tabUnselected(TabItemView itemView) {

            }
        });
        navigation.setTabItems(getTabList());
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        cloudFragment = new CloudFragment();
        exchangeFragment = new ExchangeFragment();
        userCenterFragment = new UserCenterFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.fl_container, homeFragment)
                .add(R.id.fl_container, exchangeFragment)
                .add(R.id.fl_container, cloudFragment)
                .add(R.id.fl_container, userCenterFragment)
                .commitAllowingStateLoss();
    }

    private List<TabItem> getTabList() {
        List<TabItem> tabs = new ArrayList<>();
        tabs.add(new BottomTab("首页", BottomTabNavigation.TAB_HOME));
        tabs.add(new BottomTab("云存储", BottomTabNavigation.TAB_CLOUD));
        tabs.add(new BottomTab("交易", BottomTabNavigation.TAB_EXCHANGE));
        tabs.add(new BottomTab("我的", BottomTabNavigation.TAB_USER_CENTER));
        return tabs;
    }

    private void displayFragment(String selectTabType) {
        switch (selectTabType) {
            case BottomTabNavigation.TAB_HOME:
            default:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment)
                        .hide(exchangeFragment)
                        .hide(cloudFragment)
                        .hide(userCenterFragment)
                        .commitAllowingStateLoss();
                break;
            case BottomTabNavigation.TAB_CLOUD:
                getSupportFragmentManager().beginTransaction()
                        .show(cloudFragment)
                        .hide(exchangeFragment)
                        .hide(homeFragment)
                        .hide(userCenterFragment)
                        .commitAllowingStateLoss();
                break;
            case BottomTabNavigation.TAB_EXCHANGE:
                getSupportFragmentManager().beginTransaction()
                        .show(exchangeFragment)
                        .hide(homeFragment)
                        .hide(cloudFragment)
                        .hide(userCenterFragment)
                        .commitAllowingStateLoss();
                break;
            case BottomTabNavigation.TAB_USER_CENTER:
                getSupportFragmentManager().beginTransaction()
                        .show(userCenterFragment)
                        .hide(exchangeFragment)
                        .hide(cloudFragment)
                        .hide(homeFragment)
                        .commitAllowingStateLoss();
                break;
        }
    }
}
