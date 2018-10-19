package intergalactica.game.se.myapplication;

public class TextureComponent extends BaseComponent {

    Texture texture; //innehåller data som t.ex ID (används vid bindning) m.m

    public void setTexture(Texture texture) {

        this.texture = texture;
    }

    public Texture getTexture() {

        return this.texture;
    }

    public void update() {

    }

}
