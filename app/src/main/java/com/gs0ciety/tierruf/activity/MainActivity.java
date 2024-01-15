package com.gs0ciety.tierruf.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gs0ciety.tierruf.R;
import com.gs0ciety.tierruf.fragment.ButtonPanelFragment;
import com.gs0ciety.tierruf.interfaces.MainActivityBehavior;
import com.gs0ciety.tierruf.model.KanjiItem;
import com.gs0ciety.tierruf.utils.GameFragmentLauncherUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

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

        InputStream is = getResources().openRawResource(R.raw.kanjidb);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String jsonString = writer.toString();

        List<KanjiItem> kanjiItemList = new LinkedList<>();
        JSONArray jsonArrayFinal = new JSONArray();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject jsonKanjiObject = new JSONObject();
                    String id = "";
                    String rev = "";
                    String romaji= "";
                    String hiragana= "";
                    String meaning= "";
                    String katakana= "";
                    String strokeGuideUrl= "";
                    String imageUrl= "";
                    String kanji= "";
                    if (jsonObject.has("_id")) {
                        id = jsonObject.getString("_id");
                        jsonKanjiObject.put("id", id);
                    }
                    if (jsonObject.has("_rev")) {
                        rev = jsonObject.getString("_rev");
                        jsonKanjiObject.put("rev", rev);
                    }
                    if (jsonObject.has("kunyomi_ja")) {
                        hiragana = jsonObject.getString("kunyomi_ja");
                        jsonKanjiObject.put("hiragana", hiragana);
                    }
                    if (jsonObject.has("kunyomi")) {
                        romaji = jsonObject.getString("kunyomi");
                        jsonKanjiObject.put("romaji", romaji);
                    }
                    if (jsonObject.has("meaning")) {
                        meaning = jsonObject.getString("meaning");
                        jsonKanjiObject.put("meaning", meaning);
                    }
                    if (jsonObject.has("onyomi_ja")) {
                        katakana = jsonObject.getString("onyomi_ja");
                        jsonKanjiObject.put("katakana", katakana);
                    }
                    if (jsonObject.has("ka_utf")) {
                        kanji = jsonObject.getString("ka_utf");
                        jsonKanjiObject.put("kanji", kanji);
                    }
                    if (jsonObject.has("radical")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("radical");
                        if (jsonObject1.has("image")) {
                            imageUrl = jsonObject1.getString("image");
                            jsonKanjiObject.put("imageUrl", imageUrl);
                        }
                    }
                    if (jsonObject.has("kanji")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("kanji");
                        if (jsonObject1.has("video")) {
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("video");
                            if (jsonObject2.has("mp4")) {
                                strokeGuideUrl = jsonObject2.getString("mp4");
                                jsonKanjiObject.put("strokeGuideUrl", strokeGuideUrl);
                            }
                        }
                    }

                    KanjiItem kanjiItem = new KanjiItem(id, rev, hiragana,
                            katakana, romaji, strokeGuideUrl, imageUrl, meaning, kanji);

                    kanjiItemList.add(kanjiItem);
                    jsonArrayFinal.put(jsonKanjiObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONArray jsonArray = new JSONArray(kanjiItemList);
        System.out.println(jsonArray.toString());
        System.out.println(jsonArrayFinal);

        loadFragment(new ButtonPanelFragment(initMainActivityInterface()));
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
        loadFragment(new ButtonPanelFragment(initMainActivityInterface()));
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

    private MainActivityBehavior initMainActivityInterface() {
        return new MainActivityBehavior() {
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