package intergalactica.game.se.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import renderlib.opengles.se.myapplication.GLprojection;

import static android.content.Context.MODE_PRIVATE;

public class GameRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private GameManager gameManager;
    public static final int DEFAULT_LEVEL = 1;
    private int level = DEFAULT_LEVEL;
    private int frameCounter = 5;
    private float xPos, yPos;
    private boolean down, up, move;

    private Bitmap levelMap;

    private GLprojection gLprojection = new GLprojection();

    public void setLevelMap(Bitmap levelMap) {

        this.levelMap = levelMap;
    }

    public GameRenderer(Context context) {

        this.context = context;
    }

    public GameManager getGameManager() {

        return this.gameManager;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        SharedPreferences prefs = context.getSharedPreferences(GameActivity.MY_PREFS_NAME, MODE_PRIVATE);
        level = prefs.getInt(GameActivity.MY_PREFS_STORED_LEVEL_ID, DEFAULT_LEVEL);

        gameManager = new GameManager(context, level);
        getGameManager().initLevelMap(gLprojection);

        GLES20.glEnable(GLES30.GL_BLEND);
        GLES20.glBlendFunc(GLES30.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {

        GLES30.glViewport(0, 0, width, height);
        orthoProject(width, height, -1, 1);
    }


    public void orthoProject(int width, int height, float near, float far) {


        /*final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        */
        final float ratio = (float) height / width;
        final float bottom = 0f;
        final float top = 10f;
        final float left = 0;
        final float right = top / ratio;
        //this.right = right;
        //this.top = top;

        //CollisionWalls.setWallParams(0, right, top, bottom);
        //projicering
        //Matrix.frustumM(gLprojection.getmProjectionMatrix(), 0, left, right, bottom, top, near, far);
        /*Matrix.orthoM(gLprojection.getmProjectionMatrix(), 0,  0, width,
                0, height,  near, far);
                */

        Matrix.orthoM(gLprojection.getmProjectionMatrix(), 0,  0, right, bottom, top,  -1, 1);

        int y = 111;
    }

    @Override
    public void onDrawFrame(GL10 gl10) {



        touch();

        /*
            Kartan visas alltid innan en ny level påbörjas. Genom att klicka på en avgränsad del av skärmen så startar den aktuella leveln.
         */
        boolean mapIsActive = getGameManager().getLevel().levelMap.isActive();
        //Log.d("MAP IS ACTIVE", "" + mapIsActive);
        if (mapIsActive) {
            showMap();
        } else {
            startGame();
        }


        frameCounter++;

    }

    private void showMap() {


        getGameManager().getLevel().levelMap.draw();

        if (yPos > 1000) {

            getGameManager().getLevel().levelMap.setActive(false);
        }
    }

    private void startGame() {


        if (frameCounter % 300 == 0) {

            level++;
            if (level > 3)
                level = 1;

            getGameManager().nextLevel(level);
            //nollställ
            getGameManager().getLevel().levelMap.setActive(true);
            yPos = -1;
        }


        gameManager.getLevel().draw();
        gameManager.getLevel().update();

    }

    private void touch() {

        if (down)
            Log.i("ACTION", "TOUCH" + " x, y: " + xPos + ", " + yPos);
        if (move)
            Log.i("ACTION", "MOVE" + " x, y: " + xPos + ", " + yPos );
    }

    public void setXpos(float xPos) {

        this.xPos = xPos;
    }

    public void setYpos(float yPos) {

        this.yPos = yPos;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
