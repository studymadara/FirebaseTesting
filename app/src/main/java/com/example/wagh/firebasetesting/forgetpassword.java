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

/**
 * Created by wagh on 22/7/16.
 */
public class forgetpassword extends AppCompatActivity {

    Button goback;
    LinearLayout linearLayout,linearLayout2;
    CardView cardView2,cardView1;
    FirebaseAuth auth;

    EditText getEmailId;
    String getEmail;
    Button confirm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgetpassword);

        goback=(Button)findViewById(R.id.forgetpasswordgoback);
        linearLayout=(LinearLayout)findViewById(R.id.forgetpasswordtext2);
        cardView2=(CardView)findViewById(R.id.forgetpasswordcardview2);

        auth=FirebaseAuth.getInstance();

        linearLayout.setVisibility(cardView2.GONE);

        confirm=(Button)findViewById(R.id.forgetpasswordsend);
        getEmailId=(EditText)findViewById(R.id.forgetpasswordemailid);
        linearLayout2=(LinearLayout)findViewById(R.id.forgetpasswordtext1);
        cardView1=(CardView)findViewById(R.id.forgetpasswordcardview1);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getEmail=getEmailId.getText().toString().trim();

                getEmailId.setText("");

                auth.sendPasswordResetEmail(getEmail).addOnCompleteListener(forgetpassword.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        linearLayout2.setVisibility(cardView1.GONE);

                        linearLayout.setVisibility(cardView2.VISIBLE);

                        Toast.makeText(forgetpassword.this,"Email Sent",Toast.LENGTH_SHORT).show();

                        goback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent ii =new Intent(forgetpassword.this,login_user.class);

                                startActivity(ii);

                                finish();

                            }
                        });



                    }
                });

            }
        });


    }
}
