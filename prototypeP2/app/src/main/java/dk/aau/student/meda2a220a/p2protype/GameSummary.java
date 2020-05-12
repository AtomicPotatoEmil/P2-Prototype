package dk.aau.student.meda2a220a.p2protype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesummary);

        // Instansiating sharedpreferences to use the time variable from the game view
        SharedPreferences prefs = getSharedPreferences("statistics", Context.MODE_PRIVATE);
        int obstaclesDodged = prefs.getInt("obstaclesDodged", 0);
        int obstaclesHit = prefs.getInt("obstaclesHit", 0);
        int time = prefs.getInt("time", 0);

        // Initializes the button from the design view to use them via. code
        Button gameSummaryNextButton = (Button) findViewById(R.id.GameSummaryNextButton);

        // Initializes the 'recent game views' from the design view to use them via. code
        // and sets the text to be the value of the above values from the sharedpreferences instantiations
        TextView recentGameHitsView = (TextView) findViewById(R.id.RecentGameHitsView);
        recentGameHitsView.setText(String.valueOf(obstaclesDodged));
        TextView recentGameDodgesView = (TextView) findViewById(R.id.RecentGameDodgesView);
        recentGameDodgesView.setText(String.valueOf(obstaclesHit));
        TextView recentGameTimeView = (TextView) findViewById(R.id.RecentGameTimeView);
        recentGameTimeView.setText(String.valueOf(time));

        // Best Game Views
        TextView bestGameHitsView = (TextView) findViewById(R.id.BestGameHitsView);
        TextView bestGameDodgesView = (TextView) findViewById(R.id.BestGameDodgesView);
        TextView bestGameTimeView = (TextView) findViewById(R.id.BestGameTimeView);

        gameSummaryNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSummary.this, GameActivities.class));
            }
        });
    }
}
