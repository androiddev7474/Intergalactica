package intergalactica.game.se.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */
public class Actor {

    protected String type = "";
    protected Map<String, BaseComponent> componentMap = new HashMap<>();
    protected ArrayList<String> collisionFilter = new ArrayList<>();
    private TransformComponent transformComponent;
    int id;

    public Actor() {

    }

    public void create() {

    }

    public void destroy() {

    }

    public void update() {

    }

    public boolean addComponent(BaseComponent component) {
        String type = component.getType();

        // Vill inte ha dubletter av komponenttyper
        if(hasComponent(type))
            return false;

        //om funktionen inte returnerat - kör på!
        componentMap.put(type, component);

        if(transformComponent != null)
            return true;

        // Spara denna actors transform för enkel och snabb åtkomst
        if(type.equals("TransformComponent"))
            this.transformComponent = (TransformComponent)component;

        return true;
    }

    public boolean hasComponent(String type) {

        return (componentMap.get(type) != null);
    }

    public BaseComponent getComponent(String component) {

        return componentMap.get(component);
    }

    public int getId() {

        return this.id;
    }

    public void setId(int id) {

        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
