package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES30;

import java.util.ArrayList;

import compileshaders.opengles.se.shader_compile.Compile;
import libkitv1.opengles.se.opengllibkit1.TextureDataFormatter;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */
public class ActorFactory {

    public static final String STATIC_BACKGROUND_ACTOR = "StaticBackgroundActor";
    public static final String BACKGROUND_ACTOR = "BackgroundActor";
    public static final String BACKGROUND_L2_ACTOR = "BackgroundActor_layer2";
    public static final String BACKGROUND_L3_ACTOR = "BackgroundActor_layer3";
    public static final String PLAYER_ACTOR = "PlayerActor";
    public static final String BATBOOGER_ACTOR = "BatboogerActor";
    public static final String BATBRAINS_ACTOR = "BatBrains";
    public static final String DEATHTOUCH_ACTOR = "DeathTouchActor";
    public static final String EXPLOSION_ACTOR = "ExplosionActor";
    public static final String SHIPBURNER_ACTOR = "ShipBurnerActor";
    public static final String SHOT_ACTOR = "ShotActor";
    public static final String WEAPON_ACTOR = "WeaponActor";
    public static final String PLAYERLIFE_ACTOR = "PlayerLifeActor";
    public static final String SCORE_ACTOR = "ScoreActor";

    public static final String BAT_BOOGER_XMLNAME = "batbooger";
    public static final String BAT_PURPLE_XMLNAME = "batpurple";
    public static final String BAT_BRAINS_XMLNAME = "batbrains";
    public static final String BOOGER_EXPLOSION = "batboogerx";
    public static final String BOOGER_EXPLOSION2 = "batpurplex";
    public static final String BRAINS_EXPLOSION_XMLNAME = "batbrainsx";
    public static final String PLAYER_XMLNAME = "playership";
    public static final String SHIPBURNER_XMLNAME = "shipfire";
    public static final String SHOT_XMLNAME = "weapon1shot";
    public static final String SHIP_EXPLOSION_XMLNAME = "explosionc";
    public static final String NUMBER_XMLNAME = "number";

    public static final int LEVEL_BITMAP = 0;
    private static final int MVP_MATRIX_ATTRIBUTE_NAME_IDX = 0;
    private static final int VERTEX_POSITION_ATTRIBUTE_NAME_IDX = 1;
    private static final int TEXT_POSITION_ATTRIBUTE_NAME_IDX = 2;
    private static final int PIXEL_SHADER_IDX = 0;
    private static final int FRAGMENT_SHADER_IDX = 1;
    public static final int LEVELMAP_BITMAP_IDX = 0;
    public static final int LEVEL1_BITMAP_IDX = 1;
    public static final int LEVEL1_L2_BITMAP_IDX = 2;
    public static final int LEVEL1_L3_BITMAP_IDX = 3;
    public static final int TEXTUREATLAS_IDX = 4;
    public static final int SCOREATLAS_IDX = 5;
    public static final int DJUNGLE_BACKGROUND_IDX = 6;

    private  ArrayList <Integer> unusedActorIDList = new ArrayList<>();
        private ArrayList <String> actorTypeList = new ArrayList<>();
        private int lastActorID;
        private Context context;
        private ResourceLoader resourceLoader;
        private Bitmap[] bitmaps;
        private int bitmapID; // talar om vilken bitmap som kommer att användas

        private int[] shaders;
        private String[] attrsUnifs, attributesExtras;

        public ActorFactory(Context context, Bitmap[] bitmaps) {

            this.context = context;
            this.bitmaps = bitmaps;
            unusedActorIDList 	= new ArrayList<>();
            actorTypeList 		= new ArrayList<>();
            lastActorID			= 0;

            actorTypeList.add(BACKGROUND_ACTOR);
            actorTypeList.add(BACKGROUND_L2_ACTOR);
            actorTypeList.add(BACKGROUND_L3_ACTOR);
            actorTypeList.add(PLAYER_ACTOR);
            actorTypeList.add(BATBOOGER_ACTOR);
            actorTypeList.add(DEATHTOUCH_ACTOR);
            actorTypeList.add(EXPLOSION_ACTOR);
            actorTypeList.add(SHIPBURNER_ACTOR);
            actorTypeList.add(SHOT_ACTOR);
            actorTypeList.add(PLAYERLIFE_ACTOR);
            actorTypeList.add(BATBRAINS_ACTOR);
            actorTypeList.add(STATIC_BACKGROUND_ACTOR);
            actorTypeList.add(SCORE_ACTOR);

        }

    public Bitmap[] getBitmaps() {
        return bitmaps;
    }

    public Actor createActor(String type) {
            if(!validActorType(type)) {
                // actor-typen finns inte / är inte giltig
                // Skriv ut felmeddelande i loggen om felaktig actor-typ och returnera null

                return null;
            }


            Actor actor = new Actor();

            actor.setType(type);
            actor.setId(getNextActorID());

            prepareComponents(actor);

            return actor;
        }

        public void destroyActor(Actor actor) {
            // Spara ner actorID't för senare användning till en ny actor
            unusedActorIDList.add(actor.getId());

            // Sortera listan så alla actor ID'n kommer i ordning, med lägsta numret först i listan
            //unusedActorIDList.sort();
        }


        public boolean validActorType(String type) {

            return actorTypeList.contains(type);
        }

        private int getNextActorID() {
            // Om någon actor blivit förstörd tidigare så finns minst 1 gammalt actor ID i listan
            if(!unusedActorIDList.isEmpty()) {
                int actorID = unusedActorIDList.get(0);

                unusedActorIDList.remove(0);

                return actorID;
            }

            // Om inget actor ID finns i unused-listan, returnera ett nytt
            return (++lastActorID);
        }

    public void setShaders(int[] shaders, String[]  attrsUnifs, String[] attributesExtras) {

          this.shaders = shaders;
          this.attrsUnifs =  attrsUnifs;
          this.attributesExtras = attributesExtras;
    }

    public void setBitmapID(int bitmapID) {

        this.bitmapID = bitmapID;
    }



    private boolean prepareComponents(Actor actor)
    {
        ComponentFactory factory = new ComponentFactory();
        ActorCreator actorCreator = new ActorCreator(context, actor, factory);

        switch(actor.getType())
        {

            case STATIC_BACKGROUND_ACTOR: {

                float translateX = GameRenderer.getGameSceneRight() / 2;
                float translateY = GameRenderer.GAMESCENE_TOP / 2;
                float[] xyx = {translateX, translateY, 0};
                float[] scaleXyz = {GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, 0};
                actorCreator.createTransformComponent(1, xyx, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);


                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);


                break;
            }
            case BACKGROUND_ACTOR: {

                float translateX = GameRenderer.getGameSceneRight() / 2;
                float translateY = GameRenderer.GAMESCENE_TOP / 2;
                float[] xyx = {translateX, translateY, 0};
                float[] scaleXyz = {GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, 0};
                actorCreator.createTransformComponent(1, xyx, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);


                actorCreator.createTextureComponent(bitmaps[bitmapID], GLES30.GL_TEXTURE_2D, GLES30.GL_LINEAR, GLES30.GL_LINEAR, GLES30.GL_MIRRORED_REPEAT);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                float velocity = 0.001f;
                actorCreator.createUVscrollComponent(programHandle, velocity);

                break;
            }
            case BACKGROUND_L2_ACTOR: {

                float translateX = GameRenderer.getGameSceneRight() / 2;
                float translateY = GameRenderer.GAMESCENE_TOP / 2;
                float[] xyx = {translateX, translateY, 0};
                float[] scaleXyz = {GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, 0};
                actorCreator.createTransformComponent(1, xyx, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);

                actorCreator.createTextureComponent(bitmaps[bitmapID], GLES30.GL_TEXTURE_2D, GLES30.GL_LINEAR, GLES30.GL_LINEAR, GLES30.GL_MIRRORED_REPEAT);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                float velocity = 0.0005f;
                actorCreator.createUVscrollComponent(programHandle, velocity);

                break;
            }
            case BACKGROUND_L3_ACTOR: {

                float translateX = GameRenderer.getGameSceneRight() / 2;
                float translateY = GameRenderer.GAMESCENE_TOP / 2;
                float[] xyx = {translateX, translateY, 0};
                float[] scaleXyz = {GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, 0};
                actorCreator.createTransformComponent(1, xyx, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);

                actorCreator.createTextureComponent(bitmaps[bitmapID], GLES30.GL_TEXTURE_2D, GLES30.GL_LINEAR, GLES30.GL_LINEAR, GLES30.GL_MIRRORED_REPEAT);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                float velocity = 0.00025f;
                actorCreator.createUVscrollComponent(programHandle, velocity);

                break;
            }
            case PLAYERLIFE_ACTOR: {

                float[][] textureData = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, PLAYER_XMLNAME);

                ArrayList <float[][]> textDataList = new ArrayList<>();
                textDataList.add(textureData);
                int listID = 0; // vilka ttextdata ska användas?

                float size = 0.75f;
                float[] xyz = {GameRenderer.getGameSceneRight() / 2, 2, 0}; // defaultvärden
                float[] scaleXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(size, xyz, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);
                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String u_name_xy_offset = "xyOffset";
                String u_name_wh_frac = "whFrac";
                int modulo = 1;
                actorCreator.createAnimationComponent(textDataList, listID, programHandle, u_name_xy_offset, u_name_wh_frac, modulo, true);

                actorCreator.createPositionComponent(3);

                break;
            }




            case PLAYER_ACTOR: {

                float[][] textureDataPlayer = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, PLAYER_XMLNAME);
                ArrayList <float[][]> textureDataPlayerList = new ArrayList<>();
                textureDataPlayerList.add(textureDataPlayer);
                int playerListID = 0; // vilka ttextdata ska användas?

                float playerSize = 2;
                float[] playerXyz = {GameRenderer.getGameSceneRight() / 2, GameRenderer.GAMESCENE_TOP * 0.2f, 0}; // defaultvärden
                float[] scalePlayerXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(playerSize, playerXyz, scalePlayerXyz);

                final float playerPolygonSize = 0.5f;
                float[] polySizePlayer = {playerPolygonSize, playerPolygonSize, 0};
                actorCreator.createPolygonComponent(polySizePlayer);

                float[] player_blc = {0, 0};
                float[] player_brc = {1, 0};
                float[] player_tlc = {0, 1};
                float[] player_trc = {1, 1};
                actorCreator.createUVdataComponent(player_blc, player_brc, player_tlc, player_trc, 1);

                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int playerProgramHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String player_name_xy_offset = "xyOffset";
                String player_name_wh_frac = "whFrac";
                int pModulo = 1;
                actorCreator.createAnimationComponent(textureDataPlayerList, playerListID, playerProgramHandle, player_name_xy_offset, player_name_wh_frac, pModulo, true);

                //LIFECOMPONENT
                int player_start_health = 10;
                int player_max_health = 10;
                actorCreator.createLifeComponent(player_start_health, player_max_health, 0);


                DamageComponent damageComponent = (DamageComponent) factory.createComponent(ComponentFactory.DAMAGECOMPONENT);


                ControlComponent controlComponent = (ControlComponent) factory.createComponent(ComponentFactory.CONTROLCOMPONENT);
                controlComponent.setOwner(actor);
                controlComponent.create();
                actor.addComponent(controlComponent);


                actorCreator.createBoxColliderComponent();

                break;}

            case SHOT_ACTOR: {

                float[][] textureData = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, SHOT_XMLNAME);

                ArrayList <float[][]> textDataList = new ArrayList<>();
                textDataList.add(textureData);
                int listID = 0; // vilka ttextdata ska användas?

                float size = 0.5f;
                float[] xyz = {GameRenderer.getGameSceneRight() / 2, 0, 0}; // defaultvärden
                float[] scaleXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(size, xyz, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);
                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String u_name_xy_offset = "xyOffset";
                String u_name_wh_frac = "whFrac";
                int modulo = 1;
                actorCreator.createAnimationComponent(textDataList, listID, programHandle, u_name_xy_offset, u_name_wh_frac, modulo, true);

                // default värden, hastighet o position bestäms sedan i batboogerbehaviourcomponent.
                float[] velocXY = {0.0f, 0.15f};
                int[] dirXY = {MotionComponent.HEADING_NONE, MotionComponent.HEADING_NORTH};
                float[] sceneWalls = {GameRenderer.GAMESCENE_LEFT, GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, GameRenderer.GAMESCENE_BOTTOM};
                actorCreator.createMotionComponent(velocXY[0], velocXY[1], dirXY[0], dirXY[1], sceneWalls);

                actorCreator.createShotBehaviourComponent();

                actorCreator.createBoxColliderComponent();



                int damageAmount = 10;
                actorCreator.createDamageComponent(damageAmount);


                break; }

            case SHIPBURNER_ACTOR:

                float[][] textureDataPBurner = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, SHIPBURNER_XMLNAME);
                ArrayList <float[][]> textureDataPBurnerList = new ArrayList<>();
                textureDataPBurnerList.add(textureDataPBurner);
                int pBurnerListID = 0; // vilka ttextdata ska användas?

                float pBurnerSize = 2;
                float[] pBurnerXyz = {GameRenderer.getGameSceneRight() / 2, GameRenderer.GAMESCENE_TOP * 0.2f, 0}; // defaultvärden
                float[] scalePBurnerXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(pBurnerSize, pBurnerXyz, scalePBurnerXyz);

                final float pBurnerPolygonSize = 0.5f;
                float[] polySizePBurner = {pBurnerPolygonSize, pBurnerPolygonSize, 0};
                actorCreator.createPolygonComponent(polySizePBurner);

                float[] pBurner_blc = {0, 0};
                float[] pBurner_brc = {1, 0};
                float[] pBurner_tlc = {0, 1};
                float[] pBurner_trc = {1, 1};
                actorCreator.createUVdataComponent(pBurner_blc, pBurner_brc, pBurner_tlc, pBurner_trc, 1);

                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int pBurnerProgramHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String pBurner_name_xy_offset = "xyOffset";
                String pBurner_name_wh_frac = "whFrac";
                int pBurnerModulo = 1;
                actorCreator.createAnimationComponent(textureDataPBurnerList, pBurnerListID, pBurnerProgramHandle, pBurner_name_xy_offset, pBurner_name_wh_frac, pBurnerModulo, true);

                //LIFECOMPONENT
                int pBurner_start_health = 10;
                int pBurner_max_health = 10;
                actorCreator.createLifeComponent(pBurner_start_health, pBurner_max_health, 0);


                break;

            case BATBOOGER_ACTOR: {

                float[][] textureDataBooger = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, BAT_BOOGER_XMLNAME);
                float[][] textureDataBooger2 = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, BAT_PURPLE_XMLNAME);

                ArrayList <float[][]> textDataBoogerList = new ArrayList<>();
                textDataBoogerList.add(textureDataBooger);
                textDataBoogerList.add(textureDataBooger2);
                int listID = 0; // vilka ttextdata ska användas?

                float batBoogerSize = 1;
                float[] xyz = {0, 0, 0}; // defaultvärden
                float[] scaleXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(batBoogerSize, xyz, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);
                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String u_name_xy_offset = "xyOffset";
                String u_name_wh_frac = "whFrac";
                int modulo = 4;
                actorCreator.createAnimationComponent(textDataBoogerList, listID, programHandle, u_name_xy_offset, u_name_wh_frac, modulo, true);

                // default värden, hastighet o position bestäms sedan i batboogerbehaviourcomponent.
                float[] velocXY = {0.04f, 0.025f};
                int[] dirXY = {MotionComponent.HEADING_EAST, MotionComponent.HEADING_SOUTH};
                float[] sceneWalls = {GameRenderer.GAMESCENE_LEFT, GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, GameRenderer.GAMESCENE_BOTTOM};
                actorCreator.createMotionComponent(velocXY[0], velocXY[1], dirXY[0], dirXY[1], sceneWalls);

                actorCreator.createBoxColliderComponent();

                actorCreator.createBatBoogerBehaviourComponent();

                int booger_start_health = 10;
                int booger_max_health = 50;
                actorCreator.createLifeComponent(booger_start_health, booger_max_health, 2000);


                break;}
            case BATBRAINS_ACTOR: {

                float[][] textureDataBrains = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, BAT_BRAINS_XMLNAME);

                ArrayList <float[][]> textDataBrainsList = new ArrayList<>();
                textDataBrainsList.add(textureDataBrains);
                int listID = 0; // vilka ttextdata ska användas?

                float BrainsSize = 1;
                float[] xyz = {0, 0, 0}; // defaultvärden
                float[] scaleXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(BrainsSize, xyz, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);
                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String u_name_xy_offset = "xyOffset";
                String u_name_wh_frac = "whFrac";
                int modulo = 4;
                actorCreator.createAnimationComponent(textDataBrainsList, listID, programHandle, u_name_xy_offset, u_name_wh_frac, modulo, true);

                // default värden, hastighet o position bestäms sedan i batboogerbehaviourcomponent.
                float[] velocXY = {0.04f, 0.025f};
                int[] dirXY = {MotionComponent.HEADING_EAST, MotionComponent.HEADING_SOUTH};
                float[] sceneWalls = {GameRenderer.GAMESCENE_LEFT, GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP, GameRenderer.GAMESCENE_BOTTOM};
                actorCreator.createMotionComponent(velocXY[0], velocXY[1], dirXY[0], dirXY[1], sceneWalls);

                actorCreator.createBoxColliderComponent();

                actorCreator.createBatBrainsBehaviourComponent();

                int brains_start_health = 200;
                int brains_max_health = 200;
                actorCreator.createLifeComponent(brains_start_health, brains_max_health, 5000);


                break;}
            case DEATHTOUCH_ACTOR:

                float translateX = GameRenderer.getGameSceneRight() / 2;
                float translateY = GameRenderer.GAMESCENE_TOP * 0.05f;
                float[] xYz = {translateX, translateY, 0};
                float[] scale_xyz = {GameRenderer.getGameSceneRight(), GameRenderer.GAMESCENE_TOP * 0.1f, 0};
                actorCreator.createTransformComponent(1, xYz, scale_xyz);

                actorCreator.createBoxColliderComponent();

                int damageAmount = 100;
                actorCreator.createDamageComponent(damageAmount);

                break;

            default:
                break;

            case EXPLOSION_ACTOR: {

                float[][] textureDataExp0 = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, BOOGER_EXPLOSION);
                float[][] textureDataExp1 = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, BOOGER_EXPLOSION2);
                float[][] textureDataExp2 = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, SHIP_EXPLOSION_XMLNAME);
                float[][] textureDataExp3 = actorCreator.cropTexturesFromAtlas(R.array.aliendata, R.array.alienatlas_dimen, BRAINS_EXPLOSION_XMLNAME);


                ArrayList <float[][]> textDataList = new ArrayList<>();
                textDataList.add(textureDataExp0);
                textDataList.add(textureDataExp1);
                textDataList.add(textureDataExp2);
                textDataList.add(textureDataExp3);
                int explistID = 0; // vilka ttextdata ska användas?

                float[] xYzExp = {3, 5, 0};
                float[] scale_xyzExp = {1, 1, 0};
                actorCreator.createTransformComponent(2, xYzExp, scale_xyzExp);

                final float polygonSizeExp = 0.5f;
                float[] polySizeExp = {polygonSizeExp, polygonSizeExp, 0};
                actorCreator.createPolygonComponent(polySizeExp);

                float[] blcExp = {0, 0};
                float[] brcExp = {1, 0};
                float[] tlcExp = {0, 1};
                float[] trcExp = {1, 1};
                actorCreator.createUVdataComponent(blcExp, brcExp, tlcExp, trcExp, 1);
                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandleExp = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);
                String u_name_xy_offsetExp = "xyOffset";
                String u_name_wh_fracExp = "whFrac";
                int moduloExp = 4;
                actorCreator.createAnimationComponent(textDataList, explistID, programHandleExp, u_name_xy_offsetExp, u_name_wh_fracExp, moduloExp, false);


                break;}
            case SCORE_ACTOR: {

                float[][] textureData = actorCreator.cropTexturesFromAtlas(R.array.coinsatlas, R.array.coinsatlas_dimen, NUMBER_XMLNAME);
                ArrayList <float[][]> textDataList = new ArrayList<>();

                for (int i = 0; i < textureData.length; i++) {

                    float[] f = textureData[i];
                    float[][] iNumb = { textureData[i]  };
                    textDataList.add(iNumb);
                }
                int listID = 5; // vilka ttextdata ska användas? //ad hoc

                float size = 0.5f;
                float[] xyz = {-10, -10, 0}; // defaultvärden
                float[] scaleXyz = {1, 1, 0}; //defaultvärden
                actorCreator.createTransformComponent(size, xyz, scaleXyz);

                final float polygonSize = 0.5f;
                float[] polySize = {polygonSize, polygonSize, 0};
                actorCreator.createPolygonComponent(polySize);

                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                actorCreator.createUVdataComponent(blc, brc, tlc, trc, 1);
                actorCreator.createTextureComponent(bitmaps[bitmapID]);

                int programHandle = actorCreator.createRenderComponent(shaders[PIXEL_SHADER_IDX], shaders[FRAGMENT_SHADER_IDX], attributesExtras, attrsUnifs[MVP_MATRIX_ATTRIBUTE_NAME_IDX],
                        attrsUnifs[VERTEX_POSITION_ATTRIBUTE_NAME_IDX], attrsUnifs[TEXT_POSITION_ATTRIBUTE_NAME_IDX]);

                String u_name_xy_offset = "xyOffset";
                String u_name_wh_frac = "whFrac";
                int modulo = 10;
                actorCreator.createAnimationComponent(textDataList, listID, programHandle, u_name_xy_offset, u_name_wh_frac, modulo, true);

                actorCreator.createScoreComponent();

            break;}

        }

        return true;
    }

}

