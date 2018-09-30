package intergalactica.game.se.myapplication;

import android.opengl.GLES20;
import android.opengl.GLES30;

public class Level1 extends Level {

    public static final int LEVEL_ID = 1;

    public Level1() {

        super(LEVEL_ID);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {


        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

}
