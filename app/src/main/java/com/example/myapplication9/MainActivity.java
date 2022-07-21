package com.example.myapplication9;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.memorynotfound.pdf.itext.WatermarkPageEvent;

//import org.jfre


import java.util.List;
import java.util.Objects;
import java.util.Set;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    double z_val, soil_val, spectral_val, fsi_val, imp_val;
    Double stories_val;
    Double hazard_val;
    Double exposure_val;
    Double complete_fsi_val;
    Double allowable_fsi_value;

    ArrayList<String> checkbox_data = new ArrayList<>();
    ArrayList<String> life_checkbox_data = new ArrayList<>();
    double risk_val;
    String risk_percentage;

    File myFile;
    File pdfFolder;
    String mFilename;

    String leftview_photopath;
    String rightview_photopath;
    String rearview_photopath;
    String frontview_photopath;

    Button view_file;

    Button leftview_capture, rightview_capture, frontview_capture, rearview_capture;


    boolean isAllFieldsChecked = false;


    EditText allowable_fsi_text, fsi_text, stories_text;
    CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13, ch14, ch15, ch16, ch17, ch18, ch19, ch20, ch21, ch22, ch23, ch24, ch25, ch26, ch27, ch28, ch29, ch30, ch31, ch32, ch33, ch34, ch35, ch36, ch37, ch38, ch39, ch40, ch41, ch42, ch43, ch44, ch45, ch46, ch47, ch48, ch49, ch50, ch51, ch52, ch53, ch54, ch55, ch56, ch57, ch58, ch59, ch60, ch61, ch62, ch63, ch64, ch65, ch66, ch67, ch68, ch69, ch70, ch71, ch72;
    Integer lossSite1 = 0, lossSoil1 = 0, lossSoil2 = 0, suit_soil1 = 0, suit_soil2 = 0, found1 = 0, found2 = 0, found3 = 0, found4 = 0, found5 = 0, plan1 = 0, plan2 = 0, plan3 = 0, elev1 = 0, elev2 = 0, elev3 = 0, elev4 = 0, elev5 = 0, elev6 = 0, elev7 = 0, elev8 = 0, door1 = 0, door2 = 0, door3 = 0, door4 = 0, door5 = 0, distance1 = 0, distance2 = 0, parapets1 = 0, parapets2 = 0, staircases1 = 0, staircases2 = 0, staircases3 = 0, frame1 = 0, frame2 = 0, frame3 = 0, frame4 = 0, frame5 = 0, frame6 = 0, roof1 = 0, roof2 = 0, roof3 = 0, roof4 = 0, roof_column1 = 0, member1 = 0, column1 = 0, column2 = 0, struc_staircase1 = 0, struc_staircase2 = 0, struc_staircase3 = 0, tank1 = 0, materials1 = 0, materials2 = 0, materials3 = 0, materials4 = 0, workmanship1 = 0, workmanship2 = 0, concrete1 = 0, concrete2 = 0;
    Double economic_loss;

    TextView coltextView;
    boolean[] selectedCol;
    ArrayList<Integer> col_list = new ArrayList<>();
    ArrayList<String> col_data = new ArrayList<>();
    //    List col_pdf = new List();
    String[] col_array = {"Liquefaction", "Rockfall", "Fire", "Landslide"};


    TextView zonetextView;
    boolean[] selectedzone;
    ArrayList<Integer> zone_list = new ArrayList<>();
    ArrayList<String> zone_data = new ArrayList<>();
    String[] zone_array = {"Zone-II", "Zone-III", "Zone-IV", "Zone-V"};
    int z_ans = -1;

    TextView soiltextView;
    boolean[] selectedsoil;
    ArrayList<Integer> soil_list = new ArrayList<>();
    ArrayList<String> soil_data = new ArrayList<>();
    String[] soil_array = {"Hard Rock", "Medium Soil", "Soft Soil"};
    int s_ans = -1;

    TextView imptextView;
    boolean[] selectedimp;
    ArrayList<Integer> imp_list = new ArrayList<>();
    ArrayList<String> imp_data = new ArrayList<>();
    String[] imp_array = {"Residence", "Office", "Commercial"};
    int i_ans = -1;

    private static final int STORAGE_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("EDRI - Calculator");
        view_file = findViewById(R.id.viewfile_btn);
        view_file.setVisibility(View.GONE);

        leftview_capture = findViewById(R.id.leftview_capture_btn);
        rightview_capture = findViewById(R.id.rightview_capture_btn);
        frontview_capture = findViewById(R.id.frontview_capture_btn);
        rearview_capture = findViewById(R.id.rearview_capture_btn);


        ScrollView scrollview = findViewById(R.id.scrollview);

        coltextView = findViewById(R.id.ColtextView);

        selectedCol = new boolean[col_array.length];

        coltextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Select Collateral Damage");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(col_array, selectedCol, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            col_list.add(i);
                            Collections.sort(col_list);
                        } else {
                            col_list.remove(Integer.valueOf(i));
                        }
//                        ArrayList<Integer> col_list2 = col_list;
//                            System.out.println(col_array[col_list]);
                    }

                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int r = 0; r < col_list.size(); r++) {
                            col_data.add(col_array[col_list.get(r)]);
                        }
//                        for (int k = 0; k < col_list.size(); k++) {
//                            col_pdf.add(col_array[col_list.get(k)]);
//                        }
                        for (int j = 0; j < col_list.size(); j++) {
                            stringBuilder.append(col_array[col_list.get(j)]);
                            if (j != col_list.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        coltextView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selectedCol.length; j++) {
                            selectedCol[j] = false;
                            col_list.clear();
                            col_data.clear();
                            coltextView.setText("");
                        }
                    }
                });
                builder.show();
//                System.out.println(stringb);
            }
        });

        zonetextView = findViewById(R.id.ZonetextView);

        selectedzone = new boolean[zone_array.length];

        zonetextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Select Zone Factor");
                builder.setCancelable(false);
                builder.setSingleChoiceItems(zone_array, z_ans, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        z_ans = i;
                    }

                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        zonetextView.setText(zone_array[z_ans]);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        z_ans = -1;
                        zonetextView.setText("");
                    }
                });
                builder.show();
//                System.out.println(stringb);
            }
        });

        soiltextView = findViewById(R.id.SoiltextView);

        selectedsoil = new boolean[soil_array.length];

        soiltextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Select Soil Type");
                builder.setCancelable(false);

                builder.setSingleChoiceItems(soil_array, s_ans, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        s_ans = i;
                    }

                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        soiltextView.setText(soil_array[s_ans]);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        s_ans = -1;
                        soiltextView.setText("");
                    }
                });
                builder.show();
//                System.out.println(stringb);
            }
        });

        leftview_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createLeftImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File

                    }
                    // Continue only if the File was successfully created
//                        if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.myapplication9.provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, 1);
//                        }
                }
            }
        });
        rightview_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createRightImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File

                    }
                    // Continue only if the File was successfully created
//                        if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.myapplication9.provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, 1);
//                        }
                }
            }
        });
        frontview_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createFrontImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File

                    }
                    // Continue only if the File was successfully created
//                        if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.myapplication9.provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, 1);
//                        }
                }
            }
        });
        rearview_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createRearImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File

                    }
                    // Continue only if the File was successfully created
//                        if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.myapplication9.provider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, 1);
//                        }
                }
            }
        });

        imptextView = findViewById(R.id.ImptextView);

        selectedimp = new boolean[imp_array.length];

        imptextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Select Importance Type");
                builder.setCancelable(false);

                builder.setSingleChoiceItems(imp_array, i_ans, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        i_ans = i;
                    }

                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imptextView.setText(imp_array[i_ans]);

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        i_ans = -1;
                        imptextView.setText("");
                    }
                });
                builder.show();
//                System.out.println(stringb);
            }
        });

        ch1 = (CheckBox) findViewById(R.id.site1);
        ch2 = (CheckBox) findViewById(R.id.site2);
        ch3 = (CheckBox) findViewById(R.id.site3);
        ch4 = (CheckBox) findViewById(R.id.soil1);
        ch5 = (CheckBox) findViewById(R.id.soil2);
        ch6 = (CheckBox) findViewById(R.id.arc1_check);
        ch7 = (CheckBox) findViewById(R.id.arc2_check);
        ch8 = (CheckBox) findViewById(R.id.strc1_check);
        ch9 = (CheckBox) findViewById(R.id.strc2_check);
        ch10 = (CheckBox) findViewById(R.id.strc3_check);
        ch11 = (CheckBox) findViewById(R.id.con1_check);
        ch12 = (CheckBox) findViewById(R.id.con2_check);
        ch13 = (CheckBox) findViewById(R.id.con3_check);
        ch14 = (CheckBox) findViewById(R.id.lossSite1);
        ch15 = (CheckBox) findViewById(R.id.lossSoil1);
        ch16 = (CheckBox) findViewById(R.id.lossSoil2);
        ch17 = (CheckBox) findViewById(R.id.suit_soil1);
        ch18 = (CheckBox) findViewById(R.id.suit_soil2);
        ch19 = (CheckBox) findViewById(R.id.found1);
        ch20 = (CheckBox) findViewById(R.id.found2);
        ch21 = (CheckBox) findViewById(R.id.found3);
        ch22 = (CheckBox) findViewById(R.id.found4);
        ch23 = (CheckBox) findViewById(R.id.found5);
        ch24 = (CheckBox) findViewById(R.id.plan1);
        ch25 = (CheckBox) findViewById(R.id.plan2);
        ch26 = (CheckBox) findViewById(R.id.plan3);
        ch27 = (CheckBox) findViewById(R.id.elev1);
        ch28 = (CheckBox) findViewById(R.id.elev2);
        ch29 = (CheckBox) findViewById(R.id.elev3);
        ch30 = (CheckBox) findViewById(R.id.elev4);
        ch31 = (CheckBox) findViewById(R.id.elev5);
        ch32 = (CheckBox) findViewById(R.id.elev6);
        ch33 = (CheckBox) findViewById(R.id.elev7);
        ch34 = (CheckBox) findViewById(R.id.elev8);
        ch35 = (CheckBox) findViewById(R.id.door1);
        ch36 = (CheckBox) findViewById(R.id.door2);
        ch37 = (CheckBox) findViewById(R.id.door3);
        ch38 = (CheckBox) findViewById(R.id.door4);
        ch39 = (CheckBox) findViewById(R.id.door5);
        ch40 = (CheckBox) findViewById(R.id.distance1);
        ch41 = (CheckBox) findViewById(R.id.distance2);
        ch42 = (CheckBox) findViewById(R.id.parapets1);
        ch43 = (CheckBox) findViewById(R.id.parapets2);
        ch44 = (CheckBox) findViewById(R.id.staircases1);
        ch45 = (CheckBox) findViewById(R.id.staircases2);
        ch46 = (CheckBox) findViewById(R.id.staircases3);
        ch47 = (CheckBox) findViewById(R.id.frame1);
        ch48 = (CheckBox) findViewById(R.id.frame2);
        ch49 = (CheckBox) findViewById(R.id.frame3);
        ch50 = (CheckBox) findViewById(R.id.frame4);
        ch51 = (CheckBox) findViewById(R.id.frame5);
        ch52 = (CheckBox) findViewById(R.id.frame6);

//        ch52 = (CheckBox)findViewById(R.id.frame;
        ch53 = (CheckBox) findViewById(R.id.roof1);
        ch54 = (CheckBox) findViewById(R.id.roof2);
        ch55 = (CheckBox) findViewById(R.id.roof3);
        ch56 = (CheckBox) findViewById(R.id.roof4);
        ch57 = (CheckBox) findViewById(R.id.roof_column1);
        ch58 = (CheckBox) findViewById(R.id.member1);
        ch59 = (CheckBox) findViewById(R.id.column1);
        ch60 = (CheckBox) findViewById(R.id.column2);
        ch61 = (CheckBox) findViewById(R.id.struc_staircase1);
        ch62 = (CheckBox) findViewById(R.id.struc_staircase2);
        ch63 = (CheckBox) findViewById(R.id.struc_staircase3);
        ch64 = (CheckBox) findViewById(R.id.tank1);
        ch65 = (CheckBox) findViewById(R.id.materials1);
        ch66 = (CheckBox) findViewById(R.id.materials2);
        ch67 = (CheckBox) findViewById(R.id.materials3);
        ch68 = (CheckBox) findViewById(R.id.materials4);
        ch69 = (CheckBox) findViewById(R.id.workmanship1);
        ch70 = (CheckBox) findViewById(R.id.workmanship2);
        ch71 = (CheckBox) findViewById(R.id.concrete1);
        ch72 = (CheckBox) findViewById(R.id.concrete2);


//        spectre_text = findViewById(R.id.editTextTextPersonName4);
        fsi_text = findViewById(R.id.editTextTextPersonName2);
        stories_text = findViewById(R.id.editTextTextPersonName9);
        allowable_fsi_text = findViewById(R.id.fsi_allowable);


        Button button = (Button) findViewById(R.id.postdataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder errbuilder = new AlertDialog.Builder(MainActivity.this);
                try {
                    errbuilder.setTitle("Required Fields")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .setMessage("Make sure to Fill all the required Fields.")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });


                    if (ch14.isChecked()) {
                        lossSite1 = 5;
                        checkbox_data.add(ch14.getText().toString());
                    }
                    if (ch15.isChecked()) {
                        lossSoil1 = 2;
                        checkbox_data.add(ch15.getText().toString());
                    }
                    if (ch16.isChecked()) {
                        lossSoil2 = 2;
                        checkbox_data.add(ch16.getText().toString());
                    }
                    if (ch17.isChecked()) {
                        suit_soil1 = 1;
                        checkbox_data.add(ch17.getText().toString());
                    }
                    if (ch18.isChecked()) {
                        suit_soil2 = 2;
                        checkbox_data.add(ch18.getText().toString());
                    }
                    if (ch19.isChecked()) {
                        found1 = 3;
                        checkbox_data.add(ch19.getText().toString());
                    }
                    if (ch20.isChecked()) {
                        found2 = 1;
                        checkbox_data.add(ch20.getText().toString());
                    }
                    if (ch21.isChecked()) {
                        found3 = 3;
                        checkbox_data.add(ch21.getText().toString());
                    }
                    if (ch22.isChecked()) {
                        found4 = 1;
                        checkbox_data.add(ch22.getText().toString());
                    }
                    if (ch23.isChecked()) {
                        found5 = 2;
                        checkbox_data.add(ch23.getText().toString());
                    }
                    if (ch24.isChecked()) {
                        plan1 = 5;
                        checkbox_data.add(ch24.getText().toString());
                    }
                    if (ch25.isChecked()) {
                        plan2 = 3;
                        checkbox_data.add(ch25.getText().toString());
                    }
                    if (ch26.isChecked()) {
                        plan3 = 5;
                        checkbox_data.add(ch26.getText().toString());
                    }
                    if (ch27.isChecked()) {
//            Waiting for more info, from bharat
                        elev1 = 0;
                        checkbox_data.add(ch27.getText().toString());
                    }
                    if (ch28.isChecked()) {
                        elev2 = 5;
                        checkbox_data.add(ch28.getText().toString());
                    }
                    if (ch29.isChecked()) {
                        elev3 = 3;
                        checkbox_data.add(ch29.getText().toString());
                    }
                    if (ch30.isChecked()) {
                        elev4 = 5;
                        checkbox_data.add(ch30.getText().toString());
                    }
                    if (ch31.isChecked()) {
                        elev5 = 5;
                        checkbox_data.add(ch31.getText().toString());
                    }
                    if (ch32.isChecked()) {
                        elev6 = 5;
                        checkbox_data.add(ch32.getText().toString());
                    }
                    if (ch33.isChecked()) {
                        elev7 = 5;
                        checkbox_data.add(ch33.getText().toString());
                    }
                    if (ch34.isChecked()) {
                        elev8 = 40;
                        checkbox_data.add(ch34.getText().toString());
                    }
                    if (ch35.isChecked()) {
                        door1 = 1;
                        checkbox_data.add(ch35.getText().toString());
                    }
                    if (ch36.isChecked()) {
                        door2 = 2;
                        checkbox_data.add(ch36.getText().toString());
                    }
                    if (ch37.isChecked()) {
                        door3 = 4;
                        checkbox_data.add(ch37.getText().toString());
                    }
                    if (ch38.isChecked()) {
                        door4 = 4;
                        checkbox_data.add(ch38.getText().toString());
                    }
                    if (ch39.isChecked()) {
                        door5 = 6;
                        checkbox_data.add(ch39.getText().toString());
                    }
                    if (ch40.isChecked()) {
                        distance1 = 3;
                        checkbox_data.add(ch40.getText().toString());
                    }
                    if (ch41.isChecked()) {
                        distance2 = 3;
                        checkbox_data.add(ch41.getText().toString());
                    }
                    if (ch42.isChecked()) {
                        parapets1 = 4;
                        checkbox_data.add(ch42.getText().toString());
                    }
                    if (ch43.isChecked()) {
//            Waiting for more info, from Bharat
                        parapets2 = 0;
                        checkbox_data.add(ch43.getText().toString());
                    }
                    if (ch44.isChecked()) {
                        staircases1 = 1;
                        checkbox_data.add(ch44.getText().toString());
                    }
                    if (ch45.isChecked()) {
                        staircases2 = 1;
                        checkbox_data.add(ch45.getText().toString());
                    }
                    if (ch46.isChecked()) {
                        staircases3 = 1;
                        checkbox_data.add(ch46.getText().toString());
                    }
                    if (ch47.isChecked()) {
                        frame1 = 5;
                        checkbox_data.add(ch47.getText().toString());
                    }
                    if (ch48.isChecked()) {
                        frame2 = 10;
                        checkbox_data.add(ch48.getText().toString());
                    }
                    if (ch49.isChecked()) {
                        frame3 = 5;
                        checkbox_data.add(ch49.getText().toString());
                    }
                    if (ch50.isChecked()) {
                        frame4 = 10;
                        checkbox_data.add(ch50.getText().toString());
                    }
                    if (ch51.isChecked()) {
                        frame5 = 5;
                        checkbox_data.add(ch51.getText().toString());
                    }
                    if (ch52.isChecked()) {
                        frame6 = 10;
                        checkbox_data.add(ch52.getText().toString());
                    }
                    if (ch53.isChecked()) {
                        roof1 = 10;
                        checkbox_data.add(ch53.getText().toString());
                    }
                    if (ch54.isChecked()) {
                        roof2 = 5;
                        checkbox_data.add(ch54.getText().toString());
                    }
                    if (ch55.isChecked()) {
                        roof3 = 10;
                        checkbox_data.add(ch55.getText().toString());
                    }
                    if (ch56.isChecked()) {
                        roof4 = 10;
                        checkbox_data.add(ch56.getText().toString());
                    }
                    if (ch57.isChecked()) {
                        roof_column1 = 10;
                        checkbox_data.add(ch57.getText().toString());
                    }
                    if (ch58.isChecked()) {
                        member1 = 20;
                        checkbox_data.add(ch58.getText().toString());
                    }
                    if (ch59.isChecked()) {
                        column1 = 5;
                        checkbox_data.add(ch59.getText().toString());
                    }
                    if (ch60.isChecked()) {
                        column2 = 15;
                        checkbox_data.add(ch60.getText().toString());
                    }
                    if (ch61.isChecked()) {
                        struc_staircase1 = 5;
                        checkbox_data.add(ch61.getText().toString());
                    }
                    if (ch62.isChecked()) {
                        struc_staircase2 = 5;
                        checkbox_data.add(ch62.getText().toString());
                    }
                    if (ch63.isChecked()) {
                        struc_staircase3 = 10;
                        checkbox_data.add(ch63.getText().toString());
                    }
                    if (ch64.isChecked()) {
                        tank1 = 4;
                        checkbox_data.add(ch64.getText().toString());
                    }
                    if (ch65.isChecked()) {
                        materials1 = 10;
                        checkbox_data.add(ch65.getText().toString());
                    }
                    if (ch66.isChecked()) {
                        materials2 = 5;
                        checkbox_data.add(ch66.getText().toString());
                    }
                    if (ch67.isChecked()) {
                        materials3 = 10;
                        checkbox_data.add(ch67.getText().toString());
                    }
                    if (ch68.isChecked()) {
                        materials4 = 5;
                        checkbox_data.add(ch68.getText().toString());
                    }
                    if (ch69.isChecked()) {
                        workmanship1 = 3;
                        checkbox_data.add(ch69.getText().toString());
                    }
                    if (ch70.isChecked()) {
                        workmanship2 = 10;
                        checkbox_data.add(ch70.getText().toString());
                    }
                    if (ch71.isChecked()) {
                        concrete1 = 4;
                        checkbox_data.add(ch71.getText().toString());
                    }
                    if (ch72.isChecked()) {
                        concrete2 = 4;
                        checkbox_data.add(ch72.getText().toString());
                    }

//                if(ch1.isChecked() || ch2.isChecked() || ch3.isChecked() || ch4.isChecked() || ch5.isChecked() || ch6.isChecked() || ch7.isChecked() || ch8.isChecked() || ch9.isChecked() || ch10.isChecked() || ch11.isChecked() || ch12.isChecked() || ch13.isChecked()) {
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                }else {

                    if (CheckZone() & CheckSoil() & CheckImp() & CheckStories() & CheckFsi()) {


                        switch (zone_array[z_ans]) {
                            case "Zone-II":
                                z_val = 0.10;
                                break;
                            case "Zone-III":
                                z_val = 0.16;
                                break;
                            case "Zone-IV":
                                z_val = 0.16;
                                break;
                            case "Zone-V":
                                z_val = 0.36;
                                break;
                        }

                        switch (soil_array[s_ans]) {
                            case "Hard Rock":
                                soil_val = 1.0;
                                break;
                            case "Medium Soil":
                                soil_val = 1.33;
                                break;
                            case "Soft Soil":
                                soil_val = 1.67;
                                break;
                        }

                        switch (imp_array[i_ans]) {
                            case "Residence":
                                imp_val = 1.0;
                                break;
                            case "Office":
                                imp_val = 1.25;
                                break;
                            case "Commercial":
                                imp_val = 1.5;
                                break;
                        }


//                        String spec_text = spectre_text.getText().toString();
//                        spectral_val = Double.parseDouble(spec_text);
//
//                        String fsi = fsi_text.getText().toString();
//                        fsi_val = Double.parseDouble(fsi);
//
//                        String stories = stories_text.getText().toString();
//                        stories_val = Integer.parseInt(stories);
//
//                        spectral_val = Math.min((20 / stories_val), 2.5);
//
//                        Integer economic_loss_sum = lossSite1 + lossSoil1 + lossSoil2 + suit_soil1 + suit_soil2 + found1 + found2 + found3 + found4 + found5 + plan1 + plan2 + plan3 + elev1 + elev2 + elev3 + elev4 + elev5 + elev6 + elev7 + elev8 + door1 + door2 + door3 + door4 + door5 + distance1 + distance2 + parapets1 + parapets2 + staircases1 + staircases2 + staircases3 + frame1 + frame2 + frame3 + frame4 + frame5 + frame6 + roof1 + roof2 + roof3 + roof4 + roof_column1 + member1 + column1 + column2 + struc_staircase1 + struc_staircase2 + struc_staircase3 + tank1 + materials1 + materials2 + materials3 + materials4 + workmanship1 + workmanship2 + concrete1 + concrete2;
//
//                        economic_loss = economic_loss_sum / 100.0;
//                        Double hazard_val = spectral_val * soil_val * z_val;
//                        Double exposure_val = imp_val * fsi_val;

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("Building Safety Alert")
                                .setIcon(R.drawable.asd)
                                .setMessage("This building's safety is compromised because of it's life threatening factors or Collateral Hazards, please contact the needed authorities.\n\nThough you results are still available below.")
//                                .setMessage("This building's safety is compromised because of it's life threatening factors, please contact the needed authorities.\n\nAlthough the Values of various factors are as follows:\n\n" + hazard_string + "\n" + exposure_string + "\n" + vulner_string + "\n"+ "Risk Value is: 100%" + "\n")
                                .setCancelable(false)

                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });


                        if (ch1.isChecked()) {
                            life_checkbox_data.add(ch1.getText().toString());
                        }
                        if (ch2.isChecked()) {
                            life_checkbox_data.add(ch2.getText().toString());
                        }
                        if (ch3.isChecked()) {
                            life_checkbox_data.add(ch3.getText().toString());
                        }
                        if (ch4.isChecked()) {
                            life_checkbox_data.add(ch4.getText().toString());
                        }
                        if (ch5.isChecked()) {
                            life_checkbox_data.add(ch5.getText().toString());
                        }
                        if (ch6.isChecked()) {
                            life_checkbox_data.add(ch6.getText().toString());
                        }
                        if (ch7.isChecked()) {
                            life_checkbox_data.add(ch7.getText().toString());
                        }
                        if (ch8.isChecked()) {
                            life_checkbox_data.add(ch8.getText().toString());
                        }
                        if (ch9.isChecked()) {
                            life_checkbox_data.add(ch9.getText().toString());
                        }
                        if (ch10.isChecked()) {
                            life_checkbox_data.add(ch10.getText().toString());
                        }
                        if (ch11.isChecked()) {
                            life_checkbox_data.add(ch11.getText().toString());
                        }
                        if (ch12.isChecked()) {
                            life_checkbox_data.add(ch12.getText().toString());
                        }
                        if (ch13.isChecked()) {
                            life_checkbox_data.add(ch13.getText().toString());
                        }

//                        String spec_text = spectre_text.getText().toString();
//                        spectral_val = Double.parseDouble(spec_text);

                        String fsi = fsi_text.getText().toString();
                        fsi_val = Double.parseDouble(fsi);


                        allowable_fsi_text.setText("1");
                        String fsi_allowable_text = allowable_fsi_text.getText().toString();
                        allowable_fsi_value = Double.parseDouble(fsi_allowable_text);

                        String stories = stories_text.getText().toString();
                        stories_val = Double.parseDouble(stories);

//                        Double new_stories_val = stories_val;

                        spectral_val = Math.min(20 / stories_val, 2.5);

                        Integer economic_loss_sum = lossSite1 + lossSoil1 + lossSoil2 + suit_soil1 + suit_soil2 + found1 + found2 + found3 + found4 + found5 + plan1 + plan2 + plan3 + elev1 + elev2 + elev3 + elev4 + elev5 + elev6 + elev7 + elev8 + door1 + door2 + door3 + door4 + door5 + distance1 + distance2 + parapets1 + parapets2 + staircases1 + staircases2 + staircases3 + frame1 + frame2 + frame3 + frame4 + frame5 + frame6 + roof1 + roof2 + roof3 + roof4 + roof_column1 + member1 + column1 + column2 + struc_staircase1 + struc_staircase2 + struc_staircase3 + tank1 + materials1 + materials2 + materials3 + materials4 + workmanship1 + workmanship2 + concrete1 + concrete2;


                        economic_loss = economic_loss_sum / 100.0;
                        hazard_val = spectral_val * soil_val * z_val;

                        complete_fsi_val = fsi_val/allowable_fsi_value;

                        exposure_val = imp_val * complete_fsi_val;


                        risk_val = economic_loss * hazard_val * exposure_val;
                        if (!life_checkbox_data.isEmpty() || !col_data.isEmpty()) {
                            risk_percentage = "100%";
                        }
                        else{

                            risk_percentage = String.valueOf(risk_val);
                            System.out.println(risk_percentage);
                        }
//                            Double risk_percentage_val = risk_val * 100 / 1;
//                            risk_percentage = String.valueOf(risk_percentage_val + "%");
//                        } else {
//                            risk_percentage = "100%";
//                        }


                        if (ch1.isChecked() || ch2.isChecked() || ch3.isChecked() || ch4.isChecked() || ch5.isChecked() || ch6.isChecked() || ch7.isChecked() || ch8.isChecked() || ch9.isChecked() || ch10.isChecked() || ch11.isChecked() || ch12.isChecked() || ch13.isChecked() || !col_data.isEmpty()) {
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {
                                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permissions, STORAGE_CODE);
                            } else {
                                savePdf();
                            }
                        } else {
                            savePdf();

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please make sure to fill all the required fields.", Toast.LENGTH_SHORT).show();
                        scrollview.smoothScrollTo(0, 0);

                    }

                } catch (Exception e) {
                    AlertDialog dialog = errbuilder.create();
                    dialog.show();
                    e.printStackTrace();
                }

                System.out.println(hazard_val);
                System.out.println(spectral_val);
                System.out.println(exposure_val);
                System.out.println(economic_loss);

                System.out.println(allowable_fsi_value);
                System.out.println(fsi_val);
                System.out.println(complete_fsi_val);



                col_data.clear();
                life_checkbox_data.clear();
                checkbox_data.clear();
            }
        });

    }

    private File createLeftImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "LEFTVIEW_JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir);

        leftview_photopath = "file:" + image.getAbsolutePath();
        return image;/* directory */
    }

    private File createRightImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "RIGHTVIEW_JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir);

        rightview_photopath = "file:" + image.getAbsolutePath();
        return image;/* directory */
    }

    private File createFrontImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "FRONTVIEW_JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir);

        frontview_photopath = "file:" + image.getAbsolutePath();
        return image;/* directory */
    }

    private File createRearImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "REARVIEW_JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir);

        rearview_photopath = "file:" + image.getAbsolutePath();
        return image;/* directory */
    }

    private boolean CheckZone() {
        if (zonetextView.getText().length() == 0) {
            zonetextView.setError("This field is required");
            return false;
        } else {
            return true;
        }
    }

    private boolean CheckSoil() {
        if (soiltextView.getText().length() == 0) {
            soiltextView.setError("This field is required");
            return false;
        } else {
            return true;
        }
    }

    private boolean CheckImp() {
        if (imptextView.getText().length() == 0) {
            imptextView.setError("This field is required");
            return false;
        } else {
            return true;
        }
    }

    private boolean CheckStories() {
        if (stories_text.length() == 0) {
            stories_text.setError("This field is required");
            return false;
        } else {
            return true;
        }
    }


    private boolean CheckFsi() {
        if (fsi_text.length() == 0) {
            fsi_text.setError("This field is required");
            return false;
        } else {
            return true;
        }
    }


    private void savePdf() throws FileNotFoundException, DocumentException {

        pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");

        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i(TAG, "Pdf Directory created");
        }


        Document doc = new Document();
        mFilename = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
//        String pdffile_path = pdfFolder + mFilename + ".pdf";
        myFile = new File(pdfFolder + mFilename + ".pdf");
        OutputStream mFilePath = new FileOutputStream(myFile);


        try {
            PdfWriter writer = PdfWriter.getInstance(doc, mFilePath);
            Rectangle rect = new Rectangle(30, 30, 550, 800);
            writer.setBoxSize("art", rect);
            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            writer.setPageEvent(event);
            writer.setPageEvent(new WatermarkPageEvent());

            doc.open();

            Paragraph intro = new Paragraph("\nEDRI - Calculator has generated a pdf which contains all the required data and calculations to understand the safety of the structure, please read the pdf thoroughly to understand which features add risk to the structures.\n\n");

            doc.addAuthor("EDRI-Calculator");
            doc.addTitle("EDRI - Generated PDF");

            Drawable d = getResources().getDrawable(R.drawable.hdpi);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.scaleAbsolute(90, 90);

            Paragraph intro_image = new Paragraph();
            intro_image.add(new Chunk("EDRI - Calculator\n", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.BLACK)));

            doc.add(intro_image);


            doc.add(intro);
            doc.add(new Paragraph("\n"));


            ArrayList l = new ArrayList();

            for (int j = 0; j < col_data.size(); j++) {
                l.add(col_data.get(j));
            }
            Set s = new HashSet(l);
            List<String> ll = new ArrayList<String>(s);
            com.itextpdf.text.List ktf = new com.itextpdf.text.List();
            for (int i = 0; i < ll.size(); i++) {
                ktf.add(ll.get(i));
            }


            Phrase phrase = new Phrase();
            phrase.add(ktf);
            PdfPCell phraseCell = new PdfPCell();
            phraseCell.addElement(phrase);

            PdfPTable phraseTable = new PdfPTable(2);
            phraseTable.setSpacingBefore(5);
            phraseTable.addCell("Collateral Damage: ");
            phraseTable.addCell(phraseCell);

            Phrase phraseTableWrapper = new Phrase();
            phraseTableWrapper.add(phraseTable);
            doc.add(phraseTableWrapper);
            doc.add(new Paragraph("\n"));


            PdfPCell cell = new PdfPCell();
            cell.addElement(ktf);

            PdfPTable table = new PdfPTable(2);
            table.setSpacingBefore(5);
            table.addCell("List placed directly into cell");
            table.addCell(cell);

//            Zone Column

            com.itextpdf.text.List zone_table_list = new com.itextpdf.text.List();
            zone_table_list.add(zone_array[z_ans]);
            Phrase zone_phrase = new Phrase();
            zone_phrase.add(zone_table_list);
            PdfPCell zone_phraseCell = new PdfPCell();
            zone_phraseCell.addElement(zone_phrase);

            PdfPTable zone_phraseTable = new PdfPTable(2);
            zone_phraseTable.setSpacingBefore(5);
            zone_phraseTable.addCell("Seismic Zone: ");
            zone_phraseTable.addCell(zone_phraseCell);

            Phrase zone_phraseTableWrapper = new Phrase();
            zone_phraseTableWrapper.add(zone_phraseTable);
            doc.add(zone_phraseTableWrapper);
            doc.add(new Paragraph("\n"));


            PdfPCell zone_cell = new PdfPCell();
            zone_cell.addElement(zone_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable zone_table = new PdfPTable(2);
            zone_table.setSpacingBefore(5);
            zone_table.addCell("List placed directly into cell");
            zone_table.addCell(zone_cell);

//            Soil Column

            com.itextpdf.text.List soil_table_list = new com.itextpdf.text.List();
            soil_table_list.add(soil_array[s_ans]);
            Phrase soil_phrase = new Phrase();
            soil_phrase.add(soil_table_list);
            PdfPCell soil_phraseCell = new PdfPCell();
            soil_phraseCell.addElement(soil_phrase);

            PdfPTable soil_phraseTable = new PdfPTable(2);
            soil_phraseTable.setSpacingBefore(5);
            soil_phraseTable.addCell("Soil Type: ");
            soil_phraseTable.addCell(soil_phraseCell);

            Phrase soil_phraseTableWrapper = new Phrase();
            soil_phraseTableWrapper.add(soil_phraseTable);
            doc.add(soil_phraseTableWrapper);
            doc.add(new Paragraph("\n"));


            PdfPCell soil_cell = new PdfPCell();
            soil_cell.addElement(soil_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable soil_table = new PdfPTable(2);
            soil_table.setSpacingBefore(5);
            soil_table.addCell("List placed directly into cell");
            soil_table.addCell(soil_cell);


            com.itextpdf.text.List storeys_table_list = new com.itextpdf.text.List();
            storeys_table_list.add(stories_text.getText().toString());
            Phrase storeys_phrase = new Phrase();
            storeys_phrase.add(storeys_table_list);
            PdfPCell storeys_phraseCell = new PdfPCell();
            storeys_phraseCell.addElement(storeys_phrase);

            PdfPTable storeys_phraseTable = new PdfPTable(2);
            storeys_phraseTable.setSpacingBefore(5);
            storeys_phraseTable.addCell("Storeys of the Structure: ");
            storeys_phraseTable.addCell(storeys_phraseCell);

            Phrase storeys_phraseTableWrapper = new Phrase();
            storeys_phraseTableWrapper.add(storeys_phraseTable);
            doc.add(storeys_phraseTableWrapper);
            doc.add(new Paragraph("\n"));


            PdfPCell storeys_cell = new PdfPCell();
            storeys_cell.addElement(storeys_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable storeys_table = new PdfPTable(2);
            storeys_table.setSpacingBefore(5);
            storeys_table.addCell("List placed directly into cell");
            storeys_table.addCell(storeys_cell);


            com.itextpdf.text.List spectre_table_list = new com.itextpdf.text.List();
            spectre_table_list.add(String.valueOf(spectral_val));
            Phrase spectre_phrase = new Phrase();
            spectre_phrase.add(spectre_table_list);
            PdfPCell spectre_phraseCell = new PdfPCell();
            spectre_phraseCell.addElement(spectre_phrase);

            PdfPTable spectre_phraseTable = new PdfPTable(2);
            spectre_phraseTable.setSpacingBefore(5);
            spectre_phraseTable.addCell("Spectral Shape: ");
            spectre_phraseTable.addCell(spectre_phraseCell);

            Phrase spectre_phraseTableWrapper = new Phrase();
            spectre_phraseTableWrapper.add(spectre_phraseTable);
            doc.add(spectre_phraseTableWrapper);
            doc.add(new Paragraph("\n"));


            PdfPCell spectre_cell = new PdfPCell();
            spectre_cell.addElement(spectre_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable spectre_table = new PdfPTable(2);
            spectre_table.setSpacingBefore(5);
            spectre_table.addCell("List placed directly into cell");
            spectre_table.addCell(spectre_cell);


            com.itextpdf.text.List imp_table_list = new com.itextpdf.text.List();
            imp_table_list.add(imp_array[i_ans]);
            Phrase imp_phrase = new Phrase();
            imp_phrase.add(imp_table_list);
            PdfPCell imp_phraseCell = new PdfPCell();
            imp_phraseCell.addElement(imp_phrase);

            PdfPTable imp_phraseTable = new PdfPTable(2);
            imp_phraseTable.setSpacingBefore(5);
            imp_phraseTable.addCell("Importance of the Structure: ");
            imp_phraseTable.addCell(imp_phraseCell);

            Phrase imp_phraseTableWrapper = new Phrase();
            imp_phraseTableWrapper.add(imp_phraseTable);
            doc.add(imp_phraseTableWrapper);
            doc.add(new Paragraph("\n"));

            PdfPCell imp_cell = new PdfPCell();
            imp_cell.addElement(imp_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable imp_table = new PdfPTable(2);
            imp_table.setSpacingBefore(5);
            imp_table.addCell("List placed directly into cell");
            imp_table.addCell(imp_cell);


            com.itextpdf.text.List fsi_table_list = new com.itextpdf.text.List();
            fsi_table_list.add(fsi_text.getText().toString());
            Phrase fsi_phrase = new Phrase();
            fsi_phrase.add(fsi_table_list);
            PdfPCell fsi_phraseCell = new PdfPCell();
            fsi_phraseCell.addElement(fsi_phrase);

            PdfPTable fsi_phraseTable = new PdfPTable(2);
            fsi_phraseTable.setSpacingBefore(5);
            fsi_phraseTable.addCell("Floor Space Index of the Structure(FSI):  ");
            fsi_phraseTable.addCell(fsi_phraseCell);

            Phrase fsi_phraseTableWrapper = new Phrase();
            fsi_phraseTableWrapper.add(fsi_phraseTable);
            doc.add(fsi_phraseTableWrapper);
            doc.add(new Paragraph("\n"));

            PdfPCell fsi_cell = new PdfPCell();
            fsi_cell.addElement(fsi_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable fsi_table = new PdfPTable(2);
            fsi_table.setSpacingBefore(5);
            fsi_table.addCell("List placed directly into cell");
            fsi_table.addCell(fsi_cell);


            System.out.println(checkbox_data);

            Set economic_set = new HashSet(checkbox_data);
            List<String> economic_list = new ArrayList<String>(economic_set);
            com.itextpdf.text.List economic_table_list_array = new com.itextpdf.text.List();
            for (int sts = 0; sts < economic_list.size(); sts++) {
//                    doc.add(new Paragraph("-> "+ll.get(i)));
                economic_table_list_array.add(economic_list.get(sts) + "\n\n");
            }

            Phrase eco_phrase = new Phrase();
            eco_phrase.add(economic_table_list_array);
            PdfPCell eco_phraseCell = new PdfPCell();
            eco_phraseCell.addElement(eco_phrase);

            PdfPTable eco_phraseTable = new PdfPTable(2);
            eco_phraseTable.setSpacingBefore(5);
            eco_phraseTable.addCell("Economic Loss Inducing Factors: ");
            eco_phraseTable.addCell(eco_phraseCell);

            Phrase eco_phraseTableWrapper = new Phrase();
            eco_phraseTableWrapper.add(eco_phraseTable);
            doc.add(eco_phraseTableWrapper);
            doc.add(new Paragraph("\n"));

            PdfPCell eco_cell = new PdfPCell();
            eco_cell.addElement(economic_table_list_array);

            PdfPTable eco_table = new PdfPTable(2);
            eco_table.setSpacingBefore(5);
            eco_table.addCell("List placed directly into cell");
            eco_table.addCell(eco_cell);


            Set life_set = new HashSet(life_checkbox_data);
            List<String> life_list = new ArrayList<String>(life_set);
            com.itextpdf.text.List life_table_list_array = new com.itextpdf.text.List();
            for (int sts = 0; sts < life_list.size(); sts++) {
//                    doc.add(new Paragraph("-> "+ll.get(i)));
                life_table_list_array.add(life_list.get(sts) + "\n\n");
            }

            Phrase life_phrase = new Phrase();
            life_phrase.add(life_table_list_array);
            PdfPCell life_phraseCell = new PdfPCell();
            life_phraseCell.addElement(life_phrase);

            PdfPTable life_phraseTable = new PdfPTable(2);
            life_phraseTable.setSpacingBefore(5);
            life_phraseTable.addCell("Life Threatening Factors: ");
            life_phraseTable.addCell(life_phraseCell);

            Phrase life_phraseTableWrapper = new Phrase();
            life_phraseTableWrapper.add(life_phraseTable);
            doc.add(life_phraseTableWrapper);
            doc.add(new Paragraph("\n"));

            PdfPCell life_cell = new PdfPCell();
            life_cell.addElement(life_table_list_array);

            PdfPTable life_table = new PdfPTable(2);
            life_table.setSpacingBefore(5);
            life_table.addCell("List placed directly into cell");
            life_table.addCell(life_cell);


            com.itextpdf.text.List risk_table_list = new com.itextpdf.text.List();
            risk_table_list.add(risk_percentage);
            Phrase risk_phrase = new Phrase();
            risk_phrase.add(risk_table_list);
            PdfPCell risk_phraseCell = new PdfPCell();
            risk_phraseCell.addElement(risk_phrase);

            PdfPTable risk_phraseTable = new PdfPTable(2);
            risk_phraseTable.setSpacingBefore(5);
            risk_phraseTable.addCell("Earthquake Disaster Risk Value: ");
            risk_phraseTable.addCell(risk_phraseCell);

            Phrase risk_phraseTableWrapper = new Phrase();
            risk_phraseTableWrapper.add(risk_phraseTable);
            doc.add(risk_phraseTableWrapper);
            doc.add(new Paragraph("\n"));

            PdfPCell risk_cell = new PdfPCell();
            risk_cell.addElement(risk_table_list);
//            zone_cell.addElement(zone_array[z_ans]);

            PdfPTable risk_table = new PdfPTable(2);
            risk_table.setSpacingBefore(5);
            risk_table.addCell("List placed directly into cell");
            risk_table.addCell(risk_cell);


            doc.close();
            Toast.makeText(this, mFilename + ".pdf\nis saved to\n" + mFilePath, Toast.LENGTH_SHORT).show();
            view_file.setVisibility(View.VISIBLE);
            view_file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewPdf = new Intent(Intent.ACTION_VIEW);
                    viewPdf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri URI = FileProvider.getUriForFile(MainActivity.this, MainActivity.this.getApplicationContext().getPackageName() + ".provider", myFile);
                    viewPdf.setDataAndType(URI, "application/pdf");
                    viewPdf.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    MainActivity.this.startActivity(viewPdf);
//                    File new_myfile = new File(pdfFolder + mFilename + ".pdf");
////                    File new_myfile = new File(mFilename);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(new_myfile));
//                    intent.setType("application/pdf");
//
//                    startActivity(intent);
//                    if (Build.VERSION.SDK_INT < 24) {
//
////                        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
////                                Environment.DIRECTORY_DOCUMENTS), "");
////
////                        myFile = new File(pdfFolder + mFilename + ".pdf");
//
//
////                        File file = new File(mFilename);
//                        Intent target = new Intent(Intent.ACTION_VIEW);
//                        target.setDataAndType(Uri.fromFile(myFile),"application/pdf");
//
//                        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//                        Intent intent = Intent.createChooser(target, "Open File");
//                        try {
//                            startActivity(intent);
//                        } catch (ActivityNotFoundException e) {
//                        }}else{
//                        Intent target = new Intent(Intent.ACTION_VIEW);
//                        target.setDataAndType(Uri.parse(myFile.getPath()),"application/pdf");
//                        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//                        Intent intent = Intent.createChooser(target, "Open File");
//                        try {
//                            startActivity(intent);
//                        } catch (ActivityNotFoundException e) {
//
//                        }}
                    System.out.println("-----------------------------");
                    System.out.println(myFile.toString());
                    System.out.println(mFilename);
                    System.out.println("-----------------------------");

            }});

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


//    private void previewPdf(View v,File file) {
////        String filename = null;
////        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ filename);
//        if (Build.VERSION.SDK_INT < 24) {
//            Intent target = new Intent(Intent.ACTION_VIEW);
//            target.setDataAndType(Uri.fromFile(file),"application/pdf");
//            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//            Intent intent = Intent.createChooser(target, "Open File");
//            try {
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//        }}else{
//            Intent target = new Intent(Intent.ACTION_VIEW);
//            target.setDataAndType(Uri.parse(file.getPath()),"application/pdf");
//            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//            Intent intent = Intent.createChooser(target, "Open File");
//            try {
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//
//        }

//            } else {
//                p = Uri.parse(file.getPath()); // My work-around for SDKs up to 29.
//            }
//        Intent target = new Intent(Intent.ACTION_VIEW);
//        target.setDataAndType(Uri.fromFile(file),"application/pdf");
//        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//        Intent intent = Intent.createChooser(target, "Open File");
//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {

//        if (file.exists()) {
//            Uri p;
//            if (Build.VERSION.SDK_INT < 24) {
//                p = Uri.fromFile(file);
//            } else {
//                p = Uri.parse(file.getPath()); // My work-around for SDKs up to 29.
//            }
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(p, "application/pdf");
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        try {
//            startActivity(intent);
//        }
//        catch (ActivityNotFoundException e){
//        }

        }




