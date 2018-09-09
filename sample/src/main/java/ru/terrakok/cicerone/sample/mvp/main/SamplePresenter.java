package ru.terrakok.cicerone.sample.mvp.main;

import android.os.Handler;
import android.os.Looper;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.sample.Screens;

/**
 * Created by Konstantin Tckhovrebov (aka @terrakok)
 * on 11.10.16
 */

@InjectViewState
public class SamplePresenter extends MvpPresenter<SampleView> {
    private Router router;
    private int screenNumber;
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> future;

    public SamplePresenter(Router router, int screenNumber) {
        this.router = router;
        this.screenNumber = screenNumber;
        executorService = Executors.newSingleThreadScheduledExecutor();

        getViewState().setTitle("Screen " + screenNumber);
    }

    public void onBackCommandClick() {
        router.exit();
    }

    public void onForwardCommandClick() {
        router.navigateTo(new Screens.Sample(screenNumber + 1));
    }

    public void onReplaceCommandClick() {
        router.replaceScreen(new Screens.Sample(screenNumber + 1));
    }

    public void onNewChainCommandClick() {
        router.newScreenChain(new Screens.Sample(screenNumber + 1));
    }

    public void onNewRootCommandClick() {
        router.newRootScreen(new Screens.Sample(screenNumber + 1));
    }

    public void onForwardWithDelayCommandClick() {
        if (future != null) future.cancel(true);
        future = executorService.schedule(new Runnable() {
            @Override
            public void run() {
                //WARNING! Navigation must be only in UI thread.
                new Handler(Looper.getMainLooper()).post(
                        new Runnable() {
                            @Override
                            public void run() {
                                router.navigateTo(new Screens.Sample(screenNumber + 1));
                            }
                        }
                );
            }
        }, 5, TimeUnit.SECONDS);
    }

    public void onBackToCommandClick() {
        router.backTo(new Screens.Sample(3));
    }
}
