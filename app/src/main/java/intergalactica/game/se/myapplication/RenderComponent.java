package intergalactica.game.se.myapplication;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att rendera en actor i spelvärlden. Kan användas i kombination med en AnimationComponent för att klippa ut delar av dess textur
 */
public class RenderComponent extends BaseComponent {


    Texture texture; // actorns textur(-atlas)

    /** lagra modeldata i en flyttalsbuffer */
    private FloatBuffer mCubePositions;
    private FloatBuffer mCubeTextureCoordinates;
    private int length_polygon, length_uvData;
    private float[] vertex_data, uv_data;
    private GL_fields gl_fields;

    private PolygonDataComponent polygonDataComponent;
    private UVdataComponent uVdataComponent;
    private TransformComponent transformComponent;

    /** Den slutgiltiga matrisen som sedan används till multiplicering med respektive vertis. */
    private float[] mMVPMatrix = new float[16];

    //handles
    private int mProgramHandle, mPositionHandle, mTextureCoordinateHandle, mMVPMatrixHandle;

    public void create() {


        polygonDataComponent = (PolygonDataComponent)getOwner().getComponent(ComponentFactory.MODELCOMPONENT);
        vertex_data = polygonDataComponent.getPolyVerts();
        length_polygon = polygonDataComponent.getPolyVerts().length;
        gl_fields = polygonDataComponent.getgLfields();

        uVdataComponent = (UVdataComponent)getOwner().getComponent(ComponentFactory.UVDATACOMPONENT);
        uv_data = uVdataComponent.getTextVerts();
        length_uvData = uv_data.length;

        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        initBuffers();

    }

    public void destroy() {

    }

    public void update() {



    }

    public void setHandles(int mProgramHandle, int mPositionHandle, int mTextureCoordinateHandle, int mMVPMatrixHandle) {


        this.mProgramHandle = mProgramHandle;
        this.mPositionHandle = mPositionHandle;
        this.mTextureCoordinateHandle = mTextureCoordinateHandle;
        this.mMVPMatrixHandle = mMVPMatrixHandle;

    }

    public void render() {

        GLES20.glUseProgram(mProgramHandle);

        // tala om för OpenGL var positions-data finns och vilket format det är av
        mCubePositions.position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, GL_fields.VERT_DATASIZE, GLES20.GL_FLOAT, false,
                0, mCubePositions);
        GLES20.glEnableVertexAttribArray(mPositionHandle);


        // Gör likadant med textur-data
        mCubeTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, GL_fields.TEXTURE_COORD_DATASIZE, GLES20.GL_FLOAT, false,
                0, mCubeTextureCoordinates);
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        //multiplicera
        Matrix.multiplyMM(mMVPMatrix, 0, GLcamera.getmViewMatrix(), 0, transformComponent.getMatrix(), 0);
        Matrix.multiplyMM(mMVPMatrix, 0, GLprojection.getmProjectionMatrix(), 0, mMVPMatrix, 0);
        // skicka matrisdata till GPUN
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);



        // rita kuben
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, gl_fields.getN_vertices_poly());

    }

    /**
     * Initialisera buffrar med modelldata och lägg dessa på native-heapen
     */
    public void initBuffers() {

        mCubePositions = ByteBuffer.allocateDirect(length_polygon * GL_fields.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubePositions.put(vertex_data).position(0);

        mCubeTextureCoordinates = ByteBuffer.allocateDirect(length_uvData * GL_fields.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubeTextureCoordinates.put(uv_data).position(0);

    }
}
