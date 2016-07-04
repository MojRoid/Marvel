package moj.marvel.network.marvel;


import android.util.Log;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import moj.marvel.controllers.MarvelController;
import moj.marvel.model.ComicsWrapper;
import moj.marvel.network.NetworkHelper;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;

public class MarvelNetworkManagerImpl implements MarvelNetworkManager {

    private NetworkHelper mNetworkHelper;
    private MarvelNetworkManagerListener mMarvelNetworkManagerListener;
    private Subscription mSubscription;

    @Inject
    public MarvelNetworkManagerImpl(NetworkHelper networkHelper) {
        this.mNetworkHelper = networkHelper;
    }

    @Override
    public void setListener(MarvelNetworkManagerListener marvelNetworkManagerListener) {
        mMarvelNetworkManagerListener = marvelNetworkManagerListener;
    }

    @Override
    public void requestComics() {
        mSubscription = buildRequestComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wrapper -> //TODO: check for response error codes?
                        mMarvelNetworkManagerListener.onRequestCompleted(wrapper), this::onError);
    }

    private Observable<ComicsWrapper> buildRequestComics() {
        return Observable.fromCallable(() -> {
            Response<ComicsWrapper> response = mNetworkHelper.comics().execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body();
            }
            throw Exceptions.propagate(new NullPointerException("Response is not successful or null"));
        });
    }

    private void onError(Throwable t){
        if (mMarvelNetworkManagerListener != null) {
            mMarvelNetworkManagerListener.onRequestFailed(t);
        }
    }

    @Override
    public void cancel() {
        mNetworkHelper.comics().cancel();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
