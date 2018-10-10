package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class Level {

    private Context context;
    private int level;
    protected LevelMap levelMap;
    protected boolean levelCreated;


    public Level(Context context, int level, ActorFactory actorFactory) {

        this.level = level;
        levelMap = new LevelMap(context, actorFactory);
    }

    public int getLevel() {

        return this.level;
    }

    public abstract void update();
    public abstract void draw();
    public abstract void createLevel();


}
