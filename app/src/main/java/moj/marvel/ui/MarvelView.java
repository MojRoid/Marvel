package moj.marvel.ui;

import android.view.View;

import java.util.List;

import moj.marvel.model.Comic;

public interface MarvelView {

    void init(View view);

    void showComicsList(List<Comic> OperationsList);
}