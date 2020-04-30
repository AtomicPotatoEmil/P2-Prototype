package dk.aau.student.meda2a220a.p2protype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Social extends AppCompatActivity implements AddFriendDialog.AddFriendListener {

private LinearLayout addFriendLayout;
private Button addFriendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.Social);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.GameActivities:
                        startActivity(new Intent(getApplicationContext()
                                ,GameActivities.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.OnlineStatistics:
                        startActivity(new Intent(getApplicationContext()
                                ,OnlineStatistics.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Social:
                        return true;
                }
                return false;
            }
        });
        addFriendLayout = findViewById(R.id.FriendLayout);
        addFriendButton = findViewById(R.id.addFriendButtonID);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFriend();
            }
        });
    }

    public void newFriend (){
        AddFriendDialog newFriendDialog = new AddFriendDialog();
        newFriendDialog.show(getSupportFragmentManager(), "New Friend");
    }

    public TextView createNewFriendView(String logInfo, int textSize){ //Title being the exercise, and subtitle being the weight lifted.
        final LinearLayout.LayoutParams FriendViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        final TextView newFriendTextview = new TextView(this);
        newFriendTextview.setTextSize(textSize);
        newFriendTextview.setLayoutParams(FriendViewParams);
        newFriendTextview.setText(logInfo);
        newFriendTextview.setTextColor(getResources().getColor(R.color.MINTWHITEd));

        return newFriendTextview;
    }

    @Override
    public void applyAddition(String userNameString) {
        addFriendLayout.addView(createNewFriendView(userNameString,20));

    }
}
