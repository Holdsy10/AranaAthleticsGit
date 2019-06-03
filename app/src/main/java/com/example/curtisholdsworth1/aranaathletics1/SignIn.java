package com.example.curtisholdsworth1.aranaathletics1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SignIn Class for the SignIn Activity
 */
public class SignIn extends AppCompatActivity {


    //Declarations
    private EditText parentsName;
    private Button unlanedEvents;
    private Button lanedEvents;
    private Button admin;
    private Admin adminData;

    //Creates athleteSamples arraylist
    List<AthleteSample> athleteSamples = new ArrayList<>();


    /**
     *Creates a textwatcher for the Parents name EditText to update
     * with parents names from database.
     */
    private TextWatcher parentsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String parentInput = parentsName.getText().toString().trim();

            unlanedEvents.setEnabled(!parentInput.isEmpty());
            lanedEvents.setEnabled(!parentInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Log.d("Navigate Activity","Sign In Activity Started");

        //Modifies System Properties so Apache POI can work with Android Studio
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        //Button declarations
        Button goToLaned = (Button) findViewById(R.id.btnlaned);
        Button gotoUnlaned = (Button) findViewById(R.id.btnUnlaned);
        final Button goToAdmin = (Button) findViewById(R.id.btnAdmin);


        parentsName = findViewById(R.id.autoParentName);
        parentsName.addTextChangedListener(parentsTextWatcher);
        lanedEvents = findViewById(R.id.btnlaned);
        unlanedEvents = findViewById(R.id.btnUnlaned);
        admin = findViewById(R.id.btnAdmin);

        //Creates new instance of Admin
        adminData = new Admin();

        //Gets the athletes information list and sends to athleteSamples
        athleteSamples = adminData.getList();


        //Read old Athlete Data if exists on device.
        try {
            getOldAthlete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Goes to Admin Activity when button is pressed
        goToAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent admin = new Intent(SignIn.this, Admin.class);
                startActivity(admin);
            }
        });

        //Goes to the laned activity when button is pressed
        goToLaned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanedEvents = new Intent(SignIn.this, LanedEvents.class);
                startActivity(lanedEvents);
            }
        });

        //Goes to unlaned when button is pressed
        gotoUnlaned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UnlanedEvents = new Intent(SignIn.this, UnlanedEvents.class);
                startActivity(UnlanedEvents);
            }
        });

        //Reads parents names for entry.
        autoComplete();
    }


    /**
     * Writes a message to the application screen.
     * @param message Message to write to screen.
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets old athlete information still saved on device if
     * there is no network connection to retrieve new Athlete data.
     * @throws IOException
     */
    public void getOldAthlete() throws IOException {
        adminData.readAthletesData(SignIn.this);
        Log.d("SignIn", "Old Athlete Data Imported");

    }

    /**
     * Reads the athleteSamples list for athletes parents names
     * and adds them to the parentsNames arraylist. Removes any duplicate
     * adult names for parents with multiple children registers in the database
     */
    private void autoComplete() {
        //Arraylist to store parents names
        List<String> parentNames = new ArrayList<>();

        //Clears any existing information in arraylist
        parentNames.clear();

        //Checks for same parent with multiple entries in database to prevent duplicates when singing in.
        for (int i = 0; i < athleteSamples.size();i++) {
            if (!parentNames.contains(athleteSamples.get(i).getParent1Name())) {
                parentNames.add(athleteSamples.get(i).getParent1Name());
            }

            //If no second parents in database, will not add to arraylist
            //as it can't have any null entries.
            if (!parentNames.contains(athleteSamples.get(i).getParent2Name())
                   && athleteSamples.get(i).getParent2Name() != null) {
                parentNames.add(athleteSamples.get(i).getParent2Name());
            }
        }

            //Creates adapter for parentNames
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, parentNames);

            //Sends adapter to autocomplete text view
            AutoCompleteTextView acTextView = findViewById(R.id.autoParentName);
            acTextView.setThreshold(1);
            acTextView.setAdapter(adapter);
    }
}
