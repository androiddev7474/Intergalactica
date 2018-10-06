package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.Matrix;

import compileshaders.opengles.se.shader_compile.Compile;



public class LevelMap {



    private boolean isActive = true;
    private Bitmap backgroundBitmap;
    private int mProgramHandle;
    private Context context;

    private Actor backgroundActor;

    private float[] modelMatrix;

    private TransformComponent transformComponent;
    private RenderComponent renderComponent;
    private PolygonDataComponent polygonDataComponent;
    private UVdataComponent UVdataComponent;
    private TextureComponent textureComponent;

    /** Den slutgiltiga matrisen - model-view-projektion-matrix, den matris som slutligen skickas till GPUN
     * för vidare behandling (dvs multiplicering med vertiserna) */
    private int mMVPMatrixHandle;

    /** Används för att skicka in modell-vertisdata till GPUN */
    private int mPositionHandle;

    /** Används för att skicka in texturkoordinaterna till GPUN */
    private int mTextureCoordinateHandle;



    public LevelMap(Context context, ActorFactory actorFactory) {

        this.context = context;

        backgroundActor = actorFactory.createActor("Background");

        transformComponent = (TransformComponent)backgroundActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        polygonDataComponent = (PolygonDataComponent)backgroundActor.getComponent(ComponentFactory.MODELCOMPONENT);
        UVdataComponent = (UVdataComponent)backgroundActor.getComponent(ComponentFactory.UVDATACOMPONENT);
        textureComponent = (TextureComponent)backgroundActor.getComponent(ComponentFactory.TEXTURECOMPONENT);
        renderComponent = (RenderComponent)backgroundActor.getComponent(ComponentFactory.RENDERCOMPONENT);

        modelMatrix = transformComponent.getMatrix();

        create();
    }

    public void draw() {


        GLES20.glClearColor(0.49f, 0.27f, 0.44f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, transformComponent.getX(), transformComponent.getY(), transformComponent.getZ());
        //Matrix.scaleM(models.getmModelMatrix(), 0, 12.86f, 20f, 0f);
        Matrix.scaleM(modelMatrix, 0, transformComponent.getScaleX(), transformComponent.getScaleY(), transformComponent.getScaleZ());

        renderComponent.render();
    }

    public void update() {

    }


    public void setContext(Context context) {

        this.context = context;
    }

    public void create() {


        String[] attributes = new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"};
        Compile compile = new Compile(context, R.raw.per_pixel_vertex_shader, R.raw.per_pixel_fragment_shader, attributes);
        mProgramHandle = compile.getmProgramHandle();
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVPMatrix");
        mPositionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position");
        mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_TexCoordinate");

        renderComponent.setHandles(mProgramHandle, mPositionHandle, mTextureCoordinateHandle, mMVPMatrixHandle);

    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
