package com.gs0ciety.tierruf.utils;

import android.os.Bundle;

import com.gs0ciety.tierruf.Types.BuildParamTypes;
import com.gs0ciety.tierruf.Types.GameModeTypes;
import com.gs0ciety.tierruf.fragment.GameFragment;
import com.gs0ciety.tierruf.interfaces.MainActivityBehavior;

public class GameFragmentLauncherUtils {

    public static GameFragment shapeGameLauncher(final MainActivityBehavior mainActivityBehavior) {
        return gameFragmentLauncher(mainActivityBehavior, GameModeTypes.SHAPE);
    }

    public static GameFragment soundGameLauncher(final MainActivityBehavior mainActivityBehavior) {
        return gameFragmentLauncher(mainActivityBehavior, GameModeTypes.SOUND);
    }

    public static GameFragment wordsGameLauncher(final MainActivityBehavior mainActivityBehavior) {
        return gameFragmentLauncher(mainActivityBehavior, GameModeTypes.WORDS);
    }

    private static GameFragment gameFragmentLauncher(final MainActivityBehavior mainActivityBehavior,
                                                     final String gameMode){
        final Bundle bundle = new Bundle();
        bundle.putString(BuildParamTypes.GAME_MODE, gameMode);
        final GameFragment gameFragment = new GameFragment(mainActivityBehavior);
        gameFragment.setArguments(bundle);
        return gameFragment;
    }
}
