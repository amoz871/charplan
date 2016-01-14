package com.daoc.charplan.ui.character;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoc.charplan.R;
import com.daoc.charplan.model.PlayerClass;
import com.daoc.charplan.ui.common.BaseFragment;
import com.daoc.charplan.ui.common.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Fragment used for choosing class.
 */
public class CharacterFragment extends BaseFragment {

    public static final String CLASS_BUNDLE_IDENTIFIER = "class";

    /**
     * Identifier for which current page the user is viewing in the {@link ViewPager}.
     */
    private static final String CURRENT_PAGE_BUNDLE_IDENTIFIER = "currentPage";

    /**
     * {@link ViewPager} to display the fragments.
     */
    private ViewPager mViewPager;

    /**
     * Contains a list of {@link BaseFragment} to populate the {@link FragmentPagerAdapter} with.
     */
    private ArrayList<BaseFragment> mFragments;

    private PlayerClass mPlayerClass;
    private CharacterActivity mActivity;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        setRetainInstance(true);
        final View view = inflater.inflate(R.layout.character_fragment, container, false);
        mPlayerClass = (PlayerClass) getArguments().getSerializable(CLASS_BUNDLE_IDENTIFIER);
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
     * {@inheritDoc}
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (CharacterActivity) getActivity();
    }

    /**
     * Set level of current character.
     * @param level The level to set (1-50).
     */
    public void setLevel(int level) {
        /* TODO
        StatsFragment stats = (StatsFragment) mFragments.get(0);
        stats.setLevel(level);
        SkillsFragment skills = (SkillsFragment) mFragments.get(1);
        skills.setLevel(level);*/
    }

    /**
     * Add all Fragments to the {@link Fragment} ArrayList.
     */
    private void initializeFragments() {
        mFragments = new ArrayList<>();
        final Bundle args = new Bundle();
        args.putSerializable(CLASS_BUNDLE_IDENTIFIER, mPlayerClass);

        /* TODO
        final StatsFragment statsFragment = new StatsFragment();
        statsFragment.setArguments(args);
        mFragments.add(statsFragment);

        final SkillsFragment skillsFragment = new SkillsFragment();
        skillsFragment.setArguments(args);
        mFragments.add(skillsFragment);

        final RasFragment rasFragment = new RasFragment();
        rasFragment.setArguments(args);
        mFragments.add(rasFragment);*/
    }

    /**
     * Initialize {@link ViewPager}.
     */
    private void initializeViewPager(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.char_pager);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.addOnPageChangeListener(new SetLevelOnPageChangeListener());
        mViewPager.setAdapter(new CharacterPageAdapter(getChildFragmentManager()));
        ((SlidingTabLayout) view.findViewById(R.id.char_tab_layout)).setViewPager(mViewPager);
    }

    /**
     * Implementation of PagerAdapter that represents each page as a {@link Fragment}
     * that is persistently kept in the fragment manager as long as the user can return to the page.
     */
    private class CharacterPageAdapter extends FragmentPagerAdapter {

        /**
         * FragmentPagerAdapter constructor.
         *
         * @param fragmentManager used by the adapter.
         */
        public CharacterPageAdapter(FragmentManager fragmentManager) {
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

    /**
     * Listener to update common values between fragments.
     */
    private class SetLevelOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // Not used
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPageSelected(int position) {
            /* TODO this is ugly!
            if(position == 0) {
                StatsFragment fragment = (StatsFragment) mFragments.get(0);
                fragment.setLevel(mActivity.getLevel());
            } else if (position == 1) {
                SkillsFragment fragment = (SkillsFragment) mFragments.get(1);
                fragment.setLevel(mActivity.getLevel());
            }*/
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            // Not used
        }
    }
}