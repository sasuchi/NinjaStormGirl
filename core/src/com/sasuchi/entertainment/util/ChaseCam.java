package com.sasuchi.entertainment.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.sasuchi.entertainment.enti.CharacterClass;

/**
 * Created by Zascha on 13.06.2016.
 */
public class ChaseCam {

    public static final String TAG = ChaseCam.class.getName();

    public Camera camera;
    public CharacterClass target;
    private boolean fixed;

    public ChaseCam() {
        fixed = true;
    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            fixed = !fixed;
        }

        if (fixed) {
            camera.position.x = target.getCurrentPosition().x;
            camera.position.y = target.getCurrentPosition().y;
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                camera.position.x -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                camera.position.x += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                camera.position.y += delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                camera.position.y -= delta * Constants.CHASE_CAM_MOVE_SPEED;
            }
        }
    }

}
