package com.daoc.charplan.ui.character;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoc.charplan.R;
import com.daoc.charplan.model.Skill;
import com.daoc.charplan.model.Spec;
import com.daoc.charplan.provider.DbHelper;
import com.daoc.charplan.ui.common.BaseFragment;
import com.daoc.charplan.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Fragment used for displaying {@link Spec}s.
 */
public class SkillsFragment extends BaseFragment {

    private CharacterActivity mActivity;
    private List<Spec> mSpecs;
    private SkillsAdapter mSkillsAdapter;

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
    private void initializeRecyclerView(@NotNull View view) {
        final RecyclerView skillsRecyclerView = view.findViewById(R.id.skills_recycler_view);
        if (mSkillsAdapter == null) {
            mSkillsAdapter = new SkillsAdapter();
            skillsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            skillsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            skillsRecyclerView.setAdapter(mSkillsAdapter);
            new SkillsAsyncTask().execute();
        }
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
                // Get base skills for the Spec
                spec.setBaseSkills(
                        DbHelper.getInstance(getContext()).getSkills(spec.getId(), true));
                // Fill all base skills with abilities
                for (Skill baseSkill : spec.getBaseSkills()) {
                    // TODO
                }
                spec.setSpecSkills(
                        DbHelper.getInstance(getContext()).getSkills(spec.getId(), false));
                // Fill all spec skills with abilities
                for (Skill baseSkill : spec.getBaseSkills()) {
                    // TODO
                }
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(Void v) {
            mSkillsAdapter.addContent(mSpecs);
            Log.d("Found " + mSpecs.size() +
                    " specs for class " + mActivity.getPlayerClass().getTitle());
            for (Spec spec : mSpecs) {
                Log.d(spec.getId() + ": " + spec.getTitle());
                Log.d("Found " + spec.getBaseSkills().size() +
                        " base skills for spec: " + spec.getTitle());
                for (Skill skill : spec.getBaseSkills()) {
                    Log.d(skill.getId() + ": " + skill.getTitle());
                }
                Log.d("Found " + spec.getSpecSkills().size() +
                        " spec skills for spec: " + spec.getTitle());
                for (Skill skill : spec.getSpecSkills()) {
                    Log.d(skill.getId() + ": " + skill.getTitle());
                }
            }
        }
    }
}
