package com.daoc.charplan.ui.start;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoc.charplan.Constants;
import com.daoc.charplan.R;
import com.daoc.charplan.ui.common.BaseFragment;
import com.daoc.charplan.ui.common.SlidingTabLayout;
import com.daoc.charplan.util.Log;

import java.util.ArrayList;

/**
 * Fragment used for choosing class.
 */
public class StartFragment extends BaseFragment {

    public static final String REALM_BUNDLE_IDENTIFIER = "realm";

    /**
     * Identifier for which current page the user is viewing in the {@link ViewPager}.
     */
    private static final String CURRENT_PAGE_BUNDLE_IDENTIFIER = "currentPage";

    /**
     * Bundle used between this {@link Fragment} and its child fragments.
     */
    private final Bundle mBundle = new Bundle();

    /**
     * {@link ViewPager} to display the fragments.
     */
    private ViewPager mViewPager;

    /**
     * Contains a list of {@link BaseFragment} to populate the {@link FragmentPagerAdapter} with.
     */
    private ArrayList<BaseFragment> mFragments;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        setRetainInstance(true);
        Log.d("Created StartFragment");
        final View view = inflater.inflate(R.layout.start_fragment, container, false);
        initializeFragments();
        initializeViewPager(view);
        if (savedInstanceState != null) {
            mViewPager.setCurrentItem(savedInstanceState.getInt(CURRENT_PAGE_BUNDLE_IDENTIFIER, 0));
        }
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_PAGE_BUNDLE_IDENTIFIER, mViewPager.getCurrentItem());
    }

    /**
     * Add all Fragments to the {@link Fragment} {@link ArrayList}.
     */
    private void initializeFragments() {
        mFragments = new ArrayList<>();

        Bundle alb = new Bundle();
        ClassFragment albionFragment = new ClassFragment();
        alb.putInt(REALM_BUNDLE_IDENTIFIER, Constants.ALBION_ID);
        albionFragment.setArguments(alb);
        mFragments.add(albionFragment);

        Bundle hib = new Bundle();
        ClassFragment hiberniaFragment = new ClassFragment();
        hib.putInt(REALM_BUNDLE_IDENTIFIER, Constants.HIBERNIA_ID);
        hiberniaFragment.setArguments(hib);
        mFragments.add(hiberniaFragment);

        Bundle mid = new Bundle();
        ClassFragment midgardFragment = new ClassFragment();
        mid.putInt(REALM_BUNDLE_IDENTIFIER, Constants.MIDGARD_ID);
        midgardFragment.setArguments(mid);
        mFragments.add(midgardFragment);
    }

    /**
     * Initialize {@link ViewPager}.
     */
    private void initializeViewPager(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.start_pager);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.setAdapter(new StartPageAdapter(getChildFragmentManager()));
        ((SlidingTabLayout) view.findViewById(R.id.start_tab_layout)).setViewPager(mViewPager);
    }

    /**
     * Implementation of PagerAdapter that represents each page as a {@link Fragment}
     * that is persistently kept in the fragment manager as long as the user can return to the page.
     */
    private class StartPageAdapter extends FragmentPagerAdapter {

        /**
         * FragmentPagerAdapter constructor.
         *
         * @param fragmentManager used by the adapter.
         */
        public StartPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public CharSequence getPageTitle(int position) {
            final BaseFragment fragment = mFragments.get(position);
            return fragment.getTitle(getActivity());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}