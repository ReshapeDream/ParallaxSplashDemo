package com.neil.parallaxsplash;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.neil.customattrhelper.CustomAttr;

import java.util.ArrayList;
import java.util.List;


/**
 * @author nzbao
 * @CreateTime 2018/1/15
 * @Desc
 */
public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    ArrayList<ParallaxFragment> fragments;

    private int parentWidth;
    private ViewPager viewPager;

    public ParallaxContainer(Context context) {
        this(context, null);
    }

    public ParallaxContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setup(int... layoutIds) {
        viewPager = new ViewPager(getContext());
        //代码中创建的要使用的viewpager必须设置id
        viewPager.setId(R.id.parallax_view_pager_id);
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        fragments = new ArrayList<>();

        for (int i = 0; i < layoutIds.length; i++) {
            ParallaxFragment parallaxFragment = new ParallaxFragment();
            Bundle args = new Bundle();
            args.putInt("layoutId", layoutIds[i]);
            parallaxFragment.setArguments(args);
            fragments.add(parallaxFragment);
        }

        PaViewPagerAdapter adapter = new PaViewPagerAdapter(((FragmentActivity) getContext()).getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        addView(viewPager, 0);
        viewPager.addOnPageChangeListener(this);
        parentWidth = viewPager.getMeasuredWidth();
    }

    private ParallaxFragment leftFragment;
    private ParallaxFragment rightFragment;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset==0f){//停止滑动
            return;
        }

        if (parentWidth <= 0) {
            parentWidth = viewPager.getMeasuredWidth();
        }

        try {
            leftFragment = fragments.get(position);
        } catch (Exception e) {
        }

        try {
            rightFragment = fragments.get(position + 1);
        } catch (Exception e) {
        }

        if (leftFragment != null) {
            List<View> rightViews = leftFragment.getmParallaxViews();
            for (View rightView : rightViews) {
                List<CustomAttr> tags = (List<CustomAttr>) rightView.getTag(R.id.customAttrTagId);
                for (CustomAttr attr : tags) {
                    if (attr.attrId == R.attr.x_in) {
                        rightView.setTranslationX(-positionOffsetPixels * attr.getFloatValue());
                    } else if (attr.attrId == R.attr.y_in) {
                        rightView.setTranslationY(-positionOffsetPixels * attr.getFloatValue());
                    }else if(attr.attrId==R.attr.a_in){
                        rightView.setAlpha((parentWidth - positionOffsetPixels)*attr.getFloatValue()/parentWidth);
                    }
                }
            }
        }

        if (rightFragment != null) {
            List<View> leftViews = rightFragment.getmParallaxViews();
            for (View leftView : leftViews) {
                List<CustomAttr> tags = (List<CustomAttr>) leftView.getTag(R.id.customAttrTagId);
                for (CustomAttr attr : tags) {
                    if (attr.attrId == R.attr.x_out) {
                        leftView.setTranslationX((parentWidth - positionOffsetPixels) * attr.getFloatValue());
                    }else if (attr.attrId == R.attr.y_out) {
                        leftView.setTranslationY((parentWidth - positionOffsetPixels) * attr.getFloatValue());
                    }else if(attr.attrId==R.attr.a_out){
                        leftView.setAlpha(positionOffsetPixels*attr.getFloatValue()/parentWidth);
                    }
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE://停止滑动
                if(leftFragment!=null){
                    List<View> views = leftFragment.getmParallaxViews();
                    for (View view : views) {
                        view.setTranslationX(0);
                        view.setTranslationY(0);
                        view.setAlpha(1);
                    }
                }

                if(rightFragment!=null){
                    List<View> views = rightFragment.getmParallaxViews();
                    for (View view : views) {
                        view.setTranslationX(0);
                        view.setTranslationY(0);
                        view.setAlpha(1);
                    }
                }
                break;
        }
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener){
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public ArrayList<ParallaxFragment> getFragments() {
        return fragments;
    }
}
