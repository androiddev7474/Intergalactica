package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import libkitv1.opengles.se.opengllibkit1.TextureDataFormatter;

public class Level2 extends Level {

    private static final int N_BOOGERS =7;
    private static final int N_SHOTS = 16;
    private final static int PENDING_STATE = 0;
    private final static int BOOGER_ATTACK1 = 1;
    private final static int BOOGER_ATTACK2 = 2;
    private final static int BOOGER_ATTACK3 = 3;
    private final static int BOOGER_ATTACK4 = 4;
    private final static int NEXT_LEVEL = 5;
    private int current_obstacle = PENDING_STATE;
    private boolean nextObstacle = true;
    private boolean delay = true;
    private boolean playerOnScene = true;


    public static final int LEVEL_ID = 1;
    private ActorLoader actorLoader;
    private Context context;

    private int frameCntr;


    private float[][] alienTextData, batBoogTextData;
    public static final String BAT_BOOGER = "batbooger";



    Timer timer = new Timer();

    public Level2(Context context, ActorLoader actorLoader) {

        super(context, LEVEL_ID, actorLoader);
        this.context = context;
        this.actorLoader = actorLoader;

    }


    public void createLevel() {

        String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
        int[] shadersBackground = {R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        createBackground(attributes, shadersBackground, attrsUnifs, ActorFactory.LEVEL1_BITMAP_IDX, ActorFactory.BACKGROUND_ACTOR, 0);




        levelCreated = true;

    }


    public void update() {

        for (Actor playerActor: playerActorList) {
            if (!isNotTouching)
                ((ControlComponent) playerActor.getComponent(ComponentFactory.CONTROLCOMPONENT)).setTouchCoords(touchX * fractionWidth, touchY * fractionHeight);
        }

        backgroundActor[0].update();

        for (Actor actor: scoreActorList)
            actor.update();

        for (Actor playerBurnerActor: playerBurnerActorList)
            playerBurnerActor.update();

        for (Actor playerActor: playerActorList)
            playerActor.update();


        spaceShipManager.burner();
        deathTouchActor.update();

        for (Actor actor: playerLifeList)
            actor.update();

        run();
        for (Actor actor : boogerList) {
            actor.update();
            CollisionManager.sceneCollider(actor);
        }

        for (Actor actor: explosionList)
            actor.update();


        explosionManager.removeExplosions();

        shotManager.shotIssuer();
        Iterator<Actor> iter = shotList.iterator();
        while (iter.hasNext()) {
            Actor actor = iter.next();
            actor.update();
        }
        shotManager.removeShot();


        boogerVSplayerShotCollision.checkShotCollision(shotList, boogerList, boogerPoolList);


        boogerCollisionManager.checkSingleListCollision(boogerList);
        boogerCollisionManager.checkDeathActorCollision(deathTouchActor, boogerList, boogerPoolList);

        boolean playerHit = CollisionManager.checkCollisionPlayer(playerActorList, boogerList);
        if(playerHit) {

            handlePlayerHit();
        }


        boogerCollisionManager.save(boogerList);
        boogerCollisionManager.save(deathTouchActor);
        boogerVSplayerShotCollision.save(shotList);
        boogerCollisionManager.reflectOnCollision();


    }


    @Override
    public void draw() {

        //GLES30.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        backgroundActor[0].render();

        for (Actor actor: scoreActorList)
            actor.render();

        for (Actor playerBurnerActor: playerBurnerActorList)
            playerBurnerActor.update();

        for (Actor playerActor: playerActorList)
            playerActor.render();

        for (Actor actor: boogerList)
            actor.render();

        nextObstacle(nextObstacle);

        for (Actor actor: shotList)
            actor.render();

        for (Actor actor: explosionList)
            actor.render();

        for (Actor actor: playerLifeList)
            actor.render();


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

        if (delay && playerOnScene) {

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
                    //createObstacle();
                    advanceToNextLevel = true;
                    break;
                case BOOGER_ATTACK3:
                    createObstacle();
                    break;
                case BOOGER_ATTACK4:
                    createObstacle();
                    break;
                case NEXT_LEVEL:
                    advanceToNextLevel = true;
                    break;

            }

        }

        frameCntr++;
    }

    public void createObstacle() {



        for (ActorHolder actorHolder: boogerPoolList) {

            if ( actorHolder.isAvailable()) {

                Actor actor = actorHolder.getActor();
                ((BatBoogerBehaviourComponent)actor.getComponent(ComponentFactory.BATBOOGERBEHAVIOURCOMPONENT)).create();
                BoxColliderComponent collider = (BoxColliderComponent)actor.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
                ((LifeComponent)actor.getComponent(ComponentFactory.LIFECOMPONENT)).setCurrentHealth(50);
                collider.setPreviousBoxLeft(0);
                collider.setPreviousBoxTop(0);
                collider.setPreviousBoxRight(0);
                collider.setPreviousBoxBottom(0);
                boogerList.add(actorHolder.getActor());
                actorHolder.setAvailable(false);
            }

            if (boogerList.size() == 7)
                break;
        }

    }

    private void handlePlayerHit() {

        boogerList.clear();
        reInitActorPools();
        playerLifeManager.removeLife();
        String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
        int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        actorLoader.createPlayer(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.PLAYER_ACTOR);
        playerOnScene = false;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                playerActorList.add(playerActor);
                playerOnScene = true;
            }
        }, 5000);

        current_obstacle--; // börja om

    }

}
