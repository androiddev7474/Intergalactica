package intergalactica.game.se.myapplication;

import java.util.ArrayList;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */
public class ComponentFactory
{

    public static final String TRANSFORMCOMPONENT = "TransformComponent";
    public static final String RENDERCOMPONENT =  "RenderComponent";
    public static final String MODELCOMPONENT = "PolygonDataComponent";
    public static final String UVDATACOMPONENT = "UVdataComponent";
    public static final String TEXTURECOMPONENT = "TextureComponent";
    public static final String ANIMATIONCOMPONENT = "AnimationComponent";
    public static final String BATBOOGERBEHAVIOURCOMPONENT = "BatBoogerBehaviourComponent";
    public static final String BATBRAINSBEHAVIOURCOMPONENT = "BatBrainsBehaviourComponent";
    public static final String BOXCOLLIDERCOMPONENT = "BoxColliderComponent_notused";
    public static final String MOTIONCOMPONENT = "MotionComponent";
    public static final String DAMAGECOMPONENT = "DamageComponent";
    public static final String LIFECOMPONENT = "LifeComponent";
    public static final String CONTROLCOMPONENT = "ControlComponent";
    public static final String SHOTBEHAVIOURCOMPONENT = "ShotBehaviourComponent";
    public static final String POSITIONCOMPONENT = "PositionComponent";
    public static final String UVSCROLLCOMPONENT = "UVscrollComponent";
    public static final String SCORECOMPONENT = "ScoreComponent";

    ArrayList <String> componentTypeList = new ArrayList<>();

    public ComponentFactory()
    {

        componentTypeList.add(LIFECOMPONENT);
        componentTypeList.add(RENDERCOMPONENT);
        componentTypeList.add(TRANSFORMCOMPONENT);
        componentTypeList.add(MODELCOMPONENT);
        componentTypeList.add(UVDATACOMPONENT);
        componentTypeList.add(TEXTURECOMPONENT);
        componentTypeList.add(ANIMATIONCOMPONENT);
        componentTypeList.add(BATBOOGERBEHAVIOURCOMPONENT);
        componentTypeList.add(BATBRAINSBEHAVIOURCOMPONENT);
        componentTypeList.add(BOXCOLLIDERCOMPONENT);
        componentTypeList.add(MOTIONCOMPONENT);
        componentTypeList.add(DAMAGECOMPONENT);
        componentTypeList.add(LIFECOMPONENT);
        componentTypeList.add(CONTROLCOMPONENT);
        componentTypeList.add(SHOTBEHAVIOURCOMPONENT);
        componentTypeList.add(POSITIONCOMPONENT);
        componentTypeList.add(UVSCROLLCOMPONENT);
        componentTypeList.add(SCORECOMPONENT);
    }

    public BaseComponent createComponent(String type)
    {
        if(!validComponentType(type))
        {
            // Komponenttypen finns inte / är inte giltig
            // Skriv ut felmeddelande i loggen om felaktig komponenttyp och returnera null



            return null;
        }

        BaseComponent component = null;

        // Mer optimerat än if/else if-chain statement

        switch(type) {
            case DAMAGECOMPONENT:
                component = new DamageComponent();
                component.setType(DAMAGECOMPONENT);
                break;
            case RENDERCOMPONENT:
                component = new RenderComponent();
                component.setType(RENDERCOMPONENT);
                break;
            case TRANSFORMCOMPONENT:
                component = new TransformComponent();
                component.setType(TRANSFORMCOMPONENT);
                break;
            case MODELCOMPONENT:
                component = new PolygonDataComponent();
                component.setType(MODELCOMPONENT);
                break;
            case UVDATACOMPONENT:
                component = new UVdataComponent();
                component.setType(UVDATACOMPONENT);
                break;
            case TEXTURECOMPONENT:
                component = new TextureComponent();
                component.setType(TEXTURECOMPONENT);
                break;
            case ANIMATIONCOMPONENT:
                component = new AnimationComponent();
                component.setType(ANIMATIONCOMPONENT);
                break;
            case BATBOOGERBEHAVIOURCOMPONENT:
                component = new BatBoogerBehaviourComponent();
                component.setType(BATBOOGERBEHAVIOURCOMPONENT);
                break;
            case BATBRAINSBEHAVIOURCOMPONENT:
                component = new BatBrainsBehaviourComponent();
                component.setType(BATBRAINSBEHAVIOURCOMPONENT);
                break;
            case BOXCOLLIDERCOMPONENT:
                component = new BoxColliderComponent();
                component.setType(BOXCOLLIDERCOMPONENT);
                break;
            case MOTIONCOMPONENT:
                component = new MotionComponent();
                component.setType(MOTIONCOMPONENT);
                break;
            case LIFECOMPONENT:
                component = new LifeComponent();
                component.setType(LIFECOMPONENT);
                break;
            case CONTROLCOMPONENT:
                component = new ControlComponent();
                component.setType(CONTROLCOMPONENT);
                break;
            case SHOTBEHAVIOURCOMPONENT:
                component = new ShotBehaviourComponent();
                component.setType(SHOTBEHAVIOURCOMPONENT);
                break;
            case POSITIONCOMPONENT:
                component = new PositionComponent();
                component.setType(POSITIONCOMPONENT);
                break;
            case UVSCROLLCOMPONENT:
                component = new UVscrollComponent();
                component.setType(UVSCROLLCOMPONENT);
                break;
            case SCORECOMPONENT:
                component = new ScoreComponent();
                component.setType(SCORECOMPONENT);
                break;
        }

        return component;
    }

    public boolean validComponentType(String type)
    {
        return componentTypeList.contains(type);
    }
}
