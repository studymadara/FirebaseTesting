package com.example.wagh.firebasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by wagh on 20/7/16.
 */
public class login_user extends AppCompatActivity {

    EditText iLoginid,iPassword;
    Button Login;
    String loginuser,loginpass;
    FirebaseAuth fbloginuser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_user);

        iLoginid=(EditText)findViewById(R.id.loginuserid);
        iPassword=(EditText)findViewById(R.id.loginuserpassword);
        Login=(Button)findViewById(R.id.signin);

        fbloginuser=FirebaseAuth.getInstance();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //data collection

                loginuser=iLoginid.getText().toString().trim();
                loginpass=iPassword.getText().toString().trim();


                //data checking here


                fbloginuser.signInWithEmailAndPassword(loginuser,loginpass).addOnCompleteListener(login_user.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        //bye bye this activity going to another one.

                        Intent gouserspace= new Intent(login_user.this,user_space.class);

                        gouserspace.putExtra("Username",loginuser);

                        startActivity(gouserspace);



                    }
                });




            }
        });


    }
}
