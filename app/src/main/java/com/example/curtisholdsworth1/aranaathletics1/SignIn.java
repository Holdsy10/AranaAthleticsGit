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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
    private ListView listView;
    private LanedEvents tests;
    static List<AthleteSample> athleteSamples = new ArrayList<>();
    byte[] HTML;
    private static final String FILE_NAME = "fetch2.zip";
    private static int BUFFER_SIZE = 6 * 1024;
    private static final String[] NAMES = new String[]{
            "Curtis", "Nathan"
    };


    public List<AthleteSample> getList() {
        return athleteSamples;
    }

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

                athleteSamples.add(sample);

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

        Button goToLaned = (Button) findViewById(R.id.btnlaned);
        Button gotoUnlaned = (Button) findViewById(R.id.btnUnlaned);
        Button fetchData = (Button) findViewById(R.id.fetch);
        parentsName = findViewById(R.id.autoParentName);
        parentsName.addTextChangedListener(parentsTextWatcher);

        lanedEvents = findViewById(R.id.btnlaned);
        unlanedEvents = findViewById(R.id.btnUnlaned);

        //readAthletesData();


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


        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(getApplicationContext())
                        .load("https://www.aranala.com.au/ipad/fetch2.php")
                        .progress(new ProgressCallback() {@Override
                        public void onProgress(long downloaded, long total) {
                            Log.d("Debug","" + downloaded + " / " + total);
                        }
                        })
                        .write(new File(getFilesDir() + "/fetch2.xlsx"))
                        .setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File file) {
                                try {
                                    unzip(getFilesDir()+"/fetch2.zip",getFilesDir()+"/fetch2");
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
            }
        });
    }

                //Easy method to write a message to device screen.
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



    /**
     * Unzip a zip file.  Will overwrite existing files.
     *
     * @param zipFile  Full path of the zip file you'd like to unzip.
     * @param location Full path of the directory you'd like to unzip to (will be created if it doesn't exist).
     * @throws IOException
     */
    public static void unzip(String zipFile, String location) throws IOException {
        int size;
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            if (!location.endsWith(File.separator)) {
                location += File.separator;
            }
            File f = new File(location);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile), BUFFER_SIZE));
            try {
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    String path = location + ze.getName();
                    File unzipFile = new File(path);

                    if (ze.isDirectory()) {
                        if (!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        // check for and create parent directories if they don't exist
                        File parentDir = unzipFile.getParentFile();
                        if (null != parentDir) {
                            if (!parentDir.isDirectory()) {
                                parentDir.mkdirs();
                            }
                        }

                        // unzip the file
                        FileOutputStream out = new FileOutputStream(unzipFile, false);
                        BufferedOutputStream fout = new BufferedOutputStream(out, BUFFER_SIZE);
                        try {
                            while ((size = zin.read(buffer, 0, BUFFER_SIZE)) != -1) {
                                fout.write(buffer, 0, size);
                            }

                            zin.closeEntry();
                        } finally {
                            fout.flush();
                            fout.close();
                        }
                    }
                }
            } finally {
                zin.close();
            }
        } catch (Exception e) {
            Log.e("Error", "Unzip exception", e);
        }
    }






}