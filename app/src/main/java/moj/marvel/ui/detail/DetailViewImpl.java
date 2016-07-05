package moj.marvel.ui.detail;


import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.stetho.common.StringUtil;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import moj.marvel.R;
import moj.marvel.controllers.detail.DetailController;
import moj.marvel.model.Comic;
import rx.exceptions.Exceptions;

public class DetailViewImpl implements DetailView {

    @BindView(R.id.detail_title)
    TextView mTitle;

    @BindView(R.id.detail_description)
    TextView mDescription;

    @BindView(R.id.detail_image)
    ImageView mImage;

    @BindView(R.id.detail_pagecount)
    TextView mPagecount;

    @BindView(R.id.detail_price)
    TextView mPrice;

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
    public void createMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public void setComic(Comic comic) {
        mComic = comic;
        populateDetailView();
    }

    private void populateDetailView() {
        //Log.d("Comic Title", mComic.getTitle());

        if (mComic == null) {
            return;
        }

        mTitle.setText(mComic.getTitle());
        mPagecount.setText(String.valueOf(mComic.getPageCount()) + " pages");
        mPrice.setText("£" + String.valueOf(mComic.getPrice()));

        if (mComic.getDescription() == null) {
            mDescription.setText(R.string.no_description);
        } else {
            mDescription.setText(mComic.getDescription());
        }

        if (mComic.getImageUrl() == null) {
            Picasso.with(mImage.getContext()).load(R.drawable.marvel_comics_small).into(mImage);
        } else {
            Picasso.with(mImage.getContext()).load(mComic.getImageUrl()).into(mImage);
        }
    }
}
