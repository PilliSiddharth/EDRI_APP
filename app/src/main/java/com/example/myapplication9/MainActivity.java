package com.example.myapplication9;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    double z_val, soil_val, spectral_val, fsi_val, imp_val;
    Integer stories_val;

    EditText spectre_text, fsi_text, stories_text;
    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15,ch16,ch17,ch18,ch19,ch20,ch21,ch22,ch23,ch24,ch25,ch26,ch27,ch28,ch29,ch30,ch31,ch32,ch33,ch34,ch35,ch36,ch37,ch38,ch39,ch40,ch41,ch42,ch43,ch44,ch45,ch46,ch47,ch48,ch49,ch50,ch51,ch52,ch53,ch54,ch55,ch56,ch57,ch58,ch59,ch60,ch61,ch62,ch63,ch64,ch65,ch66,ch67,ch68,ch69,ch70, ch71, ch72;
    Integer lossSite1 = 0,lossSoil1 = 0,lossSoil2 = 0,suit_soil1 = 0,suit_soil2 = 0,found1 = 0,found2 = 0,found3 = 0,found4 = 0,found5 = 0,plan1 =0, plan2 = 0,plan3 = 0,elev1 = 0,elev2 = 0,elev3 = 0,elev4 = 0,elev5 = 0,elev6 = 0,elev7 = 0,elev8 = 0,door1 = 0,door2 =0, door3 = 0,door4 = 0,door5 = 0,distance1 = 0,distance2 = 0,parapets1 = 0,parapets2 = 0,staircases1 = 0,staircases2 = 0,staircases3 = 0,frame1 =0, frame2 = 0,frame3 = 0,frame4 = 0,frame5 = 0,frame6 = 0,roof1 =0, roof2 = 0,roof3 = 0,roof4 = 0,roof_column1 = 0,member1 = 0,column1 = 0,column2 = 0,struc_staircase1 = 0,struc_staircase2 = 0,struc_staircase3 = 0,tank1 = 0,materials1 = 0,materials2 = 0,materials3 = 0,materials4 = 0,workmanship1 =0, workmanship2 = 0,concrete1 = 0,concrete2 = 0;
    Double economic_loss;

    TextView coltextView;
    boolean[] selectedCol;
    ArrayList<Integer> col_list = new ArrayList<>();
    ArrayList<String> col_data = new ArrayList<>();
    String[] col_array = {"Liquefaction","Rockfall","Fire","Landslide"};


    TextView zonetextView;
    boolean[] selectedzone;
    ArrayList<Integer> zone_list = new ArrayList<>();
    ArrayList<String> zone_data = new ArrayList<>();
    String[] zone_array = {"Zone-II","Zone-III","Zone-IV","Zone-V"};
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                            z_ans  = -1;
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

        ch1 = (CheckBox)findViewById(R.id.site1);
        ch2 = (CheckBox)findViewById(R.id.site2);
        ch3 = (CheckBox)findViewById(R.id.site3);
        ch4 = (CheckBox)findViewById(R.id.soil1);
        ch5 = (CheckBox)findViewById(R.id.soil2);
        ch6 = (CheckBox)findViewById(R.id.arc1_check);
        ch7 = (CheckBox)findViewById(R.id.arc2_check);
        ch8 = (CheckBox)findViewById(R.id.strc1_check);
        ch9 = (CheckBox)findViewById(R.id.strc2_check);
        ch10 = (CheckBox)findViewById(R.id.strc3_check);
        ch11 = (CheckBox)findViewById(R.id.con1_check);
        ch12 = (CheckBox)findViewById(R.id.con2_check);
        ch13 = (CheckBox)findViewById(R.id.con3_check);
        ch14 = (CheckBox)findViewById(R.id.lossSite1);
        ch15 = (CheckBox)findViewById(R.id.lossSoil1);
        ch16 = (CheckBox)findViewById(R.id.lossSoil2);
        ch17 = (CheckBox)findViewById(R.id.suit_soil1);
        ch18 = (CheckBox)findViewById(R.id.suit_soil2);
        ch19 = (CheckBox)findViewById(R.id.found1);
        ch20 = (CheckBox)findViewById(R.id.found2);
        ch21 = (CheckBox)findViewById(R.id.found3);
        ch22 = (CheckBox)findViewById(R.id.found4);
        ch23 = (CheckBox)findViewById(R.id.found5);
        ch24 = (CheckBox)findViewById(R.id.plan1);
        ch25 = (CheckBox)findViewById(R.id.plan2);
        ch26 = (CheckBox)findViewById(R.id.plan3);
        ch27 = (CheckBox)findViewById(R.id.elev1);
        ch28 = (CheckBox)findViewById(R.id.elev2);
        ch29 = (CheckBox)findViewById(R.id.elev3);
        ch30 = (CheckBox)findViewById(R.id.elev4);
        ch31 = (CheckBox)findViewById(R.id.elev5);
        ch32 = (CheckBox)findViewById(R.id.elev6);
        ch33 = (CheckBox)findViewById(R.id.elev7);
        ch34 = (CheckBox)findViewById(R.id.elev8);
        ch35 = (CheckBox)findViewById(R.id.door1);
        ch36 = (CheckBox)findViewById(R.id.door2);
        ch37 = (CheckBox)findViewById(R.id.door3);
        ch38 = (CheckBox)findViewById(R.id.door4);
        ch39 = (CheckBox)findViewById(R.id.door5);
        ch40 = (CheckBox)findViewById(R.id.distance1);
        ch41 = (CheckBox)findViewById(R.id.distance2);
        ch42 = (CheckBox)findViewById(R.id.parapets1);
        ch43 = (CheckBox)findViewById(R.id.parapets2);
        ch44 = (CheckBox)findViewById(R.id.staircases1);
        ch45 = (CheckBox)findViewById(R.id.staircases2);
        ch46 = (CheckBox)findViewById(R.id.staircases3);
        ch47 = (CheckBox)findViewById(R.id.frame1);
        ch48 = (CheckBox)findViewById(R.id.frame2);
        ch49 = (CheckBox)findViewById(R.id.frame3);
        ch50 = (CheckBox)findViewById(R.id.frame4);
        ch51 = (CheckBox)findViewById(R.id.frame5);
        ch52 = (CheckBox)findViewById(R.id.frame6);

//        ch52 = (CheckBox)findViewById(R.id.frame;
        ch53 = (CheckBox)findViewById(R.id.roof1);
        ch54 = (CheckBox)findViewById(R.id.roof2);
        ch55 = (CheckBox)findViewById(R.id.roof3);
        ch56 = (CheckBox)findViewById(R.id.roof4);
        ch57 = (CheckBox)findViewById(R.id.roof_column1);
        ch58 = (CheckBox)findViewById(R.id.member1);
        ch59 = (CheckBox)findViewById(R.id.column1);
        ch60 = (CheckBox)findViewById(R.id.column2);
        ch61 = (CheckBox)findViewById(R.id.struc_staircase1);
        ch62 = (CheckBox)findViewById(R.id.struc_staircase2);
        ch63 = (CheckBox)findViewById(R.id.struc_staircase3);
        ch64 = (CheckBox)findViewById(R.id.tank1);
        ch65 = (CheckBox)findViewById(R.id.materials1);
        ch66 = (CheckBox)findViewById(R.id.materials2);
        ch67 = (CheckBox)findViewById(R.id.materials3);
        ch68 = (CheckBox)findViewById(R.id.materials4);
        ch69 = (CheckBox)findViewById(R.id.workmanship1);
        ch70 = (CheckBox)findViewById(R.id.workmanship2);
        ch71 = (CheckBox)findViewById(R.id.concrete1);
        ch72 = (CheckBox)findViewById(R.id.concrete2);


        spectre_text = findViewById(R.id.editTextTextPersonName4);
        fsi_text = findViewById(R.id.editTextTextPersonName2);
        stories_text = findViewById(R.id.editTextTextPersonName9);


        Button button = (Button) findViewById(R.id.postdataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder errbuilder = new AlertDialog.Builder(MainActivity.this);
                try {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Building Safety Alert")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("This building's safety is compromised because of it's life threatening factors, please contact the needed authorities.")
                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this,"Selected Option: YES",Toast.LENGTH_SHORT).show();
//                            }
//                        })
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this,"Selected Option: No",Toast.LENGTH_SHORT).show();
                            }
                        });


                    errbuilder.setTitle("Required Fields")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .setMessage("Make sure to Fill all the required Fields.")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this,"Selected Option: YES",Toast.LENGTH_SHORT).show();
//                            }
//                        })
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this,"Selected Option: No",Toast.LENGTH_SHORT).show();
                                }
                            });
                if (ch14.isChecked())
                    lossSite1 = 5;
                if (ch15.isChecked())
                    lossSoil1 = 2;
                if (ch16.isChecked())
                    lossSoil2 = 2;
                if (ch17.isChecked())
                    suit_soil1 = 1;
                if (ch18.isChecked())
                    suit_soil2 = 2;
                if (ch19.isChecked())
                    found1 = 3;
                if (ch20.isChecked())
                    found2 = 1;
                if (ch21.isChecked())
                    found3 = 3;
                if (ch22.isChecked())
                    found4 = 1;
                if (ch23.isChecked())
                    found5 = 2;
                if (ch24.isChecked())
                    plan1 = 5;
                if (ch25.isChecked())
                    plan2 = 3;
                if (ch26.isChecked())
                    plan3 = 5;
                if (ch27.isChecked())
//            Waiting for more info, from bharat
                    elev1 = 0;
                if (ch28.isChecked())
                    elev2 = 5;
                if (ch29.isChecked())
                    elev3 = 3;
                if (ch30.isChecked())
                    elev4 = 5;
                if (ch31.isChecked())
                    elev5 = 5;
                if (ch32.isChecked())
                    elev6 = 5;
                if (ch33.isChecked())
                    elev7 = 5;
                if (ch34.isChecked())
                    elev8 = 40;
                if (ch35.isChecked())
                    door1 = 1;
                if (ch36.isChecked())
                    door2 = 2;
                if (ch37.isChecked())
                    door3 = 4;
                if (ch38.isChecked())
                    door4 = 4;
                if (ch39.isChecked())
                    door5 = 6;
                if (ch40.isChecked())
                    distance1 = 3;
                if (ch41.isChecked())
                    distance2 = 3;
                if (ch42.isChecked())
                    parapets1 = 4;
                if (ch43.isChecked())
//            Waiting for more info, from Bharat
                    parapets2 = 0;
                if (ch44.isChecked())
                    staircases1 = 1;
                if (ch45.isChecked())
                    staircases2 = 1;
                if (ch46.isChecked())
                    staircases3 = 1;
                if (ch47.isChecked())
                    frame1 = 5;
                if (ch48.isChecked())
                    frame2 = 10;
                if (ch49.isChecked())
                    frame3 = 5;
                if (ch50.isChecked())
                    frame4 = 10;
                if (ch51.isChecked())
                    frame5 = 5;
                if (ch52.isChecked())
                    frame6 = 10;
                if (ch53.isChecked())
                    roof1 = 10;
                if (ch54.isChecked())
                    roof2 = 5;
                if (ch55.isChecked())
                    roof3 = 10;
                if (ch56.isChecked())
                    roof4 = 10;
                if (ch57.isChecked())
                    roof_column1 = 10;
                if (ch58.isChecked())
                    member1 = 20;
                if (ch59.isChecked())
                    column1 = 5;
                if (ch60.isChecked())
                    column2 = 15;
                if (ch61.isChecked())
                    struc_staircase1 = 5;
                if (ch62.isChecked())
                    struc_staircase2 = 5;
                if (ch63.isChecked())
                    struc_staircase3 = 10;
                if (ch64.isChecked())
                    tank1 = 4;
                if (ch65.isChecked())
                    materials1 = 10;
                if (ch66.isChecked())
                    materials2 = 5;
                if (ch67.isChecked())
                    materials3 = 10;
                if (ch68.isChecked())
                    materials4 = 5;
                if (ch69.isChecked())
                    workmanship1 = 3;
                if (ch70.isChecked())
                    workmanship2 = 10;
                if (ch71.isChecked())
                    concrete1 = 4;
                if (ch72.isChecked())
                    concrete2 = 4;

//                if(ch1.isChecked() || ch2.isChecked() || ch3.isChecked() || ch4.isChecked() || ch5.isChecked() || ch6.isChecked() || ch7.isChecked() || ch8.isChecked() || ch9.isChecked() || ch10.isChecked() || ch11.isChecked() || ch12.isChecked() || ch13.isChecked()) {
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                }else {


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


                    String spec_text = spectre_text.getText().toString();
                    spectral_val = Double.parseDouble(spec_text);

                    String fsi = fsi_text.getText().toString();
                    fsi_val = Double.parseDouble(fsi);

                    String stories = stories_text.getText().toString();
                    stories_val = Integer.parseInt(stories);

                    spectral_val = Math.min((20/stories_val),2.5);

                    Integer economic_loss_sum = lossSite1 + lossSoil1 + lossSoil2 + suit_soil1 + suit_soil2 + found1 + found2 + found3 + found4 + found5 + plan1 + plan2 + plan3 + elev1 + elev2 + elev3 + elev4 + elev5 + elev6 + elev7 + elev8 + door1 + door2 + door3 + door4 + door5 + distance1 + distance2 + parapets1 + parapets2 + staircases1 + staircases2 + staircases3 + frame1 + frame2 + frame3 + frame4 + frame5 + frame6 + roof1 + roof2 + roof3 + roof4 + roof_column1 + member1 + column1 + column2 + struc_staircase1 + struc_staircase2 + struc_staircase3 + tank1 + materials1 + materials2 + materials3 + materials4 + workmanship1 + workmanship2 + concrete1 + concrete2;

                    economic_loss = economic_loss_sum / 100.0;

                    Double hazard_val = spectral_val * soil_val * z_val;
                    Double exposure_val = imp_val * fsi_val;

                    String hazard_string = String.format("Hazard Value is: %f", hazard_val);
                    String exposure_string = String.format("Exposure Value is: %f", exposure_val);
                    String vulner_string = String.format("Economic Loss Inducing Factors Value is: %f", economic_loss);
                    if (ch1.isChecked() || ch2.isChecked() || ch3.isChecked() || ch4.isChecked() || ch5.isChecked() || ch6.isChecked() || ch7.isChecked() || ch8.isChecked() || ch9.isChecked() || ch10.isChecked() || ch11.isChecked() || ch12.isChecked() || ch13.isChecked()) {
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Toast.makeText(getApplicationContext(), hazard_string, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), exposure_string, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), vulner_string, Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    AlertDialog dialog = errbuilder.create();
                    dialog.show();
                }

            }
        });
    }}
