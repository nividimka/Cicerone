package ru.terrakok.cicerone.sample.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Command;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppScreen;
import ru.terrakok.cicerone.sample.R;
import ru.terrakok.cicerone.sample.SampleApplication;
import ru.terrakok.cicerone.sample.mvp.start.StartActivityPresenter;
import ru.terrakok.cicerone.sample.mvp.start.StartActivityView;

/**
 * Created by terrakok 21.11.16
 */
public class StartActivity extends MvpAppCompatActivity implements StartActivityView {
    @Inject
    Router router;

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    StartActivityPresenter presenter;

    @ProvidePresenter
    public StartActivityPresenter createStartActivityPresenter() {
        return new StartActivityPresenter(router);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SampleApplication.INSTANCE.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.ordinary_nav_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOrdinaryPressed();
            }
        });
        findViewById(R.id.multi_nav_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onMultiPressed();
            }
        });
        findViewById(R.id.result_and_anim_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onResultWithAnimationPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    //Sample fully custom navigator:
    private Navigator navigator = new Navigator() {

        @Override
        public void applyCommands(Command[] commands) {
            for (Command command : commands) applyCommand(command);
        }

        private void applyCommand(Command command) {
            switch (command.getType()) {
                case Command.FORWARD:
                    forward(command);
                    break;
                case Command.REPLACE:
                    replace(command);
                    break;
                case Command.BACK:
                    back();
                    break;
                default:
                    Log.e("Cicerone", "Illegal command for this screen: " + command.getClass().getSimpleName());
                    break;
            }
        }

        private void forward(Command command) {
            SupportAppScreen screen = (SupportAppScreen) command.getScreen();
            Intent intent = screen.getActivityIntent(StartActivity.this);
            if (intent != null) {
                startActivity(intent);
            } else {
                Log.e("Cicerone", "Unknown screen: " + screen.getClass().getSimpleName());
            }
        }

        private void replace(Command command) {
            SupportAppScreen screen = (SupportAppScreen) command.getScreen();
            Intent intent = screen.getActivityIntent(StartActivity.this);
            if (intent != null) {
                startActivity(intent);
                finish();
            } else {
                Log.e("Cicerone", "Unknown screen: " + screen.getClass().getSimpleName());
            }
        }

        private void back() {
            finish();
        }
    };
}
