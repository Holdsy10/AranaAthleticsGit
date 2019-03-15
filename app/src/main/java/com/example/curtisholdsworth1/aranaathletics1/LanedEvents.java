package com.example.curtisholdsworth1.aranaathletics1;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class LanedEvents extends AppCompatActivity {

    private Button left1;
    private Button left2;
    private Button left3;
    private Button left4;
    private Button left5;
    private Button left6;
    private Button left7;
    private Button left8;
    private Button left9;
    private Button left0;
    private Button leftComma;
    private TextView leftTextEntry;
    private Button leftDelete;
    private TextView leftAthlete;
    private Button leftTrialist;
    private Button leftNoRunner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laned_events);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setupUIViews();



        left0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left0));
            }
        });

        left1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left1));
            }
        });
        left2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left2));
            }
        });
        left3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left3));
            }
        });
        left4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left4));
            }
        });
        left5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left5));
            }
        });
        left6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left6));
            }
        });
        left7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left7));
            }
        });
        left8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left8));
            }
        });
        left9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.left9));
            }
        });
        leftTrialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + "T");
            }
        });
        leftComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + getString(R.string.leftComma));
            }
        });
        leftNoRunner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftTextEntry.setText(leftTextEntry.getText().toString() + "-");
            }
        });
        leftDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry = leftTextEntry.getText().toString();
                int input = leftTextEntry.length();
                if (input > 0) {
                    leftTextEntry.setText(entry.substring(0, input-1));
                }
            }
        });



    leftTextEntry.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String strleftTextEntry = leftTextEntry.getText().toString();
            leftAthlete.setText(strleftTextEntry);

            if (strleftTextEntry == "0"){
                leftTextEntry.setText(leftTextEntry.getText().toString() + "Zero");

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    }




    private void setupUIViews() {
        left0 = (Button)findViewById(R.id.left0);
        left1 = (Button)findViewById(R.id.left1);
        left2 = (Button)findViewById(R.id.left2);
        left3 = (Button)findViewById(R.id.left3);
        left4 = (Button)findViewById(R.id.left4);
        left5 = (Button)findViewById(R.id.left5);
        left6 = (Button)findViewById(R.id.left6);
        left7 = (Button)findViewById(R.id.left7);
        left8 = (Button)findViewById(R.id.left8);
        left9 = (Button)findViewById(R.id.left9);
        leftComma = (Button)findViewById(R.id.leftComma);
        leftDelete = (Button)findViewById(R.id.leftDelete);
        leftTextEntry = (TextView)findViewById(R.id.leftTextEntry);
        leftAthlete = (TextView)findViewById(R.id.leftAthlete);
        leftTrialist = (Button)findViewById(R.id.leftTrialist);
        leftNoRunner = (Button)findViewById(R.id.leftNoRunner);

    }

}
