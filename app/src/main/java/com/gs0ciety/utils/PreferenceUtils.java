package com.gs0ciety.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import com.gs0ciety.activity.R;

public class PreferenceUtils {

    /**
     * Set preference for a locale language.
     *
     * @param locale the locale for the language selected
     */
    public static void setPreferenceLocale(@Nullable final String locale,
                                           final Context context) {
        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(context.getString(R.string.key_locale));
        editor.commit();
        editor.putString(context.getString(R.string.key_locale), locale);
        editor.apply();
    }

    /**
     * Retrieve a locale for a the selected language. In case no language has been selected it will return null
     *
     * @return the corresponding preference value.
     */
    public static String getPreferenceLocale(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.key_locale), null);
    }

    public static boolean shouldDisplayMainTutorial(final Context context) {
        return shouldAllowAction(R.string.key_show_language_tutorial, context);
    }

    public static void showMainTutorial(final boolean showMainTutorial, final Context context) {
        performAction(showMainTutorial,context, R.string.key_show_language_tutorial);
    }

    private static void performAction(final boolean showSdPermission, final Context context, final int preferenceId) {
        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(context.getString(preferenceId), showSdPermission);
        editor.apply();
    }

    private static boolean shouldAllowAction(final int preferenceId, final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(preferenceId), true);
    }
}