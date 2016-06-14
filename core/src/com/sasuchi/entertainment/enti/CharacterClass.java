package com.sasuchi.entertainment.enti;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sasuchi.entertainment.util.Constants;
import com.sasuchi.entertainment.util.UtilityFunctions;

/**
 * Created by Sascha on 13.06.2016.
 */
public class CharacterClass {

    public final static String TAG = CharacterClass.class.getName();

    private LevelClass level;
    private Vector2 currentPosition;
    private Vector2 spawnPosition;
    private Vector2 lastFramePosition;
    private Vector2 velocity;
    private Constants.Direction direction;
    private Constants.JumpState jumpState;
    private Constants.WalkState walkState;
    private long walkStartTime;
    private long jumpStartTime;
    private int extraLives;
    private int healthPoints;
    private String selectedCharacter;

    public boolean moveLeftButtonPressed;
    public boolean moveRightButtonPressed;
    public boolean attackButtonPressed;
    public boolean specialButtonPressed;
    public boolean jumpButtonPressed;

    public CharacterClass(Vector2 spawnPosition, LevelClass level, String selectedCharacter) {
        this.spawnPosition = spawnPosition;
        this.level = level;
        this.selectedCharacter = selectedCharacter;
        currentPosition = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();
        init();
    }

    public void init() {
        //TODO: Find out if neccessary
        //healthPoints = Constants.INIT_HEALTH;
        extraLives = Constants.INIT_LIVES;
        respawn();
    }

    private void respawn() {
        healthPoints = Constants.INIT_HEALTH;
        velocity.setZero();
        jumpState = Constants.JumpState.GROUNDED;
        direction = Constants.Direction.RIGHT;
        walkState = Constants.WalkState.STANDING;
    }

    public void update(float delta, Array<LevelObject> levelObjects) {
        lastFramePosition.set(currentPosition);
        velocity.y -= Constants.GRAVITY;
        currentPosition.mulAdd(velocity,delta);

        //Check Game-Over Situation
        if(currentPosition.y < Constants.KILL_PLANE){
            extraLives--;
            if(extraLives>=0){
                respawn();
            }
        }

        //TODO: Check Enemy Interaction

        //Check Level Interaction
        if(jumpState != Constants.JumpState.JUMPING) {
            if(jumpState != Constants.JumpState.GLIDING){
                jumpState = Constants.JumpState.FALLING;
            }

            for (LevelObject levelObject : levelObjects) {
                if(isLandedOn(levelObject)) {
                    jumpState = Constants.JumpState.GROUNDED;
                    velocity.x = 0;
                    velocity.y = 0;
                    currentPosition.y = levelObject.top + Constants.CHARACTER_SIZE.y;
                }
            }
        }

        //Check Movement Command
        if(jumpState != Constants.JumpState.GLIDING) {

            boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT)||moveLeftButtonPressed;
            boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT)||moveRightButtonPressed;

            if(left && !right){
                moveLeft(delta);
            } else if (right && !left){
                moveRight(delta);
            } else {
                walkState = Constants.WalkState.STANDING;
            }
        }

        //Check Jump Command
        if(Gdx.input.isButtonPressed(Input.Keys.SPACE) || jumpButtonPressed){
            switch(jumpState){
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
            }
        } else {
            endJump();
        }

        //Check Attack Command
        if(Gdx.input.isButtonPressed(Input.Keys.K) || attackButtonPressed){
            attack();
        }

        //Check Special Move Command
        if(Gdx.input.isButtonPressed(Input.Keys.L) || specialButtonPressed){
            specialMove();
        }

    }

    public void moveLeft(float delta) {
        if (jumpState == Constants.JumpState.GROUNDED && walkState != Constants.WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = Constants.WalkState.WALKING;
        direction = Constants.Direction.LEFT;
        currentPosition.x -= delta * Constants.CHAR_MOVE_SPEED;
    }

    public void moveRight(float delta) {
        if (jumpState == Constants.JumpState.GROUNDED && walkState != Constants.WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = Constants.WalkState.WALKING;
        direction = Constants.Direction.RIGHT;
        currentPosition.x += delta * Constants.CHAR_MOVE_SPEED;
    }

    public void startJump() {
        jumpState = Constants.JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();
    }

    public void continueJump() {
        if (jumpState == Constants.JumpState.JUMPING) {
            if (UtilityFunctions.secondsSince(jumpStartTime) < Constants.MAX_JUMP_HEIGHT) {
                velocity.y = Constants.JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }

    public void endJump() {
        if (jumpState == Constants.JumpState.JUMPING) {
            jumpState = Constants.JumpState.FALLING;
        }
    }

    public boolean isLandedOn(LevelObject levelObject) {
        return false;
    }

    public void attack() {
        Vector2 swordPosition;

        if(direction == Constants.Direction.RIGHT) {
            swordPosition = new Vector2(
                    currentPosition.x + Constants.SWORD_OFFSET.x,
                    currentPosition.y + Constants.SWORD_OFFSET.y
            );
        }else{
            swordPosition = new Vector2(
                    currentPosition.x - Constants.SWORD_OFFSET.x,
                    currentPosition.y + Constants.SWORD_OFFSET.y
            );
        }

        level.swordAttack(swordPosition, direction);
    }

    public void specialMove() {

    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }
}
