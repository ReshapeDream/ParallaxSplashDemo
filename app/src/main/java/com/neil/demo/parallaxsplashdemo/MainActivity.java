package com.neil.demo.parallaxsplashdemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.neil.library.parallaxsplash.ParallaxContainer;


public class MainActivity extends AppCompatActivity {
    private ParallaxContainer container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        container.setup(R.layout.view_intro_1, R.layout.view_intro_2, R.layout.view_intro_3, R.layout.view_intro_4, R.layout.view_intro_5, R.layout.view_login);

        final ImageView ivMan = findViewById(R.id.iv_man);
        ivMan.setBackgroundResource(R.drawable.man_run);
        final AnimationDrawable background = (AnimationDrawable) ivMan.getBackground();

        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0) {
                    return;
                }

                if (position == 4) {
                    float alpha=0.7f-positionOffset;
                    ivMan.setAlpha(alpha>0f?alpha:0f);
                }
                background.start();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        background.stop();
                        break;
                }
            }
        });
    }
}
