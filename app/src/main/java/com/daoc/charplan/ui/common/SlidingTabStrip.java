package com.daoc.charplan.ui.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.daoc.charplan.R;
import com.daoc.charplan.util.Log;

/**
 * Custom view to display a strip underneath the selected tab.
 */
public class SlidingTabStrip extends LinearLayout {

    /**
     * Thickness of the tab strip underneath selected tab.
     */
    private final int mSelectedIndicatorThickness;

    /**
     * {@link Paint} object of tab strip.
     */
    private final Paint mSelectedIndicatorPaint;

    /**
     * Position of selected tab.
     */
    private int mSelectedPosition;

    /**
     * Offset to selected position.
     */
    private float mSelectionOffset;

    /**
     * SlidingTabStrip Constructor.
     */
    protected SlidingTabStrip(final Context context) {
        super(context);
        setWillNotDraw(false);
        mSelectedIndicatorThickness =
            (int) getResources().getDimension(R.dimen.tab_strip_thickness);
        mSelectedIndicatorPaint = new Paint();
    }

    /**
     * Called when the page has changed in the {@link ViewPager}.
     *
     * @param position       the selected tab.
     * @param positionOffset offset to the selected tab.
     */
    protected void onViewPagerPageChanged(final int position, final float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        invalidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        final int height = getHeight();
        final int childCount = getChildCount();
        // Underline below the current selection
        if (childCount > 0) {
            View selectedTitle = getChildAt(mSelectedPosition);
            int left = selectedTitle.getLeft();
            int right = selectedTitle.getRight();
            if (mSelectionOffset > 0f && mSelectedPosition < (childCount - 1)) {
                // Draw the selection partway between the tabs
                View nextTitle = getChildAt(mSelectedPosition + 1);
                left = (int) (mSelectionOffset * nextTitle.getLeft() +
                    (1.0f - mSelectionOffset) * left);
                right = (int) (mSelectionOffset * nextTitle.getRight() +
                    (1.0f - mSelectionOffset) * right);
            }
            mSelectedIndicatorPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(left, height - mSelectedIndicatorThickness, right,
                    height, mSelectedIndicatorPaint);
        }
    }
}