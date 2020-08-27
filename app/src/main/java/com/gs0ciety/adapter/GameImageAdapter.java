package com.gs0ciety.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.activity.R;
import com.gs0ciety.model.AnimalItem;

import java.util.List;

public class GameImageAdapter extends RecyclerView.Adapter<GameImageAdapter.ViewHolder> {

    private List<AnimalItem> animalItems;
    private Context context;

    public GameImageAdapter(final Context context, final List<AnimalItem> animalItems) {
        this.animalItems = animalItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_animal, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final AnimalItem animalItem = animalItems.get(i);
        //getItemCount()-i-1 shows newer images first
        viewHolder.imageViewIcon.setImageDrawable(context.getResources().getDrawable(animalItem.getImageResId()));
    }

    @Override
    public int getItemCount() {
        return animalItems != null ? animalItems.size() : 0;
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewIcon;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.grid_item_animal_picture);
        }
    }
}
