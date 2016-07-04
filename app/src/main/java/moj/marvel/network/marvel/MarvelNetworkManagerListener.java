package moj.marvel.network.marvel;


import moj.marvel.model.ComicsWrapper;

public interface MarvelNetworkManagerListener {

        void onRequestCompleted(ComicsWrapper wrapper);

        void onRequestFailed(Throwable t);
}
