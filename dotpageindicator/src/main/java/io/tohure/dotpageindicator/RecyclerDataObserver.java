package io.tohure.dotpageindicator;

import android.support.v7.widget.RecyclerView;

/**
 * Created by tohure on 19/01/18.
 */

class RecyclerDataObserver extends RecyclerView.AdapterDataObserver {

    private final DotPageIndicator dotPageIndicator;

    RecyclerDataObserver(DotPageIndicator dotPageIndicator) {
        this.dotPageIndicator = dotPageIndicator;
    }

    @Override
    public void onChanged() {
        dotPageIndicator.updateIndicatorsCount();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        dotPageIndicator.updateIndicatorsCount();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        dotPageIndicator.updateIndicatorsCount();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        dotPageIndicator.updateIndicatorsCount();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        dotPageIndicator.updateIndicatorsCount();
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        dotPageIndicator.updateIndicatorsCount();
    }
}