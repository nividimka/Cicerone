/*
 * Created by Konstantin Tskhovrebov (aka @terrakok)
 */

package ru.terrakok.cicerone;

import java.util.HashMap;

import ru.terrakok.cicerone.result.ResultListener;

/**
 * Router is the class for high-level navigation.
 * Use it to perform needed transitions.<br>
 * This implementation covers almost all cases needed for the average app.
 * Extend it if you need some tricky navigation.
 */
public class Router extends BaseRouter {

    private HashMap<Integer, ResultListener> resultListeners = new HashMap<>();

    public Router() {
        super();
    }

    /**
     * Subscribe to the screen result.<br>
     * <b>Note:</b> only one listener can subscribe to a unique resultCode!<br>
     * You must call a <b>removeResultListener()</b> to avoid a memory leak.
     *
     * @param resultCode key for filter results
     * @param listener   result listener
     */
    public void setResultListener(Integer resultCode, ResultListener listener) {
        resultListeners.put(resultCode, listener);
    }

    /**
     * Unsubscribe from the screen result.
     *
     * @param resultCode key for filter results
     */
    public void removeResultListener(Integer resultCode) {
        resultListeners.remove(resultCode);
    }

    /**
     * Send result data to subscriber.
     *
     * @param resultCode result data key
     * @param result     result data
     * @return TRUE if listener was notified and FALSE otherwise
     */
    protected boolean sendResult(Integer resultCode, Object result) {
        ResultListener resultListener = resultListeners.get(resultCode);
        if (resultListener != null) {
            resultListener.onResult(result);
            return true;
        }
        return false;
    }

    /**
     * Open new screen and add it to the screens chain.
     *
     * @param screen screen
     */
    public void navigateTo(Screen screen) {
        executeCommands(Command.forward(screen));
    }

    /**
     * Clear the current screens chain and start new one
     * by opening a new screen right after the root.
     *
     * @param screen screen
     */
    public void newScreenChain(Screen screen) {
        executeCommands(
                Command.backTo(null),
                Command.forward(screen)
        );
    }

    /**
     * Clear all screens and open new one as root.
     *
     * @param screen screen
     */
    public void newRootScreen(Screen screen) {
        executeCommands(
                Command.backTo(null),
                Command.replace(screen)
        );
    }

    /**
     * Replace current screen.
     * By replacing the screen, you alters the backstack,
     * so by going back you will return to the previous screen
     * and not to the replaced one.
     *
     * @param screen screen
     */
    public void replaceScreen(Screen screen) {
        executeCommands(Command.replace(screen));
    }

    /**
     * Return back to the needed screen from the chain.
     * Behavior in the case when no needed screens found depends on
     * the processing of the "back_to" command in a {@link Navigator} implementation.
     *
     * @param screen screen
     */
    public void backTo(Screen screen) {
        executeCommands(Command.backTo(screen));
    }

    /**
     * Remove all screens from the chain and exit.
     * It's mostly used to finish the application or close a supplementary navigation chain.
     */
    public void finishChain() {
        executeCommands(
                Command.backTo(null),
                Command.back()
        );
    }

    /**
     * Return to the previous screen in the chain.
     * Behavior in the case when the current screen is the root depends on
     * the processing of the "back" command in a {@link Navigator} implementation.
     */
    public void exit() {
        executeCommands(Command.back());
    }

    /**
     * Return to the previous screen in the chain and send result data.
     *
     * @param resultCode result data key
     * @param result     result data
     */
    public void exitWithResult(Integer resultCode, Object result) {
        exit();
        sendResult(resultCode, result);
    }
}
