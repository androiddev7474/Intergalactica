package intergalactica.game.se.myapplication;

import android.opengl.GLES30;

public class UVscrollComponent extends BaseComponent {

    private float velocityY = 0.001f; //default
    private float uvOffsetY;
    private int mProgramHandle;

    TransformComponent transformComponent;

    public void create() {

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);
    }

    public void update() {

        GLES30.glUseProgram(mProgramHandle);
        uvOffsetY -= velocityY;

        int yOffetLoc = GLES30.glGetUniformLocation(mProgramHandle, "yCoord");
        GLES30.glUniform1f(yOffetLoc, uvOffsetY);


    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void setmProgramHandle(int mProgramHandle) {
        this.mProgramHandle = mProgramHandle;
    }
}
