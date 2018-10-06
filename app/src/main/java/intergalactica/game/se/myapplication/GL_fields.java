package intergalactica.game.se.myapplication;

public class GL_fields {

    public static final int N_TRIANGLES_FACE = 2;
    public static final int N_DRAWS_EACH_TRIANGLE = 3;
    public static final int VERT_DATASIZE = 3;   /** antalet element per vertis (x, y och z) */
    public static final int TEXTURE_COORD_DATASIZE = 2;
    public static final int BYTES_PER_FLOAT = 4; /** Antal byte per float */
    public static final int TOTAL_NUMB_FLOATS_TEXT_FACE = N_TRIANGLES_FACE * N_DRAWS_EACH_TRIANGLE * TEXTURE_COORD_DATASIZE; // totala antalet flyttal per 3D textur
    private int total_numb_floats_poly;// totala antalet flyttal per 3D polygon
    private int tot_numb_floats_text; // = TOT_NUMB_FLOATS_TEXT_FACE * n_faces;
    private int n_vertices_poly = 1; //default

    public void setNfaces(int nFaces) {

        n_vertices_poly = nFaces * N_TRIANGLES_FACE * N_DRAWS_EACH_TRIANGLE;
        tot_numb_floats_text = TOTAL_NUMB_FLOATS_TEXT_FACE * nFaces;
        total_numb_floats_poly = nFaces * N_TRIANGLES_FACE * N_DRAWS_EACH_TRIANGLE * VERT_DATASIZE;
    }

    public int getTotal_numb_floats_poly() {
        return total_numb_floats_poly;
    }

    public int getTot_numb_floats_text() {
        return tot_numb_floats_text;
    }

    public int getN_vertices_poly() {
        return n_vertices_poly;
    }

    public void setN_vertices_poly(int n_vertices_poly) {
        this.n_vertices_poly = n_vertices_poly;
    }
}
