package com.example.curtisholdsworth1.aranaathletics1;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Admin Class for the Admin Activity
 */
public class Admin extends AppCompatActivity {

    static List<AthleteSample> athleteSamples = new ArrayList<>();
    private String CSVFile = "fetch2.csv";
    private String ExcelFile = "fetch2.xlsx";


    /**
     * On create method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button fetchResults = findViewById(R.id.adminFetch);
        Button home = findViewById(R.id.adminHome);

        home.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(Admin.this, SignIn.class);
                startActivity(signIn);
            }
        });
        Log.d("Navigate Activity","Admin Activity Started");


        fetchResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(getApplicationContext())
                        .load("https://www.aranala.com.au/ipad/fetch2.php")
                        .progress(new ProgressCallback() {
                            @Override
                            public void onProgress(long downloaded, long total) {
                                Log.d("Debug", "" + downloaded + " / " + total);
                            }
                        })
                        .write(new File(getFilesDir() + File.separator + ExcelFile))
                        .setCallback(new FutureCallback<File>() {
                           @Override
                            public void onCompleted(Exception e, File file) {
                               File myFile = new File(getFilesDir() + File.separator + ExcelFile);
                               int sheetIdx = 0; // 0 for first sheet
                               try {
                                   convertSelectedSheetInXLXSFileToCSV(myFile, sheetIdx);
                                   toastMessage("Athletes Downloaded Successfully");
                               } catch (Exception e1) {
                                   e1.printStackTrace();
                               }

                               try {
                                   readAthletesData(Admin.this);

                               } catch (Exception e1) {
                                   e1.printStackTrace();
                               }
                           }
                        });
            }
        });
    }

    /**
     * Grabs a message and toasts to application
     * @param message
     */
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    /**
     * This function grabs a sheet in a excel file and converts
     * it to CSV format
     * @param xlsxFile
     * @param sheetIdx
     * @throws Exception
     */
    private void convertSelectedSheetInXLXSFileToCSV(File xlsxFile, int sheetIdx) throws Exception {

        //List<AthleteSample> athleteSamples = new ArrayList<>();
        File myFile = new File(getFilesDir() + File.separator + ExcelFile);

        //int sheetIdx = 0; // 0 for first sheet
        FileInputStream fileInStream = new FileInputStream(xlsxFile);
        AthleteSample sample = new AthleteSample();

        // Open the xlsx and get the requested sheet from the workbook
        XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
        XSSFSheet selSheet = workBook.getSheetAt(sheetIdx);
        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(getFilesDir()+File.separator+CSVFile)));
        StringBuilder sb = new StringBuilder();

        // Iterate through all the rows in the selected sheet
        Iterator<Row> rowIterator = selSheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Iterate through all the columns in the row and build ","
            // separated string
            Iterator<Cell> cellIterator = row.cellIterator();
            sb.setLength(0); //THIS LINE IS LEAK

            //StringBuilder sb = new StringBuilder();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (sb.length() != 0) {
                    sb.append(",");
                }
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        sb.append(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        sb.append(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        sb.append(cell.getBooleanCellValue());
                        break;
                    default:
                }
            }

            bwr.write(sb.toString());
            bwr.newLine();
        }
        //flush the stream
        bwr.flush();
        //close the stream
        bwr.close();

        workBook.close();
        Log.d("Debug","Database Downloaded Successfully");
        toastMessage("Athletes Downloaded Successfully");
    }

    /**
     * This application reads the CSV file on the device
     * @param context Application Context
     * @return athlete samples as arraylist
     * @throws IOException
     */
    public List readAthletesData(Context context) throws IOException{

        //Buffered Reader to Read Athlete Information CSV
        BufferedReader reader = new BufferedReader(new FileReader( context.getFilesDir()+File.separator+CSVFile));
        String line = "";

        /* Goes through CSV line by line and extracts relevant athlete information
        and adds them to the athleteSamples list so they can be accessed later
         */
        try {
            while ((line = reader.readLine()) != null) {
                AthleteSample sample = new AthleteSample();
                String[] tokens = line.split(",");
                sample.setAthleteName(tokens[1]+" "+tokens[2]);
                sample.setAthleteNumber(tokens[0]);
                sample.setAthleteAge(tokens[4]);
                sample.setAthleteGender(tokens[6]);

                sample.setParent1Name(tokens[13]+" "+tokens[14]);
                sample.setParent2Name(tokens[17]+" "+tokens[18]);


                athleteSamples.add(sample);


            }
            //Exception to Catch if file is not present
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error Reading Data File on Line " + line, e);
            e.printStackTrace();
        }
        //Once successfully imported a message is printed to the user
        //toastMessage("Athletes Imported!");
        //Toast.makeText(this, "Athletes Imported!", Toast.LENGTH_SHORT).show();
        Log.d("Debug","Athletes Imported");

        //return athleteSamples;
        return athleteSamples;
    }


    /**
     * Gets the athletesample list
     * @return all athlete samples as arraylist
     */
    public List<AthleteSample> getList() {
        return athleteSamples;
    }



}
