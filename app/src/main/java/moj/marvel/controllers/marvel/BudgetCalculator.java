package moj.marvel.controllers.marvel;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import moj.marvel.model.Comic;

public class BudgetCalculator {

    public List<Comic> calculateBudget(List<Comic> comics, double budget) {
        Sol optimal = new Sol();
        Sol current = new Sol();

        calculate(comics, budget, optimal, current, 0);

        return optimal.comics;
    }


    private void calculate(List<Comic> comics, double budget, Sol optimal, Sol current, int index) {
        if (budget < 0) {
            checkBestSolution(optimal, current);
            //Log.d("Comic problem", "calculate: no budget");
            return;
        }

        if (index == comics.size()) {
            checkBestSolution(optimal, current);
            //Log.d("Comic problem", "calculate: final comic");
            return;
        }

        if (comics.get(index).getPrice() <= budget) {
            current.addComic(comics.get(index));
            double remainingBudget = budget - comics.get(index).getPrice();
            calculate(comics, remainingBudget, optimal, current, index + 1);
            current.removeComic(comics.get(index));
            //TODO: calculate(comics, budget, optimal, current, index + 1); // Problematic call
        } else {
            calculate(comics, budget, optimal, current, index + 1);
        }
    }

    private void checkBestSolution(Sol optimal, Sol current) {
        if (optimal.pages < current.pages && optimal.comicsCount < current.comicsCount) {
            optimal.comicsCount = current.comicsCount;
            optimal.pages = current.pages;
            List<Comic> comicOptimal = new ArrayList<>();
            comicOptimal.addAll(current.comics);
            optimal.comics = comicOptimal;

            Log.i("Number of comics: ", String.valueOf(optimal.comicsCount));
            Log.i("Number of pages : ", String.valueOf(optimal.pages));
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