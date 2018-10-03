package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture_ {
    /*
     * this texture's data storage
     */
    public class Data {
        public int[] ID = new int[1];
        public int width = 0;
        public int height = 0;
        public int type = 0;
        public int minFilter = 0;
        public int magFilter = 0;
        public int wrapMode = 0;
    }

    private Data data;

    public Texture_() {

    }

    /**
     * Creates this texture
     *
     * @param context       The OpenGL context
     * @param fileName      The texture filename
     * @param resFolderName The resource folder's name in which the texture file is placed in
     * @param width         The requested texture width
     * @param height        The requested texture height
     * @param type          The texture's type (GL_TEXTURE_2D, GL_TEXTURE_3D etc)
     * @param minFilter     The texture's minification filter mode
     * @param magFilter     The texture's magnification filter mode
     * @param wrapMode      The texture's wrap mode
     * @return true if this texture was successfully created, false otherwise
     */
    public boolean create(Context context, String fileName, String resFolderName, int width, int height, int type, int minFilter, int magFilter, int wrapMode) {
        if (fileName.isEmpty()) {
            if (BuildConfig.DEBUG) {
                String message = "Error: no texture filename supplied";

                Log.e("Texture_", message);
            }

            return false;
        }

        int resourceID = context.getResources().getIdentifier(fileName, resFolderName, context.getPackageName());

        if (BuildConfig.DEBUG)
            Log.e("Texture_", fileName + " - resourceID: " + resourceID);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resourceID, options);

        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        options.inScaled = false;
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceID, options);

        if (bitmap == null) {
            if (BuildConfig.DEBUG) {
                String message = "Error: " + fileName + "- failed to create bitmap";

                Log.e("Texture_", message);

                return false;
            }

            return false;
        }

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

        if (scaledBitmap == null) {
            if (BuildConfig.DEBUG) {
                String message = "Error: " + fileName + "- failed to create scaled bitmap";

                Log.e("Texture_", message);

                return false;
            }
        }

        data = new Data();

        data.width = scaledBitmap.getWidth();
        data.height = scaledBitmap.getHeight();
        data.type = type;
        data.minFilter = minFilter;
        data.magFilter = magFilter;
        data.wrapMode = wrapMode;

        GLES30.glGenTextures(1, data.ID, 0);

        if (data.ID[0] == 0) {
            if (BuildConfig.DEBUG) {
                String message = "Error: " + fileName + "- glGenTextures() - failed to generate texture";

                Log.e("Texture_", message);
            }

            return false;
        }

        GLES30.glBindTexture(type, data.ID[0]);

        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_MIN_FILTER, minFilter);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_MAG_FILTER, magFilter);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_WRAP_S, wrapMode);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_WRAP_T, wrapMode);

        GLUtils.texImage2D(type, 0, scaledBitmap, 0);

        if (TextureFactory_.getInstance().getMipmapEnabled())
            GLES30.glGenerateMipmap(type);

        GLES30.glBindTexture(type, 0);

        return true;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int requestedWidth, int requestedHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        if ((width > requestedWidth) || (height > requestedHeight)) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;

            while ((halfWidth / inSampleSize) >= requestedWidth &&
                    (halfHeight / inSampleSize) >= requestedHeight) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public void bind(int textureUnit) {
        if (data.ID[0] == 0)
            return;

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + textureUnit);

        GLES30.glBindTexture(data.type, data.ID[0]);
    }

    public void unbind() {
        GLES30.glBindTexture(data.type, 0);
    }

    public Data getTextureData() {
        return data;
    }

}
