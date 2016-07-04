package moj.marvel.network;

import moj.marvel.model.ComicsWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/public/comics")
    Call<ComicsWrapper> getComics(@Query("ts") long timestamp, @Query("apikey") String apikey, @Query("hash") String hash);
}