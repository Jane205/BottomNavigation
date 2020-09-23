package com.example.bottomnavigation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.bottomnavigation.R;

/**
 * Created by jane on 2020/9/21.
 */
public class TabItemView extends FrameLayout {
    private Context mContext;
    private TextView mTabNameView;
    private LottieAnimationView mTabLottieView;
    private String mTabName;
    private int mTabStaticRes;
    private int mTabAnimateRes;
    private boolean isSelected;
    private int mSelectedTextColor;
    private int mUnSelectedTextColor;
    private int mTextSize;
    private int mIconSize;

    public TabItemView(@NonNull Context context) {
        this(context, null);
    }

    public TabItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TabItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.TabItemView);
        mTextSize = a.getDimensionPixelSize(R.styleable.TabItemView_textSize, 10);
        mIconSize = a.getDimensionPixelSize(R.styleable.TabItemView_iconSize, 40);
        mSelectedTextColor = a.getColor(R.styleable.TabItemView_selectedTextColor, getResources().getColor(R.color.tab_selected));
        mUnSelectedTextColor = a.getColor(R.styleable.TabItemView_unSelectedTextColor, getResources().getColor(R.color.tab_unselected));
        mTabStaticRes = a.getResourceId(R.styleable.TabItemView_mTabStaticRes, 0);
        mTabAnimateRes = a.getResourceId(R.styleable.TabItemView_mTabAnimateRes, 0);
        mTabName = a.getString(R.styleable.TabItemView_tabName);
        a.recycle();
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bottom_tab_view_item, this, false);
        mTabNameView = view.findViewById(R.id.tab_name);
        mTabLottieView = view.findViewById(R.id.tab_animation_view);
        addView(view);
    }

    private void setSelectedUI() {
        if (mTabAnimateRes == 0) {
            throw new NullPointerException("animation resource must be not empty");
        } else {
            mTabNameView.setTextColor(mSelectedTextColor);
            mTabLottieView.setAnimation(mTabAnimateRes);
            mTabLottieView.playAnimation();
        }
    }

    private void setUnselectedUI() {
        mTabNameView.setTextColor(mUnSelectedTextColor);
        mTabLottieView.clearAnimation();
        // 没有找到静态图，就选择加载gif图的第一帧，这里可以选择mipmap下的静图
        Glide.with(mContext).load(mTabStaticRes).asBitmap().into(mTabLottieView);
    }

    public void updateUI() {
        if (isSelected) {
            setSelectedUI();
        } else {
            setUnselectedUI();
        }
    }

    public TabItemView setIconSize(int iconSize) {
        if (iconSize > 0) {
            mIconSize = iconSize;
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLottieView.getLayoutParams();
            lp.width = mIconSize;
            lp.height = mIconSize;
            mTabLottieView.setLayoutParams(lp);
        }
        return this;
    }

    public TabItemView setStaticIcon(int staticRes) {
        mTabStaticRes = staticRes;
        return this;
    }

    public TabItemView setAnimateIcon(int animateRes) {
        mTabAnimateRes = animateRes;
        return this;
    }

    public TabItemView setTabName(String tabName) {
        mTabName = tabName;
        mTabNameView.setText(mTabName);
        return this;
    }

    public TabItemView setTextColor(int selectColor, int unSelectColor) {
        this.mSelectedTextColor = selectColor;
        this.mUnSelectedTextColor = unSelectColor;
        return this;
    }

    public TabItemView setTabSelected(boolean isSelected) {
        this.isSelected = isSelected;
        updateUI();
        return this;
    }

    public TabItemView setTabTextSize(int textSize) {
        this.mTextSize = textSize;
        mTabNameView.setTextSize(mTextSize);
        return this;
    }
}
