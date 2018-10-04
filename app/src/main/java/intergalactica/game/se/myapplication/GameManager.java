package intergalactica.game.se.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import renderlib.opengles.se.myapplication.GLprojection;

public class GameManager {

    private Level level; //abstrakt basklass
    private Context context;
    private ResourceLoader resourceLoader;
    private Bitmap[] bitmaps;

    private GLprojection gLprojection = new GLprojection();

    public GameManager(Context context, int level_id) {

        Looper.prepare();
        this.context = context;
        resourceLoader = new ResourceLoader(context);
        preLoadResources();
        nextLevel(level_id);



    }

    public GLprojection getgLprojection() {

        return this.gLprojection;
    }

    private void initLevelMap(GLprojection gLprojection) {

        level.levelMap.setContext(context);
        level.levelMap.setBackgroundBitmap(bitmaps[0]);
        level.levelMap.setProjection(gLprojection);
        level.levelMap.createTextures();

    }

    public Level getLevel() {

        return this.level;
    }

    public void nextLevel(int level) {

        switch (level) {

            case 1:
                this.level = new Level1(bitmaps);
                initLevelMap(gLprojection);
                infoNewLevel("level1");

                break;
            case 2:
                this.level = new Level2();
                initLevelMap(gLprojection);
                infoNewLevel("level2");
                break;
            case 3:
                this.level = new Level3();
                initLevelMap(gLprojection);
                infoNewLevel("level3");
                break;


        }
    }

    private void infoNewLevel(final String text) {

        Log.d("NY LEVEL", text);

        ((Activity)context).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                ((Activity)context).setTitle(text);

            }
        });

    }

    private void preLoadResources() {

        float width = context.getResources().getDisplayMetrics().widthPixels;
        float height = context.getResources().getDisplayMetrics().heightPixels;
        //float[][] bitmapDimens, String[] bitmapNames, String resFolderName
        float[][] bitmapDimens = { {width, height} };
        String[] bitmapNames = {"level_map"};
        bitmaps = resourceLoader.createBitmaps(bitmapDimens, bitmapNames, "drawable");

    }

    public Bitmap[] getBitmaps() {

        return this.bitmaps;
    }


}
