package intergalactica.game.se.myapplication;

import android.util.Log;

public class BoxColliderComponent extends BaseComponent {


    private float left, right, bottom, top;
    private TransformComponent transformComponent;
    private MotionComponent motionComponent;

    public static final String COLLISION_LEFT = "collision_left";
    public static final String COLLISION_RIGHT = "collision_right";
    public static final String COLLISION_TOP = "collision_top";
    public static final String COLLISION_BOTTOM = "collision_bottom";

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        motionComponent = (MotionComponent)getOwner().getComponent(ComponentFactory.MOTIONCOMPONENT);


    }

    public void destroy() {


    }


    public void update() {

        //float x = transformComponent.getX();
        //Log.d("x", "" + x);

        String actor = getOwner().getType();

        left = transformComponent.getX0();
        right = transformComponent.getX1();
        top = transformComponent.getY1();
        bottom = transformComponent.getY0();

        Log.d("LEFT RIGHT TOP BOTTOM", "(" + actor + ")" + left + ", " + right + " ," + top + ", " + bottom);

    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public float getTop() {
        return top;
    }




    public void setCollisionType(String type) {

        if (type.equals(COLLISION_LEFT)) {


        }

        if (type.equals(COLLISION_RIGHT)) {

            motionComponent.change_Xdirection();

            //centrum x + halva bredden < högra scengränsen
            float halfWidth = transformComponent.getScaleX() / 2;
            float x = GameRenderer.getGameSceneRight() - halfWidth;


            transformComponent.setX(x);
        }

        if (type.equals(COLLISION_LEFT)) {

            motionComponent.change_Xdirection();

            //centrum x + halva bredden < högra scengränsen
            float halfWidth = transformComponent.getScaleX() / 2;
            float x = GameRenderer.GAMESCENE_LEFT + halfWidth;

            transformComponent.setX(x);

        }

        if (type.equals(COLLISION_TOP)) {

            motionComponent.change_Ydirection();

            //centrum x + halva bredden < högra scengränsen
            float halfHeight = transformComponent.getScaleY() / 2;
            float y = GameRenderer.GAMESCENE_TOP - halfHeight;

            transformComponent.setY(y);

        }

        if (type.equals(COLLISION_BOTTOM)) {

            motionComponent.change_Ydirection();

            //centrum x + halva bredden < högra scengränsen
            float halfHeight = transformComponent.getScaleY() / 2;
            float y = GameRenderer.GAMESCENE_BOTTOM + halfHeight;

            transformComponent.setY(y);

        }



    }
}
