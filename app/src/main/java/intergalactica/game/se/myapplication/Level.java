package intergalactica.game.se.myapplication;

public abstract class Level {

    private int level;

    public Level(int level) {

        this.level = level;
    }

    public int getLevel() {

        return this.level;
    }

    public abstract void update();
    public abstract void draw();


}
