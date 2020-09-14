package com.gs0ciety.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.gs0ciety.Types.BuildParamTypes;
import com.gs0ciety.Types.GameModeTypes;
import com.gs0ciety.activity.R;
import com.gs0ciety.interfaces.MainActivityInterface;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameFragment extends Fragment {

    private MainActivityInterface mainActivityInterface;
    private MediaPlayer mediaPlayer;

    public GameFragment (final MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
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
                mainAnimal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), mainShapeAnimalArray.getResourceId(correctAnimalResourcePosition, -1), null));
                mainAnimal.setVisibility(View.VISIBLE);
                mainShapeAnimalArray.recycle();
                break;
            case GameModeTypes.SOUND:
                mainAnimal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.btn_play_circle_outline, null));
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


        int option = getMainAnimalPositionUsed(lastCorrectOptionsUsed, 6);
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

    private Integer getMainAnimalPositionUsed(final Set<Integer> lastMainPositionsUsed, final int animalsAmount) {
        final Random r = new Random();
        int result = r.nextInt(animalsAmount);
        while (!lastMainPositionsUsed.contains(result)) {
            result = r.nextInt(animalsAmount);
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

    private Drawable getDrawable(final TypedArray animalImages,
                                 final int animalResourcePosition) {
        return ResourcesCompat.getDrawable(getResources(), animalImages.getResourceId(animalResourcePosition, -1), null);
    }

    private void configErrorAnimalImage(final ImageView imageView,
                                        final TypedArray animalImages,
                                        final Set<Integer> lastOptionsAnimalsUsed,
                                        final int correctAnimalResourcePosition) {
        final int randomSecondAnimalPosition = getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), correctAnimalResourcePosition);
        imageView.setImageDrawable(getDrawable(animalImages, randomSecondAnimalPosition));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TypedArray animalErrorImages = getResources().obtainTypedArray(R.array.animal_error_drawables);
                imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), animalErrorImages.getResourceId(randomSecondAnimalPosition, -1), null));
                animalErrorImages.recycle();
                displayIncorrectSnackbar(view.getRootView());
            }
        });
    }

    private void configCorrectAnimalImage(final ImageView imageView,
                                          final TypedArray animalImages,
                                          final int correctAnimalResourcePosition) {
        imageView.setImageDrawable(getDrawable(animalImages, correctAnimalResourcePosition));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final LayoutInflater inflater = getLayoutInflater();
                final View dialogLayout = inflater.inflate(R.layout.dialog_success, null);
                builder.setCancelable(false).setCancelable(false);
                builder.setView(dialogLayout);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                    }
                }, 3000);
                mainActivityInterface.restartGame();
            }
        });
    }

    private void showSnackbar (final View view, final String displayText, final int color) {
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
