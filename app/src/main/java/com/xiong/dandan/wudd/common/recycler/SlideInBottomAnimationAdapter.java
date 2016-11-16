package com.xiong.dandan.wudd.common.recycler;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

@SuppressWarnings({"unchecked"})
public class SlideInBottomAnimationAdapter extends AnimationAdapter {

    public SlideInBottomAnimationAdapter(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
        };
    }
}
