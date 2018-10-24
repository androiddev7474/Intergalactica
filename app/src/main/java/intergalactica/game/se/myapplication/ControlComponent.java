package intergalactica.game.se.myapplication;

public class ControlComponent extends BaseComponent {

    TransformComponent transformComponent;

    private float x, y;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);
    }

    public void update() {

        //uppdateras endast vid touch
        if (x != 0 && y != 0) {
            transformComponent.setX(x);
            transformComponent.setY(y);
        }


    }

    public void setTouchCoords(float x, float y) {

        //justeringar för spegelvänt koordinatsystem (OpenGL utgår från 0 längst ner, skärmkoordinatsystemet utgår från 0 högst upp
        float sceneCenterY = GameRenderer.GAMESCENE_TOP / 2; // skärmitten möts de båda koordinatsystemen.
        float y_ = sceneCenterY - y;
        float y_adj = sceneCenterY + y_;

        this.x = x;
        this.y = y_adj;

    }
}
