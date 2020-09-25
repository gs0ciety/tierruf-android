package com.gs0ciety.tierruf.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.gs0ciety.tierruf.R;
import com.gs0ciety.tierruf.interfaces.MainActivityBehavior;
import com.gs0ciety.tierruf.types.BuildParamTypes;
import com.gs0ciety.tierruf.types.GameModeTypes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameFragment extends Fragment {

    private static int NUMBER_OF_CHOICES = 6;
    private MainActivityBehavior mainActivityBehavior;
    private MediaPlayer mediaPlayer;

    public GameFragment (final MainActivityBehavior mainActivityBehavior) {
        this.mainActivityBehavior = mainActivityBehavior;
    }

    private ImageView firstAnimalOption, secondAnimalOption, thirdAnimalOption, fourthAnimalOption,
        fifthAnimalOption, sixthAnimalOption, mainAnimal;

    private TextView mainAnimalText;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        final String gameMode = bundle.getString(BuildParamTypes.GAME_MODE);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        initUiElements(view);

        final Set<Integer> lastCorrectAnimalsUsed = new HashSet<>();
        final Set<Integer> lastOptionsAnimalsUsed = new HashSet<>();
        final Set<Integer> lastCorrectOptionsUsed = new HashSet<>();

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final int correctAnimalResourcePosition = getRandomCorrectAnimalPosition(lastCorrectAnimalsUsed, animalImages.length());

        switch (gameMode) {
            default:
            case GameModeTypes.SHAPE:
                final TypedArray mainShapeAnimalArray = getResources().obtainTypedArray(R.array.animal_hidden_drawables);
                mainAnimal.setImageResource(mainShapeAnimalArray.getResourceId(correctAnimalResourcePosition, -1));
                mainAnimal.setVisibility(View.VISIBLE);
                mainShapeAnimalArray.recycle();
                break;
            case GameModeTypes.SOUND:
                mainAnimal.setImageResource(R.drawable.btn_play_circle_outline);
                mainAnimal.setVisibility(View.VISIBLE);
                mainAnimal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final TypedArray mainSoundAnimalArray = getResources().obtainTypedArray(R.array.animal_sounds);
                        stopActiveSound();
                        final LottieAnimationView lottieAnimationView = view.getRootView().findViewById(R.id.animation_game_animal_sound);
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        lottieAnimationView.setMinAndMaxProgress(0.2f, 0.4f);
                        mediaPlayer = MediaPlayer.create(getContext(), mainSoundAnimalArray.getResourceId(correctAnimalResourcePosition, -1));
                        mainSoundAnimalArray.recycle();
                        lottieAnimationView.setVisibility(View.VISIBLE);
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
                });
                break;
            case GameModeTypes.WORDS:
                final TypedArray mainWordAnimalArray = getResources().obtainTypedArray(R.array.animal_names);
                mainAnimalText.setText(mainWordAnimalArray.getString(correctAnimalResourcePosition));
                mainAnimalText.setVisibility(View.VISIBLE);
                mainAnimal.setVisibility(View.INVISIBLE);
                mainWordAnimalArray.recycle();
                break;
        }


        int option = getMainAnimalPositionUsed(lastCorrectOptionsUsed);
        switch (option) {
            case 1:
            default:
                configCorrectAnimalImage(firstAnimalOption, animalImages, correctAnimalResourcePosition);
                configErrorAnimalImage(secondAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(thirdAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fourthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fifthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(sixthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                break;
            case 2:
                configCorrectAnimalImage(secondAnimalOption, animalImages, correctAnimalResourcePosition);
                configErrorAnimalImage(firstAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(thirdAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fourthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fifthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(sixthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                break;
            case 3:
                configCorrectAnimalImage(thirdAnimalOption, animalImages, correctAnimalResourcePosition);
                configErrorAnimalImage(firstAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(secondAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fourthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fifthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(sixthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                break;
            case 4:
                configCorrectAnimalImage(fourthAnimalOption, animalImages, correctAnimalResourcePosition);
                configErrorAnimalImage(firstAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(secondAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(thirdAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fifthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(sixthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                break;
            case 5:
                configCorrectAnimalImage(fifthAnimalOption, animalImages, correctAnimalResourcePosition);
                configErrorAnimalImage(firstAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(secondAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(thirdAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fourthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(sixthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                break;
            case 6:
                configCorrectAnimalImage(sixthAnimalOption, animalImages, correctAnimalResourcePosition);
                configErrorAnimalImage(firstAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(secondAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(thirdAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fourthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                configErrorAnimalImage(fifthAnimalOption, animalImages, lastOptionsAnimalsUsed, correctAnimalResourcePosition);
                break;
        }

        // recycle the arrays
        animalImages.recycle();

        return view;
    }

    /**
     * We get a random integer between 1 and the number of choices, avoiding last position numbers
     * 
     * if NUMBER_OF_CHOICES = 6, this method will return --> [1, 6]
     *
     * @param lastMainPositionsUsed the last position numbers used
     * @return a random Integer
     */
    private Integer getMainAnimalPositionUsed(final Set<Integer> lastMainPositionsUsed) {
        final Random r = new Random();
        int result = r.nextInt(NUMBER_OF_CHOICES + 1);
        while (!lastMainPositionsUsed.contains(result)) {
            result = r.nextInt(NUMBER_OF_CHOICES + 1);
            lastMainPositionsUsed.add(result);
        }
        return result;
    }

    private Integer getRandomCorrectAnimalPosition(final Set<Integer> lastMainPositionsUsed, final int animalsAmount) {
        int result  = new Random().nextInt(animalsAmount);
        while (!lastMainPositionsUsed.contains(result)) {
            result = new Random().nextInt(animalsAmount);
            lastMainPositionsUsed.add(result);
        }
        lastMainPositionsUsed.add(result);
        return result;
    }

    private Integer getRandomAnimalOptionPosition(final Set<Integer> lastAnimalsUsed,
                                                  final int animalsAmount,
                                                  final int mainPosition) {
        int result;
        do {
            result  = new Random().nextInt(animalsAmount);
        } while (lastAnimalsUsed.contains(result) || result == mainPosition);
        lastAnimalsUsed.add(result);
        return result;
    }

    private void initUiElements(final View view) {
        firstAnimalOption = view.findViewById(R.id.game_animal_option_1);
        secondAnimalOption = view.findViewById(R.id.game_animal_option_2);
        thirdAnimalOption = view.findViewById(R.id.game_animal_option_3);
        fourthAnimalOption = view.findViewById(R.id.game_animal_option_4);
        fifthAnimalOption = view.findViewById(R.id.game_animal_option_5);
        sixthAnimalOption = view.findViewById(R.id.game_animal_option_6);
        mainAnimal = view.findViewById(R.id.img_main_game_animal);
        mainAnimalText = view.findViewById(R.id.textViewMainAnimal);
        mainAnimalText.setVisibility(View.GONE);
    }

    private int getDrawable(final TypedArray animalImages,
                                   final int animalResourcePosition) {
        return animalImages.getResourceId(animalResourcePosition, -1);
    }

    private void configErrorAnimalImage(final ImageView imageView,
                                        final TypedArray animalImages,
                                        final Set<Integer> lastOptionsAnimalsUsed,
                                        final int correctAnimalResourcePosition) {
        final int randomSecondAnimalPosition = getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), correctAnimalResourcePosition);
        imageView.setImageResource(getDrawable(animalImages, randomSecondAnimalPosition));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TypedArray animalErrorImages = getResources().obtainTypedArray(R.array.animal_error_drawables);
                imageView.setImageResource(animalErrorImages.getResourceId(randomSecondAnimalPosition, -1));
                animalErrorImages.recycle();
                displayIncorrectSnackbar(view.getRootView());
            }
        });
    }

    private void configCorrectAnimalImage(final ImageView imageView,
                                          final TypedArray animalImages,
                                          final int correctAnimalResourcePosition) {
        imageView.setImageResource(getDrawable(animalImages, correctAnimalResourcePosition));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog alertDialog = new Dialog(getActivity());
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setContentView(R.layout.dialog_success);
                alertDialog.show();
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.drums_success);
                alertDialog.show();
                mediaPlayer.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (alertDialog.isShowing()){
                            alertDialog.dismiss();
                        }
                        mediaPlayer.release();
                        mainActivityBehavior.restartGame();
                    }
                }, 2300);
            }
        });
    }

    private void showSnackbar(final View view, final String displayText, final int color) {
        Snackbar snackbar = Snackbar
                .make(view.findViewById(R.id.constraint_activity_main_game), displayText, Snackbar.LENGTH_SHORT);

        View viewSnackbar = snackbar.getView();
        TextView textViewSnackbar = viewSnackbar.findViewById(com.google.android.material.R.id.snackbar_text);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textViewSnackbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            textViewSnackbar.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        textViewSnackbar.setTypeface(Typeface.DEFAULT_BOLD);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorTextLight));
        snackbar.setBackgroundTint(color);
        snackbar.show();
    }

    private void displayIncorrectSnackbar(final View view) {
        showSnackbar(view, getResources().getString(R.string.incorrect), getResources().getColor(R.color.colorIncorrectBackground));
        vibration();
    }

    private void vibration() {
        Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(500);
        }
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
