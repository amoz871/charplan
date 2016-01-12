/*
 * Copyright (C) 1998-2015 UEFA.
 * All rights, including trade secret rights, reserved.
 */
package com.daoc.charplan.ui.start;

import com.daoc.charplan.model.PlayerClass;

/**
 * Interface used between a {@link android.app.Fragment} and i
 * ts {@link android.support.v7.widget.RecyclerView.Adapter}.
 */
public interface ClassInterface {

    /**
     * Called when a {@link PlayerClass} in
     * {@link android.support.v7.widget.RecyclerView.Adapter} is clicked.
     *
     * @param playerClass The {@link PlayerClass} that was clicked.
     */
    void onClassClicked(final PlayerClass playerClass);
}