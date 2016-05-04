package com.daoc.charplan.ui.character;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.daoc.charplan.R;
import com.daoc.charplan.model.PlayerClass;
import com.daoc.charplan.ui.common.BaseActivity;
import com.daoc.charplan.util.Log;

/**
 * Charplan Activity of application.
 */
public class CharacterActivity extends BaseActivity {

    /**
     * Bundle identifier for the {@link PlayerClass} of this activity.
     */
    private static final String CLASS_BUNDLE_IDENTIFIER = "class";

    /**
     * The {@link PlayerClass} of this activity.
     */
    private PlayerClass mPlayerClass;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CharacterActivity created");
        setContentView(R.layout.character_activity);
        mPlayerClass = (PlayerClass) getIntent().getSerializableExtra(CLASS_BUNDLE_IDENTIFIER);
        if (mPlayerClass == null) {
            finish();
            Log.e("CharacterActivity created without a class");
            return;
        }
        mPlayerClass.setLevel(50);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mPlayerClass.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final Bundle args = new Bundle();
        args.putSerializable(CLASS_BUNDLE_IDENTIFIER, mPlayerClass);
        CharacterFragment characterFragment = new CharacterFragment();
        characterFragment.setArguments(args);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    characterFragment, CharacterFragment.class.getName()).commit();
        }
    }

    public PlayerClass getPlayerClass() {
        return mPlayerClass;
    }

    public void setLevel(double level) {
        mPlayerClass.setLevel(level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.character_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.menu_settings:
                openSettings();
                break;
            case R.id.menu_lvl50:
                setMaxLevel();
                break;
            case R.id.menu_lvl1:
                setMinLevel();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set character level to 50.
     */
    private void setMaxLevel() {
        CharacterFragment fragment = (CharacterFragment) getFragmentManager().
                findFragmentByTag(CharacterFragment.class.getName());
        mPlayerClass.setLevel(50);
        fragment.setLevel(50);
    }

    /**
     * Set character level to 1.
     */
    private void setMinLevel() {
        CharacterFragment fragment = (CharacterFragment) getFragmentManager().
                findFragmentByTag(CharacterFragment.class.getName());
        mPlayerClass.setLevel(1);
        fragment.setLevel(1);
    }
}