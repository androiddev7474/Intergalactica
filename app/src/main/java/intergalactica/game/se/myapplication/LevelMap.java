package intergalactica.game.se.myapplication;

import android.content.Context;
import android.opengl.GLES30;

public class LevelMap {


    private boolean isActive = true;
    private Context context;

    private Actor[] backgroundActor = new Actor[3];

    public LevelMap(Context context, ActorFactory actorFactory) {

        this.context = context;

        {
            String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
            int[] shaders = {R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader};
            String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.LEVEL_BITMAP);
            backgroundActor[0] = actorFactory.createActor(ActorFactory.STATIC_BACKGROUND_ACTOR);
            backgroundActor[0].create();
        }

        {
            String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
            int[] shaders = {R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader};
            String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.LEVEL1_L2_BITMAP_IDX);
            backgroundActor[1] = actorFactory.createActor(ActorFactory.BACKGROUND_L2_ACTOR);
            backgroundActor[1].create();
        }

        {
            String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
            int[] shaders = {R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader};
            String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.LEVEL1_L3_BITMAP_IDX);
            backgroundActor[2] = actorFactory.createActor(ActorFactory.BACKGROUND_L3_ACTOR);
            backgroundActor[2].create();
        }

    }

    public void draw() {

        GLES30.glClearColor(0.49f, 0.27f, 0.44f, 0.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        backgroundActor[0].render();
        backgroundActor[1].render();
        backgroundActor[2].render();

        backgroundActor[0].update();
        backgroundActor[2].update();
        backgroundActor[2].update();
    }

    public void update() {

        //backgroundActor.update();
    }


    public void setContext(Context context) {

        this.context = context;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
