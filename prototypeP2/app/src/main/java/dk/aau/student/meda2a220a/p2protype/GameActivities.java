package dk.aau.student.meda2a220a.p2protype;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GameActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initializing views of the progress bars
        InitializeProgressBars();

        // Setting values of the progress bars


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.GameActivities);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.GameActivities:
                        return true;
                    case R.id.OnlineStatistics:
                        startActivity(new Intent(getApplicationContext()
                                ,OnlineStatistics.class));
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

    public void InitializeProgressBars() {
        ProgressBar progressBarMo = (ProgressBar) findViewById(R.id.progressBarMo);
        ProgressBar progressBarTu = (ProgressBar) findViewById(R.id.progressBarTu);
        ProgressBar progressBarWe = (ProgressBar) findViewById(R.id.progressBarWe);
        ProgressBar progressBarTh = (ProgressBar) findViewById(R.id.progressBarTh);
        ProgressBar progressBarFr = (ProgressBar) findViewById(R.id.progressBarFr);
        ProgressBar progressBarSa = (ProgressBar) findViewById(R.id.progressBarSa);
        ProgressBar progressBarSu = (ProgressBar) findViewById(R.id.progressBarSu);
        progressBarMo.setProgress(10);
        progressBarMo.setProgressTintList(ColorStateList.valueOf(Color.RED));
        progressBarTu.setProgress(20);
        progressBarTu.setProgressTintList(ColorStateList.valueOf(Color.RED));
        progressBarWe.setProgress(40);
        progressBarWe.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        progressBarTh.setProgress(60);
        progressBarTh.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        progressBarFr.setProgress(80);
        progressBarFr.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        progressBarSa.setProgress(90);
        progressBarSa.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        progressBarSu.setProgress(100);
        progressBarSu.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
    }
}
