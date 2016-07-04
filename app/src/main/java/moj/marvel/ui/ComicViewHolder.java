package moj.marvel.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import moj.marvel.R;

public class ComicViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_comic_title)
    TextView title;

    public ComicViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}