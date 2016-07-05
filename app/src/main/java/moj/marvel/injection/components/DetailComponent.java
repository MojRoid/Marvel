package moj.marvel.injection.components;

import dagger.Subcomponent;
import moj.marvel.controllers.detail.DetailActivity;
import moj.marvel.injection.modules.DetailModule;
import moj.marvel.injection.scopes.PerActivity;


@PerActivity
@Subcomponent (modules = {DetailModule.class})
public interface DetailComponent {

    void inject (DetailActivity activity);
}
