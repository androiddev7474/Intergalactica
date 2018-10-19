package intergalactica.game.se.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */
public abstract class BaseComponent {

    private String type = ""; //	→ komponentens typ (t.ex “TransformComponent”)
    private Actor owner;  //→ den actor som äger komponenten

    public abstract void update();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getOwner() {
        return owner;
    }

    public void setOwner(Actor owner) {
        this.owner = owner;
    }
}
