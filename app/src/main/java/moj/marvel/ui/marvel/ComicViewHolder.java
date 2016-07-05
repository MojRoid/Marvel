package moj.marvel.ui.marvel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import moj.marvel.R;

public class ComicViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_comic_title)
    TextView title;

    @BindView(R.id.txt_comic_description)
    TextView description;

    @BindView(R.id.img_comic_image)
    ImageView image;

    public ComicViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}