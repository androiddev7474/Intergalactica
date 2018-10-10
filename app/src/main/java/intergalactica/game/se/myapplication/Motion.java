package intergalactica.game.se.myapplication;

/**
 *
 * Motion - en klass som håller parametrar för rörelse hos ett grafiskt objekt. Klassen har metoder
 * för att ändra riktning och hastighet.
 *
 * 2018-01-25. Förfining av kod med kommentarer o dylikt för att skapa ett bibliotek av denna klass
 *
 */

public class Motion {

    // statiska publika fält för actorns riktning
    public static final int HEADING_EAST = 1; //1
    public static final int HEADING_WEST = -1; //-1
    public static final int HEADING_NORTH = 1; //-1
    public static final int HEADING_SOUTH = -1; //1

    // actorns nuvarande riktning initieras till följande.
    private int xDirection = HEADING_EAST;
    private int yDirection = HEADING_NORTH;

    // bollens hastighet
    private float velocityX;
    private float velocityY;


    /**
     * Konstruktor för hastighet
     * @param velocityX (hastighet x-led)
     * @param velocityY (hastighet y-led)
     */
    public Motion(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;

    }


    /**
     * Konstruktor för riktning
     * @param xDir
     * @param yDir
     */
    public Motion(int xDir, int yDir) {

        xDirection = xDir;yDirection = yDir;

    }


    /**
     * Konstruktor för både hastighet och riktning
     * @param velocityX
     * @param velocityY
     * @param xDir
     * @param yDir
     */
    public Motion(float velocityX, float velocityY, int xDir, int yDir) {

        this.xDirection = xDir;
        this.yDirection = yDir;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

    }


    /**
     * mutator hastighet i x-led
     * @param velocityX
     */
    public void set_velocityX(float velocityX) {

        this.velocityX = velocityX;
    }


    /**
     * mutator hastighet i y-led
     * @param velocityY
     */
    public void set_velocityY(float velocityY) {

        this.velocityY = velocityY;
    }


    /**
     * mutator riktning x-led
     * @param xDirection
     */
    public void set_xDirection(int xDirection) {

        this.xDirection = xDirection;
    }


    /**
     * mutator riktning y-led
     * @param yDirection
     */
    public void set_yDirection(int yDirection)  {

        this.yDirection = yDirection;
    }


    /**
     * accessor hastighet x-led
     * @return
     */
    public float get_velocityX() {

        return velocityX;
    }


    /**
     * accessor hastighet y-led
     * @return
     */
    public float get_velocityY() {
        return velocityY;
    }


    /**
     * accessor riktning x-led (öster - väster)
     * @return
     */
    public int get_xDirection() {

        return xDirection;
    }


    /**
     * accessor riktning y-led (norr - söder)
     * @return
     */
    public int get_yDirection() {

        return yDirection;
    }


    /**
     * ändrar riktning i x-led
     */
    public void change_Xdirection() {
        xDirection = xDirection * -1;

    }


    /**
     * Ändra riktning i y-led
     *
     */
    public void change_Ydirection() {

        yDirection = yDirection * -1;
    }


}
