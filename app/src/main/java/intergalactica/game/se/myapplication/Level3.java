package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;

public class Level3 extends Level {

    public static final int LEVEL_ID = 3;

    private ActorFactory actorFactory;
    private Actor backgroundActor;

    public Level3(Context context, ActorFactory actorFactory) {

        super(context, LEVEL_ID, actorFactory);
        this.actorFactory = actorFactory;


    }

    @Override
    public void update() {


        backgroundActor.update();
    }

    @Override
    public void draw() {

        GLES30.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        backgroundActor.render();


    }

    @Override
    public void createLevel() {

        String[] attributes = new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"};
        int[] shaders = {R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(ActorFactory.LEVEL1_BITMAP_IDX);
        backgroundActor = actorFactory.createActor("Background");
        backgroundActor.create();

        levelCreated = true;
    }

}
