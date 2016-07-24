package com.example.wagh.firebasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by wagh on 20/7/16.
 */
public class user_space extends AppCompatActivity
{

    Button changepassword,deleteaccount,adddata,done,logout;
    EditText change;
    TextView showname;
    CardView cardView;
    LinearLayout linearLayout;
    FirebaseAuth fireattack;


    String userspacename,newpassword=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_space);

        fireattack=FirebaseAuth.getInstance();

        //this variable is for like changing some details like change password and email and many things

        final FirebaseUser currentuser=FirebaseAuth.getInstance().getCurrentUser();

        changepassword=(Button)findViewById(R.id.userspacechangepassword);
        deleteaccount=(Button)findViewById(R.id.userspacedelete);
        adddata=(Button)findViewById(R.id.userspaceadddata);
        logout=(Button)findViewById(R.id.userspacelogout);
        showname=(TextView)findViewById(R.id.usernametv);
        change=(EditText)findViewById(R.id.userchangetv);
        done=(Button)findViewById(R.id.userdone);
        cardView=(CardView)findViewById(R.id.usercv3);
        linearLayout=(LinearLayout) findViewById(R.id.hidemecardview);

        //i want card view to be disabled till the time its of no use

        linearLayout.setVisibility(cardView.GONE);

        Intent getLogin= new Intent(getIntent());

        userspacename="\nWelcome\n";

        userspacename+=getLogin.getStringExtra("Username");

        userspacename+="\n";

        showname.setText(userspacename);


        changepassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                linearLayout.setVisibility(cardView.VISIBLE);

                change.setHint("Enter new password");

                done.setText("Change");


                done.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        newpassword=change.getText().toString().trim();

                        currentuser.updatePassword(newpassword).addOnCompleteListener(user_space.this, new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                Toast.makeText(user_space.this, "Password Change Done!!", Toast.LENGTH_SHORT).show();

                                linearLayout.setVisibility(cardView.GONE);

                                change.setText("");

                            }

                        });

                    }

                });
            }

        });

        deleteaccount.setOnClickListener(new View.OnClickListener()
            {
                @Override
                 public void onClick(View view)
                {
                    if (currentuser!=null)
                    {
                        currentuser.delete().addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                Toast.makeText(user_space.this,"Account Deleted Successful",Toast.LENGTH_SHORT).show();

                                Intent login =new Intent(user_space.this,MainActivity.class);

                                startActivity(login);

                                finish();

                            }
                        });

                    }


                }
            });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fireattack.signOut();

                FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                            FirebaseUser logoutuser=firebaseAuth.getCurrentUser();

                            if (logoutuser==null)
                            {
                                Intent startagan=new Intent(user_space.this,login_user.class);

                                startActivity(startagan);

                                finish();

                            }


                    }
                };

            }
        });

    }


}
