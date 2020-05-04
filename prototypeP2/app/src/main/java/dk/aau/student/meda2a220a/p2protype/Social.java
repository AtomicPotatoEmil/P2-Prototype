package dk.aau.student.meda2a220a.p2protype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class Social extends AppCompatActivity implements AddFriendDialog.AddFriendListener {

public static final String profileNameSave = "PROFILENAMESAVE";
public static final String profileSaveKey = "PROFILESAVEKEY";


private LinearLayout addFriendLayout;
private Button addFriendButton;
private EditText setProfileName;
private Button saveNameButton;
TextView putFriendName;
private RelativeLayout social_Layout;



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
                    case R.id.OnlineStatistics:
                        startActivity(new Intent(getApplicationContext()
                                ,OnlineStatistics.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.GameActivities:
                        startActivity(new Intent(getApplicationContext()
                                ,GameActivities.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Social:
                        return true;
                }
                return false;
            }
        });
        putFriendName = findViewById(R.id.putFriendHere);
        social_Layout = findViewById(R.id.socialLayout);

        addFriendLayout = findViewById(R.id.FriendLayout);
        addFriendButton = findViewById(R.id.addFriendButtonID);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFriend();
            }
        });
        saveNameButton = findViewById(R.id.saveUserNameButtonID);
        saveNameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveUsername();

            }

        });
        setProfileName = findViewById(R.id.YourUserName);


    }


    @Override
    protected void onStart() {
        super.onStart();

        loadUsername();
    }

    public void newFriend (){
        AddFriendDialog newFriendDialog = new AddFriendDialog();
        newFriendDialog.show(getSupportFragmentManager(), "New Friend");


    }



//    public TextView createNewFriendView(String friendInfo, int textSize){ //Title being the exercise, and subtitle being the weight lifted.
//        final LinearLayout.LayoutParams FriendViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        final TextView newFriendTextview = new TextView(this);
//        newFriendTextview.setTextSize(textSize);
//        newFriendTextview.setLayoutParams(FriendViewParams);
//        newFriendTextview.setText(friendInfo);
//        newFriendTextview.setTextColor(getResources().getColor(R.color.MINTWHITEd));
//        return newFriendTextview;
//    }

    public void test(String usernamestring){
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.addfriendattributes, (ViewGroup) findViewById(R.id.FriendLayout));
        TextView putfriend = findViewById(R.id.putFriendHere);
        putfriend.setText(usernamestring);



    }

    @Override
    public void applyAddition(String userNameString) {
//        addFriendLayout.addView(createNewFriendView(userNameString,20));

        test(userNameString);
    }

    private void saveUsername() {

        SharedPreferences sharedPrefUserName = getSharedPreferences(profileNameSave, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefUserName.edit();

        editor.putString(profileSaveKey, setProfileName.getText().toString());
        editor.apply();
        Toast.makeText(this, "Profile name: "+setProfileName.getText().toString() + " saved!", Toast.LENGTH_LONG).show();


    }
    private void loadUsername(){

        SharedPreferences sharedPreferences = getSharedPreferences(profileNameSave, MODE_PRIVATE);
        String prefSaveName = sharedPreferences.getString(profileSaveKey, getString(R.string.noUsername));

        setProfileName.setText(prefSaveName);


    }

}
