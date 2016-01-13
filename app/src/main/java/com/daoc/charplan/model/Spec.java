package com.daoc.charplan.model;

import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;

/**
 * A model containing all the information specific for a Spec.
 */
public class Spec extends AbstractListItem implements Serializable {

    public int getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return "";
    }
}
