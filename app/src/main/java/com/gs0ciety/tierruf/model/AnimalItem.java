package com.gs0ciety.tierruf.model;

public class AnimalItem {

    private final String name;
    private final int imageResId;
    private final int audioResId;

    public AnimalItem(String name, int imageResId, int audioResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.audioResId = audioResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getAudioResId() {
        return audioResId;
    }
}