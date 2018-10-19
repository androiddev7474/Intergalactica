package intergalactica.game.se.myapplication;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */

public class Animation {

    private int frameCounter;
    private int modulo; // anger hur ofta frame ska uppdateras
    private int n_sprite_frames; //tot. antalet frames
    private int id = -1; // håller koll på frame
    private boolean incrementCounter = true;
    private boolean loop = true; // default dvs loopar om och om igen om inget annat anges
    private boolean oneShotAnimationDone = false;

    public Animation() {

        modulo = 1;
    }

    /**
     * Skapar animation genom att räkna upp ett id för texturen för varje frame. Om loop är satt till false görs bara en rörelse hos spriten, tex en explosion
     * @return
     */
    public boolean animate() {

        if (oneShotAnimationDone)
            return false;

        if (frameCounter % modulo == 0 || id == -1)
            id++;

        if (id >= n_sprite_frames) {
            id = 0;
            if (!loop)
                oneShotAnimationDone = true;
        }
        frameCounter++;
        return true;
    }


    public void setLoop(boolean loop) {

        this.loop = loop;
    }

    public void setOneShotAnimationDone(boolean oneShotAnimationDone) {

        this.oneShotAnimationDone = oneShotAnimationDone;
    }

    public boolean isOneShotAnimationDone() {
        return oneShotAnimationDone;
    }

    public void setModulo(int modulo) {

        this.modulo = modulo;
    }


    public void setN_sprite_frames(int n_sprite_frames) {

        this.n_sprite_frames = n_sprite_frames;
    }

    public int getID() {

        return this.id;
    }

    public void setID(int id) {

        this.id = id;
    }

}
