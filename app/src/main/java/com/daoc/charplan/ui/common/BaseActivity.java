package com.daoc.charplan.ui.common;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.daoc.charplan.R;
import com.daoc.charplan.ui.settings.SettingsActivity;
import com.daoc.charplan.util.Log;

/**
 * BaseActivity for methods used in Activities.
 */
public abstract class BaseActivity extends Activity {

    /**
     * {@link Toast} to show messages to users.
     */
    private Toast mToast;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(isTablet() ?
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED :
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                openSettings();
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     * Checks if current device should be treated as a tablet.
     *
     * @return {@code true} if tablet, {@code false} otherwise.
     */
    public boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }

    /**
     * Sets the title of the {@link Activity}, shown in the {@link ActionBar}.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    /**
     * Opens the {@link SettingsActivity}.
     */
    public void openSettings() {
        startActivity(new Intent(BaseActivity.this, SettingsActivity.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Displays the message in a TODO.
     *
     * @param message The message to display.
     */
    protected void showDialog(final String message) {
        Log.d("Showing dialog: " + message);
        //TODO
    }

    /**
     * Displays the message in a {@link Toast}.
     *
     * @param message The message to display.
     */
    protected void showToast(final String message) {
        Log.d("Showing toast: " + message);
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.BOTTOM, 0, 40);
        mToast.show();
    }
}
