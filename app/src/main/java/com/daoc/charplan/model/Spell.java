package com.daoc.charplan.model;

import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;

/**
 * A model containing all the information specific for a Spell.
 */
public class Spell extends Ability implements Serializable {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return "";
    }
}
