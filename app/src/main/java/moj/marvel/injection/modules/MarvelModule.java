package moj.marvel.injection.modules;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import moj.marvel.controllers.marvel.MarvelActivity;
import moj.marvel.controllers.marvel.MarvelController;
import moj.marvel.injection.qualifers.ForActivity;
import moj.marvel.injection.scopes.PerActivity;
import moj.marvel.network.marvel.MarvelNetworkManager;
import moj.marvel.network.marvel.MarvelNetworkManagerImpl;
import moj.marvel.ui.marvel.ComicAdapter;
import moj.marvel.ui.marvel.MarvelView;
import moj.marvel.ui.marvel.MarvelViewImpl;

@Module
public class MarvelModule {

    private final MarvelActivity mActivity;

    public MarvelModule(MarvelActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context providesMarvelActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MarvelController providesMarvelController() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MarvelView providesMarvelView(MarvelViewImpl view) {
        return view;
    }

    @Provides
    @PerActivity
    StaggeredGridLayoutManager providesLayoutManager(@ForActivity Context context) {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Provides
    @PerActivity
    ComicAdapter providesComicAdapter() {
        return new ComicAdapter(new ArrayList<>(), mActivity);
    }

    @Provides
    @PerActivity
    MarvelNetworkManager providesMarvelNetworkManager(MarvelNetworkManagerImpl operationsNetworkManager) {
        return operationsNetworkManager;
    }
}