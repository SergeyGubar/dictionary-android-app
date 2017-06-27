package com.example.android.miwok;

/**
 * Created by Sergey on 6/22/2017.
 */

public class Word {
    private String rusWord;
    private String engWord;
    private int imageSource;
    private int color;


    public String getRusWord() {
        return rusWord;
    }

    public String getEngWord() {
        return engWord;
    }

    public int getImageSource() {
        return imageSource;
    }

    public int getColor() {
        return color;
    }

    public Word(String rusWord, String engWord) {
        this.rusWord = rusWord;
        this.engWord = engWord;
    }

    public Word(String rusWord, String engWord, int imageSource) {
        this.rusWord = rusWord;
        this.engWord = engWord;
        this.imageSource = imageSource;
    }

    public Word(String rusWord, String engWord, int imageSource, int color) {
        this.rusWord = rusWord;
        this.engWord = engWord;
        this.imageSource = imageSource;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.engWord + "\n" + this.rusWord;
    }
}
