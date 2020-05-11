package dk.aau.student.meda2a220a.p2protype;

import android.content.Intent;
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

        // Buttons
        Button gameSummaryNextButton = (Button) findViewById(R.id.GameSummaryNextButton);

        // Recent Game Views
        TextView recentGameHitsView = (TextView) findViewById(R.id.RecentGameHitsView);
        TextView recentGameDodgesView = (TextView) findViewById(R.id.RecentGameDodgesView);
        TextView recentGameTimeView = (TextView) findViewById(R.id.RecentGameTimeView);

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
