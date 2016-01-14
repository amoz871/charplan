package com.daoc.charplan.model;

import java.io.Serializable;

/**
 * A model containing all the information specific for a Style.
 */
public class Style extends Ability implements Serializable {

    private String mPreReq;
    private Style mPreReqStyle;
    private String mAttack;
    private String mDefense;
    private String mCost;
    private String mDamage;
    private String mGrowthRate;
    private Spell mEffectSpell;

    /**
     * Private constructor, should only create from database.
     */
    private Style() {
        super();
    }

    public String getPreReq() {
        return mPreReq;
    }

    public Style getPreReqStyle() {
        return mPreReqStyle;
    }

    public String getAttack() {
        return mAttack;
    }

    public String getDefense() {
        return mDefense;
    }

    public String getCost() {
        return mCost;
    }

    public String getDamage() {
        return mDamage;
    }

    public String getGr() {
        return mGrowthRate;
    }

    public Spell getEffectSpell() {
        return mEffectSpell;
    }
}
