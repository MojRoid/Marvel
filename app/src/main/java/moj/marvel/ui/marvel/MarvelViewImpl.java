package moj.marvel.ui.marvel;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
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

    @BindView(R.id.comics_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.comics_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.comics_view_switcher)
    ViewSwitcher mViewSwitcher;

    private final MarvelController mController;
    private ComicAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private BudgetDialogFragment mDialogFragment;

    @Inject
    public MarvelViewImpl(MarvelController controller, ComicAdapter adapter, StaggeredGridLayoutManager layoutManager,
                          BudgetDialogFragment dialogFragment) {
        mController = controller;
        mAdapter = adapter;
        mLayoutManager = layoutManager;
        mDialogFragment = dialogFragment;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);
        initRecyclerView();

        mSwipeRefresh.setOnRefreshListener(() -> {
            mController.requestComics();
        });
    }

    @Override
    public void createMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_marvel, menu);
    }

    @Override
    public void showBudgetAlert(FragmentManager supportFragmentManager) {
        mDialogFragment.show(supportFragmentManager, "");
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showComicsList(List<Comic> list) {
        mSwipeRefresh.setRefreshing(false);
        displayView(DISPLAY_CONTENT);
        mAdapter.setListData(list);
    }

    private void displayView(int child) {
        if (mViewSwitcher.getDisplayedChild() != child) {
            mViewSwitcher.setDisplayedChild(child);
        }
    }
}