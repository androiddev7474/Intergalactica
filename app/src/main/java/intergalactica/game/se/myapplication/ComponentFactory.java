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

    ArrayList <String> componentTypeList = new ArrayList<>();

    public ComponentFactory()
    {

        componentTypeList.add("DamageComponent");
        componentTypeList.add("LifeComponent");
        componentTypeList.add(RENDERCOMPONENT);
        componentTypeList.add(TRANSFORMCOMPONENT);
        componentTypeList.add(MODELCOMPONENT);
        componentTypeList.add(UVDATACOMPONENT);
        componentTypeList.add(TEXTURECOMPONENT);
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
            case "DamageComponent": {
                component = new DamageComponent();

                break;
            }

            case "LifeComponent": {
                component = new LifeComponent();

                break;
            }

            case RENDERCOMPONENT: {
                component = new RenderComponent();
                component.setType(RENDERCOMPONENT);
                break;
            }

            case TRANSFORMCOMPONENT: {
                component = new TransformComponent();
                component.setType(TRANSFORMCOMPONENT);
                break;
            }
            case MODELCOMPONENT: {
                component = new PolygonDataComponent();
                component.setType(MODELCOMPONENT);
                break;
            }
            case UVDATACOMPONENT: {
                component = new UVdataComponent();
                component.setType(UVDATACOMPONENT);
                break;
            }
            case TEXTURECOMPONENT: {
                component = new TextureComponent();
                component.setType(TEXTURECOMPONENT);
                break;
            }

            default:
                break;
        }

        return component;
    }

    public boolean validComponentType(String type)
    {
        return componentTypeList.contains(type);
    }
}
