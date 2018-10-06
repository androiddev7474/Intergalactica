package intergalactica.game.se.myapplication;


/**
 * Skapad: 2018-10-05
 * Björn Hallström
 * Version: 1
 */
public class PolygonDataComponent extends BaseComponent {

    private float[] vertexData;
    private float[] indexData; //valfritt, ifall man vill ha indexerat
    private float[] ColorData; // valfritt, ifall man vill kunna ändra färg på en textur via shader
    private int mode; //t.ex GL_TRIANGLES, GL_LINES osv;
    private boolean IndexedDrawing;
    private GL_fields gLfields = new GL_fields();

    /**
     * vertiskoordinaterna för polygonen - moturs med början övre vänstra hörnet för triangel 1 följt av nedre vänstra hörnet, nedre vänsta hörnet triangel 2 fölt av nedre högra hörnet
     * @param x
     * @param y
     * @param z
     */
    public void create3Dpolygon(float x, float y, float z) {

        gLfields.setNfaces(6);


        float[] vertArray = {

                // framsida
                -x, y, z,
                -x, -y, z,
                x, y, z,
                -x, -y, z,
                x, -y, z,
                x, y, z,

                // högra sidan
                x, y, z,
                x, -y, z,
                x, y, -z,
                x, -y, z,
                x, -y, -z,
                x, y, -z,

                // baksidan
                x, y, -z,
                x, -y, -z,
                -x, y, -z,
                x, -y, -z,
                -x, -y, -z,
                -x, y, -z,

                // vänstra sidan
                -x, y, -z,
                -x, -y, -z,
                -x, y, z,
                -x, -y, -z,
                -x, -y, z,
                -x, y, z,

                // översidan
                -x, y, -z,
                -x, y, z,
                x, y, -z,
                -x, y, z,
                x, y, z,
                x, y, -z,

                // undersidan
                x, -y, -z,
                x, -y, z,
                -x, -y, -z,
                x, -y, z,
                -x, -y, z,
                -x, -y, -z
        };

        vertexData = vertArray;

    }


    /**
     * vertiskoordinaterna för polygonen - moturs med början övre vänstra hörnet för triangel 1 följt av nedre vänstra hörnet, nedre vänsta hörnet triangel 2 fölt av nedre högra hörnet
     * @param x
     * @param y
     * @param z
     */
    public void create2Dpolygon(float x, float y, float z) {

        gLfields.setNfaces(1);
        float[] vertArray = {

                // framsida
                -x, y, z,
                -x, -y, z,
                x, y, z,
                -x, -y, z,
                x, -y, z,
                x, y, z
        };

        vertexData = vertArray;

    }



    public float[] getPolyVerts() {

        return vertexData;
    }


    public GL_fields getgLfields() {
        return gLfields;
    }
}
