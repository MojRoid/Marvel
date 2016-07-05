package moj.marvel.ui.marvel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import moj.marvel.R;
import moj.marvel.controllers.marvel.MarvelController;
import moj.marvel.model.Comic;

public class ComicAdapter extends RecyclerView.Adapter<ComicViewHolder> {

    private final MarvelController mController;
    private int mCount;
    private List<Comic> mDataset;

    @Inject
    public ComicAdapter(List<Comic> myDataset, MarvelController controller) {
        mDataset = myDataset;
        mController = controller;
    }

    @Override
    public int getItemCount() {
        return mCount; // because we do not need to keep using mDataset.length as the size is fixed.
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_view, parent, false);
        final ComicViewHolder vh = new ComicViewHolder(v);

        /*
        vh.mViewHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vh.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    //TODO interact with controller
                }
            }
        });
        */

        return vh;
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());

        if (mDataset.get(position).getDescription() == null) {
            holder.description.setVisibility(View.GONE);
        } else {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(mDataset.get(position).getDescription());
        }

        if (mDataset.get(position).getImageUrl() == null) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(holder.image.getContext()).load(mDataset.get(position).getImageUrl()).into(holder.image);
        }

    }

    public void setListData(List<Comic> data) {
        mDataset = data;
        mCount = data.size();
        notifyDataSetChanged();
    }
}