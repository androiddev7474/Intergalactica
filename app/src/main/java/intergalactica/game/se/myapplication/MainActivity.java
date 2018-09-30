package intergalactica.game.se.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tillfällig lösning
        SharedPreferences.Editor editor = getSharedPreferences(GameActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(GameActivity.MY_PREFS_STORED_LEVEL_ID, Level1.LEVEL_ID);
        editor.apply();

        Intent intent = new Intent(this, StartMenuActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();


    }
}
