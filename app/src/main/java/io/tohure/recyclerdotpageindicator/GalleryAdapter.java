package io.tohure.recyclerdotpageindicator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tohure on 18/01/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryItemViewHolder> {
    @Override
    public GalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


    }

    @Override
    public void onBindViewHolder(GalleryItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GalleryItemViewHolder extends RecyclerView.ViewHolder {
        public GalleryItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
