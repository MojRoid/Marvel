package moj.marvel.injection.components;

import android.app.Application;

import dagger.Component;
import moj.marvel.injection.modules.ApplicationModule;
import moj.marvel.injection.modules.MarvelModule;
import moj.marvel.injection.modules.NetworkModule;
import moj.marvel.injection.scopes.PerApplication;

@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(Application application);

    MarvelComponent plus(MarvelModule module);
}