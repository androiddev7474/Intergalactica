package intergalactica.game.se.myapplication;

public class buBoxCollider {

    public static final String COLLISION_LEFT = "collision_left";
    public static final String COLLISION_RIGHT = "collision_right";
    public static final String COLLISION_TOP = "collision_top";
    public static final String COLLISION_BOTTOM = "collision_bottom";

    public static final int HEADING_EAST_RIGHT_TOUCHES_LEFT = 0;
    public static final int HEADING_WEST_LEFT_TOUCHES_RIGHT = 1;
    public static final int HEADING_NORTH_TOP_TOUCHES_BOTTOM = 2;
    public static final int HEADING_SOUTH_BOTTOM_TOUCHES_TOP = 3;

    /**
     * Kollisionsdetektor mellan två befintliga aktorn - den aktorn som äger detta objekt och den aktorn som tas in som argument.
     * Version1 (2018-10-14) - ny version som bygger på att man jämför respektive aktors position mellan två olika frames, tex om
     * aktor1s y1 (top) befinner sig under aktors2 y0 (botten) frame -1 men motsatsen råder aktuell frame är kollsision ett faktum
     *
     */
    public static int[] boxCollision(Actor actor1, Actor actor2) {

        BoxColliderComponent collider1 = (BoxColliderComponent)actor1.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        BoxColliderComponent collider2 = (BoxColliderComponent)actor2.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        float previousBox1Left = collider1.getPreviousBoxLeft();
        float previousBox1Top = collider1.getPreviousBoxTop();
        float previousBox1Right = collider1.getPreviousBoxRight();
        float previousBox1Bottom = collider1.getPreviousBoxBottom();

        float previousBox2Left = collider2.getPreviousBoxLeft();
        float previousBox2Top = collider2.getPreviousBoxTop();
        float previousBox2Right = collider2.getPreviousBoxRight();
        float previousBox2Bottom = collider2.getPreviousBoxBottom();

        float leftBox1 = collider1.getLeft();
        float topBox1 = collider1.getTop();
        float rightBox1 = collider1.getRight();
        float bottomBox1 = collider1.getBottom();

        float leftBox2 = collider2.getLeft();
        float topBox2 = collider2.getTop();
        float rightBox2 = collider2.getRight();
        float bottomBox2 = collider2.getBottom();

        int type = -1;
        int type2 = -1;


        /**
         * AKTOR 1
         */

        //NORR - SYD

        //aktuell aktor kolliderar med andra aktorn underifrån
        if ( (previousBox1Top < previousBox2Bottom) && topBox1 >= bottomBox2) {

            if ( (leftBox1 >= leftBox2 && leftBox1 <= rightBox2) ||  (rightBox1 >= leftBox2 && rightBox1 <= rightBox2) ) {

                type = HEADING_NORTH_TOP_TOUCHES_BOTTOM;
            }
        }

        //aktuell aktor kolliderar med andra aktorn överifrån
        if (previousBox1Bottom > previousBox2Top && bottomBox1 <= topBox2) {

            if ( (leftBox1 >= leftBox2 && leftBox1 <= rightBox2) ||  (rightBox1 >= leftBox2 && rightBox1 <= rightBox2) ) {

                type = HEADING_SOUTH_BOTTOM_TOUCHES_TOP;
            }
        }

        // ÖSTER _VÄSTER

        // aktuell aktor rör sig västerut och kolliderar med sidan
        if (previousBox1Left > previousBox2Right && leftBox1 <= rightBox2) {

            if ( (topBox1 >= bottomBox2 && topBox1 <= topBox2) ||  (bottomBox1 >= bottomBox2 && bottomBox1 <= topBox2) ) {

                type = HEADING_WEST_LEFT_TOUCHES_RIGHT;
            }

        }

        //aktuell aktor rör sig österut och kolliderar med sidan
        if (previousBox1Right < previousBox2Left && rightBox1 >= leftBox2) {

            if ( (topBox1 >= bottomBox2 && topBox1 <= topBox2) ||  (bottomBox1 >= bottomBox2 && bottomBox1 <= topBox2) ) {
                type = HEADING_EAST_RIGHT_TOUCHES_LEFT;
            }

        }


        /**
         * AKTOR 2
         */


        //NORR - SYD

        //aktuell aktor kolliderar med andra aktorn underifrån
        if ( (previousBox2Top < previousBox1Bottom) && topBox2 >= bottomBox1) {

            if ( (leftBox2 >= leftBox1 && leftBox2 <= rightBox1) ||  (rightBox2 >= leftBox1 && rightBox2 <= rightBox1) ) {

                type2 = HEADING_NORTH_TOP_TOUCHES_BOTTOM;
            }
        }

        //aktuell aktor kolliderar med andra aktorn överifrån
        if (previousBox2Bottom > previousBox1Top && bottomBox2 <= topBox1) {

            if ( (leftBox2 >= leftBox1 && leftBox2 <= rightBox1) ||  (rightBox2 >= leftBox1 && rightBox2 <= rightBox1) ) {

                type2 = HEADING_SOUTH_BOTTOM_TOUCHES_TOP;
            }
        }
        // ÖSTER _VÄSTER

        // aktuell aktor rör sig västerut och kolliderar med sidan
        if (previousBox2Left > previousBox1Right && leftBox2 <= rightBox1) {

            if ( (topBox2 >= bottomBox1 && topBox2 <= topBox1) ||  (bottomBox2 >= bottomBox1 && bottomBox2 <= topBox1) ) {

                type2 = HEADING_WEST_LEFT_TOUCHES_RIGHT;
            }

        }

        //aktuell aktor rör sig österut och kolliderar med sidan
        if (previousBox2Right < previousBox1Left && rightBox2 >= leftBox1) {

            if ( (topBox2 >= bottomBox1 && topBox2 <= topBox1) ||  (bottomBox2 >= bottomBox1 && bottomBox2 <= topBox1) ) {
                type2 = HEADING_EAST_RIGHT_TOUCHES_LEFT;
            }

        }


        int[] types = {type, type2};

        return types;
    }


    public static void savePositions(Actor actor1, Actor actor2, Actor actor3) {

        BoxColliderComponent collider1 = (BoxColliderComponent)actor1.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        BoxColliderComponent collider2 = (BoxColliderComponent)actor2.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        BoxColliderComponent collider3 = (BoxColliderComponent)actor3.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        float leftBox1 = collider1.getLeft();
        float topBox1 = collider1.getTop();
        float rightBox1 = collider1.getRight();
        float bottomBox1 = collider1.getBottom();

        float leftBox2 = collider2.getLeft();
        float topBox2 = collider2.getTop();
        float rightBox2 = collider2.getRight();
        float bottomBox2 = collider2.getBottom();

        float leftBox3 = collider3.getLeft();
        float topBox3 = collider3.getTop();
        float rightBox3 = collider3.getRight();
        float bottomBox3 = collider3.getBottom();

        collider1.setPreviousBoxLeft(leftBox1);
        collider1.setPreviousBoxTop(topBox1);
        collider1.setPreviousBoxRight(rightBox1);
        collider1.setPreviousBoxBottom(bottomBox1);

        collider2.setPreviousBoxLeft(leftBox2);
        collider2.setPreviousBoxTop(topBox2);
        collider2.setPreviousBoxRight(rightBox2);
        collider2.setPreviousBoxBottom(bottomBox2);

        collider3.setPreviousBoxLeft(leftBox3);
        collider3.setPreviousBoxTop(topBox3);
        collider3.setPreviousBoxRight(rightBox3);
        collider3.setPreviousBoxBottom(bottomBox3);

    }

    public static float[] getVelocity(Actor actor) {

        MotionComponent motionComponent = (MotionComponent) actor.getComponent(ComponentFactory.MOTIONCOMPONENT);

        float xSpeed = motionComponent.get_velocityX();
        float ySpeed = motionComponent.get_velocityY();

        float[] speed = {xSpeed, ySpeed};

        return speed;
    }


    /**
     * här ändrar aktorn riktning efter kollision med en annan aktor
     * @param actor (aktuell aktor)
     * @param type (typ av reflektion -se konstanterna i denna klass ( 0 - 3)
     */
    public static void reflectActor(Actor actor, int type) {

        TransformComponent transformComponent = (TransformComponent)actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        MotionComponent motionComponent = (MotionComponent) actor.getComponent(ComponentFactory.MOTIONCOMPONENT);


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
                x0_ = transformComponent.getX0();
                diff = x1 - x0_;
                x = transformComponent.getX() - diff;
                transformComponent.setX(x);
                */


                break;
            case HEADING_WEST_LEFT_TOUCHES_RIGHT:
                motionComponent.change_Xdirection();

                /*x0 = transformComponent.getX0();
                x1_ = transformComponent.getX1();
                diff = x1_ - x0;
                x = transformComponent.getX() + diff;
                transformComponent.setX(x);
                */

                break;
            case  HEADING_NORTH_TOP_TOUCHES_BOTTOM:
                motionComponent.change_Ydirection();

                /*y1 = transformComponent.getY1();
                y0_ = transformComponent.getY0();
                y = transformComponent.getY();
                diff = y1 - y0_;
                y = y - diff;
                transformComponent.setY(y);
                */

                break;
            case HEADING_SOUTH_BOTTOM_TOUCHES_TOP:
                motionComponent.change_Ydirection();


                /*y0 = transformComponent.getY0();
                y1_ = transformComponent.getY1();
                y = transformComponent.getY();
                diff = y1_ - y0;
                y = y + diff;
                transformComponent.setY(y);
                */


                break;
        }

    }



    public static String sceneCollider(Actor actor, float sceneLeft, float sceneTop, float sceneRight, float sceneBottom) {

        TransformComponent transformComponent = (TransformComponent) actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        MotionComponent motionComponent = (MotionComponent) actor.getComponent(ComponentFactory.MOTIONCOMPONENT);

        float left = transformComponent.getX0();
        float top = transformComponent.getY1();
        float right = transformComponent.getX1();
        float bottom = transformComponent.getY0();

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

}
