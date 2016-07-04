package moj.marvel.network;


import moj.marvel.model.ComicsWrapper;
import retrofit2.Call;

public interface NetworkHelper {
    Call<ComicsWrapper> comics();
}
