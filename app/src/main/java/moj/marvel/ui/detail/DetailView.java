package moj.marvel.ui.detail;


import android.view.View;

import moj.marvel.model.Comic;

public interface DetailView {

    void init(View view);
    void setComic(Comic comic);
}
