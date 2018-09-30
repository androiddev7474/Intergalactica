package intergalactica.game.se.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    private GameGLsurfaceView gameGLsurfaceView;
    public static final String MY_PREFS_NAME = "saved_game_state";
    public static final String MY_PREFS_STORED_LEVEL_ID = "level_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameGLsurfaceView = findViewById(R.id.glsurface_id);

    }

    @Override
    public void onBackPressed() {

        GameManager gm = gameGLsurfaceView.getGameRenderer().getGameManager();
        saveGameState(gm);

        finish();
    }

    private void saveGameState(GameManager gm) {

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(MY_PREFS_STORED_LEVEL_ID, gm.getLevel().getLevel());
        editor.apply();
    }
}
