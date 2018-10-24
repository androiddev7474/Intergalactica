package intergalactica.game.se.myapplication;

import android.opengl.Matrix;

import java.util.ArrayList;

public class SpaceShipManager {

    private float[] m = new float[16];

    private ArrayList <Actor> spaceShipActorList;
    private Actor spaceShipBurnerActor;


     public SpaceShipManager(ArrayList<Actor> spaceShipActorList, Actor spaceShipBurnerActor) {

         this.spaceShipActorList = spaceShipActorList;
         this.spaceShipBurnerActor = spaceShipBurnerActor;

     }

    public void burner() {

        for (Actor spaceShipActor: spaceShipActorList) {

            TransformComponent transformShip = (TransformComponent) spaceShipActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
            TransformComponent transformBurner = (TransformComponent) spaceShipBurnerActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

            float x = transformShip.getX();
            float y = transformShip.getY() - 1.5f;
            transformBurner.setX(x);
            transformBurner.setY(y);

        }

    }

}
