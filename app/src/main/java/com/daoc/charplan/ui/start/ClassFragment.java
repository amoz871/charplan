package com.daoc.charplan.ui.start;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoc.charplan.Constants;
import com.daoc.charplan.R;
import com.daoc.charplan.model.PlayerClass;
import com.daoc.charplan.provider.DbHelper;
import com.daoc.charplan.ui.character.CharacterActivity;
import com.daoc.charplan.ui.common.BaseFragment;
import com.daoc.charplan.util.Log;

import java.util.List;

/**
 * Fragment used for displaying {@link PlayerClass}es. Lives inside {@link StartActivity}.
 */
public class ClassFragment extends BaseFragment implements ClassInterface {

    /**
     * A {@link List} of all {@link PlayerClass}es.
     */
    private List<PlayerClass> mClasses;

    /**
     * Custom {@link android.support.v7.widget.RecyclerView.Adapter}.
     */
    private ClassAdapter mClassAdapter;

    /**
     * {@link Bundle} identifier for realm.
     */
    private static final String REALM_BUNDLE_IDENTIFIER = "realm";

    /**
     * {@link Bundle} identifier for class.
     */
    private static final String CLASS_BUNDLE_IDENTIFIER = "class";

    /**
     * Realm ID
     */
    private int mRealmId;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.d("Created ClassFragment");
        final View view = inflater.inflate(R.layout.classes_fragment, container, false);
        initializeRecyclerView(view);
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle(Context context) {
        mRealmId = getArguments().getInt(REALM_BUNDLE_IDENTIFIER);
        switch (mRealmId) {
            case Constants.ALBION_ID:
                return context.getString(R.string.albion);
            case Constants.HIBERNIA_ID:
                return context.getString(R.string.hibernia);
            case Constants.MIDGARD_ID:
                return context.getString(R.string.midgard);
            default:
                Log.e("Getting title for fragment with unknown realm id: " + mRealmId);
                return context.getString(R.string.unknown);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClassClicked(PlayerClass playerClass) {
        final Intent intent = new Intent(getActivity(), CharacterActivity.class);
        intent.putExtra(CLASS_BUNDLE_IDENTIFIER, playerClass);
        startActivity(intent);
    }

    /**
     * Initialize {@link RecyclerView} with {@link PlayerClass}es.
     *
     * @param view root view of {@link ClassFragment}.
     */
    private void initializeRecyclerView(View view) {
        final RecyclerView classesRecyclerView
                = (RecyclerView) view.findViewById(R.id.classes_recycler_view);
        if (mClassAdapter == null) {
            mClassAdapter = new ClassAdapter(getActivity(), mRealmId, this);
            classesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            classesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            classesRecyclerView.setAdapter(mClassAdapter);
            new ClassesAsyncTask().execute();
        }
    }

    /**
     * {@link AsyncTask} that prepares the ability list view.
     */
    private class ClassesAsyncTask extends AsyncTask<Void, Void, Void> {

        /**
         * Constructor
         */
        public ClassesAsyncTask() {}

        /**
         * {@inheritDoc}
         */
        @Override
        protected Void doInBackground(Void... params) {
            mClasses = DbHelper.getInstance(getContext()).getClasses(mRealmId);
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(Void v) {
            mClassAdapter.addContent(mClasses);
            Log.d("Found " + mClassAdapter.getItemCount() +
                    " classes for realm " + getTitle(getContext()));
        }
    }
}