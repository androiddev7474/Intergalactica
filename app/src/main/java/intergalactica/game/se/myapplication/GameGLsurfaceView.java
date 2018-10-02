package intergalactica.game.se.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();

        float x = event.getX();
        float y = event.getY();
        gameRenderer.setXpos(x);
        gameRenderer.setYpos(y);

        boolean down = false;
        boolean up = false;
        boolean move = false;
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                down = true;
                break;
            case MotionEvent.ACTION_UP:
                up = true;
                break;
            case MotionEvent.ACTION_MOVE:
                move = true;
                break;
        }

        gameRenderer.setDown(down);
        gameRenderer.setUp(up);
        gameRenderer.setMove(move);

        return true;

    }

}
