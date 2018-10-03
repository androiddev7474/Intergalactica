package intergalactica.game.se.myapplication;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture {

    private TextureData data;
    //förinställda flaggor (=lättare anrop)
    private static final int TYPE = GLES30.GL_TEXTURE_2D;
    private static final int MINFILTER = GLES30.GL_LINEAR;
    private static final int MAGFILTER = GLES30.GL_LINEAR;
    private static final int WRAPMODE = GLES30.GL_CLAMP_TO_EDGE;



    /*
     * Lagring av texturdata
     */
    private class TextureData {
        private int[] ID = new int[1]; //texturnamn
        private int type;
        private int minFilter;
        private int magFilter;
        private int wrapMode;
    }


    public TextureData getTextureData() {

        return data;
    }

    /**
     *
     * @param bitmap
     * @param type
     * @param minFilter
     * @param magFilter
     * @param wrapMode
     * @return
     */
    public boolean create(Bitmap bitmap, int type, int minFilter, int magFilter, int wrapMode) {

        data = new TextureData();

        data.type = type;
        data.minFilter = minFilter;
        data.magFilter = magFilter;
        data.wrapMode = wrapMode;

        GLES30.glGenTextures(1, data.ID, 0);

        if (data.ID[0] == 0) {
            if (BuildConfig.DEBUG) {
                String message = "Error: failed to generate texture";
                Log.e("Texture", message);
            }
            return false;
        }

        GLES30.glBindTexture(type, data.ID[0]);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_MIN_FILTER, minFilter);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_MAG_FILTER, magFilter);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_WRAP_S, wrapMode);
        GLES30.glTexParameteri(type, GLES30.GL_TEXTURE_WRAP_T, wrapMode);

        GLUtils.texImage2D(type, 0, bitmap, 0);

        if (TextureFactory_.getInstance().getMipmapEnabled())
            GLES30.glGenerateMipmap(type);

        GLES30.glBindTexture(type, 0);

        return true;
    }



    public boolean create(Bitmap bitmap) {

        data = new TextureData();

        data.type = TYPE;
        data.minFilter = MINFILTER;
        data.magFilter = MAGFILTER;
        data.wrapMode = WRAPMODE;

        GLES30.glGenTextures(1, data.ID, 0);

        if (data.ID[0] == 0) {
            if (BuildConfig.DEBUG) {
                String message = "Error: failed to generate texture";
                Log.e("Texture", message);
            }
            return false;
        }

        GLES30.glBindTexture(TYPE, data.ID[0]);
        GLES30.glTexParameteri(TYPE, GLES30.GL_TEXTURE_MIN_FILTER, MINFILTER);
        GLES30.glTexParameteri(TYPE, GLES30.GL_TEXTURE_MAG_FILTER, MAGFILTER);
        GLES30.glTexParameteri(TYPE, GLES30.GL_TEXTURE_WRAP_S, WRAPMODE);
        GLES30.glTexParameteri(TYPE, GLES30.GL_TEXTURE_WRAP_T, WRAPMODE);

        GLUtils.texImage2D(TYPE, 0, bitmap, 0);


        GLES30.glGenerateMipmap(TYPE);

        GLES30.glBindTexture(TYPE, 0);

        return true;
    }



}
