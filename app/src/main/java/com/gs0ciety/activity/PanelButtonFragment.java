package com.gs0ciety.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gs0ciety.adapter.ImageAdapter;
import com.gs0ciety.model.AnimalItem;

import java.util.LinkedList;
import java.util.List;

public class PanelButtonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final @NonNull LayoutInflater inflater,
                             final @Nullable ViewGroup container,
                             final @Nullable Bundle savedInstanceState) {
        final List<AnimalItem> animalItemList = new LinkedList<>();
        final GridView gridView = (GridView) inflater.inflate(R.layout.fragment_panel_button, container, false);

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final TypedArray animalSounds = getResources().obtainTypedArray(R.array.animal_sounds_drawables);
        for (int i = 0; i < animalImages.length() - 1  ; i++) {
            // get resource ID by index
            animalItemList.add(new AnimalItem("", animalImages.getResourceId(i, -1), animalSounds.getResourceId(i, -1)));
        }
        // recycle the arrays
        animalImages.recycle();
        animalSounds.recycle();

        gridView.setAdapter(new ImageAdapter(getContext(), animalItemList));
        return gridView;
    }
}