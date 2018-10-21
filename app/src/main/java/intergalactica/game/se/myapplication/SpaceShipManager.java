package intergalactica.game.se.myapplication;

import android.opengl.Matrix;

public class SpaceShipManager {

    private float[] m = new float[16];

    private Actor spaceShipActor;
    private Actor spaceShipBurnerActor;


     public SpaceShipManager(Actor spaceShipActor, Actor spaceShipBurnerActor) {

         this.spaceShipActor = spaceShipActor;
         this.spaceShipBurnerActor = spaceShipBurnerActor;

     }

    public void burner() {

        TransformComponent transformShip = (TransformComponent)spaceShipActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        TransformComponent transformBurner = (TransformComponent)spaceShipBurnerActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        float x = transformShip.getX();
        float y = transformShip.getY() - 1.5f;
        transformBurner.setX(x);
        transformBurner.setY(y);

    }

}
