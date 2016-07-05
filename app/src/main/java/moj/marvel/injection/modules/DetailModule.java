package moj.marvel.injection.modules;


import dagger.Module;
import dagger.Provides;
import moj.marvel.controllers.detail.DetailActivity;
import moj.marvel.controllers.detail.DetailController;
import moj.marvel.injection.qualifers.ForActivity;
import moj.marvel.injection.scopes.PerActivity;
import moj.marvel.ui.detail.DetailView;
import moj.marvel.ui.detail.DetailViewImpl;

@Module
public class DetailModule {

    private final DetailActivity mActivity;

    public DetailModule(DetailActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    DetailController providesDetailController() {
        return mActivity;
    }

    @Provides
    @PerActivity
    DetailView providesDetailView(DetailViewImpl detailView) {
        return detailView;
    }
}
