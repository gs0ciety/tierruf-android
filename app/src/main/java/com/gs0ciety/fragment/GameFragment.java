package com.gs0ciety.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.gs0ciety.activity.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameFragment extends Fragment {

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
        final int animalResourcePosition = getRandomCorrectAnimalPosition(lastCorrectAnimalsUsed, animalImages.length());
        mainAnimal.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));

        int option = getMainAnimalPositionUsed(lastCorrectOptionsUsed, 6);
        switch (option) {
            case 1:
            default:
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 2:
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 3:
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 4:
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 5:
                fifthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(animalResourcePosition, -1)));
                firstAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                secondAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                thirdAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                fourthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                sixthAnimalOption.setImageDrawable(view.getResources().getDrawable(animalImages.getResourceId(getRandomAnimalOptionPosition(lastOptionsAnimalsUsed, animalImages.length(), animalResourcePosition), -1)));
                break;
            case 6:
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

        return view;
    }


    private Integer getMainAnimalPositionUsed(final Set<Integer> lastMainPositionsUsed, final int animalsAmount) {
        if (lastMainPositionsUsed.size() == animalsAmount) {
            lastMainPositionsUsed.clear();
        }
        final Random r = new Random();
        int result = r.nextInt(animalsAmount);
        while (!lastMainPositionsUsed.contains(result)) {
            result = r.nextInt(animalsAmount);
            lastMainPositionsUsed.add(result);
        }
        return result;
    }

    private Integer getRandomCorrectAnimalPosition(final Set<Integer> lastMainPositionsUsed, final int animalsAmount) {
        if (lastMainPositionsUsed.size() == animalsAmount) {
            lastMainPositionsUsed.clear();
        }
        int result  = new Random().nextInt(animalsAmount + 1);
        while (!lastMainPositionsUsed.contains(result)) {
            result = new Random().nextInt(animalsAmount + 1);
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
            result  = new Random().nextInt(animalsAmount + 1);
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
