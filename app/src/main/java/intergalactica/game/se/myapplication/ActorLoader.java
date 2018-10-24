package intergalactica.game.se.myapplication;

import java.util.ArrayList;




public class ActorLoader {

    private Actor backgroundActor, playerActor, scoreActor, playerLifeActor, playerBurnerActor, shotActor, batboogerActor, batBrainsActor, deathTouchActor;
    private ArrayList<ActorHolder> boogerPoolList = new ArrayList();
    private ArrayList<ActorHolder> brainsPoolList = new ArrayList();
    private ArrayList <ActorHolder> explosionPoolList = new ArrayList<>();
    private ArrayList <ActorHolder> shotPoolList = new ArrayList<>();
    private ArrayList <Actor> boogerList = new ArrayList<>();
    private ArrayList <Actor> brainsList = new ArrayList<>();
    private ArrayList <Actor> explosionList = new ArrayList();
    private ArrayList <Actor> shotList = new ArrayList();
    private ArrayList <Actor> playerLifeList = new ArrayList<>();
    private ArrayList <Actor> playerActorList = new ArrayList<>();
    private ArrayList <Actor> scoreActorList = new ArrayList<>();
    private ArrayList <Actor> playerBurnerActorList = new ArrayList<>();

    private ActorFactory actorFactory;

    public ActorLoader(ActorFactory actorFactory) {

        this.actorFactory = actorFactory;

        String[] attributes = new String[]{"a_Position", "a_Color", "a_Normal", "a_TexCoordinate"};
        String[] attrsUnifs = {"u_MVPMatrix", "a_Position", "a_TexCoordinate"};
        int[] shaders = {R.raw.per_pixel_vertex_shader_sprite, R.raw.per_pixel_fragment_shader_sprite};

        createPlayer(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.PLAYER_ACTOR);
        playerActorList.add(playerActor);

        createPlayerBurner(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.SHIPBURNER_ACTOR);

        final int N_PLAYER_LIFES = 3;
        createPlayerLifes(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.PLAYERLIFE_ACTOR, N_PLAYER_LIFES);

        final int N_SCORE_POSITIONS = 8;
        createScores(attributes, shaders, attrsUnifs, ActorFactory.SCOREATLAS_IDX, ActorFactory.SCORE_ACTOR, N_SCORE_POSITIONS);


        final int N_BOOGERS = 7;
        createBoogerPool(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.BATBOOGER_ACTOR, N_BOOGERS);

        final int N_BRAINS = 3;
        createBrainsPool(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.BATBRAINS_ACTOR, N_BRAINS);

        final int N_SHOTS = 15;
        createShotPool(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.SHOT_ACTOR, N_SHOTS);

        final int N_EXPLOSIONS = 7;
        createExplosionPool(attributes, shaders, attrsUnifs, ActorFactory.TEXTUREATLAS_IDX, ActorFactory.EXPLOSION_ACTOR, N_EXPLOSIONS);

    }

    public ActorFactory getActorFactory() {
        return actorFactory;
    }

    public void createPlayer(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName) {

        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(bitmapID);
        playerActor = actorFactory.createActor(actorName);
        playerActor.create();
        //playerActorList.add(playerActor);
    }

    public void createPlayerBurner(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName) {

        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(bitmapID);
        playerBurnerActor = actorFactory.createActor(actorName);
        playerBurnerActor.create();
        playerBurnerActorList.add(playerBurnerActor);
    }

    public void createPlayerLifes(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int nTextures) {

        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(bitmapID);
        for (int i = 0; i < nTextures; i++) {

            playerLifeActor = actorFactory.createActor(actorName);
            playerLifeActor.create();

            playerLifeList.add(playerLifeActor);
        }
    }

    public void createScores(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int nTextures) {

        for (int i = 0; i < nTextures; i++) {
            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(bitmapID);
            scoreActor = actorFactory.createActor(actorName);
            scoreActor.create();
            scoreActorList.add(scoreActor);
        }
    }


    public void createBoogerPool(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int nTextures) {

        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
        //batbooger
        for (int i = 0; i < nTextures; i++) {

            batboogerActor = actorFactory.createActor(ActorFactory.BATBOOGER_ACTOR);
            batboogerActor.create();

            boogerPoolList.add(new ActorHolder(batboogerActor, true));
        }
    }


    public void createBrainsPool(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int nTextures) {

        actorFactory.setShaders(shaders, attrsUnifs, attributes);
        actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
        //batbooger
        for (int i = 0; i < nTextures; i++) {

            batBrainsActor = actorFactory.createActor(ActorFactory.BATBRAINS_ACTOR);
            batBrainsActor.create();

            brainsPoolList.add(new ActorHolder(batBrainsActor, true));
        }
    }


    public void createShotPool(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int nTextures) {

        for (int i = 0; i < nTextures; i++) {

            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(bitmapID);
            shotActor = actorFactory.createActor(actorName);
            shotActor.create();
            shotPoolList.add(new ActorHolder(shotActor, true));
        }
    }


    public void createExplosionPool(String[] attributes, int[] shaders, String[] attrsUnifs, int bitmapID, String actorName, int nTextures) {

        for (int i = 0; i < nTextures; i++) {

            actorFactory.setShaders(shaders, attrsUnifs, attributes);
            actorFactory.setBitmapID(ActorFactory.TEXTUREATLAS_IDX);
            Actor explosionActor = actorFactory.createActor(ActorFactory.EXPLOSION_ACTOR);
            explosionActor.create();
            explosionPoolList.add(new ActorHolder(explosionActor, true, ""));
        }
    }






    public Actor getBackgroundActor() {
        return backgroundActor;
    }

    public Actor getPlayerActor() {
        return playerActor;
    }

    public Actor getPlayerLifeActor() {
        return playerLifeActor;
    }

    public Actor getPlayerBurnerActor() {
        return playerBurnerActor;
    }

    public Actor getShotActor() {
        return shotActor;
    }

    public Actor getBatboogerActor() {
        return batboogerActor;
    }

    public Actor getDeathTouchActor() {
        return deathTouchActor;
    }

    public ArrayList<ActorHolder> getBoogerPoolList() {
        return boogerPoolList;
    }

    public ArrayList<ActorHolder> getExplosionPoolList() {
        return explosionPoolList;
    }

    public ArrayList<ActorHolder> getShotPoolList() {
        return shotPoolList;
    }

    public ArrayList<Actor> getBoogerList() {
        return boogerList;
    }

    public ArrayList<Actor> getExplosionList() {
        return explosionList;
    }

    public ArrayList<Actor> getShotList() {
        return shotList;
    }

    public ArrayList<Actor> getPlayerLifeList() {
        return playerLifeList;
    }

    public ArrayList<Actor> getPlayerActorList() {
        return playerActorList;
    }

    public ArrayList<Actor> getPlayerBurnerActorList() {
        return playerBurnerActorList;
    }

    public Actor getBatBrainsActor() {
        return batBrainsActor;
    }

    public ArrayList<ActorHolder> getBrainsPoolList() {
        return brainsPoolList;
    }

    public ArrayList<Actor> getBrainsList() {
        return brainsList;
    }

    public Actor getScoreActor() {
        return scoreActor;
    }

    public ArrayList<Actor> getScoreActorList() {
        return scoreActorList;
    }
}
