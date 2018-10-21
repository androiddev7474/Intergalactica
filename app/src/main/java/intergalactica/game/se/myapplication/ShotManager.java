package intergalactica.game.se.myapplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class ShotManager {

    private Timer timer = new Timer();
    private ArrayList<Actor> shotList;
    private ArrayList<ActorHolder> shotPoolList;
    private Actor playerActor;

    int frameCntr = 1;
    int modulo = 10;

    public ShotManager(ArrayList<Actor> shotList, ArrayList<ActorHolder> shotPoolList, Actor playerActor) {

        this.shotList = shotList;
        this.shotPoolList = shotPoolList;
        this.playerActor = playerActor;

    }


    public void shotIssuer() {

        if (frameCntr % modulo == 0) {
            addShot_(true);
            //addShot_(false);
        }

        frameCntr++;
    }



    private void addShot() {

        Iterator<ActorHolder> iter = shotPoolList.iterator();
        while (iter.hasNext()) {
            ActorHolder actorHolder = iter.next();

            if ( actorHolder.isAvailable()) {

                Actor actor = actorHolder.getActor();
                TransformComponent shotTransform = (TransformComponent)actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

                TransformComponent shipTransform = (TransformComponent)playerActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
                float yPos = shipTransform.getY();
                float xPos = shipTransform.getX();

                shotTransform.setY(yPos + 1);
                shotTransform.setX(xPos);
                shotList.add(actor);

                actorHolder.setAvailable(false);
                break;
            }
        }
    }

    private void addShot_(boolean left) {

        Iterator<ActorHolder> iter = shotPoolList.iterator();
        while (iter.hasNext()) {
            ActorHolder actorHolder = iter.next();

            if ( actorHolder.isAvailable()) {

                Actor actor = actorHolder.getActor();
                TransformComponent shotTransform = (TransformComponent)actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
                TransformComponent shipTransform = (TransformComponent)playerActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

                float xPos = 0;
                float yPos = shipTransform.getY();
                if (left) {

                    xPos = shipTransform.getX() - 0.5f;
                } else {

                    xPos = shipTransform.getX() + 0.5f;
                }
                shotTransform.setY(yPos + 0.5f);
                shotTransform.setX(xPos);
                shotList.add(actor);
                actorHolder.setAvailable(false);
                break;
            }
        }
    }

    public void removeShot() {

        Iterator<Actor> iter = shotList.iterator();
        while (iter.hasNext()) {
            Actor actor = iter.next();
            TransformComponent transformComponent = (TransformComponent) actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
            if (transformComponent.getY() > 10) {

                for (ActorHolder actorHolder : shotPoolList) {

                    if (actorHolder.getActor() == actor) {
                        actorHolder.setAvailable(true);
                        break;
                    }
                }
                iter.remove();
            }
        }
    }


    public void shotRemoveOnBeyondScene() {

        boolean removeActor = false;
        Iterator<Actor> iter = shotList.iterator();
        while (iter.hasNext()) {
            Actor actor = iter.next();

            String cType = CollisionManager.sceneCollider(actor);
            if (cType.equals(BoxCollider.COLLISION_TOP))
                removeActor = true;

            for (ActorHolder actorHolder: shotPoolList) {

                if (!actorHolder.isAvailable() && removeActor) {

                    if (actorHolder.getActor() == actor) {
                        actorHolder.setAvailable(true);
                    }


                    iter.remove();
                    break;


                }
            }

            if (removeActor)
              break;

        }



    }


}
