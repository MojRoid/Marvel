package moj.marvel.network.marvel;


public interface MarvelNetworkManager {

    void requestComics();
    void setListener(MarvelNetworkManagerListener marvelNetworkManagerListener);
    void cancel();
}
