package intergalactica.game.se.myapplication;

import android.opengl.GLES30;
import android.opengl.Matrix;

public class GLprojection {

    /** matrisen används till att projicera gl-scenent till en 2D viewport **/
    protected static float[] mProjectionMatrix = new float[16];

    public static void perspectiveProject(int width, int height, float near, float far) {


        GLES30.glViewport(0, 0, width, height);

        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;

        //projicering
        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    public static void perspectiveProject(int width, int height, float near, float far, float fov) {

        GLES30.glViewport(0, 0, width, height);
        final float ratio = (float) width / height;

        //projicering
        Matrix.perspectiveM(mProjectionMatrix, 0, fov, ratio, near, far);
    }

    public static void orthoProject(int width, int height, float near, float far, float top, float bottom) {

        final float ratio = (float) height / width;
        final float left = 0;
        final float right = top / ratio;

        Matrix.orthoM(mProjectionMatrix, 0,  left, right, bottom, top,  near, far);

        int y = 111;
    }




    public static float[] getmProjectionMatrix() {

        return mProjectionMatrix;
    }

}
