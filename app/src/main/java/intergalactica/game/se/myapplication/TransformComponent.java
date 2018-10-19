package intergalactica.game.se.myapplication;


import android.opengl.Matrix;

import java.util.ArrayList;

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
    private float x0, x1, y0, y1;
    private float scaleX, scaleY, scaleZ; // skalning
    private float size;


    public void create() {



    }

    public void destroy() {

    }

    public void update() {

        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, x, y, z);
        //Matrix.scaleM(models.getmModelMatrix(), 0, 12.86f, 20f, 0f);
        Matrix.scaleM(mModelMatrix, 0, scaleX, scaleY, scaleZ);

        calcXYedges();

    }

    public float[] getMatrix() {

        return mModelMatrix;
    }

    public void setmModelMatrix(float[] mModelMatrix) {
        this.mModelMatrix = mModelMatrix;
    }

    private void calcXYedges() {

        float wFrac = scaleX / 2;
        float hFrac = scaleY / 2;

        x0 = x - wFrac;
        x1 = x + wFrac;
        y0 = y - hFrac;
        y1 = y + hFrac;

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

    public float getSize() {
        return size;
    }

    public float getX0() {
        return x0;
    }

    public float getX1() {
        return x1;
    }

    public float getY0() {
        return y0;
    }

    public float getY1() {
        return y1;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
