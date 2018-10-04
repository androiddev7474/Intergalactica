package intergalactica.game.se.myapplication;

import java.util.ArrayList;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */
public class ComponentFactory
{
    ArrayList <String> componentTypeList = new ArrayList<>();

    public ComponentFactory()
    {

        componentTypeList.add("DamageComponent");
        componentTypeList.add("LifeComponent");
        componentTypeList.add("RenderComponent");
        componentTypeList.add("TransformComponent");
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

            case "RenderComponent": {
                component = new RenderComponent();
                component.setType("RenderComponent");
                break;
            }

            case "TransformComponent": {
                component = new TransformComponent();
                component.setType("TransformComponent");
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
