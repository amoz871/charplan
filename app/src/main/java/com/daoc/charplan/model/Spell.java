package com.daoc.charplan.model;

import java.io.Serializable;

/**
 * A model containing all the information specific for a Spell.
 */
public class Spell extends Ability implements Serializable {

    protected String mTarget;
    protected String mCast;
    protected String mDuration;
    protected String mReuse;
    protected String mRange;
    protected String mRadius;
    private String mCost;
    // TODO: dmg type here or in Skill?

    /**
     * Protected constructor, should only create from database.
     */
    protected Spell() {
        super();
    }

    public String getTarget() {
        return mTarget;
    }

    public String getCast() {
        return mCast;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getReuse() {
        return mReuse;
    }

    public String getRange() {
        return mRange;
    }

    public String getRadius() {
        return mRadius;
    }

    public String getCost() {
        return mCost;
    }
}
