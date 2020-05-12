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

    public static int numberOfCurrentGame = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_statistics);


        switch (numberOfCurrentGame) {
            case 1:
                Log.d("CASE 1", "CASE 1 WAS CHOSEN");
                break;
            case 2:
                Log.d("CASE 2", "CASE 2 WAS CHOSEN");
                break;
            case 3:
                Log.d("CASE 3", "CASE 3 WAS CHOSEN");
                break;
            case 4:
                Log.d("CASE 4", "CASE 4 WAS CHOSEN");
                break;
            case 5:
                Log.d("CASE 5", "CASE 5 WAS CHOSEN");
                break;
            case 6:
                Log.d("CASE 6", "CASE 6 WAS CHOSEN");
                break;
            case 7:
                Log.d("CASE 7", "CASE 7 WAS CHOSEN");
                break;
            default:
                Log.d("SWITCH", "ERROR DEFAULT WAS CHOSEN");
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.OnlineStatistics);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
