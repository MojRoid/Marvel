package moj.marvel.injection.components;

import dagger.Subcomponent;
import moj.marvel.controllers.marvel.MarvelActivity;
import moj.marvel.injection.modules.MarvelModule;
import moj.marvel.injection.scopes.PerActivity;

@PerActivity
@Subcomponent(modules = {MarvelModule.class})
public interface MarvelComponent {

    void inject(MarvelActivity activity);
}
