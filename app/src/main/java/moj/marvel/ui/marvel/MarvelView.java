package moj.marvel.ui.marvel;

import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.List;

import moj.marvel.model.Comic;

public interface MarvelView {

    void init(View view);

    void createMenu(Menu menu, MenuInflater inflater);

    void showBudgetAlert(FragmentManager supportFragmentManager);

    void showComicsList(List<Comic> OperationsList);
}