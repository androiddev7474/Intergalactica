package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.Matrix;

import compileshaders.opengles.se.shader_compile.Compile;
import renderlib.opengles.se.myapplication.GLcamera;
import renderlib.opengles.se.myapplication.GLprojection;
import renderlib.opengles.se.myapplication.GLrender;
import renderlib.opengles.se.myapplication.Models;


public class LevelMap {

    private boolean isActive = true;
    private Bitmap backgroundBitmap;
    private int mProgramHandle;
    private Context context;

    //egna klasser/bibliotek
    private Models models = new Models();
    private GLcamera gLcamera = new GLcamera();
    private GLprojection gLprojection;
    private GLrender gLrender;

    ActorFactory actorFactory = new ActorFactory();

    public LevelMap() {

        actorFactory.createActor("Background");


    }

    public void draw() {


        GLES20.glClearColor(0.49f, 0.27f, 0.44f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setIdentityM(models.getmModelMatrix(), 0);
        Matrix.translateM(models.getmModelMatrix(), 0, 3.22f, 5, 0);
        //Matrix.scaleM(models.getmModelMatrix(), 0, 12.86f, 20f, 0f);
        Matrix.scaleM(models.getmModelMatrix(), 0, 6.43f, 10f, 0f);

        gLrender.render();
    }

    public void update() {

    }

    public void setProjection(GLprojection gLprojection) {

        this.gLprojection = gLprojection;
    }

    public void setBackgroundBitmap(Bitmap backgroundBitmap) {
        this.backgroundBitmap = backgroundBitmap;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public void createTextures() {

        Texture texture = TextureFactory.createTexture(backgroundBitmap);


        String[] attributes = new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"};
        Compile compile = new Compile(context, R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader, attributes);
        mProgramHandle = compile.getmProgramHandle();

        models.create2Dpolygon(0.5f, 0.5f, 0f);

        float[] blc = {0, 0};
        float[] brc = {1, 0};
        float[] tlc = {0, 1};
        float[] trc = {1, 1};

        models.createTextData(blc, brc, tlc, trc);

        gLrender = new GLrender(models, gLcamera, gLprojection, context);
        gLrender.initBuffers();

        gLrender.setProgramHandle(mProgramHandle);
        gLrender.setHandles();
        gLcamera.createCamera();


    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
