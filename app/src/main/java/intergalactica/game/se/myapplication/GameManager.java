package intergalactica.game.se.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;



public class GameManager {

    private Level level; //abstrakt basklass
    private Context context;
    private ResourceLoader resourceLoader;
    private Bitmap[] bitmaps;

    private ActorFactory actorFactory;

    public GameManager(Context context, int level_id) {

        Looper.prepare();
        this.context = context;
        resourceLoader = new ResourceLoader(context);
        preLoadResources();
        actorFactory = new ActorFactory(bitmaps);
        nextLevel(level_id);


    }


    public Level getLevel() {

        return this.level;
    }

    public void nextLevel(int level) {

        switch (level) {

            case 1:
                this.level = new Level1(context, actorFactory);
                //initLevelMap(gLprojection);
                infoNewLevel("level1");

                break;
            case 2:
                this.level = new Level2(context, actorFactory);
                //initLevelMap(gLprojection);
                infoNewLevel("level2");
                break;
            case 3:
                this.level = new Level3(context, actorFactory);
                //initLevelMap(gLprojection);
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
        float[][] bitmapDimens = { {width, height} };
        String[] bitmapNames = {"level_map"};
        bitmaps = resourceLoader.createBitmaps(bitmapDimens, bitmapNames, "drawable");

    }



}
