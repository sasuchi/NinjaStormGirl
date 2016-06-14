package com.sasuchi.entertainment;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sasuchi.entertainment.enti.LevelClass;
import com.sasuchi.entertainment.util.Assets;
import com.sasuchi.entertainment.util.CharacterHUD;
import com.sasuchi.entertainment.util.ChaseCam;
import com.sasuchi.entertainment.util.Constants;
import com.sasuchi.entertainment.util.OnScreenControls;

/**
 * Created by Zascha on 13.06.2016.
 */
public class GameplayScreen extends ScreenAdapter {

    public final static String TAG = GameplayScreen.class.getName();

    private OnScreenControls controller;
    private SpriteBatch batch;
    private ChaseCam camera;
    private CharacterHUD hud;
    private LevelClass level;

    @Override
    public void show() {

        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);

        batch = new SpriteBatch();
        camera = new ChaseCam();
        hud = new CharacterHUD();

        controller = new OnScreenControls();
        if (onMobile()){
                Gdx.input.setInputProcessor(controller);
        }
    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }


}
