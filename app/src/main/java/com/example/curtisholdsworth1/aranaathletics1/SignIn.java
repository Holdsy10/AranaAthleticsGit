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



    private Admin adminData;

    List<AthleteSample> athleteSamples = new ArrayList<>();



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

        adminData = new Admin();
        athleteSamples = adminData.getList();


        //Read old Athlete Data if exists on device.
        try {
            getOldAthlete();
        } catch (IOException e) {
            e.printStackTrace();
        }



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

        autoComplete();



    }


                //Easy method to write a message to device screen.
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Get Old Athlete Information if still on device
    public void getOldAthlete() throws IOException {
        adminData.readAthletesData(SignIn.this);

    }

    private static final String[] NAMES = new String[]{
            "Curtis", "Nathan"
    };

    //Method to grab parents names so they can appear when singing in.
    private void autoComplete() {

        List<String> parentNames = new ArrayList<>();
        parentNames.clear();

        for (int i = 0; i < athleteSamples.size();i++) {
            //Checks for same parent with multiple entries in database to prevent duplicates when singing in.
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

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, parentNames);

            AutoCompleteTextView acTextView = findViewById(R.id.autoParentName);
            acTextView.setThreshold(1);
            acTextView.setAdapter(adapter);


    }
}






