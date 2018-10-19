package intergalactica.game.se.myapplication;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * Skapad: 2018-10-16
 * Björn Hallström
 * Version: 1
 */

public class CollisionManager {

    private ArrayList <Actor> actorList; // listan med befintliga actors, hämtas från Level
    private ArrayList <Actor> actorTempList = new ArrayList<>(); // används för tillfällig lagring av ihoparade actors (boxar) som jämförs.
    private ArrayList <Actor> explosionList;
    private ArrayList <Integer> directionTypeList = new ArrayList<>(); //samma användningsområde som ovan

    private Actor deathTouchActor, explosionActor;
    private ExplosionManager explosionManager;


    public CollisionManager (ArrayList<Actor> actorList, Actor deathTouchActor, ArrayList <Actor> explosionList, ExplosionManager explosionManager) {

        this.actorList = actorList;
        this.deathTouchActor = deathTouchActor;
        this.explosionManager = explosionManager;
        this.explosionList = explosionList;
    }


    /*
     * Så här paras actors (boxar) ihop vid testning, de testas 2 om 2 i tager - alla kombinationer. Funktionen BoxCollision returnerar heltal (-1, 0, 1, 2, 3) där -1 är utebliven kollision
     * Nedan visas antalet möjliga kombinationer av 5 aktors (10 tänkbara kombinationer). Inom parentes utgår från index från 0 och uppåt (dvs hur det lagras i listor)
     * 1-2 (0-1)
     * 1-3 (0-2)
     * 1-4 (0-3)
     * 1-5 (0-4)
     * 2-3 (1-2)
     * 2-4 (1-3)
     * 2-5 (1-4)
     * 3-4 (2-3)
     * 3-5 (2-4)
     * 4-5 (3-4)
     */
    public void checkCollision() {

        int size = actorList.size();
        int n = size;

        final int ACTOR1IDX = 0;
        final int ACTOR2IDX = 1;
        for (int i = 0; i < size; i++) {

            n--;
            for (int j = 0; j < n; j++) {

                int act1_id = i;
                int act2_id = j + i + 1;
                actorTempList.add(actorList.get(act1_id));
                actorTempList.add(actorList.get(act2_id));

                int[] cTypeAct1 = BoxCollider.boxCollision(actorList.get(act1_id), actorList.get(act2_id));
                directionTypeList.add(new Integer(cTypeAct1[ACTOR1IDX]));
                directionTypeList.add(new Integer(cTypeAct1[ACTOR2IDX]));
            }
        }


    }

    public void checkDeathActorCollision() {


        Iterator<Actor> iter = actorList.iterator();

        while (iter.hasNext()) {
            Actor actor = iter.next();

            int[] type = BoxCollider.boxCollision(actor, deathTouchActor);

            if (type[0] != -1 ) {
                System.err.println("DEAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                DamageComponent damageComponent = ((DamageComponent)deathTouchActor.getComponent(ComponentFactory.DAMAGECOMPONENT));
                ((LifeComponent)actor.getComponent(ComponentFactory.LIFECOMPONENT)).damage(damageComponent.getDamageAmount());


                explosionManager.manageExplosions(actor, actor.getType());

                iter.remove();

            }

        }


    }

    public void save() {

        //spara unden boxdata så att de kan användas till nästa frame
        BoxCollider.savePositions(actorList);
        BoxCollider.savePositions(deathTouchActor);
    }


    /**
     *
     */
    public void reflectOnCollision() {

        //hantera kollision
        int len = actorTempList.size();
        for (int i = 0; i < len; i++) {

            BoxCollider.reflectActor(actorTempList.get(i), actorTempList.get(i + 1), directionTypeList.get(i));
            BoxCollider.reflectActor(actorTempList.get(i + 1), actorTempList.get(i), directionTypeList.get(i + 1));

            i++;
        }

        //nollställ listorna då de proceduren ovan kommer att upprepas varje frame
        directionTypeList.clear();
        actorTempList.clear();

    }

    /**
     * testar kollision mellan aktor och spelscenen
     * @param actor
     */
    public static String sceneCollider(Actor actor) {

        //BoxColliderComponent colliderC = (BoxColliderComponent)actor.getComponent(ComponentFactory.BOXCOLLIDERCOMPONENT);
        //colliderC.sceneCollider(0, GameRenderer.GAMESCENE_TOP, GameRenderer.getGameSceneRight(), 0);

        return BoxCollider.sceneCollider(actor, 0, GameRenderer.GAMESCENE_TOP, GameRenderer.getGameSceneRight(), 0);
    }



}
