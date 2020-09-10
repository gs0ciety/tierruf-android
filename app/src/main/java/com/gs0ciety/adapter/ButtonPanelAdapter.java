package com.gs0ciety.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.activity.R;
import com.gs0ciety.model.AnimalItem;

import java.util.List;

public class ButtonPanelAdapter extends RecyclerView.Adapter<ButtonPanelAdapter.ViewHolder> {

    private Context context;
    private List<AnimalItem> animalItemList;

    public ButtonPanelAdapter(final Context context, final List<AnimalItem> animalItemLists) {
        this.context = context;
        this.animalItemList = animalItemLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_animal, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        // set image based on selected text
        viewHolder.animalImage.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), animalItemList.get(i).getImageResId(), null));
        viewHolder.animalName.setText(animalItemList.get(i).getName());
        viewHolder.animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(context, animalItemList.get(i).getAudioResId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(final MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return animalItemList != null ? animalItemList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView animalImage;
        final TextView animalName;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            animalImage = itemView.findViewById(R.id.grid_item_animal_picture);
            animalName = itemView.findViewById(R.id.grid_item_animal_name);
        }
    }
}