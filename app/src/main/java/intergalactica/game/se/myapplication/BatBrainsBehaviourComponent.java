package intergalactica.game.se.myapplication;

public class BatBrainsBehaviourComponent extends BaseComponent {

    private TransformComponent transformComponent;
    private MotionComponent motionComponent;
    private float xPos, yPos;
    private static int incr;

    public void create() {


        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        motionComponent = (MotionComponent)getOwner().getComponent(ComponentFactory.MOTIONCOMPONENT);

        switch (incr) {

            case 0:
                createBrains1();
                break;
            case 1:
                createBrains2();
                break;
            case 2:
                createBrains3();
                break;

        }

        incr++;

    }

    public static void setIncr(int incr) {
        BatBrainsBehaviourComponent.incr = incr;
    }

    public void createBrains1() {

        transformComponent.setX(motionComponent.getSceneWallLeft() + 0.1f * motionComponent.getSceneWallRight());
        transformComponent.setY(motionComponent.getSceneWallTop() * 0.9f);

        motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
        motionComponent.set_yDirection(MotionComponent.HEADING_NONE);

        motionComponent.set_velocityX(0.01f);
        motionComponent.set_velocityY(0.15f);

        if (Math.random() > 0.995)
            motionComponent.set_xDirection(MotionComponent.HEADING_SOUTH);


    }

    public void createBrains2() {

        transformComponent.setX(motionComponent.getSceneWallLeft() + 0.1f * motionComponent.getSceneWallRight());
        transformComponent.setY(motionComponent.getSceneWallTop() * 0.8f);

        motionComponent.set_xDirection(MotionComponent.HEADING_EAST);
        motionComponent.set_yDirection(MotionComponent.HEADING_NONE);

        motionComponent.set_velocityX(0.005f);
        motionComponent.set_velocityY(0.18f);


    }

    public void createBrains3() {


        transformComponent.setX(motionComponent.getSceneWallRight() * 0.9f);
        transformComponent.setY(motionComponent.getSceneWallTop() * 0.7f);

        motionComponent.set_xDirection(MotionComponent.HEADING_WEST);
        motionComponent.set_yDirection(MotionComponent.HEADING_NONE);

        motionComponent.set_velocityX(0.005f);
        motionComponent.set_velocityY(0.14f);

    }

    private void random(float limit) {

        if (Math.random() > limit)
            motionComponent.set_yDirection(MotionComponent.HEADING_SOUTH);
    }


    public void update() {

        random(0.995f);

        float x = transformComponent.getX();
        float y = transformComponent.getY();
        x += motionComponent.get_velocityX() * motionComponent.get_xDirection();
        y += motionComponent.get_velocityY() * motionComponent.get_yDirection();
        transformComponent.setX(x);
        transformComponent.setY(y);

    }

}
