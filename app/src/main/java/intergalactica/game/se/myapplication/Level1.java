package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import libkitv1.opengles.se.opengllibkit1.TextureDataFormatter;

public class Level1 extends Level {

    private static final int N_BOOGERS = 20;
    private final static int PENDING_STATE = 0;
    private final static int BOOGER_ATTACK1 = 1;
    private final static int BOOGER_ATTACK2 = 2;
    private int current_obstacle = PENDING_STATE;
    private boolean nextObstacle = true;
    private boolean delay = true;


    public static final int LEVEL_ID = 1;
    private ActorFactory actorFactory;
    private Context context;

    private int frameCntr;


    private float[][] alienTextData, batBoogTextData;
    public static final String BAT_BOOGER = "batbooger";


    private Actor backgroundActor, playerActor, playerBurnerActor, batboogerActor, deathTouchActor;
    private ArrayList <ActorHolder> boogerPoolList = new ArrayList();
    private ArrayList <ActorHolder> explosionPoolList = new ArrayList<>();
    private ArrayList <Actor> boogerList = new ArrayList<>();
    private ArrayList <Actor> explosionList = new ArrayList();

    private CollisionManager boogerCollisionManager;
    private ExplosionManager explosionManager;
    private SpaceShipManager spaceShipManager;

    Timer timer = new Timer();

    public Level1(Context context, ActorFactory actorFactory) {

        super(context, LEVEL_ID, actorFactory);
        this.context = context;
        this.actorFactory = actorFactory;

    }

    @Override
    public void createLevel() {

        String[] attributes = new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"};
        int[] shaders = {R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(ActorFactory.LEVEL1_BITMAP_IDX);
        backgroundActor = actorFactory.createActor(ActorFactory.BACKGROUND_ACTOR);
        backgroundActor.create();

        createPlayer();
        createPlayerBurner();

        spaceShipManager = new SpaceShipManager(playerActor, playerBurnerActor);

        deathTouchActor = actorFactory.createActor(ActorFactory.DEATHTOUCH_ACTOR);
        deathTouchActor.create();

        createActorPool();
        //createObstacle();

        explosionManager = new ExplosionManager(explosionPoolList, explosionList);
        boogerCollisionManager = new CollisionManager(boogerList, deathTouchActor, explosionList, explosionManager);



        //int n_boogers = createEnemies();
        //explosionManager.explosions(n_boogers, ActorFactory.BATBOOGER_ACTOR, actorFactory);



        levelCreated = true;
    }

    private void createPlayer() {

        String[] attributes = new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"};
        int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
        playerActor = actorFactory.createActor(ActorFactory.PLAYER_ACTOR);
        playerActor.create();


    }

    private void createPlayerBurner() {

        String[] attributes = new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"};
        int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
        playerBurnerActor = actorFactory.createActor(ActorFactory.SHIPBURNER_ACTOR);
        playerBurnerActor.create();


    }

    /*private int createEnemies() {

        final int N_BOOGERS = 7;
        //batbooger
        for (int i = 0; i < N_BOOGERS; i++) {

            TextureDataFormatter textureDataFormatter = new TextureDataFormatter(context);
            textureDataFormatter.getTextureData(R.array.aliendata, R.array.alienatlas_dimen);
            batBoogTextData = textureDataFormatter.getSortedSpriteData(BAT_BOOGER);

            String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
            int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};
            String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
            batboogerActor = actorFactory.createActor(ActorFactory.BATBOOGER_ACTOR);
            batboogerActor.create();

            boogerList.add(batboogerActor);

        }

        return N_BOOGERS;
    }*/



    public void update() {

        backgroundActor.update();
        playerBurnerActor.update();
        playerActor.update();
        spaceShipManager.burner();
        deathTouchActor.update();
        run();
        for (Actor actor : boogerList) {
            actor.update();
            CollisionManager.sceneCollider(actor);
        }

        for (Actor actor: explosionList) {
            actor.update();

        }
        explosionManager.removeExplosions();



        boogerCollisionManager.checkCollision();
        boogerCollisionManager.checkDeathActorCollision();
        boogerCollisionManager.save();
        boogerCollisionManager.reflectOnCollision();

        float y_ = touchY * fractionHeight;

        ((ControlComponent)playerActor.getComponent(ComponentFactory.CONTROLCOMPONENT)).setTouchCoords(touchX * fractionWidth , touchY * fractionHeight);
        //deathTouchActor.update();


    }


    @Override
    public void draw() {

        //GLES30.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        backgroundActor.render();
        playerBurnerActor.render();
        playerActor.render();
        for (Actor actor: boogerList)
            actor.render();

        nextObstacle(nextObstacle);



        for (Actor actor: explosionList) {

            int id = ((AnimationComponent)actor.getComponent(ComponentFactory.ANIMATIONCOMPONENT)).getCurrentAnimation().getID();
            actor.render();
            id = ((AnimationComponent)actor.getComponent(ComponentFactory.ANIMATIONCOMPONENT)).getCurrentAnimation().getID();
            System.err.println("image id = " + id);

        }



    }

    public void run() {

        if (boogerList.size() == 0) {

            triggerNextObstacle();
        } else {

            nextObstacle = false;
            delay = true;
        }

    }


    public void triggerNextObstacle() {

        if (delay) {

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    nextObstacle = true;
                    current_obstacle++;
                }
            }, 5000);

            delay = false;
        }

    }


    public void nextObstacle(boolean nextObstacle) {

        if (nextObstacle) {

            switch (current_obstacle) {


                case PENDING_STATE:


                    break;
                case BOOGER_ATTACK1:
                    createObstacle();
                    break;
                case BOOGER_ATTACK2:
                    createObstacle();
                    break;

            }

        }


    }

    public void createObstacle() {



            for (ActorHolder actorHolder: boogerPoolList) {

                if ( actorHolder.isAvailable()) {
                    boogerList.add(actorHolder.getActor());
                    actorHolder.setAvailable(false);
                }

                if (boogerList.size() == 7)
                break;
            }

    }

    public void reInitBoogerPool() {

        for (ActorHolder holder: boogerPoolList) {



        }

    }

    public void createActorPool() {

        createBoogers();
        createExplosions(ActorFactory.BATBOOGER_ACTOR);



    }

    private int createBoogers() {


        //batbooger
        for (int i = 0; i < N_BOOGERS; i++) {

            TextureDataFormatter textureDataFormatter = new TextureDataFormatter(context);
            textureDataFormatter.getTextureData(R.array.aliendata, R.array.alienatlas_dimen);
            batBoogTextData = textureDataFormatter.getSortedSpriteData(BAT_BOOGER);

            String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
            int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};
            String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
            batboogerActor = actorFactory.createActor(ActorFactory.BATBOOGER_ACTOR);
            batboogerActor.create();

            boogerPoolList.add(new ActorHolder(batboogerActor, true));

        }

        return N_BOOGERS;
    }

    public void createExplosions(String actorName) {

        for (int i = 0; i < N_BOOGERS; i++) {

            String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
            int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};
            String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
            Actor explosionActor = actorFactory.createActor(ActorFactory.EXPLOSION_ACTOR);
            explosionActor.create();
            explosionPoolList.add(new ActorHolder(explosionActor, true, actorName));
        }


    }





}
