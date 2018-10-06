package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;

public class Level3 extends Level {

    public static final int LEVEL_ID = 3;

    public Level3(Context context, ActorFactory actorFactory) {

        super(context, LEVEL_ID, actorFactory);


    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

        GLES30.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

    }

}
