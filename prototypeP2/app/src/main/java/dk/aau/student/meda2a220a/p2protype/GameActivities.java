package dk.aau.student.meda2a220a.p2protype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GameActivities extends AppCompatActivity {

    private Button playButtonOne;
    private Button playButtonTwo;

    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    //SharedPreferences prefs = getSharedPreferences("statistics", Context.MODE_PRIVATE);
    //int time = prefs.getInt("time", 0);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views of the progress bars
        InitializeProgressBars();

        Button playButtonOne = (Button) findViewById(R.id.playButtonOne);
        Button playButtonTwo = (Button) findViewById(R.id.playButtonTwo);

        Spinner dropDownMenuOne = (Spinner) findViewById(R.id.dropDownMenuOne);
        Spinner dropDownMenuTwo = (Spinner) findViewById(R.id.dropDownMenuTwo);
        Spinner dropDownMenuThree = (Spinner) findViewById(R.id.dropDownMenuThree);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GameActivities.this, R.layout.spinner_item, getResources().getStringArray(R.array.dropDownMenuItems));
        myAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropDownMenuOne.setAdapter(myAdapter);
        dropDownMenuTwo.setAdapter(myAdapter);
        dropDownMenuThree.setAdapter(myAdapter);


        playButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivities.this, Tutorial.class));
            }
        });

        playButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivities.this, GameSummary.class));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.GameActivities);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.OnlineStatistics:
                        startActivity(new Intent(getApplicationContext()
                                ,OnlineStatistics.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.GameActivities:
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void InitializeProgressBars() {
        ProgressBar progressBarMo = (ProgressBar) findViewById(R.id.progressBarMo);
        ProgressBar progressBarTu = (ProgressBar) findViewById(R.id.progressBarTu);
        ProgressBar progressBarWe = (ProgressBar) findViewById(R.id.progressBarWe);
        ProgressBar progressBarTh = (ProgressBar) findViewById(R.id.progressBarTh);
        ProgressBar progressBarFr = (ProgressBar) findViewById(R.id.progressBarFr);
        ProgressBar progressBarSa = (ProgressBar) findViewById(R.id.progressBarSa);
        ProgressBar progressBarSu = (ProgressBar) findViewById(R.id.progressBarSu);

        progressBarMo.setProgress(10);
        progressBarTu.setProgress(20);
        progressBarWe.setProgress(30);
        progressBarTh.setProgress(40);
        progressBarFr.setProgress(50);
        progressBarSa.setProgress(60);
        progressBarSu.setProgress(70);

        if (progressBarMo.getProgress() <= 30) {
            progressBarMo.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarMo.getProgress() > 30 && progressBarMo.getProgress() < 60) {
            progressBarMo.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarMo.getProgress() >= 60) {
            progressBarMo.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        if (progressBarTu.getProgress() <= 30) {
            progressBarTu.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarTu.getProgress() > 30 && progressBarTu.getProgress() < 60) {
            progressBarTu.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarTu.getProgress() >= 60) {
            progressBarTu.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        if (progressBarWe.getProgress() <= 30) {
            progressBarWe.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarWe.getProgress() > 30 && progressBarWe.getProgress() < 60) {
            progressBarWe.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarWe.getProgress() >= 60) {
            progressBarWe.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        if (progressBarTh.getProgress() <= 30) {
            progressBarTh.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarTh.getProgress() > 30 && progressBarTh.getProgress() < 60) {
            progressBarTh.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarTh.getProgress() >= 60) {
            progressBarTh.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        if (progressBarFr.getProgress() <= 30) {
            progressBarFr.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarFr.getProgress() > 30 && progressBarFr.getProgress() < 60) {
            progressBarFr.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarFr.getProgress() >= 60) {
            progressBarFr.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        if (progressBarSa.getProgress() <= 30) {
            progressBarSa.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarSa.getProgress() > 30 && progressBarSa.getProgress() < 60) {
            progressBarSa.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarSa.getProgress() >= 60) {
            progressBarSa.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        if (progressBarSu.getProgress() <= 30) {
            progressBarSu.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progressBarSu.getProgress() > 30 && progressBarSu.getProgress() < 60) {
            progressBarSu.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else if (progressBarSu.getProgress() >= 60) {
            progressBarSu.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

    }
}
