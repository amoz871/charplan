package com.daoc.charplan.ui.character;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daoc.charplan.R;
import com.daoc.charplan.model.PlayerClass;
import com.daoc.charplan.model.Skill;
import com.daoc.charplan.model.Spec;
import com.daoc.charplan.ui.common.AbstractListItem;
import com.daoc.charplan.ui.common.ListItem;
import com.daoc.charplan.ui.common.Separator;
import com.daoc.charplan.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for {@link RecyclerView} in {@link SkillsFragment}.
 */
public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ItemViewHolder> {

    /**
     * The {@link Spec}s and {@link Skill}s available for this {@link PlayerClass}.
     */
    private final List<AbstractListItem> mSpecs;

    public SkillsAdapter() {
        mSpecs = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = null;
        switch (ListItem.ListType.valueOf(viewType)) {
            case SPEC:
                viewHolder = new SpecViewHolder(parent, R.layout.spec_view_holder);
                break;
            case SKILL:
                //viewHolder = new SkillViewHolder(parent, R.layout.skill_view_holder);
                break;
            default:
                Log.e("Trying to create view holder with type: "
                        + ListItem.ListType.valueOf(viewType));
        }
        return viewHolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.initializeViewHolderContents(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mSpecs.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemViewType(int position) {
        AbstractListItem specOrSkill = mSpecs.get(position);
        return specOrSkill.getViewType().ordinal();
    }

    /**
     * Populate {@link SkillsAdapter} data set with {@link Spec} objects.
     *
     * @param specs list of {@link Spec} objects to be added.
     */
    public void addContent(List<Spec> specs) {
        mSpecs.clear();
        String title = "";
        if (specs != null && specs.isEmpty()) {
            for (Spec spec : specs) {
                if (!title.equals(spec.getTitle())) {
                    title = spec.getTitle();
                    mSpecs.add(new Separator(title));
                }
                mSpecs.add(spec);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Abstract ViewHolder class used to inflate and initialize given ViewHolder.
     */
    protected abstract class ItemViewHolder extends RecyclerView.ViewHolder {

        /**
         * Constructor for ItemViewHolder.
         */
        public ItemViewHolder(@NotNull ViewGroup parent, int resId) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }

        /**
         * Initialize ViewHolder content.
         *
         * @param position the position of the ViewHolder in the adapter.
         */
        public abstract void initializeViewHolderContents(int position);

        public abstract ListItem.ListType getViewType();
    }

    /**
     * {@link RecyclerView.ViewHolder} used for {@link Spec}s.
     */
    public class SpecViewHolder extends ItemViewHolder {

        private final CardView mCardView;
        private final TextView mNameTextView;
        private final Button mDecreaseButton;
        private final TextView mLevelTextView;
        private final Button mIncreaseButton;

        /**
         * Constructor for SpecViewHolder.
         */
        public SpecViewHolder(ViewGroup parent, int resId) {
            super(parent, resId);
            mCardView = itemView.findViewById(R.id.spec_item_view);
            mNameTextView = itemView.findViewById(R.id.spec_text_view);
            mDecreaseButton = itemView.findViewById(R.id.spec_decrease_button);
            mLevelTextView = itemView.findViewById(R.id.spec_level_text);
            mIncreaseButton = itemView.findViewById(R.id.spec_increase_button);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void initializeViewHolderContents(final int position) {
            final Spec spec = (Spec) mSpecs.get(position);
        }

        @Override
        public ListItem.ListType getViewType() {
            return ListItem.ListType.SPEC;
        }
    }
}
