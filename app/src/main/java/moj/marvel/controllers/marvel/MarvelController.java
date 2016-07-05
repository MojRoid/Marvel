package moj.marvel.controllers.marvel;

import moj.marvel.model.Comic;

public interface MarvelController {
    void requestComics();
    void openDetail(Comic comic);
}
