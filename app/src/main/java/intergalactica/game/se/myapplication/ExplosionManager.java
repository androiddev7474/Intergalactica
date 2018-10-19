package intergalactica.game.se.myapplication;

import java.util.ArrayList;
import java.util.Iterator;

public class ExplosionManager {

    private ArrayList<ActorHolder> explosionPooList;
    private ArrayList<Actor> explosionList;

    public ExplosionManager(ArrayList <ActorHolder> explosionPoolList, ArrayList <Actor> explosionList) {

        this.explosionPooList = explosionPoolList;
        this.explosionList = explosionList;

    }

    public void manageExplosions(Actor actor, String actorType) {


        switch (actorType) {

            case ActorFactory.BATBOOGER_ACTOR:

                TransformComponent transformComponent = (TransformComponent)actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
                float x = transformComponent.getX();
                float y = transformComponent.getY();
                float[] xy = {x, y};

                for (ActorHolder holder: explosionPooList) {

                    if (holder.isAvailable()) {

                        ((TransformComponent)holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setX(x);
                        ((TransformComponent)holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setY(y);
                        //((AnimationComponent)actor.getComponent(ComponentFactory.ANIMATIONCOMPONENT)).getCurrentAnimation().setID(0);
                        holder.setAvailable(false);
                        explosionList.add(holder.getActor());
                        break;
                    }
                }




                break;


        }

    }

    public void removeExplosions() {



        Iterator<Actor> iter = explosionList.iterator();
        while (iter.hasNext()) {
            Actor actor = iter.next();

            if ( ((AnimationComponent)actor.getComponent(ComponentFactory.ANIMATIONCOMPONENT)).getCurrentAnimation().isOneShotAnimationDone() ) {

                iter.remove();
            }
        }
    }





}
