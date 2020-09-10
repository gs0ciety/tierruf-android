package com.gs0ciety.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.gs0ciety.fragment.ButtonPanelFragment;
import com.gs0ciety.interfaces.MainActivityInterface;
import com.gs0ciety.utils.GameFragmentLauncherUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView panelIndicator, questionIndicator, soundIndicator, nameIndicator, panelButton,
            questionButton, soundButton ,nameButton;

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
        builder.setPositiveButton("SKIP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                // Do nothing!
            }
        });
        final AlertDialog alertDialog = builder.create();
        final ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);
        colorDrawable.setAlpha(170);
        alertDialog.getWindow().setBackgroundDrawable(colorDrawable);
        alertDialog.show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (alertDialog.isShowing()){
//                    alertDialog.dismiss();
//                }
//            }
//        }, 10000);

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
}