package intergalactica.game.se.myapplication;


public class MotionComponent extends BaseComponent {


    // statiska publika fält för actorns riktning
    public static final int HEADING_EAST = 1; //1
    public static final int HEADING_WEST = -1; //-1
    public static final int HEADING_NORTH = 1; //-1
    public static final int HEADING_SOUTH = -1; //1

    // actorns nuvarande riktning initieras till följande.
    private int xDirection = HEADING_WEST;
    private int yDirection = HEADING_NORTH;

    // bollens hastighet
    private float velocityX;
    private float velocityY;

    private float sceneWallLeft, sceneWallRight, sceneWallTop, sceneWallBottom;

    public void create() {



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

    public float getSceneWallLeft() {
        return sceneWallLeft;
    }

    public void setSceneWallLeft(float sceneWallLeft) {
        this.sceneWallLeft = sceneWallLeft;
    }

    public float getSceneWallRight() {
        return sceneWallRight;
    }

    public void setSceneWallRight(float sceneWallRight) {
        this.sceneWallRight = sceneWallRight;
    }

    public float getSceneWallTop() {
        return sceneWallTop;
    }

    public void setSceneWallTop(float sceneWallTop) {
        this.sceneWallTop = sceneWallTop;
    }

    public float getSceneWallBottom() {
        return sceneWallBottom;
    }

    public void setSceneWallBottom(float sceneWallBottom) {
        this.sceneWallBottom = sceneWallBottom;
    }
}
