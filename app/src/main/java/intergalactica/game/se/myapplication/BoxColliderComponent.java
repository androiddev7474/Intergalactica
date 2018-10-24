package intergalactica.game.se.myapplication;

import android.util.Log;

public class BoxColliderComponent extends BaseComponent {


    private float left, right, bottom, top;
    private TransformComponent transformComponent;


    //nya namn (kanter för respektive box föregående frame - sparas alltid undan inför nästa test
    float  previousBox1Left, previousBox1Top, previousBox1Right, previousBox1Bottom;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);



    }

    public void destroy() {


    }


    public void update() {

        //float x = transformComponent.getX();
        //Log.d("x", "" + x);
        String actor = getOwner().getType();

        float x = transformComponent.getX();
        float y = transformComponent.getY();
        float wFrac = transformComponent.getScaleX() / 2;
        float hFrac = transformComponent.getScaleY() / 2;

        left = x - wFrac;
        right = x + wFrac;
        bottom = y - hFrac;
        top = y + hFrac;

        /*float wFrac = scaleX / 2;
        float hFrac = scaleY / 2;

        x0 = x - wFrac;
        x1 = x + wFrac;
        y0 = y - hFrac;
        y1 = y + hFrac;*/


        /*left = transformComponent.getX0();
        right = transformComponent.getX1();
        top = transformComponent.getY1();
        bottom = transformComponent.getY0();
        */

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


    public float getPreviousBoxLeft() {
        return previousBox1Left;
    }

    public void setPreviousBoxLeft(float previousBox1Left) {
        this.previousBox1Left = previousBox1Left;
    }

    public float getPreviousBoxTop() {
        return previousBox1Top;
    }

    public void setPreviousBoxTop(float previousBox1Top) {
        this.previousBox1Top = previousBox1Top;
    }

    public float getPreviousBoxRight() {
        return previousBox1Right;
    }

    public void setPreviousBoxRight(float previousBox1Right) {
        this.previousBox1Right = previousBox1Right;
    }

    public float getPreviousBoxBottom() {
        return previousBox1Bottom;
    }

    public void setPreviousBoxBottom(float previousBox1Bottom) {
        this.previousBox1Bottom = previousBox1Bottom;
    }

}