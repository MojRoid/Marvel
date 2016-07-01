package moj.marvel.injection.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import moj.marvel.MarvelApplication;
import moj.marvel.injection.qualifers.ForApplication;
import moj.marvel.injection.scopes.PerApplication;

@Module
public class ApplicationModule {

    private final MarvelApplication mApplication;

    public ApplicationModule(MarvelApplication application) {
        mApplication = application;
    }

    @Provides
    @PerApplication
    @ForApplication
    Context providesApplicationContext() {
        return mApplication;
    }
}
