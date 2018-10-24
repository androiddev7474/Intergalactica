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
    private ShotManager shotManager;
    private ScoreManager scoreManager;


    public CollisionManager (ArrayList <Actor> explosionList, ExplosionManager explosionManager, ShotManager shotManager, ScoreManager scoreManager) {

        //this.actorList = actorList;
        //this.deathTouchActor = deathTouchActor;
        this.explosionManager = explosionManager;
        this.explosionList = explosionList;
        this.shotManager = shotManager;
        this.scoreManager = scoreManager;
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
     * upplägget med kollsionstest baserat på successiva frames fungerar inte i nuläget då skotten testas mot en annan actor.
     * Kollisionskontroll mellan två listor med actors
     * @param actorList1
     * @param actorList2
     */
    public void checkDualListCollision_(ArrayList <Actor> actorList1, ArrayList <Actor> actorList2) {


        for (Actor actor1: actorList1) {

            for (Actor actor2: actorList2) {

                int[] types = BoxCollider.boxCollision(actor1, actor2);

                int xx = 0;
                if (types[0] != -1 || types[1] != -1)
                    xx = 1;
            }
        }
    }


    public static boolean checkCollisionPlayer(ArrayList <Actor> playerActorList, ArrayList <Actor> actorList) {

        for (Actor playerActor: playerActorList) {

            boolean hit = false;
            Iterator<Actor> iter = actorList.iterator();
            while (iter.hasNext()) {
                Actor actor = iter.next();

                hit = BoxCollider.boxVsBox(playerActor, actor);
                int x = 0;
                if (hit) {
                    return true;
                }

            }
        }
        return false;
    }


    /**
     *
     * @param deathTouchActor
     * @param actorList
     */
    public void checkDeathActorCollision(Actor deathTouchActor, ArrayList <Actor> actorList, ArrayList <ActorHolder> actorPoolList) {


        Iterator<Actor> iter = actorList.iterator();
        while (iter.hasNext()) {
            Actor actor = iter.next();

            //int[] type = BoxCollider.boxCollision(actor, deathTouchActor);
            boolean hit = BoxCollider.boxVsBox(actor, deathTouchActor);

            if (hit) {
                System.err.println("DEAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                DamageComponent damageComponent = ((DamageComponent)deathTouchActor.getComponent(ComponentFactory.DAMAGECOMPONENT));
                ((LifeComponent)actor.getComponent(ComponentFactory.LIFECOMPONENT)).damage(damageComponent.getDamageAmount()); // sätt STOR skada på aktorn, så stor att dess hälsa blir 0
                explosionManager.manageExplosions(actor, actor.getType());
                for (ActorHolder actorHolder : actorPoolList) {
                    if (actorHolder.getActor() == actor) {
                        actorHolder.setAvailable(true);
                        break;
                    }
                }

                iter.remove();
            }
        }


    }


    /**
     * Kollisionskontroll mellan två listor med actors
     * @param shotActorList
     * @param actorList
     */
    public boolean checkShotCollision(ArrayList <Actor> shotActorList, ArrayList <Actor> actorList, ArrayList <ActorHolder> actorPoolList) {

        boolean hit = false;
        Iterator<Actor> shotIterator = shotActorList.iterator();
        while (shotIterator.hasNext()) {
            Actor shotActor = shotIterator.next();

            Iterator<Actor> actorIterator = actorList.iterator();
            while (actorIterator.hasNext()) {
                Actor actor = actorIterator.next();

                hit = BoxCollider.boxVsBox(shotActor, actor);
                if (hit) {

                    int amount = ((DamageComponent)shotActor.getComponent(ComponentFactory.DAMAGECOMPONENT)).getDamageAmount();
                    ((LifeComponent)actor.getComponent(ComponentFactory.LIFECOMPONENT)).damage(amount);
                    int health = ((LifeComponent)actor.getComponent(ComponentFactory.LIFECOMPONENT)).getCurrentHealth();
                    if (health <= 0) {
                        //lämna tillbaka till poolen
                        int score =  ((LifeComponent)actor.getComponent(ComponentFactory.LIFECOMPONENT)).getScore();
                        int gameScore = Level.gameScore += score;
                        scoreManager.generateScore(gameScore);
                        for (ActorHolder actorHolder : actorPoolList) {
                            if (actorHolder.getActor() == actor) {
                                actorHolder.setAvailable(true);
                                break;
                            }
                        }

                        actorIterator.remove();
                        explosionManager.manageExplosions(actor, actor.getType());
                        break;
                    }
                }

                if (hit) {
                    shotManager.removeShot(shotActor, shotIterator);
                    return true; // måste returnera - annars uppstår ett ConcurrencyModificationException
                }
            }


        }

        return false;
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
