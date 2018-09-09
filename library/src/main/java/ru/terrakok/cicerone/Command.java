/*
 * Created by Konstantin Tskhovrebov (aka @terrakok)
 */

package ru.terrakok.cicerone;

/**
 * Navigation command describes screens transition.
 * that can be processed by {@link ru.terrakok.cicerone.Navigator}.
 */
public class Command {
    public static final int BACK = 0;
    public static final int BACK_TO = 1;
    public static final int FORWARD = 2;
    public static final int REPLACE = 3;

    private int type;
    private Screen screen;

    public Command(int type) {
        this(type, null);
    }

    public Command(int type, Screen screen) {
        this.type = type;
        this.screen = screen;
    }

    public int getType() {
        return type;
    }

    public Screen getScreen() {
        return screen;
    }

    public static Command back() {
        return new Command(BACK);
    }

    public static Command backTo(Screen screen) {
        return new Command(BACK_TO, screen);
    }

    public static Command forward(Screen screen) {
        return new Command(FORWARD, screen);
    }

    public static Command replace(Screen screen) {
        return new Command(REPLACE, screen);
    }
}
