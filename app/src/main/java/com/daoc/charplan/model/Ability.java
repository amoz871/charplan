package com.daoc.charplan.model;

import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;

/**
 * A model containing all the information specific for an Ability.
 */
public class Ability extends AbstractListItem implements Serializable {

    protected int mId;
    protected int mLevel;
    protected String mName;
    protected String mEffect;

    /**
     * Protected constructor, only create from database.
     */
    protected Ability() {}

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

    public String getEffect() {
        return mEffect;
    }
}
