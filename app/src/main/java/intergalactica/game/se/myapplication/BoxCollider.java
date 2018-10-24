package intergalactica.game.se.myapplication;

import java.util.ArrayList;

public class BoxCollider {

    public static final String COLLISION_LEFT = "collision_left";
    public static final String COLLISION_RIGHT = "collision_right";
    public static final String COLLISION_TOP = "collision_top";
    public static final String COLLISION_BOTTOM = "collision_bottom";

    public static final int HEADING_EAST_RIGHT_TOUCHES_LEFT = 0;
    public static final int HEADING_WEST_LEFT_TOUCHES_RIGHT = 1;
    public static final int HEADING_NORTH_TOP_TOUCHES_BOTTOM = 2;
    public static final int HEADING_SOUTH_BOTTOM_TOUCHES_TOP = 3;

    /*
    bool boxVsBox(collisionBox box1, collisionBox box2)
    {
        int box1Top 	= box1.y;
        int box1Bottom 	= box1.y + box1.height;
        int box1Left 	= box1.x;
        int box1Right 	= box1.x + box1.width;

        int box2Top 	= box2.y;
        int box2Bottom 	= box2.y + box2.height;
        int box2Left 	= box2.x;
        int box2Right 	= box2.x + box2.width;

        if	(
                (box1Top 	< box2Bottom) 	&&
                        (box1Bottom	> box2Top) 		&&
                        (box1Left 	< box2Right) 	&&
                        (box1Right 	< box2Left) 	&&
                )
            return true;

        return false;
    }
    */

    public static boolean boxVsBox(Actor actor1, Actor actor2) {

        BoxColliderComponent collider1 = (BoxColliderComponent)actor1.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        BoxColliderComponent collider2 = (BoxColliderComponent)actor2.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        float box1Left = collider1.getLeft();
        float box1Top = collider1.getTop();
        float box1Right = collider1.getRight();
        float box1Bottom = collider1.getBottom();

        float box2Left = collider2.getLeft();
        float box2Top = collider2.getTop();
        float box2Right = collider2.getRight();
        float box2Bottom = collider2.getBottom();

        /*
        int box1Top 	= box1.y;
        int box1Bottom 	= box1.y + box1.height;
        int box1Left 	= box1.x;
        int box1Right 	= box1.x + box1.width;

        int box2Top 	= box2.y;
        int box2Bottom 	= box2.y + box2.height;
        int box2Left 	= box2.x;
        int box2Right 	= box2.x + box2.width;


        if	(
                (box1Top 	< box2Bottom) 	&&
                        (box1Bottom	> box2Top) 		&&
                        (box1Left 	< box2Right) 	&&
                        (box1Right 	> box2Left)
                )
            return true;

        return false;
        */

        //if	((box1Top 	< box2Bottom) 	&& (box1Bottom	> box2Top) && (box1Left < box2Right) && (box1Right 	< box2Left))
        if	((box1Top 	> box2Bottom) 	&& (box1Bottom	< box2Top) && (box1Left < box2Right) && (box1Right 	> box2Left))
            return true;

        return false;



    }



    public static int[] boxCollision_(Actor actor1, Actor actor2) {

        Actor[] actors = {actor1, actor2};

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


    /**
     * Kollisionsdetektor mellan två befintliga aktorn - den aktorn som äger detta objekt och den aktorn som tas in som argument.
     * Version1 (2018-10-14) - ny version som bygger på att man jämför respektive aktors position mellan två olika frames, tex om
     * aktor1s y1 (top) befinner sig under aktors2 y0 (botten) frame -1 men motsatsen råder aktuell frame är kollsision ett faktum
     * Version2 (2018-10-16) - Båda actors testas på en och samma gång till skillnad från tidigare.
     *
     */
    public static int[] boxCollision(Actor actor1, Actor actor2) {


        BoxColliderComponent collider1 = (BoxColliderComponent)actor1.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        BoxColliderComponent collider2 = (BoxColliderComponent)actor2.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        int[] types = {-1, -1};

        //båda aktörerna måste ha en kollider
        if (collider1 == null || collider2 == null)
            return types;

        float[] previousBoxLeft = {collider1.getPreviousBoxLeft(), collider2.getPreviousBoxLeft()};
        float[] previousBoxTop = {collider1.getPreviousBoxTop(), collider2.getPreviousBoxTop()};
        float[] previousBoxRight = {collider1.getPreviousBoxRight(), collider2.getPreviousBoxRight()};
        float[] previousBoxBottom = {collider1.getPreviousBoxBottom(), collider2.getPreviousBoxBottom()};

        float[] leftBox = {collider1.getLeft(), collider2.getLeft()};
        float[] topBox = {collider1.getTop(), collider2.getTop()};
        float[] rightBox = {collider1.getRight(), collider2.getRight()};
        float[] bottomBox = {collider1.getBottom(), collider2.getBottom()};




        /**
         * AKTOR 1
         */

        for (int i = 0; i < 2; i++) {

            int box1ID, box2ID;
            if (i == 0) {
                box1ID = 0;
                box2ID = 1;
            } else {
                box1ID = 1;
                box2ID = 0;
            }

            //NORR - SYD

            //aktuell aktor kolliderar med andra aktorn underifrån
            if ((previousBoxTop[box1ID] < previousBoxBottom[box2ID]) && topBox[box1ID] >= bottomBox[box2ID]) {

                if ((leftBox[box1ID] >= leftBox[box2ID] && leftBox[box1ID] <= rightBox[box2ID]) || (rightBox[box1ID] >= leftBox[box2ID] && rightBox[box1ID] <= rightBox[box2ID]) || (leftBox[box1ID] < leftBox[box2ID] && rightBox[box1ID] > rightBox[box2ID])) {

                    types[i] = HEADING_NORTH_TOP_TOUCHES_BOTTOM;
                }
            }

            //aktuell aktor kolliderar med andra aktorn överifrån
            if (previousBoxBottom[box1ID] > previousBoxTop[box2ID] && bottomBox[box1ID] <= topBox[box2ID]) {

                if ((leftBox[box1ID] >= leftBox[box2ID] && leftBox[box1ID] <= rightBox[box2ID]) || (rightBox[box1ID] >= leftBox[box2ID] && rightBox[box1ID] <= rightBox[box2ID]) /*|| (leftBox[box1ID] < leftBox[box2ID] && rightBox[box1ID] > rightBox[box2ID])*/) {

                    types[i] = HEADING_SOUTH_BOTTOM_TOUCHES_TOP;
                }
            }

            // ÖSTER _VÄSTER

            // aktuell aktor rör sig västerut och kolliderar med sidan
            if (previousBoxLeft[box1ID] > previousBoxRight[box2ID] && leftBox[box1ID] <= rightBox[box2ID]) {

                if ((topBox[box1ID] >= bottomBox[box2ID] && topBox[box1ID] <= topBox[box2ID]) || (bottomBox[box1ID] >= bottomBox[box2ID] && bottomBox[box1ID] <= topBox[box2ID]) || (topBox[box1ID] > topBox[box2ID] && bottomBox[box1ID] < bottomBox[box2ID]) ) {

                    types[i] = HEADING_WEST_LEFT_TOUCHES_RIGHT;
                }
            }

            //aktuell aktor rör sig österut och kolliderar med sidan
            if (previousBoxRight[box1ID] < previousBoxLeft[box2ID] && rightBox[box1ID] >= leftBox[box2ID]) {

                if ((topBox[box1ID] >= bottomBox[box2ID] && topBox[box1ID] <= topBox[box2ID]) || (bottomBox[box1ID] >= bottomBox[box2ID] && bottomBox[box1ID] <= topBox[box2ID]) || (topBox[box1ID] > topBox[box2ID] && bottomBox[box1ID] < bottomBox[box2ID]) ) {

                    types[i] = HEADING_EAST_RIGHT_TOUCHES_LEFT;
                }
            }
        }

        return types;
    }


    /**
     * Spara boxens kantvärden för att kunna utnyttja dessa nästa frame. På så sätt kan det avgöras om en kollision uppstårr
     * @param actors
     */
    public static boolean savePositions(ArrayList<Actor> actors) {

        if (actors == null)
            return false;

        for (Actor actor: actors) {

            BoxColliderComponent collider = (BoxColliderComponent)actor.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

            float leftBox = collider.getLeft();
            float topBox = collider.getTop();
            float rightBox = collider.getRight();
            float bottomBox = collider.getBottom();

            collider.setPreviousBoxLeft(leftBox);
            collider.setPreviousBoxTop(topBox);
            collider.setPreviousBoxRight(rightBox);
            collider.setPreviousBoxBottom(bottomBox);
        }

        return true;
    }

    /**
     * Spara boxens kantvärden för att kunna utnyttja dessa nästa frame. På så sätt kan det avgöras om en kollision uppstårr
     * @param actor
     */
    public static void savePositions(Actor actor) {

        BoxColliderComponent collider = (BoxColliderComponent)actor.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);

        float leftBox = collider.getLeft();
        float topBox = collider.getTop();
        float rightBox = collider.getRight();
        float bottomBox = collider.getBottom();

        collider.setPreviousBoxLeft(leftBox);
        collider.setPreviousBoxTop(topBox);
        collider.setPreviousBoxRight(rightBox);
        collider.setPreviousBoxBottom(bottomBox);

    }

    public static float[] getVelocity(Actor actor) {

        MotionComponent motionComponent = (MotionComponent) actor.getComponent(ComponentFactory.MOTIONCOMPONENT);

        float xSpeed = motionComponent.get_velocityX();
        float ySpeed = motionComponent.get_velocityY();

        float[] speed = {xSpeed, ySpeed};

        return speed;
    }


    /**
     *  här ändrar aktorn riktning efter kollision med en annan aktor
     * @param actorTobeReflected
     * @param actorOther (den andra aktorns transform behövs för att göra beräkningar
     * @param type (typ av reflektion -se konstanterna i denna klass ( 0 - 3)
     */
    public static void reflectActor(Actor actorTobeReflected, Actor actorOther, int type) {

        TransformComponent transformComponent = (TransformComponent)actorTobeReflected.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        MotionComponent motionComponent = (MotionComponent) actorTobeReflected.getComponent(ComponentFactory.MOTIONCOMPONENT);

        TransformComponent otherActorTransform = (TransformComponent) actorOther.getComponent(ComponentFactory.TRANSFORMCOMPONENT);


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

                x1 = transformComponent.getX1();
                x0_ = otherActorTransform.getX0();
                diff = x1 - x0_;
                x = transformComponent.getX() - diff;
                transformComponent.setX(x);



                break;
            case HEADING_WEST_LEFT_TOUCHES_RIGHT:
                motionComponent.change_Xdirection();

                x0 = transformComponent.getX0();
                x1_ = otherActorTransform.getX1();
                diff = x1_ - x0;
                x = transformComponent.getX() + diff;
                transformComponent.setX(x);


                break;
            case  HEADING_NORTH_TOP_TOUCHES_BOTTOM:
                motionComponent.change_Ydirection();

                y1 = transformComponent.getY1();
                y0_ = otherActorTransform.getY0();
                y = transformComponent.getY();
                diff = y1 - y0_;
                y = y - diff;
                transformComponent.setY(y);


                break;
            case HEADING_SOUTH_BOTTOM_TOUCHES_TOP:
                motionComponent.change_Ydirection();


                y0 = transformComponent.getY0();
                y1_ = otherActorTransform.getY1();
                y = transformComponent.getY();
                diff = y1_ - y0;
                y = y + diff;
                transformComponent.setY(y);



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
