package intergalactica.game.se.myapplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att klippa ut delar av en textur och skapa animationsframes, vilket i sin tur innebär en animerad textur
 */
public class AnimationComponent extends BaseComponent {

    //Map<String, Animation*> → String = t.ex “Idle”, “Shooting”, “Death” osv
    Map <String, Animation> animationMap = new HashMap<>(); //String = t.ex “Idle”, “Shooting”, “Death” osv
    int numAnimations;
    Animation currentAnimation;

    public void create() {

    }

    public void destroy() {

    }

    public void update() {

    }


    public void activateAnimation() { //Sätter currentAnimation till en specifik animation


    }


}
