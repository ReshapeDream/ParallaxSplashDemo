package com.neil.parallaxsplash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neil.customattrhelper.CustomAttr;
import com.neil.customattrhelper.CustomAttrsLayoutInflater;
import com.neil.customattrhelper.OnViewCreateListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nzbao
 * @CreateTime 2018/1/15
 * @Desc
 */
public class ParallaxFragment extends Fragment implements OnViewCreateListener {
    List<View> mParallaxViews = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CustomAttrsLayoutInflater customAttrsLayoutInflater = new CustomAttrsLayoutInflater(getLayoutInflater(), getContext());
        CustomAttr[] customAttrs = new CustomAttr[]{
                new CustomAttr(R.attr.a_in, 0f),
                new CustomAttr(R.attr.a_out, 0f),
                new CustomAttr(R.attr.x_in, 0f),
                new CustomAttr(R.attr.x_out, 0f),
                new CustomAttr(R.attr.y_in, 0f),
                new CustomAttr(R.attr.y_out, 0f)
        };
        customAttrsLayoutInflater.setCustomAttrs(customAttrs);
        customAttrsLayoutInflater.addOnViewCreateListener(this);
        int layoutId = getArguments().getInt("layoutId");
        return customAttrsLayoutInflater.inflate(layoutId, null);
    }

    @Override
    public void onViewCreated(View view) {
        mParallaxViews.add(view);
    }

    public List<View> getmParallaxViews() {
        return mParallaxViews;
    }
}
