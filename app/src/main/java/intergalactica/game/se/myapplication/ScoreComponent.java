package intergalactica.game.se.myapplication;

public class ScoreComponent extends BaseComponent {



    private TransformComponent transformComponent;
    private AnimationComponent animationComponent;

    private static int incr;
    private int id = 3;
    private int iNumb;
    private float xPos, yPos;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        animationComponent = (AnimationComponent) getOwner().getComponent(ComponentFactory.ANIMATIONCOMPONENT);

    }

    public void update() {

        animationComponent.setListID(iNumb);
        transformComponent.setX(xPos);
        transformComponent.setY(yPos);

    }




    public int getiNumb() {
        return iNumb;
    }

    public void setiNumb(int iNumb) {
        this.iNumb = iNumb;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }
}
