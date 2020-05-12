package dk.aau.student.meda2a220a.p2protype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OnlineStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_statistics);

        // Handles the bottom navigation menu at the bottom of the application
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.OnlineStatistics);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Switch statement to determine which page we are currently on
                switch (menuItem.getItemId()) {
                    case R.id.OnlineStatistics:
                        return true;
                    case R.id.GameActivities:
                        startActivity(new Intent(getApplicationContext()
                                ,GameActivities.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Social:
                        startActivity(new Intent(getApplicationContext()
                                ,Social.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}
