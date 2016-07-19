package com.example.wagh.firebasetesting;

import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    EditText inputEmail, inputPassword;
    Button btnSignIn;
    String iEmail,iPass;
    //private Firebase mref;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputEmail=(EditText)findViewById(R.id.tv1);
        inputPassword=(EditText)findViewById(R.id.tv2);
        btnSignIn=(Button)findViewById(R.id.signin);

        String Firebase_url="https://fir-test-18c37.firebaseio.com/";

        //this one is the start of Firebase


        auth=FirebaseAuth.getInstance();



      //  mref=new Firebase(Firebase_url);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iEmail=inputEmail.getText().toString().trim();
                iPass=inputPassword.getText().toString().trim();


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


                auth.createUserWithEmailAndPassword(iEmail,iPass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(MainActivity.this,"DONE",Toast.LENGTH_LONG).show();

                        if(!task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"NOT-_-DONE",Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });


    }
}
