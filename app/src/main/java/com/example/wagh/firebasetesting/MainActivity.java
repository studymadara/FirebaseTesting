package com.example.wagh.firebasetesting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText inputEmail, inputPassword,inputPasswordAgain;
    Button btnRegister;
    String iEmail,iPass,iPassAgain;

    //Button testdata;



    ProgressDialog progressDialog;

    DatabaseReference mref;


    int i;


    FirebaseAuth auth;
    TextView login;


    ///Ad view test

    AdView adView;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputEmail = (EditText) findViewById(R.id.tv1);
        inputPassword = (EditText) findViewById(R.id.tv2);
        inputPasswordAgain = (EditText) findViewById(R.id.tv3);
        btnRegister = (Button) findViewById(R.id.Register);
        login = (TextView) findViewById(R.id.nextlogin);




        //testdata=(Button)findViewById(R.id.test);


        ///ad view testing

        adView=(AdView)findViewById(R.id.ad);
        adRequest=new AdRequest.Builder().addTestDevice("B85C9E3C34F5DDD28AFBBF2D31766D77").build();
        adView.loadAd(adRequest);





        //this one is the start of Firebase


        auth=FirebaseAuth.getInstance();

/** **********************************************************************
        //Progress bar But dosen't work for some reasons that ui thread and all need to implement it on the background

        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Finishing some work");
        progressDialog.setProgress(0);
        progressDialog.setMax(10000);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.show();
**/

        mref= FirebaseDatabase.getInstance().getReference();





  /**      testdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                for (i=1;i<100;i++)
                {
                    Thread t1=new Thread()
                    {
                        public void run()
                        {
                            try
                            {
                                progressDialog.setProgress(i);
//*********************************************************** Only part which works..:(

                                mref.child("Name"+i).setValue("viraj"+i);

//***********************************************************
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }

                    };
                    t1.start();


                    progressDialog.dismiss();
                }





            }
        });

        //********************************************** all part waste until u put it in the background thread   **/


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iEmail=inputEmail.getText().toString().trim();
                iPass=inputPassword.getText().toString().trim();
                iPassAgain=inputPasswordAgain.getText().toString().trim();


                //this one is actually for the database part but ryt now i m happy with login HAHA :P

                /**mref.authWithPassword(iEmail, iPass, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        mref.child("Email").setValue(iEmail);
                        mref.child("Pass").setValue(iPass);

                        Toast.makeText(MainActivity.this,"DONE",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                    }
                });**/

            //the actual function which makes all the work...:)

                if(!iEmail.equals("")) {

                    if (!iPass.equals("") && !iPassAgain.equals("")) {
                        if (iPass.equals(iPassAgain) && iPass.length() >= 8) {

                            auth.createUserWithEmailAndPassword(iEmail, iPass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                   // Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_LONG).show();

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "NOT-_-DONE", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                        }

                    }

                }
                else
                {
                    YoYo.with(Techniques.Shake).duration(2000).playOn(inputPassword);
                    //YoYo.with(Techniques.Shake).duration(2000).playOn(inputPasswordAgain);
                    Toast.makeText(MainActivity.this,"Check Password Again && Make sure password greater than 8",Toast.LENGTH_SHORT).show();
                }


                Intent ii=new Intent(MainActivity.this,login_user.class);
                startActivity(ii);
                Toast.makeText(MainActivity.this,"Registration Done!!",Toast.LENGTH_SHORT).show();


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii=new Intent(MainActivity.this,login_user.class);
                startActivity(ii);

            }
        });

    }
}
