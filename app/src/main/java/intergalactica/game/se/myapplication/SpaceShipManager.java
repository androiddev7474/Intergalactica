package intergalactica.game.se.myapplication;

import android.opengl.Matrix;

public class SpaceShipManager {

    Actor spaceShipActor;
    Actor spaceShipBurnerActor;
    float[] m = new float[16];


     public SpaceShipManager(Actor spaceShipActor, Actor spaceShipBurnerActor) {

         this.spaceShipActor = spaceShipActor;
         this.spaceShipBurnerActor = spaceShipBurnerActor;

     }

    public void burner() {


        TransformComponent transformShip = (TransformComponent)spaceShipActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        TransformComponent transformBurner = (TransformComponent)spaceShipBurnerActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);

        float[] matrixShip = transformShip.getMatrix();

        //float[] matrixBurner = transformBurner.getMatrix();
        //Matrix.setIdentityM(matrixBurner, 0);

        //Matrix.multiplyMM(mMVPMatrix, 0, GLprojection.getmProjectionMatrix(), 0, mMVPMatrix, 0);
        //Matrix.multiplyMM(matrixBurner, 0, matrixBurner, 0, matrixShip, 0);

        float y = transformShip.getY() * 0.7f;
        float x = transformShip.getX();

        //transformBurner.setX(x);
        //transformBurner.setY(y);

        transformBurner.setmModelMatrix(matrixShip);

    }

}
