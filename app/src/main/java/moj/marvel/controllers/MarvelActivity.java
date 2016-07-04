package moj.marvel.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import moj.marvel.MarvelApplication;
import moj.marvel.R;
import moj.marvel.injection.modules.MarvelModule;
import moj.marvel.model.Comic;
import moj.marvel.model.ComicsWrapper;
import moj.marvel.network.marvel.MarvelNetworkManager;
import moj.marvel.network.marvel.MarvelNetworkManagerListener;
import moj.marvel.ui.MarvelView;

public class MarvelActivity extends AppCompatActivity implements MarvelController, MarvelNetworkManagerListener {

    @Inject
    MarvelView mView;

    @Inject
    MarvelNetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel);

        initComponent();

        mView.init(findViewById(android.R.id.content));
        mNetworkManager.setListener(this);
        mNetworkManager.requestComics();
        //mView.showComicsList(createDummyComicList());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNetworkManager.cancel();
    }

    private void initComponent() {
        MarvelApplication
                .getApp()
                .getComponent()
                .plus(new MarvelModule(this))
                .inject(this);
    }

    /*
    private List<Comic> createDummyComicList() {
        List<Comic> list = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Comic comic = new Comic();
            comic.setTitle("Object " + i);
            list.add(comic);
        }
        return list;
    }
    */

    @Override
    public void onRequestCompleted(ComicsWrapper wrapper) {
        mView.showComicsList(wrapper.getComicsList());
    }

    @Override
    public void onRequestFailed(Throwable t) {
        Log.e(getClass().getName(), "Error requesting comics", t);
    }
}