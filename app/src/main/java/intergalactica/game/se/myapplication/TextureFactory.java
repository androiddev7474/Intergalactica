package intergalactica.game.se.myapplication;

import android.graphics.Bitmap;
import android.util.Log;

public class TextureFactory {


    public static Texture createTexture(Bitmap bitmap, int type, int minFilter, int magFilter, int wrapMode) {

        Texture texture = new Texture();

        if(!texture.create(bitmap, type, minFilter, magFilter, wrapMode))
        {
            String message = "Error: failed to create texture '";

            Log.e("TextureFactory_", message);

            return null;
        }

        return texture;
    }


    public static Texture createTexture(Bitmap bitmap) {

        Texture texture = new Texture();

        if(!texture.create(bitmap))
        {
            String message = "Error: failed to create texture '";

            Log.e("TextureFactory_", message);

            return null;
        }

        return texture;
    }


}
