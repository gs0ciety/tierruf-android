package com.gs0ciety.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GameFragment extends Fragment {

    ImageView firstAnimalOption, secondAnimalOption, thirdAnimalOption, fourthAnimalOption,
        fifthAnimalOption, sixthAnimalOption, mainAnimal;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        initUiElements(view);

        final Set<Integer> lastMainAnimalsUsed = new HashSet<>();
        final Set<Integer> lastOptionsAnimalsUsed = new HashSet<>();
        final Set<Integer> lastCorrectOptionsUsed = new HashSet<>();

        final TypedArray animalImages = getResources().obtainTypedArray(R.array.animal_images_drawables);
        final TypedArray animalSounds = getResources().obtainTypedArray(R.array.animal_sounds_drawables);

        int option = getRandomAnimalPosition(lastCorrectOptionsUsed, 5);
        final int animalResourcePosition = getRandomAnimalPosition(lastMainAnimalsUsed, animalImages.length());
        mainAnimal.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
        switch (option) {
            case 0:
            default:
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 1:
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 2:
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 3:
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 4:
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 5:
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
        }


        // recycle the arrays
        animalImages.recycle();
        animalSounds.recycle();

        return view;
    }

    private Integer getRandomAnimalPosition(final Set<Integer> lastMainPositionsUsed, final int animalsAmount) {
        if (lastMainPositionsUsed.size() == animalsAmount) {
            lastMainPositionsUsed.clear();
        }
        int result = ((Double) (Math.random() * animalsAmount)).intValue();
        while (!lastMainPositionsUsed.contains(result)) {
            result = ((Double) (Math.random() * animalsAmount)).intValue();
            lastMainPositionsUsed.add(result);
        }
        lastMainPositionsUsed.add(result);
        return result;
    }

    private Integer getRandomAnimalOptionPosition(final Set<Integer> lastAnimalsUsed,
                                                  final int animalsAmount,
                                                  final int mainPosition) {
        if (lastAnimalsUsed.size() == animalsAmount - 2) {
            lastAnimalsUsed.clear();
        }

        int result;
        do {
            result = ((Double) (Math.random() * animalsAmount)).intValue();
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
}
