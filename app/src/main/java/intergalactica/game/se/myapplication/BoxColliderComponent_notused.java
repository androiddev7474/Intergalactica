package intergalactica.game.se.myapplication;

public class BoxColliderComponent_notused extends BaseComponent {


    private float left, right, bottom, top;
    private TransformComponent transformComponent;
    private MotionComponent motionComponent;

    public static final String COLLISION_LEFT = "collision_left";
    public static final String COLLISION_RIGHT = "collision_right";
    public static final String COLLISION_TOP = "collision_top";
    public static final String COLLISION_BOTTOM = "collision_bottom";

    public static final int HEADING_EAST_RIGHT_TOUCHES_LEFT = 0;
    public static final int HEADING_WEST_LEFT_TOUCHES_RIGHT = 1;
    public static final int HEADING_NORTH_TOP_TOUCHES_BOTTOM = 2;
    public static final int HEADING_SOUTH_BOTTOM_TOUCHES_TOP = 3;


    float previousBottom, previousTop, previousLeft, previousRight;
    float previousColliderBottom, previousColliderTop, previousColliderLeft, previousColliderRight;





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

        //Log.d("LEFT RIGHT TOP BOTTOM", "(" + actor + ")" + left + ", " + right + " ," + top + ", " + bottom);

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


    public String sceneCollider(float sceneLeft, float sceneTop, float sceneRight, float sceneBottom) {

        if (left < sceneLeft) {

            //centrum x + halva bredden < högra scengränsen
            float halfWidth = transformComponent.getScaleX() / 2;
            float x = sceneLeft + halfWidth;
            transformComponent.setX(x);
            motionComponent.change_Xdirection();

            return COLLISION_LEFT;
        }

        if (right > sceneRight) {

            //centrum x + halva bredden < högra scengränsen
            float halfWidth = transformComponent.getScaleX() / 2;
            float x = sceneRight - halfWidth;
            transformComponent.setX(x);
            motionComponent.change_Xdirection();

            return COLLISION_RIGHT;
        }

        if (top > sceneTop) {

            //centrum x + halva bredden < högra scengränsen
            float halfHeight = transformComponent.getScaleY() / 2;
            float y = sceneTop - halfHeight;
            transformComponent.setY(y);
            motionComponent.change_Ydirection();

            return COLLISION_TOP;
        }

        if (bottom < sceneBottom) {

            //centrum x + halva bredden < högra scengränsen
            float halfHeight = transformComponent.getScaleY() / 2;
            float y = sceneBottom + halfHeight;
            transformComponent.setY(y);
            motionComponent.change_Ydirection();

            return COLLISION_BOTTOM;
        }

        return "";
    }













    /**
     * Kollisionsdetektor mellan två befintliga aktorn - den aktorn som äger detta objekt och den aktorn som tas in som argument.
     * Version1 (2018-10-14) - ny version som bygger på att man jämför respektive aktors position mellan två olika frames, tex om
     * aktor1s y1 (top) befinner sig under aktors2 y0 (botten) frame -1 men motsatsen råder aktuell frame är kollsision ett faktum
     *
     */
    public int boxCollision(Actor actor1, Actor actor2) {

        BoxColliderComponent_notused collider1 = (BoxColliderComponent_notused)actor1.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        BoxColliderComponent_notused collider2 = (BoxColliderComponent_notused)actor2.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        int type = -1;

        float collider2Bottom = collider2.getBottom();
        float collider2Top = collider2.getTop();
        float collider2Left = collider2.getLeft();
        float collider2Right = collider2.getRight();

        //NORR - SYD

        //aktuell aktor kolliderar med andra aktorn underifrån
        if ( (previousTop <= previousColliderBottom) && top > collider2Bottom) {

            if ( (left > collider2.getLeft() && left < collider2.getRight()) ||  (right > collider2.getLeft() && right < collider2.getRight()) ) {

                type =  HEADING_NORTH_TOP_TOUCHES_BOTTOM;
            }
        }

        //aktuell aktor kolliderar med andra aktorn överifrån
        if (previousBottom >= previousColliderTop && bottom < collider2Top) {

            if ( (left > collider2.getLeft() && left < collider2.getRight()) ||  (right > collider2.getLeft() && right < collider2.getRight()) ) {

                type = HEADING_SOUTH_BOTTOM_TOUCHES_TOP;
            }
        }

        // ÖSTER _VÄSTER

        // aktuell aktor rör sig västerut och kolliderar med sidan
        if (previousLeft > previousColliderRight && left < collider2Right) {

            if ( (top > collider2.getBottom() && top < collider2.getTop()) ||  (bottom > collider2.getBottom() && bottom < collider2.getTop()) ) {

                type = HEADING_WEST_LEFT_TOUCHES_RIGHT;
            }

        }

        //aktuell aktor rör sig österut och kolliderar med sidan
        if (previousRight < previousColliderLeft && right > collider2Left) {


            if ( (top > collider2.getBottom() && top < collider2.getTop()) ||  (bottom > collider2.getBottom() && bottom < collider2.getTop()) ) {

                type = HEADING_EAST_RIGHT_TOUCHES_LEFT;
            }

        }

        saveBoxData(collider2Top, collider2Bottom, collider2Left, collider2Right);

        return type;
    }


    /**
     * Dessa sparade dator används till frame + 1 (dvs nästa frame) för att se om kollision uppstått.
     * @param colliderTop
     * @param colliderBottom
     * @param colliderLeft
     * @param colliderRight
     */
    private void save_box_data(float colliderTop, float colliderBottom, float colliderLeft, float colliderRight) {

        previousColliderTop = colliderTop;
        previousColliderBottom = colliderBottom;
        previousColliderLeft = colliderLeft;
        previousColliderRight = colliderRight;

        previousBottom = bottom;
        previousTop = top;
        previousLeft = left;
        previousRight = right;
    }






















    /**
     * Kollisionsdetektor mellan två befintliga aktorn - den aktorn som äger detta objekt och den aktorn som tas in som argument.
     * Version1 (2018-10-14) - ny version som bygger på att man jämför respektive aktors position mellan två olika frames, tex om
     * aktor1s y1 (top) befinner sig under aktors2 y0 (botten) frame -1 men motsatsen råder aktuell frame är kollsision ett faktum
     *
     * @param otherActor
     * @return
     */
    public int boxCollision(Actor otherActor) {

        BoxColliderComponent_notused collider = (BoxColliderComponent_notused)otherActor.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        int type = -1;

        float colliderBottom = collider.getBottom();
        float colliderTop = collider.getTop();
        float colliderLeft = collider.getLeft();
        float colliderRight = collider.getRight();

        //NORR - SYD

        //aktuell aktor kolliderar med andra aktorn underifrån
        if ( (previousTop <= previousColliderBottom) && top > colliderBottom) {

            if ( (left > collider.getLeft() && left < collider.getRight()) ||  (right > collider.getLeft() && right < collider.getRight()) ) {

                type =  HEADING_NORTH_TOP_TOUCHES_BOTTOM;
            }
        }

        //aktuell aktor kolliderar med andra aktorn överifrån
        if (previousBottom >= previousColliderTop && bottom < colliderTop) {

            if ( (left > collider.getLeft() && left < collider.getRight()) ||  (right > collider.getLeft() && right < collider.getRight()) ) {

                type = HEADING_SOUTH_BOTTOM_TOUCHES_TOP;
            }
        }

        // ÖSTER _VÄSTER

        // aktuell aktor rör sig västerut och kolliderar med sidan
        if (previousLeft > previousColliderRight && left < colliderRight) {

            if ( (top > collider.getBottom() && top < collider.getTop()) ||  (bottom > collider.getBottom() && bottom < collider.getTop()) ) {

                type = HEADING_WEST_LEFT_TOUCHES_RIGHT;
            }

        }

        //aktuell aktor rör sig österut och kolliderar med sidan
        if (previousRight < previousColliderLeft && right > colliderLeft) {


            if ( (top > collider.getBottom() && top < collider.getTop()) ||  (bottom > collider.getBottom() && bottom < collider.getTop()) ) {

                type = HEADING_EAST_RIGHT_TOUCHES_LEFT;
            }

        }

        saveBoxData(colliderTop, colliderBottom, colliderLeft, colliderRight);

        return type;
    }


    /**
     * Dessa sparade dator används till frame + 1 (dvs nästa frame) för att se om kollision uppstått.
     * @param colliderTop
     * @param colliderBottom
     * @param colliderLeft
     * @param colliderRight
     */
    private void saveBoxData(float colliderTop, float colliderBottom, float colliderLeft, float colliderRight) {

        previousColliderTop = colliderTop;
        previousColliderBottom = colliderBottom;
        previousColliderLeft = colliderLeft;
        previousColliderRight = colliderRight;

        previousBottom = bottom;
        previousTop = top;
        previousLeft = left;
        previousRight = right;
    }



    /**
     * här ändrar aktorn riktning efter kollision med en annan aktor
     * @param otherActor (den andra aktorn)
     * @param type (typ av reflektion -se konstanterna i denna klass ( 0 - 3)
     */
    public void reflectActor(Actor otherActor, int type) {

        TransformComponent otherActorsTransform = (TransformComponent)otherActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        //hörn för aktuell aktor
        float y0 = 0;
        float y1 = 0;
        float x0 = 0;
        float x1 = 0;

        //hörn för den andra aktorn
        float y0_ = 0;
        float y1_ = 0;
        float x0_ = 0;
        float x1_ = 0;

        // resultatet som kommer från subtraktion med diff
        float x = 0;
        float y = 0;
        float diff = 0;

        switch (type) {

            case HEADING_EAST_RIGHT_TOUCHES_LEFT:
                motionComponent.change_Xdirection();

                /*x1 = transformComponent.getX1();
                x0_ = otherActorsTransform.getX0();
                diff = x1 - x0_;
                x = transformComponent.getX() - diff;
                transformComponent.setX(x);
                */


                break;
            case HEADING_WEST_LEFT_TOUCHES_RIGHT:
                motionComponent.change_Xdirection();

                /*x0 = transformComponent.getX0();
                x1_ = otherActorsTransform.getX1();
                diff = x1_ - x0;
                x = transformComponent.getX() + diff;
                transformComponent.setX(x);
                */


                break;
            case  HEADING_NORTH_TOP_TOUCHES_BOTTOM:
                motionComponent.change_Ydirection();

                /*y1 = transformComponent.getY1();
                y0_ = otherActorsTransform.getY0();
                y = transformComponent.getY();
                diff = y1 - y0_;
                y = y - diff;
                transformComponent.setY(y);
                */

                break;
            case HEADING_SOUTH_BOTTOM_TOUCHES_TOP:
                motionComponent.change_Ydirection();


                /*y0 = transformComponent.getY0();
                y1_ = otherActorsTransform.getY1();
                y = transformComponent.getY();
                diff = y1_ - y0;
                y = y + diff;
                transformComponent.setY(y);
                */

                break;
        }

    }
}
