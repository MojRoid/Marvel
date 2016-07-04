package moj.marvel.injection.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import moj.marvel.injection.scopes.PerApplication;
import moj.marvel.model.ComicsWrapper;
import moj.marvel.network.NetworkHelper;
import moj.marvel.network.NetworkHelperImpl;
import moj.marvel.network.parsers.ComicsTypeAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * All network provides. Module providing network related dependencies across the app.
 */

@Module
public class NetworkModule {
    // TODO Ideally should be in buildconfig.
    private final String BASE_URL = "http://gateway.marvel.com/";

    @Provides
    @PerApplication
    NetworkHelper providesNetworkHelper(NetworkHelperImpl networkHelper) {
        return networkHelper;
    }

    @Provides
    @PerApplication
    Retrofit providesMarvelApi(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    @Provides
    @PerApplication
    Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ComicsWrapper.class, new ComicsTypeAdapter().nullSafe()).create();
    }
}
