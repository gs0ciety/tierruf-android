package com.gs0ciety.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.GridView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gs0ciety.adapter.ImageAdapter;
import com.gs0ciety.fragment.CustomDialogFragment;
import com.gs0ciety.model.AnimalItem;

import java.util.LinkedList;
import java.util.List;

public class PanelButtonActivity extends BaseActivity {

    private List<AnimalItem> animalItemList;

    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_button);

        final GridView gridView = findViewById(R.id.grid_animal_buttons);
        animalItemList = new LinkedList<>();

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

    public void showDialog() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final CustomDialogFragment newFragment = CustomDialogFragment.createCustomDialogFragment(animalItemList);

        if (true) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                    .addToBackStack(null).commit();
        }
    }
}