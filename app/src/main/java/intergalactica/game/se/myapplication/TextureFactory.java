package intergalactica.game.se.myapplication;

import android.content.Context;
import android.opengl.GLES30;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class TextureFactory
{
    private static  TextureFactory  instance;
    private         boolean         mipmapEnabled;

    /*
     * Initialize the singleton
     * Should be executed only once, at application start
     */
    public static void initialize()
    {
        if(instance != null)
        {
            Log.e("TextureFactory", "TextureFactory::initialize() is being executed multiple times. TextureFactory should only be initialized once");

            return;
        }

        else
        {
            instance = new TextureFactory();

            if(BuildConfig.DEBUG)
                Log.e("TextureFactory", "TextureFactory successfully initialized");
        }
    }

    /*
     * Retrieve the singleton instance of TextureFactory
     */
    public static TextureFactory getInstance()
    {
        /*
         * In case getInstance() is executed before initialize()
         */
        if(instance == null)
        {
            if(BuildConfig.DEBUG)
                Log.e("TextureFactory", "Error: Singleton class TextureFactory not initialized");

            throw new RuntimeException("Error: Singleton class TextureFactory not initialized");
        }

        return instance;
    }

    public TextureFactory()
    {
        instance        = null;
        mipmapEnabled   = true;
    }

    /**
     * Creates a texture
     * @param context        The OpenGL context
     * @param fileName       The texture's filename
     * @param resFolderName  The resource folder's name in which the texture file is placed in
     * @param width          The requested texture width
     * @param height         The requested texture height
     * @param type           The texture's type (GL_TEXTURE_2D, GL_TEXTURE_3D etc)
     * @param minFilter      The texture's minification filter mode
     * @param magFilter      The texture's magnification filter mode
     * @param wrapMode       The texture's wrap mode
     * @return               A Texture object or null if something failed during the creation
     */
    public static Texture createTexture(Context context, String fileName, String resFolderName, int width, int height, int type, int minFilter, int magFilter, int wrapMode)
    {
        Texture texture = new Texture();

        if(!texture.create(context, fileName, resFolderName, width, height, type, minFilter, magFilter, wrapMode))
        {
            String message = "Error: failed to create texture '" + fileName + "'";

            Log.e("TextureFactory", message);

            return null;
        }

        return texture;
    }

    public boolean  getMipmapEnabled()                  {return this.mipmapEnabled;}
    public void     setMipmapEnabled(boolean enabled)   {this.mipmapEnabled = enabled;}
}
