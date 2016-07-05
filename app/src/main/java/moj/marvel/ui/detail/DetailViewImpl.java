package moj.marvel.ui.detail;


import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import moj.marvel.controllers.detail.DetailController;
import moj.marvel.model.Comic;

public class DetailViewImpl implements DetailView {

    private final DetailController mController;
    private Comic mComic;

    @Inject
    public DetailViewImpl(DetailController controller) {
        mController = controller;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setComic(Comic comic) {
        mComic = comic;
        populateDetailView();
    }

    private void populateDetailView(){
        Log.d("Comic Title", mComic.getTitle());
        //TODO: populate view and style layout
    }
}
