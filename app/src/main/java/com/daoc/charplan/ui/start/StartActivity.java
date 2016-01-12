package com.daoc.charplan.ui.start;

import android.os.Bundle;

import com.daoc.charplan.R;
import com.daoc.charplan.ui.common.BaseActivity;
import com.daoc.charplan.util.Log;

/**
 * Start Activity of application.
 */
public class StartActivity extends BaseActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("StartActivity created");
        setContentView(R.layout.start_activity);
        StartFragment startFragment = new StartFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, startFragment,
                    StartFragment.class.getName()).commit();
        }
    }
}