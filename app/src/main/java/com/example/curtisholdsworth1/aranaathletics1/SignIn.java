package com.example.curtisholdsworth1.aranaathletics1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SignIn extends AppCompatActivity {


    private EditText parentsName;
    private Button unlanedEvents;
    private Button lanedEvents;
    private String parentName;





    private static final String[] NAMES = new String[]{
            "Curtis Holdsworth", "Nathan Haslam", " Santosh Pun", "Benjamin Martin"

    };



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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button goToLaned = (Button) findViewById(R.id.btnlaned);
        Button gotoUnlaned = (Button) findViewById(R.id.btnUnlaned);
        parentsName = findViewById(R.id.autoParentName);
        parentsName.addTextChangedListener(parentsTextWatcher);

        lanedEvents = findViewById(R.id.btnlaned);
        unlanedEvents = findViewById(R.id.btnUnlaned);



        readCsvData();



        goToLaned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lanedEvents = new Intent(SignIn.this, LanedEvents.class);
                startActivity(lanedEvents);
            }
        });


        gotoUnlaned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UnlanedEvents = new Intent (SignIn.this, UnlanedEvents.class);
                startActivity(UnlanedEvents);
            }
        });

        AutoCompleteTextView editText = findViewById(R.id.autoParentName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, NAMES);
        editText.setAdapter(adapter);
    }


    public List<ReadCsv> athleteSamples= new ArrayList<>();
    private void readCsvData() {

        InputStream is = getResources().openRawResource(R.raw.parentsnames);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                //split by ","
                String[] tokens = line.split(",");

                //Read the Data
                ReadCsv sample = new ReadCsv();
                sample.setParentName(tokens[0]);
                //samples.set(kidsname)(tokens[1]);
                //samples.setAge(Integer.parseInt(tokens[2]));
                athleteSamples.add(sample);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error Reading CSV on line " + line, e);
            e.printStackTrace();
        }
    }
}
