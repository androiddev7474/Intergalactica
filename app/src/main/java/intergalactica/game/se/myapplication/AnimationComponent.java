package intergalactica.game.se.myapplication;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Skapad: 2018-10-04
 * Björn Hallström
 * Version: 1
 * Används för att klippa ut delar av en textur och skapa animationsframes, vilket i sin tur innebär en animerad textur
 */
public class AnimationComponent extends BaseComponent {

    //Map<String, Animation*> → String = t.ex “Idle”, “Shooting”, “Death” osv
    private Map <String, Animation> animationMap = new HashMap<>(); //String = t.ex “Idle”, “Shooting”, “Death” osv
    private int nFrames;
    private Animation currentAnimation;
    private float[][] spriteTextData; // texturdata - fem kolumner, första två är offsetvärdena för x- och y dvs var på atlas texturen befinner sig. kolumn 3 och fyra är skalningsvärden (fraktioner) för x,y = texturens storlek på atlas
    private int uvOffset, wHfrac;
    private int mProgramHandle;
    private TransformComponent transformComponent;
    private ArrayList<float[][]> spriteTextDataList;
    private int listID;

    public void create() {

        currentAnimation = new Animation();
        transformComponent = (TransformComponent)getOwner().getComponent(ComponentFactory.TRANSFORMCOMPONENT);

    }

    public void destroy() {

    }

    public void update() {

        currentAnimation.animate();
        int id = currentAnimation.getID();

        GLES30.glUseProgram(mProgramHandle);
        //animationstest
        float[] xyOffsets = {spriteTextDataList.get(listID)[id][0], spriteTextDataList.get(listID)[id][1]};
        float[] whFracs = {spriteTextDataList.get(listID)[id][2], spriteTextDataList.get(listID)[id][3]};

        float wHratio = spriteTextDataList.get(listID)[id][4];
        float width = wHratio * transformComponent.getSize();
        float height = transformComponent.getSize();
        transformComponent.setScaleX(width);
        transformComponent.setScaleY(height);


        GLES30.glUniform2fv(uvOffset, 1, xyOffsets, 0);
        GLES30.glUniform2fv(wHfrac, 1, whFracs, 0);



    }


    /**
     *
     * @param spriteTextDataList
     */
    public void setSpriteTextData(ArrayList<float[][]> spriteTextDataList, int listID) {

        this.spriteTextDataList = spriteTextDataList;
        this.listID = listID;
        //this.spriteTextData = spriteTextDataList.get(listID);
        this.nFrames = spriteTextDataList.size();
    }

    public float[][] getSpriteTextData() {

        return spriteTextData;
    }

    public void activateAnimation() { //Sätter currentAnimation till en specifik animation



    }


    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public void setUVcoordsHandles(int uvOffset, int wHfrac) {

        this.uvOffset = uvOffset;
        this.wHfrac = wHfrac;

    }

    public void setmProgramHandle(int mProgramHandle) {
        this.mProgramHandle = mProgramHandle;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getListID() {
        return listID;
    }
}
