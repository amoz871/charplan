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
public class Spec extends AbstractListItem implements Serializable {

    private int mId;
    private String mTitle;
    private String mSpecTitle;
    private List<Skill> mBaseSkills;
    private List<Skill> mSpecSkills;

    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public String getSpecTitle() {
        return mSpecTitle;
    }

    public List<Skill> getBaseSkills() {
        return mBaseSkills;
    }

    public List<Skill> getSpecSkills() {
        return mSpecSkills;
    }

    /**
     * Constructed from database.
     */
    @Contract("null -> fail")
    public static Spec loadFromCursor(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            throw new UnsupportedOperationException("Cursor is null or closed");
        }
        final Spec spec = new Spec();
        spec.mId = cursor.getInt(cursor.getColumnIndex(DbContract.TableSpecs._ID));
        spec.mTitle = cursor.getString(cursor.getColumnIndex(DbContract.TableSpecs.TITLE));
        // TODO: add the rest

        return spec;
    }
}
