package moj.marvel.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ComicsWrapper implements Parcelable {

    private List<Comic> comicsList;

    public ComicsWrapper() {
    }

    protected ComicsWrapper(Parcel in) {
        comicsList = in.createTypedArrayList(Comic.CREATOR);
    }

    public List<Comic> getComicsList() {
        return comicsList;
    }

    public void setComicsList(List<Comic> comicsList) {
        this.comicsList = comicsList;
    }

    public static final Creator<ComicsWrapper> CREATOR = new Creator<ComicsWrapper>() {
        @Override
        public ComicsWrapper createFromParcel(Parcel in) {
            return new ComicsWrapper(in);
        }

        @Override
        public ComicsWrapper[] newArray(int size) {
            return new ComicsWrapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(comicsList);
    }
}