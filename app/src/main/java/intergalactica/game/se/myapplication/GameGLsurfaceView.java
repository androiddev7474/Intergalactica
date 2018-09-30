package intergalactica.game.se.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GameGLsurfaceView extends GLSurfaceView {

    public static final int GL_VERSION = 3;
    private GameRenderer gameRenderer;

    public GameGLsurfaceView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        setEGLContextClientVersion(GL_VERSION);
        gameRenderer = new GameRenderer(context);
        setRenderer(gameRenderer);

    }

    public GameRenderer getGameRenderer() {

        return this.gameRenderer;
    }

}
