package moj.marvel.injection.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import moj.marvel.controllers.MarvelActivity;
import moj.marvel.controllers.MarvelController;
import moj.marvel.injection.qualifers.ForActivity;
import moj.marvel.injection.scopes.PerActivity;
import moj.marvel.ui.ComicAdapter;
import moj.marvel.ui.MarvelView;
import moj.marvel.ui.MarvelViewImpl;

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
    LinearLayoutManager providesLinearLayoutManager(@ForActivity Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    @PerActivity
    ComicAdapter providesComicAdapter() {
        return new ComicAdapter(new ArrayList<>(), mActivity);
    }

    /*
    @Provides
    @PerActivity
    MarvelNetworkManager providesOperationsNetworkManager(MarvelNetworkManagerImpl operationsNetworkManager) {
        return operationsNetworkManager;
    }
    */
}