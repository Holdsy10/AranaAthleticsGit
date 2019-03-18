package com.example.curtisholdsworth1.aranaathletics1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;


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
    private Button exitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laned_events);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setupUIViews();
        leftKeypad();


        Button goToSignIn = (Button) findViewById(R.id.exitButton);
        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignIn = new Intent (LanedEvents.this, SignIn.class);
                startActivity(SignIn);
            }

        });


    leftTextEntry.addTextChangedListener(inputWatcher);
    final Pattern pLeftInput = Pattern.compile("[0-9]");


    }





    private void leftKeypad() {
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
    }

    private TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                String leftInput = leftTextEntry.getText().toString();
                int input = leftTextEntry.length();

                //Normal Entry
                //123
                if (leftInput.length() > 3 && !leftInput.contains(",") && !leftInput.startsWith("0")) {
                    leftTextEntry.setText(leftInput.substring(0, input - 1));
                }
                //123,123
                if (leftInput.length() > 7 && leftInput.contains(",") && !leftInput.startsWith("0") && !leftInput.contains(",0")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }
                //123,0123
                if (leftInput.length() > 8 && !leftInput.startsWith(("0")) && leftInput.contains(",0")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }


                // Runner is From Another Club
                if (leftInput.length() > 4 && leftInput.substring(0, 1).equals("0") && !leftInput.contains(",")) {
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }
                if (leftInput.length() > 9 && leftInput.startsWith(("0")) && leftInput.contains(",0") ){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }
                if (leftInput.length() > 8 && leftInput.startsWith(("0")) && !leftInput.contains(",0") ){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }


                // If Trialist, Next input has to be comma.
                if (leftInput.equals("T")) {
                    leftAthlete.setText("Trialist");
                }
                if (leftInput.length() > 5 && leftInput.contains("T,") && !leftInput.contains(",0")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }
                if (leftInput.length() > 6 && leftInput.contains("T,") && leftInput.contains(",0")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }
                if (leftInput.startsWith("T") && leftInput.length() > 1 && !leftInput.contains(",")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }

                // Double input on accident
                if (leftInput.contains(",,") || leftInput.contains("TT") || leftInput.contains("--") || leftInput.contains("T-") || leftInput.contains("-T")|| leftInput.contains("T,") || leftInput.contains(",T") || leftInput.contains(",-")|| leftInput.contains("-,")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }


                // If there is no runner
                if (leftInput.startsWith("-") && leftInput.length() > 1) {

                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }
                //if comma is first input
                if (leftInput.startsWith(",")){
                    leftTextEntry.setText(leftInput.substring(0, input-1));
                }






            } catch (NumberFormatException e) {
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String leftInput = leftTextEntry.getText().toString();
            int input = leftTextEntry.length();
            if (leftInput.contains(",")){
                int max = leftInput.length();
                String maxx = String.valueOf(max);
                leftTextEntry.setFilters(new InputFilter[] {new InputFilter.LengthFilter(max+3)});
                //leftAthlete.setText(maxx);
            }
        }
    };


    private void setupUIViews () {
                left0 = (Button) findViewById(R.id.left0);
                left1 = (Button) findViewById(R.id.left1);
                left2 = (Button) findViewById(R.id.left2);
                left3 = (Button) findViewById(R.id.left3);
                left4 = (Button) findViewById(R.id.left4);
                left5 = (Button) findViewById(R.id.left5);
                left6 = (Button) findViewById(R.id.left6);
                left7 = (Button) findViewById(R.id.left7);
                left8 = (Button) findViewById(R.id.left8);
                left9 = (Button) findViewById(R.id.left9);
                leftComma = (Button) findViewById(R.id.leftComma);
                leftDelete = (Button) findViewById(R.id.leftDelete);
                leftTextEntry = (TextView) findViewById(R.id.leftTextEntry);
                leftAthlete = (TextView) findViewById(R.id.leftAthlete);
                leftTrialist = (Button) findViewById(R.id.leftTrialist);
                leftNoRunner = (Button) findViewById(R.id.leftNoRunner);
                Button goToSignIn = (Button) findViewById(R.id.exitButton);

            }

        }

