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

            case ActorFactory.BATBOOGER_ACTOR: {

                TransformComponent transformComponent = (TransformComponent) actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
                float x = transformComponent.getX();
                float y = transformComponent.getY();
                float[] xy = {x, y};

                for (ActorHolder holder : explosionPooList) {

                    if (holder.isAvailable()) {

                        ((TransformComponent) holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setX(x);
                        ((TransformComponent) holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setY(y);
                        ((AnimationComponent)holder.getActor().getComponent(ComponentFactory.ANIMATIONCOMPONENT)).setListID(0);
                        holder.setAvailable(false);
                        explosionList.add(holder.getActor());
                        break;
                    }
                }
                break;
            }
            case ActorFactory.BATBRAINS_ACTOR: {

                TransformComponent transformComponent = (TransformComponent) actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
                float x = transformComponent.getX();
                float y = transformComponent.getY();
                float[] xy = {x, y};

                for (ActorHolder holder : explosionPooList) {

                    if (holder.isAvailable()) {

                        ((TransformComponent) holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setX(x);
                        ((TransformComponent) holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setY(y);
                        ((AnimationComponent)holder.getActor().getComponent(ComponentFactory.ANIMATIONCOMPONENT)).setListID(3);
                        holder.setAvailable(false);
                        explosionList.add(holder.getActor());
                        break;
                    }
                }
                break;
            }
            case ActorFactory.PLAYER_ACTOR: {

                TransformComponent transformComponent = (TransformComponent) actor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
                float x = transformComponent.getX();
                float y = transformComponent.getY();
                float[] xy = {x, y};

                for (ActorHolder holder : explosionPooList) {

                    if (holder.isAvailable()) {

                        ((TransformComponent) holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setX(x);
                        ((TransformComponent) holder.getActor().getComponent(ComponentFactory.TRANSFORMCOMPONENT)).setY(y);
                        //((AnimationComponent)actor.getComponent(ComponentFactory.ANIMATIONCOMPONENT)).getCurrentAnimation().setID(0);
                        holder.setAvailable(false);
                        Actor explosionActor = holder.getActor();
                        AnimationComponent animationComponent = (AnimationComponent) explosionActor.getComponent(ComponentFactory.ANIMATIONCOMPONENT);
                        animationComponent.setListID(2);
                        explosionList.add(holder.getActor());
                        break;
                    }
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

                for (ActorHolder actorHolder : explosionPooList) {
                    if (actorHolder.getActor() == actor) {
                        actorHolder.setAvailable(true);
                        ((AnimationComponent)actorHolder.getActor().getComponent(ComponentFactory.ANIMATIONCOMPONENT)).getCurrentAnimation().setOneShotAnimationDone(false);
                        break;
                    }
                }

                iter.remove();
            }
        }
    }





}
