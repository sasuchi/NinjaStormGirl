package com.sasuchi.entertainment.enti;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sasuchi.entertainment.util.Assets;

/**
 * Created by Sascha on 13.06.2016.
 */
public class LevelObject {

    public float top;
    public float bottom;
    public float left;
    public float right;
    String identifier;

    public LevelObject(float top, float left, float height, float width){
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
    }

    public void render(SpriteBatch batch) {
        final float width = right - left;
        final float height = bottom - top;
        Assets.instance.levelAsset.platformTypeAPatch.draw(batch,left-1,bottom-1,width+2,height+2);
    }

    public String getIdentifier(){
        return identifier;
    }

    public void setIdentifier(String name) {
        this.identifier = name;
    }

}
