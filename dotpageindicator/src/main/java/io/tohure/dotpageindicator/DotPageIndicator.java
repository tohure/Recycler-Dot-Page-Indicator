package io.tohure.dotpageindicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Arrays;

/**
 * Created by tohure on 19/01/18.
 */

public class DotPageIndicator extends LinearLayout {

    private static final int MAX_INDICATORS = 9;
    private static final int INDICATOR_SIZE_DIP = 12;
    private static final int INDICATOR_MARGIN_DIP = 2;

    // State Indicator for scale factor
    private static final float STATE_GONE = 0;
    private static final float STATE_SMALLEST = 0.2f;
    private static final float STATE_SMALL = 0.4f;
    private static final float STATE_NORMAL = 0.6f;
    private static final float STATE_SELECTED = 1.0f;

    private int mIndicatorCount;
    private int mLastSelected;
    private int mIndicatorSize;
    private int mIndicatorMargin;

    private RecyclerView recyclerView;
    private DataObserver dataObserver;

    public DotPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mIndicatorSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, INDICATOR_SIZE_DIP, dm);
        mIndicatorMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, INDICATOR_MARGIN_DIP, dm);

        dataObserver = new DataObserver(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (recyclerView != null) {
            try {
                recyclerView.getAdapter().unregisterAdapterDataObserver(dataObserver);
            } catch (IllegalStateException ignored) {}
        }

        super.onDetachedFromWindow();
    }

    public void onPageSelected(int position) {
        if (mIndicatorCount > MAX_INDICATORS) {
            updateOverflowState(position);
        } else {
            updateSimpleState(position);
        }
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {

        new CustomPagerHelper(this).attachToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
        this.recyclerView.getAdapter().registerAdapterDataObserver(dataObserver);

        initIndicators();
    }

    void updateIndicatorsCount() {
        if (mIndicatorCount != recyclerView.getAdapter().getItemCount()) initIndicators();
    }

    private void initIndicators() {
        mLastSelected = -1;
        mIndicatorCount = recyclerView.getAdapter().getItemCount();
        createIndicators(mIndicatorSize, mIndicatorMargin);
        onPageSelected(0);
    }

    private void updateSimpleState(int position) {
        if (mLastSelected != -1) {
            animateViewScale(getChildAt(mLastSelected), STATE_NORMAL);
        }

        animateViewScale(getChildAt(position), STATE_SELECTED);

        mLastSelected = position;
    }

    private void updateOverflowState(int position) {

        if (mIndicatorCount == 0) return;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Transition transition = new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeBounds())
                    .addTransition(new Fade());

            TransitionManager.beginDelayedTransition(this, transition);
        }

        float[] positionStates = new float[mIndicatorCount + 1];
        Arrays.fill(positionStates, STATE_GONE);

        int start = position - MAX_INDICATORS + 4;
        int realStart = Math.max(0, start);

        if (realStart + MAX_INDICATORS > mIndicatorCount) {
            realStart = mIndicatorCount - MAX_INDICATORS;
            positionStates[mIndicatorCount - 1] = STATE_NORMAL;
            positionStates[mIndicatorCount - 2] = STATE_NORMAL;
        } else {
            if (realStart + MAX_INDICATORS - 2 < mIndicatorCount) {
                positionStates[realStart + MAX_INDICATORS - 2] = STATE_SMALL;
            }
            if (realStart + MAX_INDICATORS - 1 < mIndicatorCount) {
                positionStates[realStart + MAX_INDICATORS - 1] = STATE_SMALLEST;
            }
        }

        for (int i = realStart; i < realStart + MAX_INDICATORS - 2; i++) {
            positionStates[i] = STATE_NORMAL;
        }

        if (position > 5) {
            positionStates[realStart] = STATE_SMALLEST;
            positionStates[realStart + 1] = STATE_SMALL;
        } else if (position == 5) {
            positionStates[realStart] = STATE_SMALL;
        }

        positionStates[position] = STATE_SELECTED;

        updateIndicators(positionStates);

        mLastSelected = position;
    }

    private void updateIndicators(float[] positionStates) {
        for (int i = 0; i < mIndicatorCount; i++) {
            View v = getChildAt(i);
            float state = positionStates[i];

            if (state == STATE_GONE) {
                v.setVisibility(GONE);
            } else {
                v.setVisibility(VISIBLE);
                animateViewScale(v, state);
            }
        }
    }

    private void createIndicators(int indicatorSize, int margin) {
        removeAllViews();

        if (mIndicatorCount <= 1) return;

        for (int i = 0; i < mIndicatorCount; i++) {
            addIndicator(mIndicatorCount > MAX_INDICATORS, indicatorSize, margin);
        }
    }

    private void addIndicator(boolean isOverflowState, int indicatorSize, int margin) {

        View view = new View(getContext());
        view.setBackgroundResource(R.drawable.dot_indicator);

        if (isOverflowState) {
            animateViewScale(view, STATE_SMALLEST);
        } else {
            animateViewScale(view, STATE_NORMAL);
        }

        MarginLayoutParams params = new MarginLayoutParams(indicatorSize, indicatorSize);
        params.leftMargin = margin;
        params.rightMargin = margin;

        addView(view, params);
    }

    private void animateViewScale(@Nullable View view, float scale) {
        if (view == null) return;

        view.animate()
                .scaleX(scale)
                .scaleY(scale);
    }
}