package com.daoc.charplan.provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.daoc.charplan.Constants;
import com.daoc.charplan.model.PlayerClass;
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
     * Queries a database for the all the {@link PlayerClass}es.
     *
     * @return The result of the query.
     */
    public List<PlayerClass> getClasses() {
        return getClasses(0);
    }

    /**
     * Queries a database for the classes. If ID is 0 all classes will get returned.
     *
     * @param realmId The realm to get classes for, see {@link Constants}.
     * @return The result of the query.
     */
    public List<PlayerClass> getClasses(int realmId) {
        String table = DbContract.TableClasses.TABLE;
        String[] projection = {DbContract.TableClasses._ID,
                DbContract.TableClasses.NAME,
                DbContract.TableClasses.REALM,
                DbContract.TableClasses.SUBCLASS};
        String selection;
        String[] selectionArgs;

        switch (realmId) {
            case Constants.ALBION_ID:
            case Constants.HIBERNIA_ID:
            case Constants.MIDGARD_ID:
                selection = DbContract.TableClasses.REALM + " = ?";
                selectionArgs = new String[]{String.valueOf(realmId)};
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

    private static Cursor query(SQLiteDatabase db, String table, String[] projection,
                                String selection, String[] selectionArgs) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(table);

        return qb.query(db, projection, selection, selectionArgs, null, null, null);
    }
}
