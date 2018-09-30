package intergalactica.game.se.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartMenuActivity extends AppCompatActivity {

    private Intent startGameActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void onClickStartGame(View view) {


        startGameActivityIntent = new Intent(this, GameActivity.class);
        startActivity(startGameActivityIntent);

    }




}
