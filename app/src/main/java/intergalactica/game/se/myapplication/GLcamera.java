package intergalactica.game.se.myapplication;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class GLcamera {

    //kameramatrisen - förflyttar "världen" mot kameraögat
    private static float[] mViewMatrix = new float[16];

    public static void createCamera() {

        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);


        // positionera ögonen
        final float eyeX = -0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = 0.5f;

        // vad vi tittar mot
        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -5.0f;


        // vårat huvuds position - rättvänt eller uppochner?
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;


        //kamerapositionen
        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
    }



    public static float[] getmViewMatrix() {

        return mViewMatrix;
    }

    public static void setmViewMatrix(float[] mView_matrix) {

        mViewMatrix = mView_matrix;
    }



}
