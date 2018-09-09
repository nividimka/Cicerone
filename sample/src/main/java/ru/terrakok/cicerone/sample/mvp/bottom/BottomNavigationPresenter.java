package ru.terrakok.cicerone.sample.mvp.bottom;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.sample.Screens;

/**
 * Created by terrakok 25.11.16
 */
@InjectViewState
public class BottomNavigationPresenter extends MvpPresenter<BottomNavigationView> {
    private Router router;

    public BottomNavigationPresenter(Router router) {
        this.router = router;
    }

    public void onTabAndroidClick() {
        getViewState().highlightTab(BottomNavigationView.ANDROID_TAB_POSITION);
        router.replaceScreen(new Screens.Android());
    }

    public void onTabBugClick() {
        getViewState().highlightTab(BottomNavigationView.BUG_TAB_POSITION);
        router.replaceScreen(new Screens.Bug());
    }

    public void onTabDogClick() {
        getViewState().highlightTab(BottomNavigationView.DOG_TAB_POSITION);
        router.replaceScreen(new Screens.Dog());
    }

    public void onBackPressed() {
        router.exit();
    }
}
