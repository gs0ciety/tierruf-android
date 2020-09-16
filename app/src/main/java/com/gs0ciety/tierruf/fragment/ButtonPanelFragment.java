package com.gs0ciety.tierruf.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.gs0ciety.tierruf.R;
import com.gs0ciety.tierruf.adapter.ButtonPanelAdapter;
import com.gs0ciety.tierruf.adapter.LanguageListAdapter;
import com.gs0ciety.tierruf.interfaces.ButtonPanelBehaviors;
import com.gs0ciety.tierruf.interfaces.MainActivityBehavior;
import com.gs0ciety.tierruf.listeners.OnSwipeTouchListener;
import com.gs0ciety.tierruf.model.AnimalItem;
import com.gs0ciety.tierruf.model.LanguageItem;
import com.gs0ciety.utils.PreferenceUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ButtonPanelFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private LottieAnimationView currentAnimation;
    private MainActivityBehavior mainActivityBehavior;

    private AlertDialog languageSelectionDialog;

    public ButtonPanelFragment (final MainActivityBehavior mainActivityBehavior) {
        this.mainActivityBehavior = mainActivityBehavior;
    }

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
        gridRecycler.setAdapter(new ButtonPanelAdapter(getContext(), animalItemList, initButtonPanelFragmentBehavior()));

        if (PreferenceUtils.shouldDisplayMainTutorial(getContext())) {
            PreferenceUtils.showMainTutorial(false, getContext());
            showTutorial();
        }

        gridRecycler.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeRight() {
                showLanguageSelection();
            }

            @Override
            public void onSwipeLeft() {
                //DO NOTHING
            }
        });
        return gridRecycler;
    }

    @Override
    public void onDestroy() {
        stopActiveSoundAndAnimation();
        super.onDestroy();
    }

    private void stopActiveSoundAndAnimation() {
        if (currentAnimation != null) {
            currentAnimation.cancelAnimation();
            currentAnimation.setVisibility(View.INVISIBLE);
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void showTutorial() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialog_Theme);
        final LayoutInflater inflater = getLayoutInflater();
        final View tutorialDialogLayout = inflater.inflate(R.layout.dialog_tutorial, null);
        builder.setCancelable(false).setCancelable(false);
        builder.setView(tutorialDialogLayout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                // Do nothing!
            }
        });
        final AlertDialog tutorialAlertDialog = builder.create();
        final ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);
        colorDrawable.setAlpha(170);
        tutorialAlertDialog.getWindow().setBackgroundDrawable(colorDrawable);
        tutorialAlertDialog.show();

        tutorialDialogLayout.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeRight() {
                showLanguageSelection();
                tutorialAlertDialog.dismiss();
            }

            @Override
            public void onSwipeLeft() {
                // Do nothing!
            }
        });
    }

    private void showLanguageSelection() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_select_language, null);
        final List<LanguageItem> languageItems = new LinkedList<>();
        final TypedArray languages = getResources().obtainTypedArray(R.array.languages);
        final TypedArray languagesShort = getResources().obtainTypedArray(R.array.languages_short);
        final TypedArray countryFlags = getResources().obtainTypedArray(R.array.country_flags);
        for (int i = 0; i < languages.length() ; i++) {
            // get resource ID by index
            languageItems.add(new LanguageItem(languages.getString(i), countryFlags.getResourceId(i, -1), languagesShort.getString(i)));
        }
        final RecyclerView recyclerLanguages = dialogLayout.findViewById(R.id.recycler_languages);
        final ImageView closeButton = dialogLayout.findViewById(R.id.btn_closed_language_list);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (languageSelectionDialog.isShowing()) {
                    languageSelectionDialog.dismiss();
                }
            }
        });
        final LinearLayoutManager folderLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerLanguages.setLayoutManager(folderLayoutManager);
        recyclerLanguages.setAdapter(new LanguageListAdapter(getContext(), languageItems, initButtonPanelFragmentBehavior()));
        builder.setView(dialogLayout);
        languages.recycle();
        countryFlags.recycle();
        languagesShort.recycle();
        languageSelectionDialog = builder.create();
        languageSelectionDialog.show();
    }

    private ButtonPanelBehaviors initButtonPanelFragmentBehavior() {
        return new ButtonPanelBehaviors() {
            @Override
            public void playSound(final @IntegerRes int audioResId, final LottieAnimationView lottieAnimationView) {
                stopActiveSoundAndAnimation();
                mediaPlayer = MediaPlayer.create(getContext(), audioResId);
                currentAnimation = lottieAnimationView;
                currentAnimation.setVisibility(View.VISIBLE);
                currentAnimation.setMinAndMaxProgress(0.2f, 0.4f);
                currentAnimation.playAnimation();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(final MediaPlayer mp) {
                        mp.release();
                        currentAnimation.cancelAnimation();
                        currentAnimation.setVisibility(View.INVISIBLE);
                    }
                });
            }

            @Override
            public void changeLanguage(final String language) {
                final Locale locale = new Locale(language);
                final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                final Configuration configuration = getResources().getConfiguration();
                configuration.locale = locale;
                getResources().updateConfiguration(configuration, displayMetrics);
                languageSelectionDialog.dismiss();
                mainActivityBehavior.restartGame();
            }
        };
    }
}