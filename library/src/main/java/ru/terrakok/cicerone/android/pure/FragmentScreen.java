package ru.terrakok.cicerone.android.pure;

import android.app.Fragment;

import ru.terrakok.cicerone.Screen;

/**
 * Created by Konstantin Tskhovrebov (aka @terrakok) on 09.09.18.
 */
public abstract class FragmentScreen extends Screen {

    public FragmentScreen(String screenKey) {
        super(screenKey);
    }

    public abstract Fragment getFragment();
}
