package intergalactica.game.se.myapplication;



public class ShotBehaviourComponent extends BaseComponent {

    private TransformComponent transformComponent;
    private MotionComponent motionComponent;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        motionComponent = (MotionComponent)getOwner().getComponent(ComponentFactory.MOTIONCOMPONENT);
    }

    public void update() {

        //float x = transformComponent.getX();
        float y = transformComponent.getY();
        //x += motionComponent.get_velocityX() * motionComponent.get_xDirection();
        y += motionComponent.get_velocityY() * motionComponent.get_yDirection();
        //transformComponent.setX(x);
        transformComponent.setY(y);

    }

}
