package com.daoc.charplan.ui.start;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daoc.charplan.Constants;
import com.daoc.charplan.R;
import com.daoc.charplan.model.PlayerClass;
import com.daoc.charplan.ui.common.AbstractListItem;
import com.daoc.charplan.ui.common.ListItem;
import com.daoc.charplan.ui.common.Separator;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for {@link RecyclerView} in {@link ClassFragment}.
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ItemViewHolder> {

    /**
     * Keep reference to {@link Context}.
     */
    private final Context mContext;

    /**
     * {@link List} of {@link PlayerClass}es.
     */
    private final List<AbstractListItem> mClasses;

    /**
     * Realm identifier.
     */
    private final int mRealmId;

    /**
     * Callback to {@link ClassFragment}.
     */
    private final ClassInterface mListener;

    /**
     * Constructor for ContactsAdapter.
     */
    public ClassAdapter(final Context context,
                        final int realmId,
                        final ClassInterface startInterface) {
        mContext = context;
        mClasses = new ArrayList<>();
        mRealmId = realmId;
        mListener = startInterface;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = null;
        switch (ListItem.ListType.valueOf(viewType)) {
            case CLASS:
                viewHolder = new ClassViewHolder(parent, R.layout.class_view_holder, mClasses,
                        mRealmId, mContext);
                break;
            case SEPARATOR:
                viewHolder = new SeparatorViewHolder(parent, R.layout.separator_view_holder);
                break;
            default:
        }
        return viewHolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.initializeViewHolderContents(position);
        if (holder.getViewType() == ListItem.ListType.CLASS) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClassClicked(holder.getPlayerClass(position));
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mClasses.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemViewType(int position) {
        AbstractListItem playerClass = mClasses.get(position);
        return playerClass.getViewType().ordinal();
    }

    /**
     * Populate {@link ClassAdapter} data set with {@link PlayerClass} items.
     *
     * @param classes list of {@link PlayerClass} objects to be added.
     */
    public void addContent(List<PlayerClass> classes) {
        mClasses.clear();
        String title = "";
        if (classes != null && !classes.isEmpty()) {
            for (PlayerClass playerClass : classes) {
                if (!title.equals(playerClass.getSubclass())) {
                    title = playerClass.getSubclass();
                    mClasses.add(new Separator(title));
                }
                mClasses.add(playerClass);
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
        public ItemViewHolder(ViewGroup parent, int resId) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }

        /**
         * Initialize ViewHolder content.
         *
         * @param position the position of the ViewHolder in the adapter.
         */
        void initializeViewHolderContents(int position) {
        }

        public abstract ListItem.ListType getViewType();

        /**
         * Retrieve the title of the {@link PlayerClass} object.
         *
         * @param position ViewHolder placement in adapter.
         * @return {@link PlayerClass#mName} .
         */
        public PlayerClass getPlayerClass(int position) {
            return (PlayerClass) mClasses.get(position);
        }
    }

    /**
     * {@link RecyclerView.ViewHolder} used for {@link PlayerClass}es.
     */
    public class ClassViewHolder extends ItemViewHolder {

        private final TextView mNameTextView;

        private List<AbstractListItem> mClasses;

        /**
         * Constructor for ClassViewHolder.
         */
        public ClassViewHolder(ViewGroup parent, int resId, List<AbstractListItem> classes,
                               int realmId, Context context) {
            super(parent, resId);
            mNameTextView = (TextView) itemView.findViewById(R.id.class_text_view);
            final CardView card = (CardView) itemView.findViewById(R.id.classes_item_view);
            switch (realmId) {
                case Constants.ALBION_ID:
                    card.setCardBackgroundColor(
                            ContextCompat.getColor(context, R.color.albionRed));
                    break;
                case Constants.MIDGARD_ID:
                    card.setCardBackgroundColor(
                            ContextCompat.getColor(context, R.color.midgardBlue));
                    break;
                case Constants.HIBERNIA_ID:
                    card.setCardBackgroundColor(
                            ContextCompat.getColor(context, R.color.hiberniaGreen));
                    break;
                default:
                    throw new IllegalStateException("Called with unknown realm ID: " +realmId);
            }
            mClasses = classes;
        }

        public TextView getNameTextView() {
            return mNameTextView;
        }

        /**
         *{@inheritDoc}
         */
        @Override
        public void initializeViewHolderContents(int position) {
            final AbstractListItem playerClass = mClasses.get(position);
            getNameTextView().setText(playerClass.getTitle());
        }

        /**
         *{@inheritDoc}
         */
        @Override
        public ListItem.ListType getViewType() {
            return ListItem.ListType.CLASS;
        }
    }

    /**
     * ViewHolder used for {@link Separator}.
     */
    private class SeparatorViewHolder extends ItemViewHolder {

        private final TextView mTitleTextView;

        /**
         * Constructor for SeparatorViewHolder.
         *
         * @param parent {@link ViewGroup} this holder is attached to.
         * @param resId  depicts which layout to inflate.
         */
        public SeparatorViewHolder(ViewGroup parent, int resId) {
            super(parent, resId);
            mTitleTextView = (TextView) itemView.findViewById(R.id.separator_title_text_view);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void initializeViewHolderContents(int position) {
            super.initializeViewHolderContents(position);
            mTitleTextView.setText(mClasses.get(position).getTitle());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListItem.ListType getViewType() {
            return ListItem.ListType.SEPARATOR;
        }
    }
}