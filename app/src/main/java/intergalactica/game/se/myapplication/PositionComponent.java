package intergalactica.game.se.myapplication;

public class PositionComponent extends BaseComponent {

    private int n_positions;

    private TransformComponent transformComponent;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        for (int i = 0; i < n_positions; i++) {



        }

    }

    public void createPosition(float x, float y) {


        transformComponent.setX(x);
        transformComponent.setY(y);

    }


    public void update() {


    }

    public int getN_positions() {
        return n_positions;
    }

    public void setN_positions(int n_positions) {
        this.n_positions = n_positions;
    }

    public TransformComponent getTransformComponent() {
        return transformComponent;
    }
}
