package moj.marvel.controllers.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import moj.marvel.MarvelApplication;
import moj.marvel.R;
import moj.marvel.injection.modules.DetailModule;
import moj.marvel.ui.detail.DetailView;


public class DetailActivity extends AppCompatActivity implements DetailController {

    @Inject
    DetailView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initComponent();

        mView.init(findViewById(android.R.id.content));
        mView.setComic(getIntent().getExtras().getParcelable("comic"));
    }

    private void initComponent() {
        MarvelApplication
                .getApp()
                .getComponent()
                .plus(new DetailModule(this))
                .inject(this);
    }
}
