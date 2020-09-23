package com.example.bottomnavigation.bean;

import androidx.annotation.DrawableRes;

/**
 * Created by jane on 2020/9/21.
 */
public interface TabItem {

    String getName();

    @DrawableRes
    int getStaticRes();

    @DrawableRes
    int getAnimateRes();

    String getTabType();
}
