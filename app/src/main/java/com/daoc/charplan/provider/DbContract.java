package com.daoc.charplan.provider;

import android.provider.BaseColumns;

/**
 * Contract class to explicitly specify the layout of the schema.
 */
public class DbContract {

    public static abstract class TableClasses implements BaseColumns {
        public static final String TABLE = "classes";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String REALM = "realm";
        public static final String SUBCLASS = "subclass";
    }

    public static abstract class TableAbilities implements BaseColumns {
        public static final String TABLE = "abilities";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String LEVEL = "level";
        public static final String EFFECT = "effect";
    }
}
