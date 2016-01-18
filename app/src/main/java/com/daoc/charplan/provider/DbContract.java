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
        // TODO: add the rest
    }

    public static abstract class TableAbilities implements BaseColumns {
        public static final String TABLE = "abilities";

        public static final String _ID = "_id";
        public static final String LEVEL = "level";
        public static final String NAME = "name";
        public static final String EFFECT = "effect";
    }

    public static abstract class TableSpecs implements BaseColumns {
        public static final String TABLE = "specs";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        // TODO: add the rest
    }

    public static abstract class TableSkills implements BaseColumns {
        public static final String TABLE = "skills";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String ABILITY_IDS = "abilityIds";
    }

    public static abstract class TableSpells implements BaseColumns {
        public static final String TABLE = "spells";

        public static final String _ID = "_id";
        public static final String LEVEL = "level";
        public static final String NAME = "name";
        public static final String TARGET = "target";
        public static final String CAST = "cast";
        public static final String DURATION = "duration";
        public static final String REUSE = "reuse";
        public static final String RANGE = "range";
        public static final String RADIUS = "radius";
        public static final String EFFECT = "effect";
        public static final String COST = "cost";
        public static final String DMG_TYPE = "dmgtype";
        public static final String CLASS_ID = "classId";
    }

    public static abstract class TableStyles implements BaseColumns {
        public static final String TABLE = "styles";

        public static final String _ID = "_id";
        public static final String LEVEL = "level";
        public static final String NAME = "name";
        public static final String PREREQUISITE = "prerequisite";
        public static final String ATTACK = "attack";
        public static final String DEFENSE = "defense";
        public static final String COST = "cost";
        public static final String DAMAGE = "damage";
        public static final String EFFECT = "effect";
        public static final String GROWTH_RATE = "growthRate";
        public static final String PREREQ_STYLE = "prereqstyle";
        public static final String EFFECT_SPELL = "effectSpell";
        public static final String CLASS_ID = "classId";
    }
}
