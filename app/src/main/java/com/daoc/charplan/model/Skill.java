package com.daoc.charplan.model;

import android.database.Cursor;

import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;
import java.util.List;

/**
 * A model containing all the information specific for a specialization.
 */
public class Skill extends AbstractListItem implements Serializable {

    private int mId;
    private String mTitle;

    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    /**
     * Constructed from database.
     */
    public static Skill loadFromCursor(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            throw new UnsupportedOperationException("Cursor is null or closed");
        }
        final Skill skill = new Skill();
        //FIXME
        return skill;
    }
}
