package intergalactica.game.se.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.content.Context.MODE_PRIVATE;

public class GameRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private GameManager gameManager;
    public static final int DEFAULT_LEVEL = 1;
    private int level = DEFAULT_LEVEL;
    private int frameCounter = 5;
    private float xPos, yPos;
    private boolean down, up, move;

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

        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        //float[][] bitmapDimens, String[] bitmapNames, String resFolderName
        int[][] bitmapDimens = { {width, height} };
        String[] bitmapNames = {"level_map"};
        //Context context, String fileName, String resFolderName, int width, int height, int type, int minFilter, int magFilter, int wrapMode
        TextureFactory.initialize();
        Texture myTexture = TextureFactory.getInstance().createTexture(context, bitmapNames[0], "drawable", bitmapDimens[0][0], bitmapDimens[0][1], GLES30.GL_TEXTURE_2D, GLES30.GL_LINEAR, GLES30.GL_LINEAR, GLES30.GL_CLAMP_TO_EDGE);
        int y = 1;

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

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
