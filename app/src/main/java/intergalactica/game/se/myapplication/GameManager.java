package intergalactica.game.se.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GameManager {

    private Level level; //abstrakt basklass
    private Context context;

    public GameManager(Context context, int level_id) {

        Looper.prepare();
        this.context = context;
        nextLevel(level_id);

    }

    public Level getLevel() {

        return this.level;
    }

    public void nextLevel(int level) {

        switch (level) {

            case 1:
                this.level = new Level1();
                infoNewLevel("level1");

                break;
            case 2:
                this.level = new Level2();
                infoNewLevel("level2");
                break;
            case 3:
                this.level = new Level3();
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


}
