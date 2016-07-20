package com.example.wagh.firebasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by wagh on 20/7/16.
 */
public class user_space extends AppCompatActivity {

    Button changepassword,deleteaccount,adddata,done;
    EditText change;
    TextView showname;
    CardView cardView;
    ScrollView scrollView;

    String userspacename;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_space);

        changepassword=(Button)findViewById(R.id.userchangepassword);
        deleteaccount=(Button)findViewById(R.id.userdelete);
        adddata=(Button)findViewById(R.id.useradddata);
        showname=(TextView)findViewById(R.id.usernametv);
        change=(EditText)findViewById(R.id.userchangetv);
        done=(Button)findViewById(R.id.userdone);
        cardView=(CardView)findViewById(R.id.usercv3);
        scrollView=(ScrollView)findViewById(R.id.userscroll);

        //i want card view to be disabled till the time its of no use

        //scrollView.setVisibility(cardView.GONE);

        Intent getLogin= new Intent(getIntent());

        userspacename="\nWelcome\n";

        userspacename+=getLogin.getStringExtra("Username");

        userspacename+="\n";

        showname.setText(userspacename);



    }
}
