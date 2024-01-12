package com.gs0ciety.tierruf.model;

public class KanjiItem {

    private String id;
    private String rev;
    private String hiragana;
    private String katakana;
    private String romaji;
    private String strokeGuideUrl;
    private String imageUrl;
    private String meaning;

    private String kanji;

    public void setId(String id) {
        this.id = id;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public void setStrokeGuideUrl(String strokeGuideUrl) {
        this.strokeGuideUrl = strokeGuideUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public KanjiItem(String id, String rev, String hiragana,
                     String katana, String romaji, String strokeGuideUrl,
                     String imageUrl, String meaning, String kanji) {
        this.id = id;
        this.rev = rev;
        this.hiragana = hiragana;
        this.katakana = katana;
        this.romaji = romaji;
        this.strokeGuideUrl = strokeGuideUrl;
        this.imageUrl = imageUrl;
        this.meaning = meaning;
        this.kanji = kanji;
    }

    public String getId() {
        return id;
    }

    public String getRev() {
        return rev;
    }

    public String getHiragana() {
        return hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public String getRomaji() {
        return romaji;
    }

    public String getStrokeGuideUrl() {
        return strokeGuideUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMeaning() {
        return meaning;
    }
    public String getKanji() {
        return kanji;
    }
}