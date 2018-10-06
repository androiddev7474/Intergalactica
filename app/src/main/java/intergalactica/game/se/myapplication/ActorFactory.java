package intergalactica.game.se.myapplication;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 */
public class ActorFactory {

        public static final int LEVEL_BITMAP = 0;

        private  ArrayList <Integer> unusedActorIDList = new ArrayList<>();
        private ArrayList <String> 	actorTypeList = new ArrayList<>();
        private int lastActorID;
        private Context context;
        private ResourceLoader resourceLoader;
        private Bitmap[] bitmaps;

        public ActorFactory(Bitmap[] bitmaps) {

            this.bitmaps = bitmaps;
            unusedActorIDList 	= new ArrayList<>();
            actorTypeList 		= new ArrayList<>();
            lastActorID			= 0;

            actorTypeList.add("Background");
            //actorType.add("Player");
            //	actorType.add("BatBooger");
            //	actorType.add("TentacleAlien");
            //	Osv
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


    private boolean prepareComponents(Actor actor)
    {
        ComponentFactory factory = new ComponentFactory();

        switch(actor.getType())
        {
            case "Background":
            {
                TransformComponent transformComponent = (TransformComponent) factory.createComponent(ComponentFactory.TRANSFORMCOMPONENT);

                // Sätt inställningar för varje individuell komponent
                // Sätt transformens position
                // Osv

                transformComponent.setX(3.22f);
                transformComponent.setY(5);
                transformComponent.setZ(0);
                transformComponent.setScaleX(6.43f);
                transformComponent.setScaleY(10);
                transformComponent.setScaleZ(0);

                transformComponent.setOwner(actor);
                actor.addComponent(transformComponent);

                PolygonDataComponent polygonDataComponent = (PolygonDataComponent) factory.createComponent(ComponentFactory.MODELCOMPONENT);
                polygonDataComponent.create2Dpolygon(0.5f, 0.5f, 0);
                polygonDataComponent.setOwner(actor);
                actor.addComponent(polygonDataComponent);

                UVdataComponent UVdataComponent = (UVdataComponent) factory.createComponent(ComponentFactory.UVDATACOMPONENT);
                float[] blc = {0, 0};
                float[] brc = {1, 0};
                float[] tlc = {0, 1};
                float[] trc = {1, 1};
                UVdataComponent.createTextData(blc, brc, tlc, trc, 1);
                UVdataComponent.setOwner(actor);
                actor.addComponent(UVdataComponent);

                TextureComponent textureComponent = (TextureComponent) factory.createComponent(ComponentFactory.TEXTURECOMPONENT);
                Texture texture = TextureFactory.createTexture(bitmaps[0]);
                textureComponent.setTexture(texture);
                textureComponent.setOwner(actor);
                actor.addComponent(textureComponent);

                RenderComponent renderComponent = (RenderComponent) factory.createComponent(ComponentFactory.RENDERCOMPONENT);
                renderComponent.setOwner(actor);
                renderComponent.create();
                actor.addComponent(renderComponent);

                // Sätt inställningar för varje individuell komponent
                // Sätt render-komponentens textur
                // Osv

                break;
            }

            case "Player":
            {
                TransformComponent 	transformComponent 	= (TransformComponent) factory.createComponent("TransformComponent");
                RenderComponent 	renderComponent 	= (RenderComponent) factory.createComponent("RenderComponent");
                LifeComponent 		lifeComponent 		= (LifeComponent) factory.createComponent("LifeComponent");
                DamageComponent 	damageComponent 	= (DamageComponent) factory.createComponent("DamageComponent");

                // Sätt inställningar för varje individuell komponent
                // Sätt transformens position
                // Sätt liv till det värde spelaren ska ha (LifeComponent)
                // Sätt hur mycket skada spelaren ska göra mot andra actors (DamageComponent)
                // Osv

                break;
            }

            case "BatBooger":
            {
                TransformComponent 	transformComponent 	= (TransformComponent) factory.createComponent("TransformComponent");
                RenderComponent 	renderComponent 	= (RenderComponent) factory.createComponent("RenderComponent");
                LifeComponent 		lifeComponent 		= (LifeComponent) factory.createComponent("LifeComponent");
                DamageComponent 	damageComponent 	=(DamageComponent) factory.createComponent("DamageComponent");



                break;
            }

            default:
                break;
        }

        return true;
    }
}

