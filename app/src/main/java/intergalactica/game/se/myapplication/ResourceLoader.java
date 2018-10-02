package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import se.test.app.bitmapdecoder.BitmapDecoder;

public class ResourceLoader {

    private Context context;


    public ResourceLoader(Context context) {

        this.context = context;

    }


    public Bitmap[] createBitmaps(float[][] bitmapDimens, String[] bitmapNames, String resFolderName) {

        Bitmap[] bitmaps = new Bitmap[bitmapNames.length];
        for (int i = 0; i < bitmapNames.length; i++) {

            int resId = context.getResources().getIdentifier(bitmapNames[i], resFolderName, context.getPackageName());
            bitmaps[i] = BitmapDecoder.decodeSampledBitmapFromResource(context.getResources(), resId, (int) bitmapDimens[i][0], (int) bitmapDimens[i][1], Bitmap.Config.RGB_565);

        }

        return bitmaps;
    }


}
