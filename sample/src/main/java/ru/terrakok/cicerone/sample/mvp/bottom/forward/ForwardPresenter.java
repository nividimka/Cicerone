package ru.terrakok.cicerone.sample.mvp.bottom.forward;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.sample.Screens;

/**
 * Created by terrakok 26.11.16
 */

@InjectViewState
public class ForwardPresenter extends MvpPresenter<ForwardView> {
    private Router router;
    private int number;
    private String containerName;

    public ForwardPresenter(Router router, int number, String containerName) {
        this.router = router;
        this.number = number;
        this.containerName = containerName;

        getViewState().setChainText(createChain(number));
    }

    private String createChain(int number) {
        String chain = "[0]";

        for (int i = 0; i < number; i++) {
            chain += "➔" + (i + 1);
        }

        return chain;
    }

    public void onForwardPressed() {
        router.navigateTo(new Screens.Forward(containerName, number + 1));
    }

    public void onGithubPressed() {
        router.navigateTo(new Screens.Github());
    }

    public void onBackPressed() {
        router.exit();
    }
}
