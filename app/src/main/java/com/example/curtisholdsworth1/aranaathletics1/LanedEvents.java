package com.example.curtisholdsworth1.aranaathletics1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * LanedEvents Class for the LanedEvents Activity
 */
public class LanedEvents extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Left Side Buttons
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

    //Right Side Buttons
    private Button right1;
    private Button right2;
    private Button right3;
    private Button right4;
    private Button right5;
    private Button right6;
    private Button right7;
    private Button right8;
    private Button right9;
    private Button right0;
    private Button rightComma;
    private TextView rightTextEntry;
    private Button rightDelete;
    private TextView rightAthlete;
    private Button rightTrialist;
    private Button rightNoRunner;

    //Other Buttons
    private Button exitButton;
    private LanedEvents SignIn;
    private Button nextButton;
    private TextView raceNumber;

    private SignIn signin;
    private Button prevButton;
    private Admin admin;

    //Race Variable
    int race = 1;
    List<AthleteSample> athleteSamples = new ArrayList<>();
    List<resultList> resultLists = new ArrayList<>();
    String raceDistance;
    String raceType = "Laned";
    int raceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laned_events);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Log.d("Navigate Activity","Laned Events Activity Started");
        setupUIViews();
        leftKeypad();
        rightKeypad();
        Button goToSignIn = findViewById(R.id.exitButton);

        admin = new Admin();
        athleteSamples = admin.getList();

        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignIn = new Intent(LanedEvents.this, SignIn.class);
                startActivity(SignIn);
            }

        });

        leftTextEntry.addTextChangedListener(inputWatcher);
        rightTextEntry.addTextChangedListener(inputWatcher);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResults();
                race++;
                raceNumber.setText("Race " + race);

            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (race >= 2) {
                    race--;
                    raceNumber.setText("Race " + race);
                    getResults(race);

                }
            }
        });


        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyDir");
        if (!fileDir.exists()) {
            try {
                fileDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "BlogData" + File.separator + "MyText.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Set's the Results of the specific race and saves it
     */
        private void setResults() {
            leftTextEntry.setText("");
            rightTextEntry.setText("");


        }

/**
 * Creates a listener for each button on the right side keypad of the application
 */
    public void rightKeypad() {
        right0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right0));
            }
        });

        right1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right1));
            }
        });
        right2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right2));
            }
        });
        right3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right3));
            }
        });
        right4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right4));
            }
        });
        right5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right5));
            }
        });
        right6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right6));
            }
        });
        right7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right7));
            }
        });
        right8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right8));
            }
        });
        right9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.right9));
            }
        });
        rightTrialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + "T");
            }
        });
        rightComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + getString(R.string.rightComma));
            }
        });
        rightNoRunner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightTextEntry.setText(rightTextEntry.getText().toString() + "-");
            }
        });
        rightDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry = rightTextEntry.getText().toString();
                int input = rightTextEntry.length();
                if (input > 0) {
                    rightTextEntry.setText(entry.substring(0, input-1));
                }
            }
        });
    }


    /**
     * Creates a listener for each button on the left side keypad of the application
     */
    public void leftKeypad() {
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

    /**
     * Makes sure that all athlete information entered is correct.
     * If incorrect data is entered, this function deletes it from
     * the right side athlete bib number input
     */
    public void updateRightKeypad() {
        try {


            String rightInput = rightTextEntry.getText().toString();
            int input = rightTextEntry.length();

            //Normal Entry
            //123
            if (rightInput.length() > 3 && !rightInput.contains(",") && !rightInput.startsWith("0")) {
                rightTextEntry.setText(rightInput.substring(0, input - 1));
            }
            //123,123
            if (rightInput.length() > 7 && rightInput.contains(",") && !rightInput.startsWith("0") && !rightInput.contains(",0")){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }
            //123,0123
            if (rightInput.length() > 8 && !rightInput.startsWith(("0")) && rightInput.contains(",0")){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }


            // Runner is From Another Club
            if (rightInput.length() > 4 && rightInput.substring(0, 1).equals("0") && !rightInput.contains(",")) {
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }
            if (rightInput.length() > 9 && rightInput.startsWith(("0")) && rightInput.contains(",0") ){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }
            if (rightInput.length() > 8 && rightInput.startsWith(("0")) && !rightInput.contains(",0") ){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }


            // If Trialist, Next input has to be comma.
            if (rightInput.equals("T")) {
                rightAthlete.setText("Trialist");
            }
            if (rightInput.length() > 5 && rightInput.contains("T,") && !rightInput.contains(",0")){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }
            if (rightInput.length() > 6 && rightInput.contains("T,") && rightInput.contains(",0")){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }
            if (rightInput.startsWith("T") && rightInput.length() > 1 && !rightInput.contains(",")){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }

            // Double input on accident
            if (rightInput.contains(",,") || rightInput.contains("TT") || rightInput.contains("--") || rightInput.contains("T-") ||
                    rightInput.contains("-T") || rightInput.contains(",-")|| rightInput.contains("-,") || rightInput.contains(",T,")) {
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }

            // If there is no runner
            if (rightInput.startsWith("-") && rightInput.length() > 1) {

                rightTextEntry.setText(rightInput.substring(0, input-1));
            }
            //if comma is first input
            if (rightInput.startsWith(",")){
                rightTextEntry.setText(rightInput.substring(0, input-1));
            }

            if (rightInput.length() == 0){
                rightAthlete.setText("");
            }

            if (rightInput.contains(",")) {
                int comma = 0;
                comma++;
                if (comma == 2) {
                    rightTextEntry.setText(rightInput.substring(0, input - 1));
                }
            }
        } catch (NumberFormatException e) {
        }
    }



    /**
     * Makes sure that all athlete information entered is correct.
     * If incorrect data is entered, this function deletes it from
     * the left side athlete bib number input
     */
    public void updateLeftKeypad() {
        try {
            String leftInput = leftTextEntry.getText().toString();
            int input = leftTextEntry.length();

            //Normal Entry
            //123
            if (leftInput.length() > 3 && !leftInput.contains(",") && !leftInput.startsWith("0")) {
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input - 1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            //123,123
            if (leftInput.length() > 7 && leftInput.contains(",") && !leftInput.startsWith("0") && !leftInput.contains(",0")){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            //123,0123
            if (leftInput.length() > 8 && !leftInput.startsWith(("0")) && leftInput.contains(",0")){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }


            // Runner is From Another Club
            if (leftInput.length() > 4 && leftInput.substring(0, 1).equals("0") && !leftInput.contains(",")) {
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            if (leftInput.length() > 9 && leftInput.startsWith(("0")) && leftInput.contains(",0") ){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            if (leftInput.length() > 8 && leftInput.startsWith(("0")) && !leftInput.contains(",0") ){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }


            // If Trialist, Next input has to be comma.
            if (leftInput.equals("T")) {
                leftAthlete.setText("Trialist");
            }
            if (leftInput.length() > 5 && leftInput.contains("T,") && !leftInput.contains(",0")){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            if (leftInput.length() > 6 && leftInput.contains("T,") && leftInput.contains(",0")){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            if (leftInput.startsWith("T") && leftInput.length() > 1 && !leftInput.contains(",")){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }

            // Double input on accident
            if (leftInput.contains(",,") || leftInput.contains("TT") || leftInput.contains("--") || leftInput.contains("T-") ||
                    leftInput.contains("-T") || leftInput.contains(",-")|| leftInput.contains("-,") || leftInput.contains(",T,")) {
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }


            // If there is no runner
            if (leftInput.startsWith("-") && leftInput.length() > 1) {
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }
            //if comma is first input
            if (leftInput.startsWith(",")){
                Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                leftTextEntry.setText(leftInput.substring(0, input-1));
                Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
            }

            if (leftInput.length() == 0){
                leftAthlete.setText("");
            }

            if (leftInput.contains(",")) {
                int comma = 0;
                comma++;
                if (comma == 2) {
                    Log.d("LanedEvents", "Incorrect Entry: " + leftTextEntry.getText().toString());
                    leftTextEntry.setText(leftInput.substring(0, input - 1));
                    Log.d("LanedEvents", "New Entry: " + leftTextEntry.getText().toString());
                }
            }
        } catch (NumberFormatException e) {
        }
    }


    /**
     * This gets the leftTextEntry field for athlete bib numbers, and
     * then displays the athlete information in the textfield above.
     * This function gets called every time the text changes in either
     * the left or right athlete text entry.
     */
    public void showLeftAthleteData() {
        String leftInput = leftTextEntry.getText().toString();
        for (int i=0; i <athleteSamples.size(); i++) {
            for (int j=0; j <athleteSamples.size(); j++) {

                // For Single Athlete Crossing Finish Line
                if (leftInput.equals(athleteSamples.get(i).getAthleteNumber())) {
                    leftAthlete.setText(athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender());
                }

                //For 2 athletes crossing in the same lane
                else if (leftInput.equals(athleteSamples.get(i).getAthleteNumber()+ "," + athleteSamples.get(j).getAthleteNumber())) {
                    leftAthlete.setText(athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender()
                            +"\n"+ athleteSamples.get(j).getAthleteName() + " " + athleteSamples.get(j).getAthleteAge() + " " + athleteSamples.get(j).getAthleteGender());
                }

                //For 2 Athletes crossing in the same lane but first athlete is a Trialist
                else if (leftInput.equals("T,"+athleteSamples.get(i).getAthleteNumber())) {
                    leftAthlete.setText("Trialist\n"
                            +athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender());
                }

                //For 2 Athletes crossing in the same lane but second athlete is a Trialist
                else if (leftInput.equals(athleteSamples.get(i).getAthleteNumber()+ ",T")) {
                    leftAthlete.setText(athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender()
                            +"\nTrialist");
                }
            }
        }
    }

    /**
     * This gets the rightTextEntry field for athlete bib numbers, and
     * then displays the athlete information in the textfield above.
     * This function gets called every time the text changes in either
     * the left or right athlete text entry.
     */
    public void showRightAthleteData() {
        String rightInput = rightTextEntry.getText().toString();
        for (int i=0; i <athleteSamples.size(); i++) {
            for (int j=0; j <athleteSamples.size(); j++) {

                // For Single Athlete Crossing Finish Line
                if (rightInput.equals(athleteSamples.get(i).getAthleteNumber())) {
                    rightAthlete.setText(athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender());
                }

                //For 2 athletes crossing in the same lane
                else if (rightInput.equals(athleteSamples.get(i).getAthleteNumber()+ "," + athleteSamples.get(j).getAthleteNumber())) {
                    rightAthlete.setText(athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender()
                            +"\n"+ athleteSamples.get(j).getAthleteName() + " " + athleteSamples.get(j).getAthleteAge() + " " + athleteSamples.get(j).getAthleteGender());
                }

                //For 2 Athletes crossing in the same lane but first athlete is a Trialist
                else if (rightInput.equals("T,"+athleteSamples.get(i).getAthleteNumber())) {
                    rightAthlete.setText("Trialist\n"
                            +athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender());
                }

                //For 2 Athletes crossing in the same lane but second athlete is a Trialist
                else if (rightInput.equals(athleteSamples.get(i).getAthleteNumber()+ ",T")) {
                    rightAthlete.setText(athleteSamples.get(i).getAthleteName() + " " + athleteSamples.get(i).getAthleteAge() + " " + athleteSamples.get(i).getAthleteGender()
                            +"\nTrialist");
                }
            }
        }
    }

    /**
     * Gets the results for a specific race
     * @param raceNumber The race number for which the results you want returned
     * @return The results as a list
     */
    public List getResults(Integer raceNumber) {
        resultList results = new resultList();

        Log.d("Results",Integer.toString(raceNumber));

        leftTextEntry.setText(resultLists.get(0).getLane1AthleteNumber());
        rightTextEntry.setText(resultLists.get(0).getLane2AthleteNumber());

        return resultLists;
    }

    private TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateLeftKeypad();
            updateRightKeypad();
        }

        @Override
        public void afterTextChanged(Editable s) {
            showLeftAthleteData();
            showRightAthleteData();
        }
    };

    /**
     * Sets up the user interface for everything on the Laned Events screen
     */
    private void setupUIViews () {
        //Left Side Entry
        left0 = findViewById(R.id.left0);
        left1 = findViewById(R.id.left1);
        left2 = findViewById(R.id.left2);
        left3 = findViewById(R.id.left3);
        left4 = findViewById(R.id.left4);
        left5 = findViewById(R.id.left5);
        left6 = findViewById(R.id.left6);
        left7 = findViewById(R.id.left7);
        left8 = findViewById(R.id.left8);
        left9 = findViewById(R.id.left9);
        leftComma = findViewById(R.id.leftComma);
        leftDelete = findViewById(R.id.leftDelete);
        leftTextEntry = findViewById(R.id.leftTextEntry);
        leftAthlete = findViewById(R.id.leftAthlete);
        leftTrialist = findViewById(R.id.leftTrialist);
        leftNoRunner = findViewById(R.id.leftNoRunner);

        //Extra Buttons
        Button goToSignIn = findViewById(R.id.exitButton);
        nextButton = findViewById(R.id.nextButton);
        raceNumber = findViewById(R.id.raceNumber);
        prevButton = findViewById(R.id.prevButton);

        //Race Distance
        Spinner spinner = findViewById(R.id.raceDistance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Right Side Entry
        right0 = findViewById(R.id.right0);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        right4 = findViewById(R.id.right4);
        right5 = findViewById(R.id.right5);
        right6 = findViewById(R.id.right6);
        right7 = findViewById(R.id.right7);
        right8 = findViewById(R.id.right8);
        right9 = findViewById(R.id.right9);
        rightComma = findViewById(R.id.rightComma);
        rightDelete = findViewById(R.id.rightDelete);
        rightTextEntry = findViewById(R.id.rightTextEntry);
        rightAthlete = findViewById(R.id.rightAthlete);
        rightTrialist = findViewById(R.id.rightTrialist);
        rightNoRunner = findViewById(R.id.rightNoRunner);


    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        raceDistance = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

