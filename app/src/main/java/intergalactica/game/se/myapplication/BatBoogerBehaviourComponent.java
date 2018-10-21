package intergalactica.game.se.myapplication;

import java.util.Random;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att ge Bat booger-fienden (eller någon annan typ av actor) ett beteendemönster som består av att röra sig diagonalt över skärmen
 */
public class BatBoogerBehaviourComponent extends BaseComponent {

    private static final float MAX_SPEED_X_DIRECTION = 0.065f;
    private static final float MIN_SPEED_X_DIRECTION = 0.035f;
    private static final float MAX_SPEED_Y_DIRECTION = 0.065f;
    private static final float MIN_SPEED_Y_DIRECTION = 0.035f;

    private TransformComponent transformComponent;
    private BoxColliderComponent boxColliderComponent;
    private MotionComponent motionComponent;
    private float xPos, yPos;

    public static boolean booger1Created;
    public static int incr;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        boxColliderComponent = (BoxColliderComponent)getOwner().getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        motionComponent = (MotionComponent)getOwner().getComponent(ComponentFactory.MOTIONCOMPONENT);



        //initBehaviour();
        /*switch (incr) {

            case 0:
                createBooger1();
                break;
            case 1:
                createBooger2();
                break;
            case 2:
                //createBooger3();
                break;

        }

        incr++;

        //testCollision6();
        */

        //testCollision2();
        createBooger1();

    }

    private void createBooger1() {


        motionComponent.set_velocityX(0.01f);
        motionComponent.set_velocityY(0.01f);

        //placering (första boogern vänstra kanten
        transformComponent.setX(motionComponent.getSceneWallLeft());
        //och y-led
        float y = motionComponent.getSceneWallTop();
        transformComponent.setY(y * 1f);

        //riktning
        motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
        motionComponent.set_yDirection(MotionComponent.HEADING_SOUTH);

    }

    private void createBooger2() {


        motionComponent.set_velocityX(0.02f);
        motionComponent.set_velocityY(0.02f);

        //placering (första boogern vänstra kanten
        transformComponent.setX(motionComponent.getSceneWallLeft());
        //och y-led
        float y = motionComponent.getSceneWallTop();
        transformComponent.setY(y * 0.8f);

        //riktning
        motionComponent.set_xDirection(MotionComponent.HEADING_WEST);

    }


    private void createBooger3() {


        motionComponent.set_velocityX(0.02f);
        motionComponent.set_velocityY(0.02f);

        //placering (första boogern vänstra kanten
        transformComponent.setX(motionComponent.getSceneWallLeft());
        //och y-led
        float y = motionComponent.getSceneWallTop();
        transformComponent.setY(y * 0.3f);

        //riktning
        motionComponent.set_xDirection(MotionComponent.HEADING_EAST);

    }


    private void testCollision() {


        //booger1
        if (!booger1Created) {

            //hastighet
            motionComponent.set_velocityX(0.02f);
            motionComponent.set_velocityY(0.02f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallLeft());
            //och y-led
            float y = motionComponent.getSceneWallTop();
            transformComponent.setY(y * 1.205f);

            //riktning
            motionComponent.set_xDirection(MotionComponent.HEADING_EAST);

        //booger2
        } else {

            //hastighet
            motionComponent.set_velocityX(0.02f);
            motionComponent.set_velocityY(0.02f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallRight());
            //och y-led
            float y = motionComponent.getSceneWallTop() / 2;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_xDirection(MotionComponent.HEADING_WEST);



        }

        booger1Created = true;

    }

    //lodrätt upp och ner
    //per 2018-10-14 och ändrad strategi så fungerar detta just nu dvs norr - syd
    private void testCollision2() {


        //booger1
        if (!booger1Created) {

            //hastighet
            motionComponent.set_velocityX(0);
            motionComponent.set_xDirection(MotionComponent.HEADING_NONE);
            motionComponent.set_velocityY(0.01f);

            //placering (första boogern vänstra kanten
            float x = motionComponent.getSceneWallRight() / 2;
            transformComponent.setX(x * 0.65f);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 3f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_NORTH);

            //booger2
        } else {

            //hastighet
            motionComponent.set_velocityX(0);
            motionComponent.set_xDirection(MotionComponent.HEADING_NONE);
            motionComponent.set_velocityY(0.01f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallRight() / 2);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 2;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_SOUTH);


        }

        booger1Created = true;

    }


    private void testCollision3() {


        //booger1
        if (!booger1Created) {

            //hastighet
            motionComponent.set_velocityX(0.005f);
            motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
            motionComponent.set_velocityY(0.02f);

            //placering (första boogern vänstra kanten
            float x = motionComponent.getSceneWallLeft();
            transformComponent.setX(x * 0.75f);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 3f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_NORTH);

            //booger2
        } else {

            //hastighet
            motionComponent.set_velocityX(0);
            motionComponent.set_xDirection(MotionComponent.HEADING_NONE);
            motionComponent.set_velocityY(0.02f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallRight() / 2);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 1;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_SOUTH);


        }

        booger1Created = true;

    }

    private void testCollision4() {


        //booger1
        if (!booger1Created) {

            //hastighet
            motionComponent.set_velocityX(0.06f);
            motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
            motionComponent.set_velocityY(0.01f);

            //placering (första boogern vänstra kanten
            float x = motionComponent.getSceneWallLeft();
            transformComponent.setX(x * 0.75f);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 3f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_NORTH);

            //booger2
        } else {

            //hastighet
            motionComponent.set_velocityX(0.09f);
            motionComponent.set_xDirection(MotionComponent.HEADING_WEST);
            motionComponent.set_velocityY(0.03f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallRight() / 2);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 1;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_SOUTH);


        }

        booger1Created = true;

    }


    private void testCollision5() {


        //booger1
        if (!booger1Created) {

            //hastighet
            motionComponent.set_velocityX(0.01f);
            motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
            motionComponent.set_velocityY(0);

            //placering (första boogern vänstra kanten
            float x = motionComponent.getSceneWallLeft();
            transformComponent.setX(x * 0.75f);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 2f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_NONE);

            //booger2
        } else {

            //hastighet
            motionComponent.set_velocityX(0.05f);
            motionComponent.set_xDirection(MotionComponent.HEADING_WEST);
            motionComponent.set_velocityY(0.01f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallRight());
            //och y-led
            float y = motionComponent.getSceneWallTop() / 2.6f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_NORTH);


        }

        booger1Created = true;

    }


    private void testCollision6() {


        //booger1
        if (!booger1Created) {

            //hastighet
            motionComponent.set_velocityX(0.19f);
            motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
            motionComponent.set_velocityY(0.09f);

            //placering (första boogern vänstra kanten
            float x = motionComponent.getSceneWallLeft();
            transformComponent.setX(x);
            //och y-led
            float y = motionComponent.getSceneWallTop() / 1f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_SOUTH);

            //booger2
        } else {

            //hastighet
            motionComponent.set_velocityX(0.01f);
            motionComponent.set_xDirection(MotionComponent.HEADING_WEST);
            motionComponent.set_velocityY(0.01f);

            //placering (första boogern vänstra kanten
            transformComponent.setX(motionComponent.getSceneWallRight());
            //och y-led
            float y = motionComponent.getSceneWallTop() / 2.6f;
            transformComponent.setY(y);

            //riktning
            motionComponent.set_yDirection(MotionComponent.HEADING_NORTH);


        }

        booger1Created = true;

    }







    private void initBehaviour() {

        //randomisera hastighet i x- och y-led

        Random rand = new Random();

        //float result = rand.nextFloat() * (max - min) + min;
        float velocX = rand.nextFloat() * (MAX_SPEED_X_DIRECTION - MIN_SPEED_X_DIRECTION) + MIN_SPEED_X_DIRECTION;
        float velocY = rand.nextFloat() * (MAX_SPEED_Y_DIRECTION - MIN_SPEED_Y_DIRECTION) + MIN_SPEED_Y_DIRECTION;
        motionComponent.set_velocityX(velocX);
        motionComponent.set_velocityY(velocY);

        //öst eller väst
        int dirX = Motion.HEADING_EAST;
        if (Math.random() > 0.5)  {
            dirX = Motion.HEADING_WEST;
        }

        int dirY = Motion.HEADING_SOUTH; // alltid söderut

        motionComponent.set_xDirection(dirX);
        motionComponent.set_yDirection(dirY);


        //bestäm placering öst eller väst (X-led)
        if (motionComponent.get_xDirection() == MotionComponent.HEADING_EAST) {

            transformComponent.setX(motionComponent.getSceneWallLeft());
        } else if (motionComponent.get_xDirection() == MotionComponent.HEADING_WEST) {

            transformComponent.setX(motionComponent.getSceneWallRight());
        }



        //bestäm placering norr söder (Y-led)
        final float GAME_SCENE_TOP = motionComponent.getSceneWallTop();
        final float LOWER_Y_BOUND = GAME_SCENE_TOP * 0.75f;

        //float result = rand.nextFloat() * (max - min) + min;
        float position_y = rand.nextFloat() * (GAME_SCENE_TOP - LOWER_Y_BOUND) + LOWER_Y_BOUND;
        transformComponent.setY(position_y);

    }

    public void destroy() {



    }


    public void update() {

        float x = transformComponent.getX();
        float y = transformComponent.getY();
        x += motionComponent.get_velocityX() * motionComponent.get_xDirection();
        y += motionComponent.get_velocityY() * motionComponent.get_yDirection();
        transformComponent.setX(x);
        transformComponent.setY(y);

        //boxColliderComponent.update();

    }



}
