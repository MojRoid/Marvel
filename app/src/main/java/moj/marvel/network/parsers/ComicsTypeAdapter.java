package moj.marvel.network.parsers;


import android.util.Log;
import android.widget.Switch;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import moj.marvel.model.Comic;
import moj.marvel.model.ComicsWrapper;

public class ComicsTypeAdapter extends TypeAdapter<ComicsWrapper> {


    private static final String DATA = "data";
    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String IMAGES = "images";
    private static final String IMAGE_PATH = "path";
    private static final String IMAGE_EXT = "extension";

    @Override
    public ComicsWrapper read(JsonReader in) throws IOException {
        ComicsWrapper comicsWrapper = new ComicsWrapper();
        List<Comic> comicList = new ArrayList<>();

        in.beginObject();

        while (in.hasNext()) {
            switch (in.nextName()) {
                case DATA:
                    in.beginObject();

                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case RESULTS:

                                in.beginArray();
                                while (in.hasNext()) {
                                    comicList.add(getComic(in));
                                }
                                in.endArray();
                                break;

                            default:
                                in.skipValue();
                                break;
                        }
                    }

                    in.endObject();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }

        in.endObject();

        comicsWrapper.setComicsList(comicList);
        return comicsWrapper;
    }

    @Override
    public void write(JsonWriter out, ComicsWrapper value) throws IOException {

    }

    private String readString(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return in.nextString();
    }


    public Comic getComic(JsonReader in) throws IOException {
        Comic comic = new Comic();
        in.beginObject();

        while (in.hasNext()) {
            switch (in.nextName()) {

                case TITLE:
                    comic.setTitle(readString(in));
                    break;

                case DESCRIPTION:
                    comic.setDescription(readString(in));
                    break;

                case IMAGES:
                    in.beginArray();
                    while(in.hasNext()) {
                        in.beginObject();
                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case IMAGE_PATH:
                                    comic.setImageUrl(readString(in));
                                    break;
                                case IMAGE_EXT:
                                    comic.setImageUrl(comic.getImageUrl() + "." + readString(in));
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                    }
                    in.endArray();
                    break;

                default:
                    in.skipValue();
                    break;
            }
        }

        //Log.d("Comic Title", comic.getTitle());
        in.endObject();
        return comic;
    }
}
