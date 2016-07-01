package moj.marvel.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import moj.marvel.MarvelApplication;
import moj.marvel.R;
import moj.marvel.injection.modules.MarvelModule;
import moj.marvel.model.Comic;
import moj.marvel.ui.MarvelView;

public class MarvelActivity extends AppCompatActivity implements MarvelController {

    @Inject
    MarvelView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel);

        initComponent();

        mView.init(findViewById(android.R.id.content));

        mView.showComicsList(createDummyComicList());
    }

    private void initComponent() {
        MarvelApplication
                .getApp()
                .getComponent()
                .plus(new MarvelModule(this))
                .inject(this);
    }

    private List<Comic> createDummyComicList() {
        List<Comic> list = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Comic comic = new Comic();
            comic.setTitle("Object " + i);
            list.add(comic);
        }
        return list;
    }
}