package moj.marvel.ui.marvel;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ViewSwitcher;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moj.marvel.R;
import moj.marvel.controllers.marvel.MarvelController;
import moj.marvel.model.Comic;

public class MarvelViewImpl implements MarvelView {

    private static final int DISPLAY_PROGRESS = 0;
    private static final int DISPLAY_CONTENT = 1;

    @BindView(R.id.comics_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.comics_view_switcher)
    ViewSwitcher mViewSwitcher;

    private final MarvelController mController;
    private ComicAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

    @Inject
    public MarvelViewImpl(MarvelController controller, ComicAdapter adapter, StaggeredGridLayoutManager layoutManager) {
        mController = controller;
        mAdapter = adapter;
        mLayoutManager = layoutManager;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showComicsList(List<Comic> list) {
        displayView(DISPLAY_CONTENT);
        mAdapter.setListData(list);
    }

    private void displayView(int child) {
        if (mViewSwitcher.getDisplayedChild() != child) {
            mViewSwitcher.setDisplayedChild(child);
        }
    }
}