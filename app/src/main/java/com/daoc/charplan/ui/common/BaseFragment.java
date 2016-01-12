package com.daoc.charplan.ui.common;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.daoc.charplan.util.Log;

/**
 * BaseFragment that contains core functionality for all fragments.
 */
public class BaseFragment extends Fragment {

    /**
     * Get reference to {@link BaseActivity}
     */
    protected BaseActivity mActivity;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Checks if current device should be treated as a tablet.
     *
     * @return {@code true} if tablet, {@code false} otherwise.
     */
    public boolean isTablet() {
        if (mActivity == null) {
            onDetach();
        }
        return mActivity.isTablet();
    }

    /**
     * Displays a message as a {@link android.widget.Toast}.
     *
     * @param message The message to display.
     */
    protected void showToast(final String message) {
        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                /**
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    mActivity.showToast(message);
                }
            });
        }
    }

    /**
     * Displays a message as in a TODO
     *
     * @param message The message to display.
     */
    protected void showDialog(final String message) {
        mActivity.showDialog(message);
    }

    /**
     * Retrieve title of the {@link Fragment}.
     */
    public String getTitle(Context context) {
        Log.w("Not overriding getTitle!");
        return "";
    }
}
