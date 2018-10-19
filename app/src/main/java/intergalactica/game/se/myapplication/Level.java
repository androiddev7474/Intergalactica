package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class Level {

    private Context context;
    private int level;
    protected LevelMap levelMap;
    protected boolean levelCreated;
    public float touchX, touchY; //
    public float displayWidth, displayHeight;
    public float fractionWidth, fractionHeight;

    public Level(Context context, int level, ActorFactory actorFactory) {

        this.level = level;
        levelMap = new LevelMap(context, actorFactory);

        displayWidth = context.getResources().getDisplayMetrics().widthPixels;
        displayHeight = context.getResources().getDisplayMetrics().heightPixels;

        fractionWidth = GameRenderer.getGameSceneRight() / displayWidth;
        fractionHeight = GameRenderer.GAMESCENE_TOP / displayHeight;

        int y = 1;
    }

    public int getLevel() {

        return this.level;
    }

    public void touchInput() {




    }

    public abstract void update();
    public abstract void draw();
    public abstract void createLevel();


}
