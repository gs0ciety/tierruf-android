package com.gs0ciety.fragment;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.gs0ciety.activity.R;
import com.gs0ciety.interfaces.MainActivityInterface;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameFragment extends Fragment {

    private MainActivityInterface mainActivityInterface;

    public GameFragment (final MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
    }

    private ImageView firstAnimalOption, secondAnimalOption, thirdAnimalOption, fourthAnimalOption,
        fifthAnimalOption, sixthAnimalOption, mainAnimal;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        initUiElements(view);

        final Set<Integer> lastCorrectAnimalsUsed = new HashSet<>();
        final Set<Integer> lastOptionsAnimalsUsed = new HashSet<>();
        final Set<Integer> lastCorrectOptionsUsed = new HashSet<>();

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final TypedArray animalHiddenImages = getResources().obtainTypedArray(R.array.animal_hidden_drawables);

        final int correctAnimalResourcePosition = getRandomCorrectAnimalPosition(lastCorrectAnimalsUsed, animalImages.length());
        mainAnimal.setImageDrawable(ResourcesCompat.getDrawable(getResources(), animalHiddenImages.getResourceId(correctAnimalResourcePosition, -1), null));

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
        animalHiddenImages.recycle();

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
                displayCorrectSnackbar(view.getRootView());
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                mainActivityInterface.restartGame();
            }
        });
    }

    private void showSnackbar (final View view, final String displayText, final int color) {
        Snackbar snackbar = Snackbar
                .make(view.findViewById(R.id.constraint_activity_main_game), displayText, Snackbar.LENGTH_SHORT);

        snackbar.setActionTextColor(color);
        snackbar.show();
    }

    private void displayCorrectSnackbar (final View view) {
        showSnackbar(view, "CORRECT", Color.GREEN);
    }

    private void displayIncorrectSnackbar (final View view) {
        showSnackbar(view, "TRY AGAIN!", Color.RED);
    }
}
