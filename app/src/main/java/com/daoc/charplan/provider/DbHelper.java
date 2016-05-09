package com.daoc.charplan.provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.NonNull;

import com.daoc.charplan.Constants;
import com.daoc.charplan.model.PlayerClass;
import com.daoc.charplan.model.Skill;
import com.daoc.charplan.model.Spec;
import com.daoc.charplan.model.Spell;
import com.daoc.charplan.model.Style;
import com.daoc.charplan.util.Log;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "classic.db";
    private static final int DATABASE_VERSION = 1;
    private static DbHelper sInstance;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbHelper(context);
        }
        return sInstance;
    }

    /**
     * Queries a database for the {@link PlayerClass}es.
     * If ID is 0 all {@link PlayerClass}es will get returned.
     *
     * @param realmId The realm to get {@link PlayerClass}es for, see {@link Constants}.
     * @return The result of the query.
     */
    public List<PlayerClass> getClasses(int realmId) {
        String table = DbContract.TableClasses.TABLE;
        String[] projection = {DbContract.TableClasses._ID,
                DbContract.TableClasses.TITLE,
                DbContract.TableClasses.REALM,
                DbContract.TableClasses.SUBCLASS,
                DbContract.TableClasses.MULTIPLIER};
        String selection;
        String[] selectionArgs;

        switch (realmId) {
            case Constants.ALBION_ID:
            case Constants.HIBERNIA_ID:
            case Constants.MIDGARD_ID:
                selection = DbContract.TableClasses.REALM + " = ?";
                selectionArgs = new String[]{getRealmFromId(realmId)};
                break;
            case Constants.NO_REALM:
                selection = null;
                selectionArgs = null;
                break;
            default:
                throw new IllegalStateException("Illegal realm ID when getting classes");
        }

        final Cursor cursor = query(getReadableDatabase(),
                table, projection, selection, selectionArgs);
        final List<PlayerClass> classes = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                final PlayerClass playerClass = PlayerClass.loadFromCursor(cursor);
                classes.add(playerClass);
            }
            cursor.close();
        }
        return classes;
    }

    /**
     * Queries a database for all {@link Spec}s for a {@link PlayerClass}.
     *
     * @param classId The {@link PlayerClass} to get {@link Spec}s for.
     * @return The result of the query.
     */
    public List<Spec> getSpecs(int classId) {
        String table = DbContract.TableSpecs.TABLE;
        String[] projection = {DbContract.TableSpecs._ID,
                DbContract.TableSpecs.TITLE};
        String selection = DbContract.TableSpecs.CLASS + " LIKE ?";
        String selectionArgs[] = new String[]{"%,"+Integer.toString(classId)+",%"};

        final Cursor cursor = query(getReadableDatabase(),
                table, projection, selection, selectionArgs);
        final List<Spec> specs = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                final Spec spec = Spec.loadFromCursor(cursor);
                specs.add(spec);
            }
            cursor.close();
        }
        return specs;
    }

    /**
     * Queries a database for all {@link Skill}s for a {@link Spec}.
     *
     * @param specId The {@link Spec} to get {@link Skill}s for.
     * @return The result of the query.
     */
    public List<Skill> getSkills(int specId, boolean isBase) {
        String table = DbContract.TableSkills.TABLE;
        String[] projection = {DbContract.TableSkills._ID,
                DbContract.TableSkills.TITLE};
        String selection = DbContract.TableSkills.SPECS + " LIKE ? AND " +
                DbContract.TableSkills.BASE + " = ?";
        String selectionArgs[] = new String[]{"%,"+Integer.toString(specId)+",%",
                isBase ? "1": "0"};

        final Cursor cursor = query(getReadableDatabase(),
                table, projection, selection, selectionArgs);
        final List<Skill> skills = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                final Skill skill = Skill.loadFromCursor(cursor);
                skills.add(skill);
            }
            cursor.close();
        }
        return skills;
    }

    /**
     * Queries a database for all {@link Spell}s for a {@link Skill}.
     *
     * @param skillId The {@link Skill} to get {@link Spell}s for.
     * @param classId The {@link PlayerClass} to get {@link Spell}s for.
     * @return The result of the query.
     */
    public List<Spell> getSpells(int skillId, int classId) {
        String table = DbContract.TableSpells.TABLE;
        String[] projection = {DbContract.TableSpells._ID,
                DbContract.TableSpells.TITLE};
        String selection = DbContract.TableSpells.SKILL;
        String selectionArgs[] = new String[]{Integer.toString(skillId)};

        final Cursor cursor = query(getReadableDatabase(),
                table, projection, selection,selectionArgs);
        final List<Spell> spells = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                final Spell spell = Spell.loadFromCursor(cursor);
                spells.add(spell);
            }
        }
        return spells;
    }

    /**
     * Queries a database for all {@link Style}s for a {@link Skill}.
     *
     * @param skillId The {@link Skill} to get {@link Style}s for.
     * @param classId The {@link PlayerClass} to get {@link Style}s for.
     * @return The result of the query.
     */
    public List<Style> getStyles(int skillId, int classId) {
        String table = DbContract.TableStyles.TABLE;
        String[] projection = {DbContract.TableStyles._ID,
                DbContract.TableStyles.TITLE};
        String selection = DbContract.TableStyles.SKILL;
        String selectionArgs[] = new String[]{Integer.toString(skillId)};

        final Cursor cursor = query(getReadableDatabase(),
                table, projection, selection,selectionArgs);
        final List<Style> styles = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                final Style style = Style.loadFromCursor(cursor);
                styles.add(style);
            }
        }
        return styles;
    }

    private static Cursor query(SQLiteDatabase db, String table, String[] projection,
                                String selection, String[] selectionArgs) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(table);

        return qb.query(db, projection, selection, selectionArgs, null, null, null);
    }

    /**
     * Converts the realm ID into a {@link String} that can be used to query the database.
     *
     * @param id The realm ID.
     * @return The corresponding realm {@link String}.
     */
    @NonNull
    private static String getRealmFromId(int id) {
        switch (id) {
            case 1:
                return "Albion";
            case 2:
                return "Hibernia";
            case 3:
                return "Midgard";
            default:
                throw new IllegalArgumentException("Trying to get Realm from id: " + id);
        }
    }
}
