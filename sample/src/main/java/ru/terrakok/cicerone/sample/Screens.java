package ru.terrakok.cicerone.sample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;
import ru.terrakok.cicerone.sample.ui.animations.ProfileActivity;
import ru.terrakok.cicerone.sample.ui.animations.photos.SelectPhotoFragment;
import ru.terrakok.cicerone.sample.ui.animations.profile.ProfileFragment;
import ru.terrakok.cicerone.sample.ui.bottom.BottomNavigationActivity;
import ru.terrakok.cicerone.sample.ui.bottom.ForwardFragment;
import ru.terrakok.cicerone.sample.ui.main.MainActivity;
import ru.terrakok.cicerone.sample.ui.main.SampleFragment;
import ru.terrakok.cicerone.sample.ui.start.StartActivity;

/**
 * Created by Konstantin Tckhovrebov (aka @terrakok)
 * on 11.10.16
 */

public class Screens {
    public static final class Sample extends SupportAppScreen {
        private int number;
        public Sample(int number) {
            super("sample_screen_" + number);
            this.number = number;
        }

        @Override
        public Fragment getFragment() {
            return SampleFragment.getNewInstance(number);
        }
    }

    public static final class Start extends SupportAppScreen {
        public Start() {
            super("start activity screen");
        }

        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, StartActivity.class);
        }
    }

    public static final class Main extends SupportAppScreen {
        public Main() {
            super("main activity screen");
        }

        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, MainActivity.class);
        }
    }

    public static final class BottomNavigation extends SupportAppScreen {
        public BottomNavigation() {
            super("bna screen");
        }

        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, BottomNavigationActivity.class);
        }
    }

    public static final class Profile extends SupportAppScreen {
        public Profile() {
            super("profile screen");
        }

        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, ProfileActivity.class);
        }
    }

    public static final class ProfileInfo extends SupportAppScreen {
        public ProfileInfo() {
            super("profile info screen");
        }

        @Override
        public Fragment getFragment() {
            return new ProfileFragment();
        }
    }

    public static final class Android extends SupportAppScreen {
        public Android() {
            super("android screen");
        }
    }

    public static final class Bug extends SupportAppScreen {
        public Bug() {
            super("bug screen");
        }
    }

    public static final class Dog extends SupportAppScreen {
        public Dog() {
            super("dog screen");
        }
    }

    public static final class Forward extends SupportAppScreen {
        private String name;
        private int number;

        public Forward(String name, int number) {
            super("forward screen");
            this.name = name;
            this.number = number;
        }

        @Override
        public Fragment getFragment() {
            return ForwardFragment.getNewInstance(name, number);
        }
    }

    public static final class Github extends SupportAppScreen {
        public Github() {
            super("github screen");
        }

        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/terrakok/Cicerone"));
        }
    }

    public static final class SelectPhoto extends SupportAppScreen {
        private int resultCode;
        public SelectPhoto(int resultCode) {
            super("select photo screen");
            this.resultCode = resultCode;
        }

        @Override
        public Fragment getFragment() {
            return SelectPhotoFragment.getNewInstance(resultCode);
        }
    }
}
