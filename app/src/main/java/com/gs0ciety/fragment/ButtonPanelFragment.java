package com.gs0ciety.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.activity.R;
import com.gs0ciety.adapter.ButtonPanelAdapter;
import com.gs0ciety.model.AnimalItem;

import java.util.LinkedList;
import java.util.List;

public class ButtonPanelFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final @NonNull LayoutInflater inflater,
                             final @Nullable ViewGroup container,
                             final @Nullable Bundle savedInstanceState) {
        final List<AnimalItem> animalItemList = new LinkedList<>();
        final RecyclerView gridRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_panel_button, container, false);

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final TypedArray animalSounds = getResources().obtainTypedArray(R.array.animal_sounds_drawables);
        for (int i = 0; i < animalImages.length() - 1  ; i++) {
            // get resource ID by index
            animalItemList.add(new AnimalItem("", animalImages.getResourceId(i, -1), animalSounds.getResourceId(i, -1)));
        }
        // recycle the arrays
        animalImages.recycle();
        animalSounds.recycle();

        final GridLayoutManager folderLayoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
        gridRecycler.setLayoutManager(folderLayoutManager);
        gridRecycler.setAdapter(new ButtonPanelAdapter(getContext(), animalItemList));
        return gridRecycler;
    }
}