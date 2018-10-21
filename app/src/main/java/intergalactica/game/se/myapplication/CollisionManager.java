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

    //private ArrayList <Actor> actorList; // listan med befintliga actors, hämtas från Level
    //private ArrayList <Actor> actorList2;
    private ArrayList <Actor> actorSingleList = new ArrayList<>(); // används för tillfällig lagring av ihoparade actors (boxar) som jämförs. En och samma lista
    private ArrayList <Actor> explosionList;
    private ArrayList <Integer> directionTypeList = new ArrayList<>(); //samma användningsområde som ovan

    //private Actor deathTouchActor, explosionActor;
    private ExplosionManager explosionManager;


    public CollisionManager (ArrayList <Actor> explosionList, ExplosionManager explosionManager) {

        //this.actorList = actorList;
        //this.deathTouchActor = deathTouchActor;
        this.explosionManager = explosionManager;
        this.explosionList = explosionList;
    }


    /*
     * Kollisionskontroll INOM en och samma lista
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
    public void checkSingleListCollision(ArrayList <Actor> actorList) {

        int size = actorList.size();
        int n = size;

        final int ACTOR1IDX = 0;
        final int ACTOR2IDX = 1;
        for (int i = 0; i < size; i++) {

            n--;
            for (int j = 0; j < n; j++) {

                int act1_id = i;
                int act2_id = j + i + 1;
                actorSingleList.add(actorList.get(act1_id));
                actorSingleList.add(actorList.get(act2_id));

                int[] cTypeAct1 = BoxCollider.boxCollision(actorList.get(act1_id), actorList.get(act2_id));
                directionTypeList.add(new Integer(cTypeAct1[ACTOR1IDX]));
                directionTypeList.add(new Integer(cTypeAct1[ACTOR2IDX]));
            }
        }
    }


    /**
     * Kollisionskontroll mellan två listor med actors
     * @param actorList1
     * @param actorList2
     */
    public void checkDualListCollision(ArrayList <Actor> actorList1, ArrayList <Actor> actorList2) {


        for (Actor actor1: actorList1) {

            for (Actor actor2: actorList2) {

                int[] types = BoxCollider.boxCollision(actor1, actor2);

                int xx = 0;
                if (types[0] != -1 || types[1] != -1)
                    xx = 1;
            }

        }

    }

    public void checkDeathActorCollision(Actor deathTouchActor, ArrayList <Actor> actorList) {


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

    /**
     * Kollisionskontroll mellan två listor med actors
     * @param actorList1
     * @param actorList2
     */
    public void checkDualListCollision2(ArrayList <Actor> actorList1, ArrayList <Actor> actorList2) {


        for (Actor actor1: actorList1) {

            for (Actor actor2: actorList2) {

                boolean b = BoxCollider.boxVsBox(actor1, actor2);

                int xxx = 0;
                if(b) {
                    xxx = 1;
                }


            }

        }

    }

    public void save(ArrayList <Actor> actorList) {

        //spara unden boxdata så att de kan användas till nästa frame
        BoxCollider.savePositions(actorList);

    }

    /**
     *
     * @param actor tex deathtouch
     */
    public void save(Actor actor) {

        BoxCollider.savePositions(actor);
    }





    /**
     *
     */
    public void reflectOnCollision() {

        //hantera kollision
        int len = actorSingleList.size();
        for (int i = 0; i < len; i++) {

            BoxCollider.reflectActor(actorSingleList.get(i), actorSingleList.get(i + 1), directionTypeList.get(i));
            BoxCollider.reflectActor(actorSingleList.get(i + 1), actorSingleList.get(i), directionTypeList.get(i + 1));

            i++;
        }

        //nollställ listorna då de proceduren ovan kommer att upprepas varje frame
        directionTypeList.clear();
        actorSingleList.clear();

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
