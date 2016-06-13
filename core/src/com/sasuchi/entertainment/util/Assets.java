package com.sasuchi.entertainment.util;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by Zascha on 13.06.2016.
 */
public class Assets {

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

}
