package intergalactica.game.se.myapplication;

/**
 * Används för att ge en actor en livskraft, vilket innebär att en actor kan dö/förstöras när currentHealth i denna komponent blir 0

 */
public class LifeComponent extends BaseComponent {

    int currentHealth;
    int maxHealth;

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
