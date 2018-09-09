package ru.terrakok.cicerone.android.support;

import android.support.v4.app.Fragment;

import ru.terrakok.cicerone.Screen;

/**
 * Created by Konstantin Tskhovrebov (aka @terrakok) on 09.09.18.
 */
public abstract class SupportFragmentScreen extends Screen {

    public SupportFragmentScreen(String screenKey) {
        super(screenKey);
    }

    public abstract Fragment getFragment();
}
