package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

public abstract class Level {

    private Context context;
    private int level;
    private ActorFactory actorFactory;
    protected LevelMap levelMap;
    protected boolean levelCreated;
    protected boolean advanceToNextLevel = false;
    protected static boolean game_over;
    public float touchX, touchY;
    public boolean isNotTouching;
    public float displayWidth, displayHeight;
    public float fractionWidth, fractionHeight;
    public static int gameScore;

    private static final int N_BACKGROUND_LAYERS = 3;
    protected Actor[] backgroundActor = new Actor[N_BACKGROUND_LAYERS];
    protected Actor playerActor, playerLifeActor, scoreActor, playerBurnerActor, shotActor, batboogerActor, batBrainsActor, deathTouchActor;
    protected ArrayList<ActorHolder> boogerPoolList;
    protected ArrayList<ActorHolder> brainsPoolList;
    protected ArrayList <ActorHolder> explosionPoolList;
    protected ArrayList <ActorHolder> shotPoolList;
    protected ArrayList <Actor> boogerList;
    protected ArrayList <Actor> brainsList;
    protected ArrayList <Actor> explosionList;
    protected ArrayList <Actor> shotList;
    protected ArrayList <Actor> playerLifeList;
    protected ArrayList <Actor> playerActorList;
    protected ArrayList <Actor> scoreActorList;
    protected ArrayList <Actor> playerBurnerActorList;

    protected CollisionManager boogerCollisionManager;
    protected CollisionManager boogerVSplayerShotCollision;
    protected ExplosionManager explosionManager;
    protected SpaceShipManager spaceShipManager;
    protected ShotManager shotManager;
    protected PlayerLifeManager playerLifeManager;
    protected  ScoreManager scoreManager;


    public Level(Context context, int level, ActorLoader actorLoader) {

        this.level = level;
        this.actorFactory = actorLoader.getActorFactory();
        levelMap = new LevelMap(context, actorFactory);

        displayWidth = context.getResources().getDisplayMetrics().widthPixels;
        displayHeight = context.getResources().getDisplayMetrics().heightPixels;

        fractionWidth = GameRenderer.getGameSceneRight() / displayWidth;
        fractionHeight = GameRenderer.GAMESCENE_TOP / displayHeight;

        deathTouchActor = actorFactory.createActor(ActorFactory.DEATHTOUCH_ACTOR);
        deathTouchActor.create();


        playerActor = actorLoader.getPlayerActor();
        playerLifeActor = actorLoader.getPlayerLifeActor();
        scoreActor = actorLoader.getScoreActor();
        playerBurnerActor = actorLoader.getPlayerBurnerActor();
        shotActor = actorLoader.getShotActor();
        batboogerActor = actorLoader.getBatboogerActor();
        boogerPoolList = actorLoader.getBoogerPoolList();
        brainsPoolList = actorLoader.getBrainsPoolList();
        explosionPoolList = actorLoader.getExplosionPoolList();
        shotPoolList = actorLoader.getShotPoolList();
        boogerList = actorLoader.getBoogerList();
        brainsList = actorLoader.getBrainsList();
        explosionList = actorLoader.getExplosionList();
        shotList = actorLoader.getShotList();
        playerLifeList = actorLoader.getPlayerLifeList();
        playerActorList = actorLoader.getPlayerActorList();
        scoreActorList = actorLoader.getScoreActorList();
        playerBurnerActorList = actorLoader.getPlayerBurnerActorList();

        scoreManager = new ScoreManager(scoreActorList);
        explosionManager = new ExplosionManager(explosionPoolList, explosionList);
        shotManager = new ShotManager(shotList, shotPoolList, playerActorList);
        boogerVSplayerShotCollision = new CollisionManager(explosionList, explosionManager, shotManager, scoreManager);
        boogerCollisionManager = new CollisionManager(explosionList, explosionManager, shotManager, scoreManager);
        spaceShipManager = new SpaceShipManager(playerActorList, playerBurnerActor);
        playerLifeManager = new PlayerLifeManager(playerActorList, playerBurnerActorList, playerLifeList, explosionManager);


    }

    public int getLevel() {

        return this.level;
    }


    public abstract void update();
    public abstract void draw();
    public abstract void createLevel();




    public void createBackground(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int layer) {


        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(bitmapID);
        backgroundActor[layer] = actorFactory.createActor(actorName);
        backgroundActor[layer].create();
    }


    public void reInitActorPools() {

        for (ActorHolder holder: boogerPoolList) {

            holder.setAvailable(true);
        }

        for (ActorHolder holder: brainsPoolList) {

            holder.setAvailable(true);
        }
    }


    public boolean isGameOver() {

        return game_over;
    }


    public static void setGame_over(boolean game_over) {
        Level.game_over = game_over;
    }


}
