package com.example.android.ronald_1202150002_modul3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;

/**
 * Created by user on 2/23/2018.
 */

class Air {
    static final String TITLE_KEY = "Title";
    static final String IMAGE_KEY = "Image Resource";
    private final int imageResource;
    private final String title;
    private final String info;

    public Air(String title, String info, int imageResource){
        this.title=title;
        this.info=info;
        this.imageResource = imageResource;
    }

    public int getImageResource(){
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    static Intent starter(Context context, String title, @DrawableRes int imageResId) {
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(TITLE_KEY, title);
        detailIntent.putExtra(IMAGE_KEY, imageResId);
        return detailIntent;
    }
}
