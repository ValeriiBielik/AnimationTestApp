package com.test.projectsanimationapp.util;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import com.test.projectsanimationapp.R;
import com.test.projectsanimationapp.databinding.LayoutToolbarBinding;
import com.test.projectsanimationapp.model.State;

public class ToolbarAnimationUtil {

    private static final int DURATION = 300;
    private State state = State.NORMAL;
    private final int toolbarHeightMin, toolbarHeightMax, toolbarHeightNormal;

    public ToolbarAnimationUtil(Context context) {
        toolbarHeightNormal = (int) context.getResources().getDimension(R.dimen.toolbar_height_normal);
        toolbarHeightMin = (int) context.getResources().getDimension(R.dimen.toolbar_height_min);
        toolbarHeightMax = (int) context.getResources().getDimension(R.dimen.toolbar_height_max);
    }

    public State getState() {
        return state;
    }

    public void resizeToMax(LayoutToolbarBinding binding) {
        if (state == State.NORMAL) {
            binding.titleReduced.setVisibility(View.GONE);
            resize(binding, toolbarHeightMax);
            fadeIn(binding.searchHolder);
            fadeIn(binding.title);
            state = State.EXPANDED;
        }
    }

    public void resizeToMin(LayoutToolbarBinding binding) {
        if (state == State.NORMAL) {
            resize(binding, toolbarHeightMin);
            binding.searchHolder.setVisibility(View.GONE);
            binding.title.setVisibility(View.GONE);
            fadeIn(binding.titleReduced);
            state = State.REDUCED;
        } else if (state == State.EXPANDED) {
            resizeToNormal(binding);
        }
    }

    public void resizeToNormal(LayoutToolbarBinding binding) {
        if (state == State.REDUCED || state == State.EXPANDED) {
            resize(binding, toolbarHeightNormal);
            binding.searchHolder.setVisibility(View.GONE);
            binding.titleReduced.setVisibility(View.GONE);
            fadeIn(binding.title);
            state = State.NORMAL;
        }
    }

    private void fadeIn(View view) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(DURATION);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(fadeIn);
    }

    private void resize(LayoutToolbarBinding binding, int resultHeight) {
        ValueAnimator anim = ValueAnimator.ofInt(binding.parent.getMeasuredHeight(), resultHeight);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = binding.parent.getLayoutParams();
            layoutParams.height = val;
            binding.parent.setLayoutParams(layoutParams);
        });
        anim.setDuration(DURATION);
        anim.start();
    }
}
