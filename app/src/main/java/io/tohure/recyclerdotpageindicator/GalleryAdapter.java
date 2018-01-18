package io.tohure.recyclerdotpageindicator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 18/01/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryItemViewHolder> {

    private List<String> listImages;

    public GalleryAdapter() {
        this.listImages = new ArrayList<>();
    }

    public void addData(List<String> listImages) {
        this.listImages.addAll(listImages);
        notifyDataSetChanged();
    }

    @Override
    public GalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        return new GalleryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryItemViewHolder holder, int position) {
        Picasso.with(holder.imgPage.getContext())
                .load(listImages.get(position))
                .fit().centerCrop()
                .error(android.R.drawable.ic_dialog_alert)
                .into(holder.imgPage);
    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    class GalleryItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgPage;

        GalleryItemViewHolder(View itemView) {
            super(itemView);
            imgPage = itemView.findViewById(R.id.imgPage);
        }
    }
}
