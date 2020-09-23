package com.example.bottomnavigation.bean;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.view.BottomTabNavigation;

/**
 * Created by jane on 2020/9/21.
 */
public class BottomTab implements TabItem {
    private String name;
    @BottomTabNavigation.BottomTabType
    private String type;

    public BottomTab(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStaticRes() {
        int staticRes = R.drawable.icon_home;
        switch (type){
            case BottomTabNavigation.TAB_HOME:
                staticRes = R.drawable.icon_home;
                break;
            case BottomTabNavigation.TAB_CLOUD:
                staticRes = R.drawable.icon_cloud;
                break;
            case BottomTabNavigation.TAB_EXCHANGE:
                staticRes = R.drawable.icon_exchange;
                break;
            case BottomTabNavigation.TAB_USER_CENTER:
                staticRes = R.drawable.icon_user_center;
                break;
        }
        return staticRes;
    }

    @Override
    public int getAnimateRes() {
        int animateRes = R.raw.lottie_home;
        switch (type){
            case BottomTabNavigation.TAB_HOME:
                animateRes = R.raw.lottie_home;
                break;
            case BottomTabNavigation.TAB_CLOUD:
                animateRes = R.raw.lottie_cloud;
                break;
            case BottomTabNavigation.TAB_EXCHANGE:
                animateRes = R.raw.lottie_exchange;
                break;
            case BottomTabNavigation.TAB_USER_CENTER:
                animateRes = R.raw.lottie_user_center;
                break;
        }
        return animateRes;
    }

    @Override
    public String getTabType() {
        return type;
    }
}
