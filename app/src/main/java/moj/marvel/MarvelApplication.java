package moj.marvel;

import android.app.Application;

import moj.marvel.injection.components.ApplicationComponent;
import moj.marvel.injection.components.DaggerApplicationComponent;
import moj.marvel.injection.modules.ApplicationModule;

public class
MarvelApplication extends Application {

    private static MarvelApplication sApp;

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;

        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mComponent.inject(this);
    }

    public static MarvelApplication getApp() {
        return sApp;
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
