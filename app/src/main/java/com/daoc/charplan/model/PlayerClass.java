package com.daoc.charplan.model;

import android.database.Cursor;

import com.daoc.charplan.Constants;
import com.daoc.charplan.provider.DbContract;
import com.daoc.charplan.ui.common.AbstractListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A model containing all the information specific for a PlayerClass.
 */
public class PlayerClass extends AbstractListItem implements Serializable {

    // Static fields assigned at creation.
    private static int mId;
    private static String mName;
    private static String mRealm;
    private static int mRealmId;
    private static String mSubclass;

    // Fields assigned on selection
    private static double mMultiplier;
    private List<Ability> mAbilities;
    private List<Spec> mSpecs;
    // TODO: add the rest

    // Dynamic fields to be used after selection
    private double mLevel;
    private int mRealmRank;
    private int mMasterLevel;
    private int mChampionLevel;

    /**
     * Private constructor, only create from database.
     */
    private PlayerClass() {
        mAbilities = new ArrayList<>();
        mSpecs = new ArrayList<>();
    }

    // Items from creation
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

    // Items assigned on selection
    public void addAbilities(List<Ability> abilities) {
        mAbilities.addAll(abilities);
    }

    public List<Ability> getAbilities() {
        return mAbilities;
    }

    public void addSpecs(List<Spec> specs) {
        mSpecs.addAll(specs);
    }

    public List<Spec> getSpecs() {
        return mSpecs;
    }

    // Dynamic items
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
    public static PlayerClass loadFromCursor(Cursor cursor) {
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
     * @return Realm ID, see {@link Constants}.
     */
    private static int getIdForRealm(String realm) {
        switch (realm) {
            // TODO: add FR and DE strings
            case "Albion":
                return Constants.ALBION_ID;
            case "Hibernia":
                return Constants.HIBERNIA_ID;
            case "Midgard":
                return Constants.MIDGARD_ID;
            default:
                throw new IllegalArgumentException("Trying to get realm ID for unknown string: "
                        + realm + " for class: " + mName);
        }
    }
}