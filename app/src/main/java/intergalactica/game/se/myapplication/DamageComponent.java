package intergalactica.game.se.myapplication;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att ge en actor möjligheten att skada en annan actor vid beröring, men endast om den andra actorn har en LifeComponent
 */
public class DamageComponent extends BaseComponent {

    int damageAmount;

    public void create() {

    }

    public void destroy() {

    }

    public void update() {



    }

    public int getDamageAmount() {
        return damageAmount;
    }

    public void setDamageAmount(int damageAmount) {

        this.damageAmount = damageAmount;
    }
}
