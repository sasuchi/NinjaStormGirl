package com.sasuchi.entertainment.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Zascha on 13.06.2016.
 */
public class Constants {


    //Game Mechanics
    public final static float GRAVITY = 9.81f;
    public final static float KILL_PLANE = -200.0f;
    public final static int INIT_HEALTH = 100;
    public final static int INIT_LIVES = 3;
    public final static int CHARACTER_HEIGHT = 100;
    public final static Vector2 CHARACTER_SIZE = new Vector2(30.0f, 50.0f);
    public final static Vector2 SWORD_OFFSET = new Vector2(5.0f, 8.0f);

    //Character Sprites
    public final static String STAND_RIGHT = "stand-right";
    public final static String STAND_LEFT = "stand-left";

    public final static String JUMP_LEFT_1 = "jump-left-1";
    public final static String JUMP_LEFT_2 = "jump-left-2";
    public final static String JUMP_LEFT_3 = "jump-left-3";

    public final static String JUMP_RIGHT_1 = "jump-right-1";
    public final static String JUMP_RIGHT_2 = "jump-right-2";
    public final static String JUMP_RIGHT_3 = "jump-right-3";

    public final static String WALK_RIGHT_1 = "walk-right-1";
    public final static String WALK_RIGHT_2 = "walk-right-2";
    public final static String WALK_RIGHT_3 = "walk-right-3";

    public final static String WALK_LEFT_1 = "walk-left-1";
    public final static String WALK_LEFT_2 = "walk-left-2";
    public final static String WALK_LEFT_3 = "walk-left-3";

    public final static float ANIM_LOOP_DUR = 0.25f;

    //Level Sprites

    public final static String PLATFORM_TYPE_A = "platform-type-a";
    public final static int PLATFORM_TYPE_A_EDGE = 5;

    //TODO add TextureAtlas Path
    public final static String TEXTURE_ATLAS = "images/ninjagirl.image.png";

    //Game States ENUMS

    public enum Direction {
        LEFT,
        RIGHT
    }

    public enum JumpState {
        JUMPING,
        GLIDING,
        FALLING,
        GROUNDED
    }

    public enum WalkState {
        WALKING,
        STANDING
    }

    public enum CharacterSelection {
        THUNDER,
        FIRE,
        WATER
    }
}
