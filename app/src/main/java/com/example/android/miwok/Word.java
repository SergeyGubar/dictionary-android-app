package com.example.android.miwok;

/**
 * Created by Sergey on 6/22/2017.
 */

class Word {
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


    public Word(String rusWord, String engWord) {
        this.rusWord = rusWord;
        this.engWord = engWord;
    }

    public Word(final String rusWord, final String engWord, final int imageSource) {
        this.rusWord = rusWord;
        this.engWord = engWord;
        this.imageSource = imageSource;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Word wordToCompare = (Word)o;
        return wordToCompare.engWord.equals(this.engWord) && wordToCompare.rusWord.equals(this.rusWord);
    }

    @Override
    public String toString() {
        return this.engWord + "\n" + this.rusWord;
    }
}
