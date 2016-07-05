package moj.marvel.ui.detail;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import moj.marvel.model.Comic;

public interface DetailView {

    void init(View view);
    void createMenu(Menu menu, MenuInflater inflater);
    void setComic(Comic comic);
}
