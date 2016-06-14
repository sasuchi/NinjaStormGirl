package com.sasuchi.entertainment.enti;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sasuchi.entertainment.util.Constants;

/**
 * Created by Sascha on 13.06.2016.
 */
public class LevelClass {

    public final static String TAG = LevelClass.class.getName();

    public boolean gameOver;
    public boolean victory;
    public String characterSelection;
    public int score;

    public Viewport viewport;
    public CharacterClass character;



    private Array<LevelObject> levelObjects;

    public LevelClass() {

        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        character = new CharacterClass(new Vector2(50, 50), this, characterSelection);
        levelObjects = new Array<LevelObject>();
        gameOver = false;
        victory = false;
        score = 0;
    }

    public void update(float delta) {

        if(character.getExtraLives() < 0) {
            gameOver = true;
        }else if(score >= 5000){
            victory = true;
        }

        if(!victory && !gameOver){
            character.update(delta,levelObjects);

        }
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        for (LevelObject levelObject : levelObjects){
            levelObject.render(batch);
        }

        character.render(batch);

        batch.end();
    }

    public static LevelClass debugLevel() {
        LevelClass levelClass = new LevelClass();
        levelClass.initializeDebugLevel();
        return levelClass;
    }

    private void initializeDebugLevel() {
        character = new CharacterClass(new Vector2(50, 50), this, characterSelection);
        levelObjects = new Array<LevelObject>();
        levelObjects.add(new LevelObject(0,0,50,50));
    }

    public void swordAttack(Vector2 location, Constants.Direction direction) {

    }

    public Array<LevelObject> getLevelObjects() {
        return levelObjects;
    }
}
