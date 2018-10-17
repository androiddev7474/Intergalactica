package intergalactica.game.se.myapplication;

import android.opengl.GLES30;

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
    private RenderComponent renderComponent;
    private TextureComponent textureComponent;
    private AnimationComponent animationComponent;
    private BatBoogerBehaviourComponent batBoogerBehaviourComponent;
    private DamageComponent damageComponent;
    private LifeComponent lifeComponent;
    private BoxColliderComponent boxColliderComponent;

    private int textureID;
    int id;


    public Actor() {

    }

    public void create() {

        renderComponent = (RenderComponent)componentMap.get(ComponentFactory.RENDERCOMPONENT);
        textureComponent = (TextureComponent)componentMap.get(ComponentFactory.TEXTURECOMPONENT);
        if (textureComponent != null)
            textureID = textureComponent.getTexture().getTextureData().ID[0];
        animationComponent = (AnimationComponent)componentMap.get(ComponentFactory.ANIMATIONCOMPONENT);

        batBoogerBehaviourComponent = (BatBoogerBehaviourComponent)componentMap.get(ComponentFactory.BATBOOGERBEHAVIOURCOMPONENT);

        damageComponent = (DamageComponent)componentMap.get(ComponentFactory.DAMAGECOMPONENT);

        lifeComponent = (LifeComponent)componentMap.get(ComponentFactory.LIFECOMPONENT);

        boxColliderComponent = (BoxColliderComponent)componentMap.get(ComponentFactory.BOXCOLLIDERCOMPONENT);

    }

    public void destroy() {

    }

    public void update() {



        transformComponent.update();

        if(batBoogerBehaviourComponent != null)
            batBoogerBehaviourComponent.update();

        if(boxColliderComponent != null) {
            boxColliderComponent.update();
        }

        if (animationComponent != null)
            animationComponent.update();

        if (damageComponent != null)
            damageComponent.update();

        if (lifeComponent != null)
            lifeComponent.getCurrentHealth();

    }

    public void render() {




        if (renderComponent != null) {
            GLES30.glBindTexture(Texture.TYPE, textureID);
            renderComponent.render();
        }



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
        if(type.equals(ComponentFactory.TRANSFORMCOMPONENT))
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
