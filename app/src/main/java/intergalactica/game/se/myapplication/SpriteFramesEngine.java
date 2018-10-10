package intergalactica.game.se.myapplication;

public class SpriteFramesEngine {

    private int frameCounter;
    private int modulo; // anger hur ofta frame ska uppdateras
    private int n_sprite_frames; //tot. antalet frames
    private int id; // håller koll på frame
    private boolean incrementCounter = true;
    private boolean loop = true; // default dvs loopar om och om igen om inget annat anges
    private boolean oneShotAnimationDone = false;


    /**
     * Skapar animation genom att räkna upp ett id för texturen för varje frame. Om loop är satt till false görs bara en rörelse hos spriten, tex en explosion
     * @return
     */
    public boolean animate() {

        if (oneShotAnimationDone)
            return false;

        if (frameCounter % modulo == 0)
            id++;

        if (id >= n_sprite_frames) {
            id = 0;
            if (!loop)
                oneShotAnimationDone = true;
        }
        frameCounter++;
        return true;
    }


    //räknar upp och sen tillbaka osv.
    public void animateReverse() {

        if (frameCounter % modulo == 0) {
            if (incrementCounter)
                id++;
            else
                id--;
        }

        if (id >= n_sprite_frames - 1) {
            incrementCounter = false;
        } else if (id <= 0) {
            incrementCounter = true;
        }

        frameCounter++;
    }

    public void setNframes(int n_sprite_frames) {

        this.n_sprite_frames = n_sprite_frames;
    }

    public void setLoop(boolean loop) {

        this.loop = loop;
    }

    public void setOneShotAnimationDone(boolean oneShotAnimationDone) {

        this.oneShotAnimationDone =oneShotAnimationDone;
    }

    public void setModulo(int modulo) {

        this.modulo = modulo;
    }

    public int getID() {

        return this.id;
    }




}
