package io.tohure.dotpageindicator;

import android.support.annotation.NonNull;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tohure on 19/01/18.
 */

public class CustomPagerHelper extends PagerSnapHelper {

    private DotPageIndicator dotPageIndicator;

    CustomPagerHelper(@NonNull DotPageIndicator dotPageIndicator) {
        this.dotPageIndicator = dotPageIndicator;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);

        // Notify when page changed
        dotPageIndicator.onPageSelected(position);

        return position;
    }

}