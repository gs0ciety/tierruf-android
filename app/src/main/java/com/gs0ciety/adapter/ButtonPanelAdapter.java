package com.gs0ciety.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

//      final AnimalItem itemPicture = animalItemList.get(getItemCount() - i - 1);

        ResourcesCompat.getDrawable(context.getResources(), animalItemList.get(i).getImageResId(), null);

        // set image based on selected text
        viewHolder.animalImage.setImageDrawable(context.getResources().getDrawable(animalItemList.get(i).getImageResId()));

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

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            //USER IMAGE TO BE SHOWN
            animalImage = itemView.findViewById(R.id.grid_item_animal_picture);
            //SELECTED OVERLAY
        }
    }
}