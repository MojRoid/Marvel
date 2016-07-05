package moj.marvel.controllers.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import moj.marvel.MarvelApplication;
import moj.marvel.R;
import moj.marvel.injection.modules.DetailModule;
import moj.marvel.model.Comic;
import moj.marvel.ui.detail.DetailView;


public class DetailActivity extends AppCompatActivity implements DetailController {

    @Inject
    DetailView mView;
    private Comic mComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initComponent();

        mView.init(findViewById(android.R.id.content));
        mComic = getIntent().getParcelableExtra("comic");
        mView.setComic(mComic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mView.createMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_buy_comic) {
            openComicUri();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openComicUri() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mComic.getBuyUrl()));
        startActivity(i);
    }

    private void initComponent() {
        MarvelApplication
                .getApp()
                .getComponent()
                .plus(new DetailModule(this))
                .inject(this);
    }
}
