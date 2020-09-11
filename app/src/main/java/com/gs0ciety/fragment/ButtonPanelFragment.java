package com.gs0ciety.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.gs0ciety.activity.R;
import com.gs0ciety.adapter.ButtonPanelAdapter;
import com.gs0ciety.adapter.LanguageListAdapter;
import com.gs0ciety.interfaces.ButtonPanelBehaviours;
import com.gs0ciety.listeners.OnSwipeTouchListener;
import com.gs0ciety.model.AnimalItem;
import com.gs0ciety.model.LanguageItem;

import java.util.LinkedList;
import java.util.List;

public class ButtonPanelFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(final @NonNull LayoutInflater inflater,
                             final @Nullable ViewGroup container,
                             final @Nullable Bundle savedInstanceState) {
        final List<AnimalItem> animalItemList = new LinkedList<>();
        final RecyclerView gridRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_panel_button, container, false);

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final TypedArray animalSounds = getResources().obtainTypedArray(R.array.animal_sounds);
        final TypedArray animalNames = getResources().obtainTypedArray(R.array.animal_names);
        for (int i = 0; i < animalImages.length() - 1  ; i++) {
            // get resource ID by index
            animalItemList.add(new AnimalItem(animalNames.getString(i), animalImages.getResourceId(i, -1), animalSounds.getResourceId(i, -1)));
        }
        // recycle the arrays
        animalImages.recycle();
        animalNames.recycle();
        animalSounds.recycle();

        final GridLayoutManager folderLayoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
        gridRecycler.setLayoutManager(folderLayoutManager);
        gridRecycler.setAdapter(new ButtonPanelAdapter(getContext(), animalItemList, createButtonPanelBehaviours()));

        gridRecycler.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeRight() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_select_language, null);
                final List<LanguageItem> languageItems = new LinkedList<>();
                final TypedArray languages = getResources().obtainTypedArray(R.array.languages);
                final TypedArray countryFlags = getResources().obtainTypedArray(R.array.country_flags);
                for (int i = 0; i < languages.length() ; i++) {
                    // get resource ID by index
                    languageItems.add(new LanguageItem(languages.getString(i), countryFlags.getResourceId(i, -1)));
                }
                languages.recycle();
                countryFlags.recycle();
                final RecyclerView recyclerLanguages = dialogLayout.findViewById(R.id.recycler_languages);
                final LinearLayoutManager folderLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerLanguages.setLayoutManager(folderLayoutManager);
                recyclerLanguages.setAdapter(new LanguageListAdapter(getContext(), languageItems));
                builder.setView(dialogLayout);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
            }
        });
        return gridRecycler;
    }

    private ButtonPanelBehaviours createButtonPanelBehaviours() {
        return new ButtonPanelBehaviours() {
            @Override
            public void playSound(final @IntegerRes int audioResId, final LottieAnimationView lottieAnimationView) {
                stopActiveSound();
                mediaPlayer = MediaPlayer.create(getContext(), audioResId);
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.setMinAndMaxProgress(0.2f, 0.4f);
                lottieAnimationView.playAnimation();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(final MediaPlayer mp) {
                        mp.release();
                        lottieAnimationView.cancelAnimation();
                        lottieAnimationView.setVisibility(View.INVISIBLE);
                    }
                });
            }
        };
    }

    @Override
    public void onDestroy() {
        stopActiveSound();
        super.onDestroy();
    }

    private void stopActiveSound() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}