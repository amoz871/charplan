package com.daoc.charplan.ui.character;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoc.charplan.R;
import com.daoc.charplan.model.Spec;
import com.daoc.charplan.provider.DbHelper;
import com.daoc.charplan.ui.common.BaseFragment;
import com.daoc.charplan.util.Log;

import java.util.List;

/**
 * Fragment used for displaying {@link Spec}s.
 */
public class SkillsFragment extends BaseFragment {

    private CharacterActivity mActivity;
    private List<Spec> mSpecs;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Created SkillsFragment");
        final View view = inflater.inflate(R.layout.skills_fragment, container, false);
        initializeRecyclerView(view);
        return view;
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
     * Initialize {@link RecyclerView} with {@link Spec}s.
     *
     * @param view root view of {@link SkillsFragment}.
     */
    private void initializeRecyclerView(View view) {
        new SkillsAsyncTask().execute();
    }

    /**
     * {@link AsyncTask} that prepares the skill list view.
     */
    private class SkillsAsyncTask extends AsyncTask<Void, Void, Void> {

        /**
         * Constructor
         */
        public SkillsAsyncTask() {}

        @Override
        protected Void doInBackground(Void... params) {
            // Get all Specs for the PlayerClass
            mSpecs = DbHelper.getInstance(
                    getContext()).getSpecs(mActivity.getPlayerClass().getId());
            for (Spec spec : mSpecs) {
                // Get Skills for the Spec
                //DbHelper.getInstance(getContext()).getSkills(spec.getId());

            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(Void v) {
            /*mSkillsAdapter.addContent(mSpecs);*/
            Log.d("Found " + mSpecs.size() +
                    " specs for class " + mActivity.getPlayerClass().getTitle());
            for (Spec spec : mSpecs) {
                Log.d(spec.getId() + ": " + spec.getTitle());
            }
        }
    }
}
