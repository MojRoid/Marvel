package moj.marvel.controllers.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import moj.marvel.MarvelApplication;
import moj.marvel.R;
import moj.marvel.controllers.detail.DetailActivity;
import moj.marvel.injection.modules.MarvelModule;
import moj.marvel.model.Comic;
import moj.marvel.model.ComicsWrapper;
import moj.marvel.network.marvel.MarvelNetworkManager;
import moj.marvel.network.marvel.MarvelNetworkManagerListener;
import moj.marvel.ui.marvel.MarvelView;

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
        requestComics();
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

    @Override
    public void onRequestCompleted(ComicsWrapper wrapper) {
        mView.showComicsList(wrapper.getComicsList());
    }

    @Override
    public void onRequestFailed(Throwable t) {
        Log.e(getClass().getName(), "Error requesting comics", t);
    }

    @Override
    public void requestComics() {
        mNetworkManager.requestComics();
    }

    @Override
    public void openDetail(Comic comic) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("comic",comic);
        startActivity(intent);
    }
}