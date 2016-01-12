package com.daoc.charplan.model;

import android.database.Cursor;

import com.daoc.charplan.provider.DbContract;
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
    private int mRealmRank;
    private int mMasterLevel;
    private int mChampionLevel;

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

    public void setRR(int rr) {
        mRealmRank = rr;
    }

    public int getRR() {
        return mRealmRank;
    }

    public void setML(int ml) {
        mMasterLevel = ml;
    }

    public int getML() {
        return mMasterLevel;
    }

    public void setCL(int cl) {
        mChampionLevel = cl;
    }

    public int getCL() {
        return mChampionLevel;
    }

    /**
     * Constructed from database.
     */
    public static PlayerClass loadFrom(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            throw new UnsupportedOperationException("Cursor is null or closed");
        }
        final PlayerClass playerClass = new PlayerClass();
        playerClass.mId = cursor.getInt(cursor.getColumnIndex(DbContract.TableClasses._ID));
        playerClass.mName = cursor.getString(cursor.getColumnIndex(DbContract.TableClasses.NAME));
        playerClass.mRealm = cursor.getString(
                cursor.getColumnIndex(DbContract.TableClasses.REALM));
        playerClass.mSubclass = cursor.getString(
                cursor.getColumnIndex(DbContract.TableClasses.SUBCLASS));
        playerClass.mRealmId = cursor.getInt(cursor.getColumnIndex(DbContract.TableClasses._ID));

        playerClass.mRealmRank = 10; //RR1L0 is min
        playerClass.mMasterLevel = 0;
        playerClass.mChampionLevel = 0;

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