package com.example.curtisholdsworth1.aranaathletics1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SignIn extends AppCompatActivity {

    Button laned = (Button) findViewById(R.id.btnlaned);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        laned.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SignIn.this, LanedEvents.class));
            }

        });


    }
}
