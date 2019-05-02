package com.example.curtisholdsworth1.aranaathletics1;

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
import android.widget.ListView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SignIn extends AppCompatActivity {


    private EditText parentsName;
    private Button unlanedEvents;
    private Button lanedEvents;
    private Button admin;
    //static List<AthleteSample> athleteSamples = new ArrayList<>();
    private static final String FILE_NAME = "fetch2.zip";
    private static int BUFFER_SIZE = 6 * 1024;
    private static final String[] NAMES = new String[]{
            "Curtis", "Nathan"
    };



    //public List<AthleteSample> getList() {
     //   return athleteSamples;
    //}

    public void readAthletesData() {
        InputStream is = getResources().openRawResource(R.raw.athletes);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                AthleteSample sample = new AthleteSample();
                sample.setAthleteName(tokens[0]);
                //sample.setAthleteNumber(Integer.parseInt(tokens[1]));
                //sample.setAthleteAge(Integer.parseInt(tokens[2]));
                sample.setAthleteNumber(tokens[1]);
                sample.setAthleteAge(tokens[2]);
                sample.setAthleteGender(tokens[3]);
                sample.setAthleteParent(tokens[4]);

                //athleteSamples.add(sample);

                Log.d("MyActivity", "Just created: " + sample);


            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error Reading Data File on Line " + line, e);
            e.printStackTrace();
        }


    }


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
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        Button goToLaned = (Button) findViewById(R.id.btnlaned);
        Button gotoUnlaned = (Button) findViewById(R.id.btnUnlaned);
        final Button goToAdmin = (Button) findViewById(R.id.btnAdmin);

        parentsName = findViewById(R.id.autoParentName);
        parentsName.addTextChangedListener(parentsTextWatcher);

        lanedEvents = findViewById(R.id.btnlaned);
        unlanedEvents = findViewById(R.id.btnUnlaned);
        admin = findViewById(R.id.btnAdmin);

        //readAthletesData();

        goToAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent admin = new Intent(SignIn.this, Admin.class);
                startActivity(admin);
            }
        });

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
                Intent UnlanedEvents = new Intent(SignIn.this, UnlanedEvents.class);
                startActivity(UnlanedEvents);
            }
        });

        AutoCompleteTextView editText = findViewById(R.id.autoParentName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, NAMES);
        editText.setAdapter(adapter);

    }
                //Easy method to write a message to device screen.
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}






