package intergalactica.game.se.myapplication;

class ActorHolder {

    private boolean isAvailable;
    private Actor actor;
    private String ownedBy;


    /**
     *
     * @param actor
     * @param isAvailable
     */
    public ActorHolder(Actor actor, boolean isAvailable) {

        this.actor = actor;
        this.isAvailable = isAvailable;
    }


    /**
     *
     * @param actor
     * @param isAvailable
     * @param ownedBy aktuellt för tex explosioner som ska tillhöra actorn BatBooger
     */
    public ActorHolder(Actor actor, boolean isAvailable, String ownedBy) {

        this.actor = actor;
        this.isAvailable = isAvailable;
        this.ownedBy = ownedBy;
    }


    public boolean isAvailable() {
        return isAvailable;
    }


    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public Actor getActor() {
        return actor;
    }


    public String getActorType() {
        return ownedBy;
    }
}
