package com.daoc.charplan.ui.common;

/**
 * Abstract base implementation of Appointment item.
 */
public abstract class AbstractListItem implements ListItem {

    /**
     * ViewType of the Item.
     */
    private ListType mViewType;

    /**
     * The title of the Separator.
     */
    private String mTitle;

    /**
     * Default empty Constructor.
     */
    protected AbstractListItem() {
    }

    /**
     * Constructor for AbstractAppointmentItem.
     */
    protected AbstractListItem(String title, ListType appointmentType) {
        mTitle = title;
        mViewType = appointmentType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListType getViewType() {
        return mViewType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setViewType(ListType viewType) {
        mViewType = viewType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return mTitle;
    }
}