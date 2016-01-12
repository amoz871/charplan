package com.daoc.charplan.model;

import android.database.Cursor;

import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;

/**
 * A model containing all the information specific for a Class.
 */
public class PlayerClass extends AbstractListItem implements Serializable {

    private int mId;
    private String mName;
    private String mRealm;
    private int mRealmId;
    private String mSubclass;
    private double mLevel;
    //private RealmRank mRealmRank;
    //TODO: list of RAs

    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mName;
    }

    public String getSubclass() {
        return mSubclass;
    }

    public String getRealm() {
        return mRealm;
    }

    public int getRealmId() {
        return mRealmId;
    }

    public void setLevel(double level) {
        mLevel = level;
    }

    public double getLevel() {
        return mLevel;
    }

    //TODO: nest realm rank?
    /*public RealmRank getRealmRank() {
        return mRealmRank;
    }*/

    /**
     * Constructed from database.
     */
    public static PlayerClass loadFrom(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            throw new UnsupportedOperationException("Cursor is null or closed");
        }
        final PlayerClass playerClass = new PlayerClass();
        //TODO: load player class from db
        /*playerClass.mId = cursor.getInt(cursor.getColumnIndex(DbContract.TableClasses._ID));
        playerClass.mName = cursor.getString(cursor.getColumnIndex(DbContract.TableClasses.NAME));
        playerClass.mRealm = cursor.getString(
                cursor.getColumnIndex(DbContract.TableClasses.REALM));
        playerClass.mSubclass = cursor.getString(
                cursor.getColumnIndex(DbContract.TableClasses.SUBCLASS));
        playerClass.mRealmId = getIdFromRealm(playerClass.mRealm);
        playerClass.mRealmRank = new RealmRank(playerClass.mRealmId);*/

        return playerClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListType getViewType() {
        return ListType.CLASS;
    }
}