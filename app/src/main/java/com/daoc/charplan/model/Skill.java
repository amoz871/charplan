package com.daoc.charplan.model;

import android.database.Cursor;

import com.daoc.charplan.provider.DbContract;
import com.daoc.charplan.ui.common.AbstractListItem;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.util.List;

/**
 * A model containing all the information specific for a specialization.
 */
public class Skill extends AbstractListItem implements Serializable {

    private int mId;
    private String mTitle;
    private List<Ability> mAbilities;

    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public List<Ability> getAbilities() {
        return mAbilities;
    }

    /**
     * Constructed from database.
     */
    @Contract("null -> fail")
    public static Skill loadFromCursor(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            throw new UnsupportedOperationException("Cursor is null or closed");
        }
        final Skill skill = new Skill();
        skill.mId = cursor.getInt(cursor.getColumnIndex(DbContract.TableSkills._ID));
        skill.mTitle = cursor.getString(cursor.getColumnIndex(DbContract.TableSkills.TITLE));
        // TODO: add the rest
        
        return skill;
    }
}
