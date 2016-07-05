package moj.marvel.controllers.marvel;


import java.util.ArrayList;
import java.util.List;

import moj.marvel.model.Comic;

public class BudgetCalculator {

    public List<Comic> removeExpensiveComics(List<Comic> comics, double budget) {
        Sol optimal = new Sol();
        Sol current = new Sol();

        calculcate(comics, budget, optimal, current, 0);

        return optimal.comics;
    }


    private void calculcate(List<Comic> comics, double budget, Sol optimal, Sol current, int index) {
        if (budget == 0) {
            checkBestSolution(optimal, current);
            return;
        }

        if (index == comics.size()) {
            checkBestSolution(optimal, current);
            return;
        }

        if (comics.get(index).getPrice() <= budget) {
            current.addComic(comics.get(index));
            double remainingBudget = budget - comics.get(index).getPrice();
            calculcate(comics, remainingBudget, optimal, current, index + 1);
            current.removeComic(comics.get(index));
            calculcate(comics, budget, optimal, current, index + 1);
        } else {
            calculcate(comics, budget, optimal, current, index + 1);
        }
    }

    private void checkBestSolution(Sol optimal, Sol current) {
        if (optimal.pages < current.pages && optimal.comicsCount < current.comicsCount) {
            optimal.comicsCount = current.comicsCount;
            optimal.pages = current.pages;
            List<Comic> comicOptimal = new ArrayList<>();
            comicOptimal.addAll(current.comics);
            optimal.comics = comicOptimal;
        }
    }

    public static class Sol {
        int pages;
        int comicsCount;
        List<Comic> comics = new ArrayList<>();

        public void addComic(Comic comic) {
            pages += comic.getPageCount();
            comicsCount++;
            comics.add(comic);
        }

        public void removeComic(Comic comic) {
            pages -= comic.getPageCount();
            comicsCount--;
            comics.remove(comic);
        }
    }
}