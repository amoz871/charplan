package com.daoc.charplan.model;

import android.database.Cursor;

import com.daoc.charplan.provider.DbContract;
import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;

/**
 * A model containing all the information specific for a Class.
 */
public class PlayerClass extends AbstractListItem implements Serializable {

    // Static fields assigned at creation.
    private static int mId;
    private static String mName;
    private static String mRealm;
    private static int mRealmId;
    private static String mSubclass;

    // Non-static fields holding current status.
    private double mLevel;
    private int mRealmRank;
    private int mMasterLevel;
    private int mChampionLevel;

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mName;
    }

    public String getName() {
        return getTitle();
    }

    public String getRealm() {
        return mRealm;
    }

    public int getRealmId() {
        return mRealmId;
    }

    public String getSubclass() {
        return mSubclass;
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
        mId = cursor.getInt(cursor.getColumnIndex(DbContract.TableClasses._ID));

        // Name, realm and subclass will depend on language (EN/FR/DE)
        mName = cursor.getString(cursor.getColumnIndex(DbContract.TableClasses.NAME));
        mRealm = cursor.getString(
                cursor.getColumnIndex(DbContract.TableClasses.REALM));
        mRealmId = getIdForRealm(mRealm);
        // Only used for classic
        mSubclass = cursor.getString(
                cursor.getColumnIndex(DbContract.TableClasses.SUBCLASS));

        // RR1L0 is min
        playerClass.mRealmRank = 10;
        // Only used for live
        playerClass.mMasterLevel = 0;
        // Only used for live
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

    /**
     * Gets the ID of the realm from the string retrieved from database
     *
     * @param realm The name of the realm
     * @return Realm ID, see #Constrants
     */
    private static int getIdForRealm(String realm) {
        switch (realm) {
            // TODO: add FR and DE strings
            case "Albion":
                return 1;
            case "Hibernia":
                return 2;
            case "Midgard":
                return 3;
            default:
                throw new IllegalArgumentException("Trying to get realm ID for unknown string: "
                        + realm + " for class: " + mName);
        }
    }
}