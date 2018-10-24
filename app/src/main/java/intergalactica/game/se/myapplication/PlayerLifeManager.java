package intergalactica.game.se.myapplication;

import java.util.ArrayList;

public class PlayerLifeManager {

    int nRemainingLifes;

    private ArrayList <Actor> playerLifesList;
    private ExplosionManager explosionManager;
    private PositionComponent positionComponent;
    private ArrayList <Actor> playerActorList;
    private ArrayList <Actor> playerBurnerActorList;

    public PlayerLifeManager(ArrayList <Actor> playerActorList, ArrayList <Actor> playerBurnerActorList, ArrayList <Actor> playerLifesList, ExplosionManager explosionManager) {

        this.playerLifesList = playerLifesList;
        this.playerActorList = playerActorList;
        this.playerBurnerActorList = playerBurnerActorList;
        this.explosionManager = explosionManager;

        int cnt = 0;
        for (Actor actor: playerLifesList) {

            positionComponent = (PositionComponent)actor.getComponent(ComponentFactory.POSITIONCOMPONENT);

            switch (cnt) {

                case 0:
                    positionComponent.createPosition(3, 0.5f);
                    break;
                case 1:
                    positionComponent.createPosition(4, 0.5f);
                    break;

                case 2:
                    positionComponent.createPosition(5, 0.5f);
                    break;
            }

            cnt++;
        }

    }

    public void createLifes() {



    }

    public boolean removeLife() {

        Actor actor = playerActorList.get(0);
        explosionManager.manageExplosions(actor, ActorFactory.PLAYER_ACTOR);

        if (playerLifesList.size() > 0) {
            playerLifesList.remove(0);
            playerActorList.clear();
            playerBurnerActorList.clear();
            return true;
        }

        return false;
    }


}
