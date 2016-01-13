package com.daoc.charplan.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A model containing all the information specific for a realm ability.
 */
public class Ra extends Spell implements Serializable {

    // Fields assigned at creation.
    private boolean mIsPassive;
    private int mMaxLevel;
    private List<Integer> mCosts;
    private List<String> mEffects;

    // Dynamic field
    private int mLevel;

    /**
     * Private constructor, should only create from database.
     */
    private Ra() {
        super();
        mCosts = new ArrayList<>();
        mEffects = new ArrayList<>();
    }

    public boolean isPassive() {
        return mIsPassive;
    }

    public int getMaxLevel() {
        return mMaxLevel;
    }

    public List<Integer> getCosts() {
        return mCosts;
    }

    public List<String> getEffects() {
        return mEffects;
    }

    /**
     * Sets the current level of the Ra. If its below 0 or above max exception is thrown.
     * @param level The level to set.
     * @throws IllegalArgumentException if level is invalid.
     */
    public void setLevel(int level) {
        if (level < 0 || level > mMaxLevel) {
            throw new IllegalArgumentException("Trying to set level of " + mName + " to " + level
                    + " but max level is " + mMaxLevel);
        } else {
            mLevel = level;
        }
    }

    /**
     * Gets the current level of the Ra.
     * @return The current level.
     */
    @Override
    public int getLevel() {
        return mLevel;
    }
}
