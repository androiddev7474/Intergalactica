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

import static android.content.Context.MODE_PRIVATE;

public class GameRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private GameManager gameManager;
    public static final int DEFAULT_LEVEL = 1;
    private int level_id = DEFAULT_LEVEL;
    private int frameCounter = 5;
    private float xPos, yPos;
    private boolean down, up, move;

    //globala parametrar för spelets koordinatsystem. Scenens högra kant räknas senare ut med hjälp av aspect ratio.
    public static final float GAMESCENE_LEFT = 0;
    public static final float GAMESCENE_TOP = 10;
    public static final float GAMESCENE_BOTTOM = 0;
    private static float gameSceneRight;

    private Bitmap levelMap;

    //private GLprojection gLprojection;

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
        level_id = prefs.getInt(GameActivity.MY_PREFS_STORED_LEVEL_ID, DEFAULT_LEVEL);


        //gLprojection = gameManager.getgLprojection();

        GLES20.glEnable(GLES30.GL_BLEND);
        GLES20.glBlendFunc(GLES30.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLcamera.createCamera();

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {

        GLES30.glViewport(0, 0, width, height);
        //orthoProject(width, height, -1, 1);
        gameSceneRight = GLprojection.orthoProject(width, height, -1, 1, GAMESCENE_LEFT, GAMESCENE_TOP, GAMESCENE_BOTTOM);

        gameManager = new GameManager(context, level_id);
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

        if(!getGameManager().getLevel().levelCreated) {
            getGameManager().getLevel().createLevel();
        }

        if (frameCounter % 300 == -1) {

            level_id++;
            if (level_id > 3)
                level_id = 1;


            getGameManager().nextLevel(level_id);

            //nollställ
            getGameManager().getLevel().levelMap.setActive(true);
            yPos = -1;
        }

        if(!getGameManager().getLevel().levelMap.isActive()) {
            gameManager.getLevel().draw();
            gameManager.getLevel().update();
        }

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

    public static float getGameSceneRight() {
        return gameSceneRight;
    }
}
