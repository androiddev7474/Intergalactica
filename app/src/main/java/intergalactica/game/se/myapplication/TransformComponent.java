package intergalactica.game.se.myapplication;


/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att positionera en actor i spelvärlden. Hanterar även rotering och skalning.
 * En transform-komponent kan “child’as” till en annan transform-komponent och därmed förflyttas och roteras relativt till dess “parent”
 */
public class TransformComponent extends BaseComponent {

    private float[] mModelMatrix = new float[16];
    private float x, y, z; // positioner
    private float scaleX, scaleY, scaleZ; // skalning



    public void create() {



    }

    public void destroy() {

    }

    public void update() {

    }

    public float[] getMatrix() {

        return mModelMatrix;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getScaleZ() {
        return scaleZ;
    }

    public void setScaleZ(float scaleZ) {
        this.scaleZ = scaleZ;
    }


}
