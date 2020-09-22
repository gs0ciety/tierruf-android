package com.gs0ciety.tierruf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.gs0ciety.tierruf.R;
import com.gs0ciety.tierruf.interfaces.ButtonPanelBehaviors;
import com.gs0ciety.tierruf.model.AnimalItem;

import java.util.List;

public class ButtonPanelAdapter extends RecyclerView.Adapter<ButtonPanelAdapter.ViewHolder> {

    private Context context;
    private List<AnimalItem> animalItemList;
    private ButtonPanelBehaviors buttonPanelBehaviors;

    public ButtonPanelAdapter(final Context context,
                              final List<AnimalItem> animalItemLists,
                              final ButtonPanelBehaviors buttonPanelBehaviors) {
        this.context = context;
        this.animalItemList = animalItemLists;
        this.buttonPanelBehaviors = buttonPanelBehaviors;
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
        viewHolder.animalImage.setImageResource(animalItemList.get(i).getImageResId());
        viewHolder.animalName.setText(animalItemList.get(i).getName());
        viewHolder.animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                    buttonPanelBehaviors.playSound(animalItemList.get(i).getAudioResId(), viewHolder.lottieAnimationView);
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
        final LottieAnimationView lottieAnimationView;
        final TextView animalName;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            animalImage = itemView.findViewById(R.id.grid_item_animal_picture);
            animalName = itemView.findViewById(R.id.grid_item_animal_name);
            lottieAnimationView = itemView.findViewById(R.id.grid_item_animal_animation_sound);
        }
    }
}