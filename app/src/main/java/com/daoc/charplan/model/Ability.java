package com.daoc.charplan.model;

import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;

/**
 * A model containing all the information specific for an Ability.
 */
public class Ability extends AbstractListItem implements Serializable {

    protected static int mId;
    protected static int mLevel;
    protected static String mName;

    public int getId() {
        return mId;
    }

    public int getLevel() {
        return mLevel;
    }

    @Override
    public String getTitle() {
        return mName;
    }
}
