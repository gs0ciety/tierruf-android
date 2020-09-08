package com.gs0ciety.utils;

import android.os.Bundle;

import com.gs0ciety.Types.BuildParamTypes;
import com.gs0ciety.Types.GameModeTypes;
import com.gs0ciety.fragment.GameFragment;
import com.gs0ciety.interfaces.MainActivityInterface;

public class GameFragmentLauncherUtils {

    public static GameFragment shapeGameLauncher(final MainActivityInterface mainActivityInterface) {
        return gameFragmentLauncher(mainActivityInterface, GameModeTypes.SHAPE);
    }

    public static GameFragment soundGameLauncher(final MainActivityInterface mainActivityInterface) {
        return gameFragmentLauncher(mainActivityInterface, GameModeTypes.SOUND);
    }

    public static GameFragment wordsGameLauncher(final MainActivityInterface mainActivityInterface) {
        return gameFragmentLauncher(mainActivityInterface, GameModeTypes.WORDS);
    }

    private static GameFragment gameFragmentLauncher(final MainActivityInterface mainActivityInterface,
                                                     final String gameMode){
        Bundle bundle = new Bundle();
        bundle.putString(BuildParamTypes.GAME_MODE, gameMode);
        GameFragment gameFragment = new GameFragment(mainActivityInterface);
        gameFragment.setArguments(bundle);
        return gameFragment;
    }
}
