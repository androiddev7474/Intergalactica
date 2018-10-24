package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES30;

import java.util.ArrayList;

import compileshaders.opengles.se.shader_compile.Compile;
import libkitv1.opengles.se.opengllibkit1.TextureDataFormatter;

public class ActorCreator {

    private Context context;
    private Actor actor;
    private ComponentFactory componentFactory;

    public ActorCreator(Context context, Actor actor, ComponentFactory componentFactory) {

        this.context = context;
        this.actor = actor;
        this.componentFactory = componentFactory;

    }

    public float[][] cropTexturesFromAtlas(int atlasRes, int atlasDimenRes, String textureName) {

        TextureDataFormatter textureDataFormatter = new TextureDataFormatter(context);
        textureDataFormatter.getTextureData(atlasRes, atlasDimenRes);

        return textureDataFormatter.getSortedSpriteData(textureName);
    }


    /**
     *
     * @param size
     * @param xyz
     * @param scaleXyz
     */
    public void createTransformComponent(float size, float[] xyz, float[] scaleXyz) {

        TransformComponent 	transformComponent 	= (TransformComponent) componentFactory.createComponent(ComponentFactory.TRANSFORMCOMPONENT);
        transformComponent.setOwner(actor);
        transformComponent.setSize(size);
        transformComponent.setX(xyz[0]);
        transformComponent.setY(xyz[1]);
        transformComponent.setZ(xyz[2]);
        transformComponent.setScaleX(scaleXyz[0]);
        transformComponent.setScaleY(scaleXyz[1]);
        transformComponent.setScaleZ(scaleXyz[2]);
        actor.addComponent(transformComponent);

    }


    /**
     *
     * @param verticeSize
     */
    public void createPolygonComponent(float[] verticeSize) {

        PolygonDataComponent polygonDataComponent = (PolygonDataComponent) componentFactory.createComponent(ComponentFactory.MODELCOMPONENT);
        polygonDataComponent.create2Dpolygon(verticeSize[0], verticeSize[1], verticeSize[2]);
        polygonDataComponent.setOwner(actor);
        actor.addComponent(polygonDataComponent);

    }


    /**
     *
     * @param bottomLeftCorner
     * @param bottomRightCorner
     * @param topLeftCorner
     * @param topRightCorner
     * @param nFaces (antal sidor - 2D = 1 sida, 3D = 6 sidor
     */
    public void createUVdataComponent(float[] bottomLeftCorner, float[] bottomRightCorner, float[] topLeftCorner, float[] topRightCorner, int nFaces) {

        UVdataComponent UVdataComponent = (UVdataComponent) componentFactory.createComponent(ComponentFactory.UVDATACOMPONENT);
        UVdataComponent.createTextData(bottomLeftCorner, bottomRightCorner, topLeftCorner, topRightCorner, nFaces);
        UVdataComponent.setOwner(actor);
        actor.addComponent(UVdataComponent);
    }


    /**
     *
     * @param textBitmap
     */
    public void createTextureComponent(Bitmap textBitmap) {

        TextureComponent textureComponent = (TextureComponent) componentFactory.createComponent(ComponentFactory.TEXTURECOMPONENT);
        Texture texture = TextureFactory.createTexture(textBitmap);

        textureComponent.setTexture(texture);
        textureComponent.setOwner(actor);
        actor.addComponent(textureComponent);

    }


    public void createTextureComponent(Bitmap bitmap, int type, int minFilter, int magFilter, int wrapMode) {

        TextureComponent textureComponent = (TextureComponent) componentFactory.createComponent(ComponentFactory.TEXTURECOMPONENT);
        Texture texture = TextureFactory.createTexture(bitmap, type, minFilter, magFilter, wrapMode);
        textureComponent.setTexture(texture);
        textureComponent.setOwner(actor);
        actor.addComponent(textureComponent);

    }


    /**
     *
     * @param vertexShader
     * @param pixelShader
     * @param attrsExtras
     * @param mvpMatrixHandle
     * @param vertexPosHandle
     * @param textCoordHandle
     * @return  mProgramHandle
     */
    public int createRenderComponent(int vertexShader, int pixelShader, String[] attrsExtras, String mvpMatrixHandle, String vertexPosHandle, String textCoordHandle) {

        RenderComponent renderComponent = (RenderComponent) componentFactory.createComponent(ComponentFactory.RENDERCOMPONENT);
        Compile compile = new Compile(context, vertexShader, pixelShader, attrsExtras);
        int mProgramHandle = compile.getmProgramHandle();
        int mMVPMatrixHandle = GLES30.glGetUniformLocation(mProgramHandle, mvpMatrixHandle);
        int mPositionHandle = GLES30.glGetAttribLocation(mProgramHandle, vertexPosHandle);
        int mTextureCoordinateHandle = GLES30.glGetAttribLocation(mProgramHandle, textCoordHandle);
        renderComponent.setHandles(mProgramHandle, mPositionHandle, mTextureCoordinateHandle, mMVPMatrixHandle);
        renderComponent.setOwner(actor);
        renderComponent.create();
        actor.addComponent(renderComponent);

        return mProgramHandle;
    }


    /**
     *
     * @param textureData
     * @param mProgramHandle
     * @param xyOffset
     * @param widthHeightFrac
     * @param modulo
     */
    public void createAnimationComponent(ArrayList<float[][]> textureData, int listID, int mProgramHandle, String xyOffset, String widthHeightFrac, int modulo, boolean loop) {

        AnimationComponent animationComponent = (AnimationComponent) componentFactory.createComponent(ComponentFactory.ANIMATIONCOMPONENT);
        animationComponent.setSpriteTextData(textureData, listID);
        int offsetLoc = GLES30.glGetUniformLocation(mProgramHandle, xyOffset);
        int wHfracLoc =  GLES30.glGetUniformLocation(mProgramHandle, widthHeightFrac);
        animationComponent.setUVcoordsHandles(offsetLoc, wHfracLoc);
        animationComponent.setmProgramHandle(mProgramHandle);
        animationComponent.setOwner(actor);
        animationComponent.create();
        animationComponent.getCurrentAnimation().setN_sprite_frames(textureData.get(listID).length);
        animationComponent.getCurrentAnimation().setModulo(modulo);
        animationComponent.getCurrentAnimation().setLoop(loop);
        actor.addComponent(animationComponent);

    }


    /**
     *
     * @param velocX
     * @param velocY
     * @param dirX
     * @param dirY
     */
    public void createMotionComponent(float velocX, float velocY, int dirX, int dirY, float[] sceneWalls) {

        MotionComponent motionComponent = (MotionComponent) componentFactory.createComponent(ComponentFactory.MOTIONCOMPONENT);
        motionComponent.setOwner(actor);
        motionComponent.create();
        motionComponent.set_velocityX(velocX);
        motionComponent.set_velocityY(velocY);
        motionComponent.set_xDirection(dirX);
        motionComponent.set_yDirection(dirY);
        motionComponent.setSceneWallLeft(sceneWalls[0]);
        motionComponent.setSceneWallRight(sceneWalls[1]);
        motionComponent.setSceneWallTop(sceneWalls[2]);
        motionComponent.setSceneWallBottom(sceneWalls[3]);
        actor.addComponent(motionComponent);

    }


    /**
     *
     */
    public void createBoxColliderComponent() {

        BoxColliderComponent boxColliderComponent = (BoxColliderComponent) componentFactory.createComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        boxColliderComponent.setOwner(actor);
        boxColliderComponent.create();
        actor.addComponent(boxColliderComponent);
    }


    /**
     *
     */
    public void createBatBoogerBehaviourComponent() {

        BatBoogerBehaviourComponent batBoogerBehaviourComponent = (BatBoogerBehaviourComponent) componentFactory.createComponent(ComponentFactory.BATBOOGERBEHAVIOURCOMPONENT);
        batBoogerBehaviourComponent.setOwner(actor);
        batBoogerBehaviourComponent.create();
        actor.addComponent(batBoogerBehaviourComponent);
    }


    public void createBatBrainsBehaviourComponent() {

        BatBrainsBehaviourComponent batBrainsBehaviourComponent = (BatBrainsBehaviourComponent) componentFactory.createComponent(ComponentFactory.BATBRAINSBEHAVIOURCOMPONENT);
        batBrainsBehaviourComponent.setOwner(actor);
        batBrainsBehaviourComponent.create();
        actor.addComponent(batBrainsBehaviourComponent);
    }

    public void createShotBehaviourComponent() {

        ShotBehaviourComponent shotBehaviourComponent = (ShotBehaviourComponent) componentFactory.createComponent(ComponentFactory.SHOTBEHAVIOURCOMPONENT);
        shotBehaviourComponent.setOwner(actor);
        shotBehaviourComponent.create();
        actor.addComponent(shotBehaviourComponent);
    }

    public void createDamageComponent(int damageAmount) {

        DamageComponent damageComponent = (DamageComponent) componentFactory.createComponent(ComponentFactory.DAMAGECOMPONENT);
        damageComponent.setOwner(actor);
        damageComponent.create();
        damageComponent.setDamageAmount(damageAmount);
        actor.addComponent(damageComponent);

    }

    public void createLifeComponent(int maxHealth, int currentHealth, int score) {

        LifeComponent lifeComponent = (LifeComponent) componentFactory.createComponent(ComponentFactory.LIFECOMPONENT);
        lifeComponent.setOwner(actor);
        lifeComponent.create();
        lifeComponent.setMaxHealth(maxHealth);
        lifeComponent.setCurrentHealth(currentHealth);
        lifeComponent.setScore(score);
        actor.addComponent(lifeComponent);

    }

    public void createPositionComponent(int nPositions) {

        PositionComponent positionComponent = (PositionComponent) componentFactory.createComponent(ComponentFactory.POSITIONCOMPONENT);
        positionComponent.setOwner(actor);
        positionComponent.create();
        positionComponent.setN_positions(nPositions);
        actor.addComponent(positionComponent);

    }

    public void createUVscrollComponent(int programHandle, float velocity ) {

        UVscrollComponent uVscrollComponent = (UVscrollComponent) componentFactory.createComponent(ComponentFactory.UVSCROLLCOMPONENT);
        uVscrollComponent.setOwner(actor);
        uVscrollComponent.create();
        uVscrollComponent.setmProgramHandle(programHandle);
        uVscrollComponent.setVelocityY(velocity);
        actor.addComponent(uVscrollComponent);

    }

    public void createScoreComponent() {

        ScoreComponent scoreComponent = (ScoreComponent) componentFactory.createComponent(ComponentFactory.SCORECOMPONENT);
        scoreComponent.setOwner(actor);
        scoreComponent.create();
        actor.addComponent(scoreComponent);

    }

}
