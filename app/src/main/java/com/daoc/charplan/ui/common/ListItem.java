package com.daoc.charplan.ui.common;

/**
 * Interface used to get AppointmentType and title of separator.
 */
public interface ListItem {

    /**
     * Retrieve viewType of the ListItem.
     * @return ViewType of ListItem.
     */
    ListType getViewType();

    /**
     * Set the ViewType of the ListItem.
     * @param viewType the ListItem will be assigned to.
     */
    void setViewType(ListType viewType);

    /**
     * Retrieve the ID for the ListItem
     * @return ID of ListItem
     */
    int getId();

    /**
     * Retrieve the title for the ListItem.
     * @return title of ListItem.
     */
    String getTitle();

    /**
     * Enum to get which item type an object belongs to.
     */
    enum ListType {

        /**
         * Item is a {@link Separator}.
         */
        SEPARATOR,

        /**
         * Item is a {@link com.daoc.charplan.model.PlayerClass}.
         */
        CLASS,

        /**
         * Item is a TODO: Stat.
         */
        STAT,

        /**
         * Item is an {@link com.daoc.charplan.model.Ability}.
         */
        ABILITY,

        /**
         * Item is a {@link com.daoc.charplan.model.Spell}.
         */
        SPELL,

        /**
         * Item is a {@link com.daoc.charplan.model.Style}.
         */
        STYLE,

        /**
         * Item is a {@link com.daoc.charplan.model.Skill}.
         */
        SKILL,

        /**
         * Item is a {@link com.daoc.charplan.model.Spec}.
         */
        SPEC,

        /**
         * Item is a {@link com.daoc.charplan.model.RA}.
         */
        RA,

        /**
         * Item is a TODO: ML.
         */
        ML;

        /**
         * Returns the ordinal of this enumeration constant.
         *
         * @param ordinal the position.
         * @return the position of the ListType in its enum declaration.
         */
        public static ListType valueOf(int ordinal) {
            return values()[ordinal];
        }
    }
}