package intergalactica.game.se.myapplication;

public abstract class Level {

    private int level;
    protected LevelMap levelMap;

    public Level(int level) {

        this.level = level;
        levelMap = new LevelMap();
    }

    public int getLevel() {

        return this.level;
    }

    public abstract void update();
    public abstract void draw();


}
