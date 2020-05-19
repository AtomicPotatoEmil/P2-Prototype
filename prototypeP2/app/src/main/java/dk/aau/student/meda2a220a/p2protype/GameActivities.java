package dk.aau.student.meda2a220a.p2protype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

public class GameActivities extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views of the progress bars
        InitializeProgressBars();

        // Initializes the buttons from the design view to be able to use them via code
        Button playButtonOne = (Button) findViewById(R.id.playButtonOne);
        Button playButtonTwo = (Button) findViewById(R.id.playButtonTwo);

        // Initializes the drop down menus from the design view to be able to use them via code
        Spinner dropDownMenuOne = (Spinner) findViewById(R.id.dropDownMenuOne);
        Spinner dropDownMenuTwo = (Spinner) findViewById(R.id.dropDownMenuTwo);
        Spinner dropDownMenuThree = (Spinner) findViewById(R.id.dropDownMenuThree);

        // Applys the custom layout to the drop down menus to make them fit accordingly
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GameActivities.this, R.layout.spinner_item, getResources().getStringArray(R.array.dropDownMenuItems));
        myAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropDownMenuOne.setAdapter(myAdapter);
        dropDownMenuTwo.setAdapter(myAdapter);
        dropDownMenuThree.setAdapter(myAdapter);


        // Makes the button clickable by adding a on-click listener to them
        playButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switches the activity to Tutorial.class
                startActivity(new Intent(GameActivities.this, Tutorial.class));
            }
        });

        playButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switches the activity to GameSummary.class
                startActivity(new Intent(GameActivities.this, GameSummary.class));
            }
        });


        // Handles the bottom navigation menu at the bottom of the application
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.GameActivities);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Switch statement to determine which page we are currently on
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

        // Instansiating sharedpreferences to use the time variable from the game view
        SharedPreferences prefs = getSharedPreferences("statistics", Context.MODE_PRIVATE);
        int time = prefs.getInt("time", 0);

        // Initalizing the progressbars from the design view to use them via. code
        ProgressBar progressBarMo = (ProgressBar) findViewById(R.id.progressBarMo);
        ProgressBar progressBarTu = (ProgressBar) findViewById(R.id.progressBarTu);
        ProgressBar progressBarWe = (ProgressBar) findViewById(R.id.progressBarWe);
        ProgressBar progressBarTh = (ProgressBar) findViewById(R.id.progressBarTh);
        ProgressBar progressBarFr = (ProgressBar) findViewById(R.id.progressBarFr);
        ProgressBar progressBarSa = (ProgressBar) findViewById(R.id.progressBarSa);
        ProgressBar progressBarSu = (ProgressBar) findViewById(R.id.progressBarSu);

        // Setting the value of the progressbar to the time used in the game view + a preset value / 60 to get the
        // value in minutes since time is seconds
        progressBarMo.setProgress((time+500)/60);
        progressBarTu.setProgress((time+1000)/60);
        progressBarWe.setProgress((time+1500)/60);
        progressBarTh.setProgress((time+2000)/60);
        progressBarFr.setProgress((time+2500)/60);
        progressBarSa.setProgress((time+3000)/60);
        progressBarSu.setProgress((time+4000)/60);



        // If statements to regulate the automatic color coding system used in the chart
        // below 30 = red   ;   above 30 = yellow     ;   above 60 = green
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
