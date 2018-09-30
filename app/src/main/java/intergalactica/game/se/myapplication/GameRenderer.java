package intergalactica.game.se.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
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

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {


        //Log.d("render", "drawframe");


        if (frameCounter % 300 == 0) {

            level++;
            if (level > 3)
                level = 1;

            getGameManager().nextLevel(level);
        }


        gameManager.getLevel().draw();
        gameManager.getLevel().update();

        frameCounter++;

    }
}
