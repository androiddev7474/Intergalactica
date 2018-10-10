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



    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        boxColliderComponent = (BoxColliderComponent)getOwner().getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        motionComponent = (MotionComponent)getOwner().getComponent(ComponentFactory.MOTIONCOMPONENT);

        initBehaviour();


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

        boxColliderComponent.update();

    }



}
