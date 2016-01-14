package com.daoc.charplan.ui.common;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoc.charplan.R;
import com.daoc.charplan.util.Log;

/**
 * To be used with {@link ViewPager} to provide a tab indicator component which give constant
 * feedback as to the user's scroll progress.
 */
public class SlidingTabLayout extends HorizontalScrollView {

    /**
     * {@link SlidingTabStrip}.
     */
    private final SlidingTabStrip mTabStrip;

    /**
     * Minimum width of tab item
     */
    private final int mTabMinWidth;

    /**
     * Offset between tab titles.
     */
    private final int mTitleOffset;

    /**
     * Used to depict if the tabs should be distributed evenly.
     */
    private boolean mDistributeEvenly;

    /**
     * {@link ViewPager} to hold the Fragments.
     */
    private ViewPager mViewPager;

    /**
     * Constructor for SlidingTabLayout.
     */
    public SlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mTabMinWidth = (int) getResources().getDimension(R.dimen.tab_min_width);
        // Disable the Scroll Bar
        setHorizontalScrollBarEnabled(false);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);
        setDistributeEvenly();
        mTitleOffset = (int) getResources().getDimension(R.dimen.tab_title_offset);
        mTabStrip = new SlidingTabStrip(context);
        addView(mTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * Called if the tabs should be distributed evenly in the {@link LinearLayout}.
     */
    public void setDistributeEvenly() {
        mDistributeEvenly = true;
    }

    /**
     * Create view to be used for tab.
     */
    protected LinearLayout createTabView(final Context context) {
        return (LinearLayout) View.inflate(context, R.layout.sliding_tab_item_view, null);
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     *
     * @param viewPager which {@link ViewPager} to use.
     */
    public void setViewPager(final ViewPager viewPager) {
        mTabStrip.removeAllViews();
        mViewPager = viewPager;
        final DataSetObserver observer = new DataSetObserver() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onChanged() {
                super.onChanged();
                populateTabStrip();
            }
            /**
             * {@inheritDoc}
             */
            @Override
            public void onInvalidated() {
                super.onInvalidated();
                populateTabStrip();
            }
        };
        mViewPager.getAdapter().registerDataSetObserver(observer);
        viewPager.addOnPageChangeListener(new InternalViewPagerListener());
        populateTabStrip();
    }

    /**
     * Initialize the tabs.
     */
    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();

        for (int index = 0; index < adapter.getCount(); index++) {
            final LinearLayout tabView;
            if (mTabStrip.getChildCount() < adapter.getCount()) {
                tabView = createTabView(getContext());
            } else {
                tabView = (LinearLayout) mTabStrip.getChildAt(index);
            }

            if (mDistributeEvenly) {
                LinearLayout.LayoutParams layoutParams
                    = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = 0;
                    layoutParams.weight = 1;
                    tabView.setLayoutParams(layoutParams);
                    Log.d("Layout params set to even weight");
                } else {
                    Log.e("Layout params NULL!");
                }
            }

            final TextView titleView = (TextView) tabView.findViewById(android.R.id.text1);
            if (titleView != null) {
                titleView.setText(adapter.getPageTitle(index).toString());

                tabView.setOnClickListener(tabClickListener);
                if (tabView.getParent() == null) {
                    mTabStrip.addView(tabView);
                }
                if (index == mViewPager.getCurrentItem()) {
                    titleView.setTextColor(ContextCompat.getColor(
                            getContext(), R.color.colorAccent));
                    tabView.setSelected(true);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mViewPager != null) {
            scrollToTab(mViewPager.getCurrentItem(), 0);
        }
    }

    /**
     * Scroll to clicked tab.
     *
     * @param tabIndex       of current tab.
     * @param positionOffset of the selected tab.
     */
    private void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        final View selectedChild = mTabStrip.getChildAt(tabIndex);

        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() + positionOffset;

            if (tabIndex > 0 || positionOffset > 0) {
                // If we're not at the first child and are mid-scroll, make sure we obey the offset
                targetScrollX -= mTitleOffset;
            }
            scrollTo(targetScrollX, 0);
        }
    }

    /**
     * Callback for responding to changing state of the selected page.
     */
    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {

        /**
         * Current Scroll state.
         */
        private int mScrollState;

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }

            mTabStrip.onViewPagerPageChanged(position, positionOffset);
            final View selectedTitle = mTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth()) : 0;
            scrollToTab(position, extraOffset);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPageSelected(int position) {
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                mTabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }
            final int size = mTabStrip.getChildCount();
            for (int i = 0; i < size; i++) {
                mTabStrip.getChildAt(i).setSelected(position == i);
                if (i != position) {
                    ((TextView) mTabStrip.getChildAt(i).findViewById(android.R.id.text1))
                            .setTextColor(Color.WHITE);
                } else {
                    ((TextView) mTabStrip.getChildAt(i).findViewById(android.R.id.text1))
                            .setTextColor(ContextCompat.getColor(
                                    getContext(), R.color.colorAccent));
                }
            }
        }

    }

    /**
     * Callback when clicking a tab.
     */
    private class TabClickListener implements OnClickListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void onClick(View view) {
            final int size = mTabStrip.getChildCount();
            for (int i = 0; i < size; i++) {
                if (view == mTabStrip.getChildAt(i)) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}