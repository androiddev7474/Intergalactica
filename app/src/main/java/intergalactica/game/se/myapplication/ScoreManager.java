package intergalactica.game.se.myapplication;

import java.util.ArrayList;

public class ScoreManager {

    private Actor scoreActor;
    private TransformComponent transformComponent;
    private AnimationComponent animationComponent;

    private ArrayList <Actor> scoreActorList;

    public ScoreManager(ArrayList <Actor> scoreActorList) {

        this.scoreActorList = scoreActorList;

        //transformComponent = (TransformComponent)scoreActor.getComponent(ComponentFactory.TRANSFORMCOMPONENT);
        //animationComponent = (AnimationComponent) scoreActor.getComponent(ComponentFactory.ANIMATIONCOMPONENT);
    }


    public void generateScore(int inputValues) {

        //int score = 2000009;
        String scoreString = "" + inputValues;
        int endIndex = scoreString.length();


        int[] scoreArray = new int[endIndex];
        for (int i = 0; i < endIndex; i++) {

            String s = scoreString.substring(i, i + 1);
            int numb = Integer.parseInt(s);
            scoreArray[i] = numb;
        }

        ///´sätt nummer på sina givna positioner
        float incr = 0.5f;
        for (int i = 0; i < scoreActorList.size(); i++) {

            ScoreComponent scoreComponent = (ScoreComponent)scoreActorList.get(i).getComponent(ComponentFactory.SCORECOMPONENT);
            scoreComponent.setiNumb(scoreArray[i]);
            float x_pos = 0.5f  + i * 0.5f;
            scoreComponent.setxPos(x_pos);
            scoreComponent.setyPos(9);

            //ev. tomma positioner måste flyttas långt utanför clipspace. Kanske inte den bästa lösningen men tillsvidare :-p
            if (i == scoreArray.length - 1) {

                int remaingSize = scoreActorList.size() - scoreArray.length;
                for (int j = scoreActorList.size() - 1; j >= scoreArray.length; j--) {

                    ScoreComponent scoreComp = (ScoreComponent)scoreActorList.get(j).getComponent(ComponentFactory.SCORECOMPONENT);
                    scoreComp.setxPos(-100);
                }
                break;
            }

        }

    }

    public void generateScore_(String inputValues) {

        int i = 0;
        for (Actor scoreActor: scoreActorList) {

          ScoreComponent scoreComponent = (ScoreComponent)scoreActor.getComponent(ComponentFactory.SCORECOMPONENT);
          scoreComponent.setiNumb(i);
          scoreComponent.setxPos(i);
          scoreComponent.setyPos(9);

          i++;

        }

    }

}
