package ru.terrakok.cicerone.android.support;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Konstantin Tskhovrebov (aka @terrakok) on 09.09.18.
 */
public abstract class SupportAppScreen extends SupportFragmentScreen {

    public SupportAppScreen(String screenKey) {
        super(screenKey);
    }

    @Override
    public Fragment getFragment() {
        return null;
    }

    public Intent getActivityIntent(Context context) {
        return null;
    }
}
