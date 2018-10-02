package intergalactica.game.se.myapplication;

import android.opengl.GLES20;

public class LevelMap {

    private boolean isActive = true;

    public void draw() {

        GLES20.glClearColor(0.49f, 0.27f, 0.44f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    public void update() {

    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
