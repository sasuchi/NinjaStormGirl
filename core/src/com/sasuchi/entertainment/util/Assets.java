package com.sasuchi.entertainment.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Zascha on 13.06.2016.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    public CharacterAsset characterAsset;
    public LevelAsset levelAsset;
    //TODO Add the sprites and animations needed
    /*
    public LevelAsset levelAsset;
    public ItemAsset itemAsset;
    public EnemyAsset enemyAsset;
    public EffectAsset effectAsset;
    */
    private Assets(){
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas imageAtlas = assetManager.get(Constants.TEXTURE_ATLAS);
        characterAsset = new CharacterAsset(imageAtlas);
        levelAsset = new LevelAsset(imageAtlas);
        //TODO add all assets
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    public class CharacterAsset {

        public final TextureAtlas.AtlasRegion standingLeft;
        public final TextureAtlas.AtlasRegion standingRight;
        public final TextureAtlas.AtlasRegion walkingLeft;
        public final TextureAtlas.AtlasRegion walkingRight;
        public final TextureAtlas.AtlasRegion jumpingLeft;
        public final TextureAtlas.AtlasRegion jumpingRight;

        public final Animation jumpingLeftAnimation;
        public final Animation jumpingRightAnimation;
        public final Animation walkingLeftAnimation;
        public final Animation walkingRightAnimation;


        public CharacterAsset(TextureAtlas atlas) {
            standingLeft = atlas.findRegion(Constants.STAND_LEFT);
            standingRight = atlas.findRegion(Constants.STAND_RIGHT);
            walkingLeft = atlas.findRegion(Constants.WALK_LEFT_2);
            walkingRight = atlas.findRegion(Constants.WALK_RIGHT_2);

            jumpingLeft = atlas.findRegion(Constants.JUMP_LEFT_1);
            jumpingRight = atlas.findRegion(Constants.JUMP_RIGHT_2);

            Array<TextureAtlas.AtlasRegion> jumpingLeftFrames = new Array<TextureAtlas.AtlasRegion>();
            jumpingLeftFrames.add(atlas.findRegion(Constants.JUMP_LEFT_2));
            jumpingLeftFrames.add(atlas.findRegion(Constants.JUMP_LEFT_3));
            jumpingLeftFrames.add(atlas.findRegion(Constants.JUMP_LEFT_2));
            jumpingLeftFrames.add(atlas.findRegion(Constants.JUMP_LEFT_1));
            jumpingLeftAnimation = new Animation(Constants.ANIM_LOOP_DUR, jumpingLeftFrames, Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> jumpingRightFrames = new Array<TextureAtlas.AtlasRegion>();
            jumpingRightFrames.add(atlas.findRegion(Constants.JUMP_RIGHT_2));
            jumpingRightFrames.add(atlas.findRegion(Constants.JUMP_RIGHT_3));
            jumpingRightFrames.add(atlas.findRegion(Constants.JUMP_RIGHT_2));
            jumpingRightFrames.add(atlas.findRegion(Constants.JUMP_RIGHT_1));
            jumpingRightAnimation = new Animation(Constants.ANIM_LOOP_DUR, jumpingRightFrames, Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> walkingLeftFrames = new Array<TextureAtlas.AtlasRegion>();
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_LEFT_2));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_LEFT_1));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_LEFT_2));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_LEFT_3));
            walkingLeftAnimation = new Animation(Constants.ANIM_LOOP_DUR, walkingLeftFrames, Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> walkingRightFrames = new Array<TextureAtlas.AtlasRegion>();
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_RIGHT_2));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_RIGHT_1));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_RIGHT_2));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_RIGHT_3));
            walkingRightAnimation = new Animation(Constants.ANIM_LOOP_DUR, walkingRightFrames, Animation.PlayMode.LOOP);
        }
    }

    public class LevelAsset {

        public final NinePatch platformTypeAPatch;

        public LevelAsset(TextureAtlas atlas){
            TextureAtlas.AtlasRegion region = atlas.findRegion(Constants.PLATFORM_TYPE_A);
            int edge = Constants.PLATFORM_TYPE_A_EDGE;
            platformTypeAPatch = new NinePatch(region, edge, edge, edge, edge);
        }

    }
}
