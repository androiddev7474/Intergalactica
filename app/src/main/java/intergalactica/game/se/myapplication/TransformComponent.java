package intergalactica.game.se.myapplication;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att positionera en actor i spelvärlden. Hanterar även rotering och skalning.
 * En transform-komponent kan “child’as” till en annan transform-komponent och därmed förflyttas och roteras relativt till dess “parent”
 */
public class TransformComponent extends BaseComponent {

    private float[] mModelMatrix = new float[16];

    public void create() {

    }

    public void destroy() {

    }

    public void update() {

    }

    public float[] getMatrix() {

        return this.mModelMatrix;
    }

}
