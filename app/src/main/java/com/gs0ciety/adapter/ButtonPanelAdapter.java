package com.gs0ciety.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.gs0ciety.activity.R;
import com.gs0ciety.interfaces.ButtonPanelBehaviours;
import com.gs0ciety.model.AnimalItem;

import java.util.List;

public class ButtonPanelAdapter extends RecyclerView.Adapter<ButtonPanelAdapter.ViewHolder> {

    private Context context;
    private List<AnimalItem> animalItemList;
    private ButtonPanelBehaviours buttonPanelBehaviours;

    public ButtonPanelAdapter(final Context context,
                              final List<AnimalItem> animalItemLists,
                              final ButtonPanelBehaviours buttonPanelBehaviours) {
        this.context = context;
        this.animalItemList = animalItemLists;
        this.buttonPanelBehaviours = buttonPanelBehaviours;
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
                    buttonPanelBehaviours.playSound(animalItemList.get(i).getAudioResId(), viewHolder.lottieAnimationView);
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