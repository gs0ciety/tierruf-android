package com.gs0ciety.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.GridView;

import com.gs0ciety.adapter.ImageAdapter;
import com.gs0ciety.model.AnimalItem;

import java.util.LinkedList;
import java.util.List;

public class PanelButtonActivity extends BaseActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_button);

        final GridView gridView = findViewById(R.id.grid_animal_buttons);
        final List<AnimalItem> animalItemList = new LinkedList<>();

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final TypedArray animalSounds = getResources().obtainTypedArray(R.array.animal_sounds_drawables);
        for (int i = 0; i < animalImages.length() - 1  ; i++) {
            // get resource ID by index
            animalItemList.add(new AnimalItem("", animalImages.getResourceId(i, -1), animalSounds.getResourceId(i, -1)));
        }

        // recycle the arrays
        animalImages.recycle();
        animalSounds.recycle();

        gridView.setAdapter(new ImageAdapter(this, animalItemList));
    }
}