package moj.marvel.ui.marvel;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import moj.marvel.R;
import moj.marvel.model.Comic;

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


    public void bind(Comic comic){

        title.setText(comic.getTitle());

        if (comic.getDescription() == null) {
            description.setText(itemView.getResources().getString(R.string.no_description));
        } else {
            description.setText(comic.getDescription());
        }

        if (comic.getImageUrl() == null) {
            Picasso.with(image.getContext()).load(R.drawable.marvel_comics_small).into(image);
        } else {
            Picasso.with(image.getContext()).load(comic.getImageUrl()).into(image);
        }
    }
}