package moj.marvel.network;


import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import moj.marvel.model.ComicsWrapper;
import retrofit2.Call;
import retrofit2.Retrofit;

public class NetworkHelperImpl implements NetworkHelper {

    // Test URL: http://gateway.marvel.com/v1/public/comics?ts=1&apikey=54306733de0f5cd1418aa05a85fa062a&hash=359e14db6b6a7bed5c31d81b2c00f36b
    // TODO: Ideally should be in build config.
    private ApiService mMarvelService;
    private static final long TIME_STAMP = 1; //TODO: generate a timestamp
    private static final String PRIVATE_KEY = "5de1fabcda2ea08912bd8b09bca4321f50563655";
    private static final String PUBLIC_KEY = "54306733de0f5cd1418aa05a85fa062a";
    private static final int LIMIT = 100;

    @Inject
    public NetworkHelperImpl(Retrofit marvelApi) {
        mMarvelService = marvelApi.create(ApiService.class);
    }

    public static String calculateMd5(String s)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")),0,s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Call<ComicsWrapper> comics() {
        return mMarvelService.getComics(
                TIME_STAMP,
                PUBLIC_KEY,
                calculateMd5(TIME_STAMP + PRIVATE_KEY + PUBLIC_KEY),
                100);
    }
}