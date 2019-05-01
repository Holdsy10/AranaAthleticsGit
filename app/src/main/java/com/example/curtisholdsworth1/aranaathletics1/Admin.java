package com.example.curtisholdsworth1.aranaathletics1;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Admin extends AppCompatActivity {

    private static int BUFFER_SIZE = 6 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button fetchResults = findViewById(R.id.adminFetch);

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
                        .write(new File(getFilesDir() + File.separator + "fetch2.xlsx"))
                        .setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File file) {
                                try {
                                    unzip(getFilesDir() + File.separator + "fetch2.xlsx", getFilesDir() + File.separator + "fetch2");
                                    toastMessage("Unzipped File Successfully");
                                    //readExcel();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                //} catch (InvalidFormatException e1) {
                                //    e1.printStackTrace();
                                }

                                File myFile = new File(getFilesDir() + File.separator + "fetch2.xlsx");
                                int sheetIdx = 0; // 0 for first sheet

                                try {
                                    convertSelectedSheetInXLXSFileToCSV(myFile, sheetIdx);
                                } catch (Exception e1) {
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

    public void readExcel() throws IOException, InvalidFormatException {
        //https://www.callicoder.com/java-read-excel-file-apache-poi/
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(getFilesDir() + "/fetch2.xlsx"));
        //toastMessage("Created Workbook");
        // Retrieving the number of sheets in the Workbook
        Log.d("Debug", "Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        Log.d("Debug", "Retrieving Sheets using for-each loop");
        for (Sheet sheet : workbook) {
            Log.d("Debug", "=> " + sheet.getSheetName());
        }

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // use a for-each loop to iterate over the rows and columns
        Log.d("Debug", "\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                Log.d("Debug", cellValue + "\t");
            }

        }


    }

    private void convertSelectedSheetInXLXSFileToCSV(File xlsxFile, int sheetIdx) throws Exception {

        FileInputStream fileInStream = new FileInputStream(xlsxFile);
        AthleteSample sample = new AthleteSample();
        // Open the xlsx and get the requested sheet from the workbook
        XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
        XSSFSheet selSheet = workBook.getSheetAt(sheetIdx);

        // Iterate through all the rows in the selected sheet
        Iterator<Row> rowIterator = selSheet.iterator();
        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();

            // Iterate through all the columns in the row and build ","
            // separated string
            Iterator<Cell> cellIterator = row.cellIterator();
            StringBuffer sb = new StringBuffer();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (sb.length() != 0) {
                    sb.append(",");
                }

                // If you are using poi 4.0 or over, change it to
                // cell.getCellType
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
            System.out.println(sb.toString());

        }
        workBook.close();
    }



}
