package moj.marvel.controllers.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.common.StringUtil;

import javax.inject.Inject;

import moj.marvel.MarvelApplication;
import moj.marvel.R;
import moj.marvel.controllers.detail.DetailActivity;
import moj.marvel.injection.components.ApplicationComponent;
import moj.marvel.injection.components.MarvelComponent;
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

    private MarvelComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel);

        initComponent();
        mView.init(findViewById(android.R.id.content));

        mNetworkManager.setListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mView.createMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_budget) {
            mView.showBudgetAlert(getSupportFragmentManager());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNetworkManager.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestComics();
    }

    private void initComponent() {
        mComponent =
        MarvelApplication
                .getApp()
                .getComponent()
                .plus(new MarvelModule(this));

        mComponent
                .inject(this);
    }

    public MarvelComponent getComponent() {
        return mComponent;
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
        intent.putExtra("comic", comic);
        startActivity(intent);
    }

    public void onBudgetSet(double budget) {
        Log.d("Budget: ", String.valueOf(budget));
    }
}