package intergalactica.game.se.myapplication;

/**
 * Skapad: 2018-10-05
 * Björn Hallström
 * Version: 1
 * Används för icke-animerade sprites, vilket innebär en stillbild (passande för t.ex bakgrund)
 */
public class UVdataComponent extends BaseComponent {


    private float[] textVerts;

    private GL_fields gLfields = new GL_fields();

    /**
     * texturkoordinaterna - klockvis med början i nedre vänstra hörnet för första triangeln följt av övre vänstra hörnet, övre vänstra hörnet andra triangeln följt av övre högra hörnet
     * @param blc - botton left corner
     * @param brc - bottom right corner
     * @param tlc - top left corner
     * @param trc - top right corner
     */
    public void createTextData(float[] blc, float[] brc, float[] tlc, float[] trc, int n_faces){

        gLfields.setNfaces(n_faces);
        textVerts = new float[gLfields.getTot_numb_floats_text()];

        for (int i = 0; i < textVerts.length; i += gLfields.TOTAL_NUMB_FLOATS_TEXT_FACE) {

            //sida för sida (framsidan första - övriga sidor likadana

            //1:a triangeln - klockvis med början i nedre vänstra hörnet och följs av övre högra hörnet
            textVerts[i] = blc[0];
            textVerts[i + 1] = blc[1];

            textVerts[i + 2] = tlc[0];
            textVerts[i + 3] = tlc[1];

            textVerts[i + 4] = brc[0];
            textVerts[i + 5] = brc[1];


            //2:a triangeln - klockvis börjar övre vänstra hörnet och följs av övre högra hörnet
            textVerts[i + 6] = tlc[0];
            textVerts[i + 7] = tlc[1];

            textVerts[i + 8] = trc[0];
            textVerts[i + 9] = trc[1];

            textVerts[i + 10] = brc[0];
            textVerts[i + 11] = brc[1];
        }
    }


    /**
     * texturkoordinaterna - klockvis med början i nedre vänstra hörnet för första triangeln följt av övre vänstra hörnet, övre vänstra hörnet andra triangeln följt av övre högra hörnet
     * default - dvs koordinaterna täcker hela texturen
     */
    public void createTextData(int n_faces){

        gLfields.setNfaces(n_faces);

        for (int i = 0; i < gLfields.getTot_numb_floats_text(); i += gLfields.TOTAL_NUMB_FLOATS_TEXT_FACE) {

            //sida för sida (framsidan första - övriga sidor likadana

            //1:a triangeln - klockvis med början i nedre vänstra hörnet och följs av övre högra hörnet
            textVerts[i] = 0.0f;
            textVerts[i + 1] = 0.0f;

            textVerts[i + 2] = 0.0f;
            textVerts[i + 3] = 1.0f;

            textVerts[i + 4] = 1.0f;
            textVerts[i + 5] = 0.0f;


            //2:a triangeln - klockvis börjar övre vänstra hörnet och följs av övre högra hörnet
            textVerts[i + 6] = 0.0f;
            textVerts[i + 7] = 1.0f;

            textVerts[i + 8] = 1.0f;
            textVerts[i + 9] = 1.0f;

            textVerts[i + 10] = 1.0f;
            textVerts[i + 11] = 0.0f;
        }

    }


    public float[] getTextVerts() {

        return textVerts;
    }
}
