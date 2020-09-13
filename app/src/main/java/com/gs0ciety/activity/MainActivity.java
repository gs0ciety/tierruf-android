package com.gs0ciety.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.adapter.LanguageListAdapter;
import com.gs0ciety.fragment.ButtonPanelFragment;
import com.gs0ciety.interfaces.MainActivityInterface;
import com.gs0ciety.listeners.OnSwipeTouchListener;
import com.gs0ciety.model.LanguageItem;
import com.gs0ciety.utils.GameFragmentLauncherUtils;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView panelIndicator, questionIndicator, soundIndicator, nameIndicator, panelButton,
            questionButton, soundButton ,nameButton;

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        panelIndicator = findViewById(R.id.img_indicator_animal_panel);
        questionIndicator = findViewById(R.id.img_indicator_animal_question);
        soundIndicator = findViewById(R.id.img_indicator_animal_sound);
        nameIndicator = findViewById(R.id.img_indicator_animal_name);

        panelButton = findViewById(R.id.btn_animal_panel);
        questionButton = findViewById(R.id.btn_animal_question);
        soundButton = findViewById(R.id.btn_animal_sound);
        nameButton = findViewById(R.id.btn_animal_name);

        loadFragment(new ButtonPanelFragment());

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog_Theme);
        final LayoutInflater inflater = getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_tutorial, null);
        builder.setCancelable(false).setCancelable(false);
        builder.setView(dialogLayout);
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

        dialogLayout.setOnTouchListener(new OnSwipeTouchListener(getApplication()) {
            @Override
            public void onSwipeRight() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_select_language, null);
                final List<LanguageItem> languageItems = new LinkedList<>();
                final TypedArray languages = getResources().obtainTypedArray(R.array.languages);
                final TypedArray countryFlags = getResources().obtainTypedArray(R.array.country_flags);
                for (int i = 0; i < languages.length() ; i++) {
                    // get resource ID by index
                    languageItems.add(new LanguageItem(languages.getString(i), countryFlags.getResourceId(i, -1)));
                }
                final RecyclerView recyclerLanguages = dialogLayout.findViewById(R.id.recycler_languages);
                final LinearLayoutManager folderLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
                recyclerLanguages.setLayoutManager(folderLayoutManager);
                recyclerLanguages.setAdapter(new LanguageListAdapter(getApplication(), languageItems));
                builder.setView(dialogLayout);
                final AlertDialog alertDialog = builder.create();
                languages.recycle();
                countryFlags.recycle();
                tutorialAlertDialog.dismiss();
                alertDialog.show();
            }

            @Override
            public void onSwipeLeft() {
                // Do nothing!
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public void onClickPanelButton(final View view) {
        panelIndicator.setAlpha(1f);
        panelButton.setAlpha(1f);
        questionIndicator.setAlpha(0f);
        questionButton.setAlpha(0.7f);
        soundIndicator.setAlpha(0f);
        soundButton.setAlpha(0.7f);
        nameIndicator.setAlpha(0f);
        nameButton.setAlpha(0.7f);
        loadFragment(new ButtonPanelFragment());
        setTransitionGameSound(view.getContext());
    }

    public void onClickSoundButton(final View view) {
        soundIndicator.setAlpha(1f);
        soundButton.setAlpha(1f);
        panelIndicator.setAlpha(0f);
        panelButton.setAlpha(0.7f);
        questionIndicator.setAlpha(0f);
        questionButton.setAlpha(0.7f);
        nameIndicator.setAlpha(0f);
        nameButton.setAlpha(0.7f);
        loadFragment(GameFragmentLauncherUtils.soundGameLauncher(initMainActivityInterface()));
        setTransitionGameSound(view.getContext());
    }

    public void onClickQuestionButton(final View view) {
        questionIndicator.setAlpha(1f);
        questionButton.setAlpha(1f);
        panelIndicator.setAlpha(0f);
        panelButton.setAlpha(0.7f);
        soundIndicator.setAlpha(0f);
        soundButton.setAlpha(0.7f);
        nameIndicator.setAlpha(0f);
        nameButton.setAlpha(0.7f);
        loadFragment(GameFragmentLauncherUtils.shapeGameLauncher(initMainActivityInterface()));
        setTransitionGameSound(view.getContext());
    }

    public void onClickNameButton(final View view) {
        nameIndicator.setAlpha(1f);
        nameButton.setAlpha(1f);
        panelIndicator.setAlpha(0f);
        panelButton.setAlpha(0.7f);
        questionIndicator.setAlpha(0f);
        questionButton.setAlpha(0.7f);
        soundIndicator.setAlpha(0f);
        soundButton.setAlpha(0.7f);
        loadFragment(GameFragmentLauncherUtils.wordsGameLauncher(initMainActivityInterface()));
        setTransitionGameSound(view.getContext());
    }

    private void loadFragment(final Fragment fragment) {
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frame_base_activity_content, fragment, "Your_Fragment_TAG");
        fragmentTransaction.commit(); // save the changes
    }

    private MainActivityInterface initMainActivityInterface() {
        return new MainActivityInterface() {
            @Override
            public void restartGame() {
                final Fragment frg = getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();
            }
        };
    }

    public void setTransitionGameSound(final Context context) {
        stopActiveSound();
        mediaPlayer = MediaPlayer.create(context, R.raw.transition_game_btn_sound);
        mediaPlayer.start();
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