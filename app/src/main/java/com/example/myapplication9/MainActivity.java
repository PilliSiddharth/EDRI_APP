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

    EditText spectre_text, fsi_text;
    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15,ch16,ch17;

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
    int z_ans = 0;

    TextView soiltextView;
    boolean[] selectedsoil;
    ArrayList<Integer> soil_list = new ArrayList<>();
    ArrayList<String> soil_data = new ArrayList<>();
    String[] soil_array = {"Hard Rock", "Medium Soil", "Soft Soil"};
    int s_ans = 0;

    TextView imptextView;
    boolean[] selectedimp;
    ArrayList<Integer> imp_list = new ArrayList<>();
    ArrayList<String> imp_data = new ArrayList<>();
    String[] imp_array = {"Residence", "Office", "Commercial"};
    int i_ans = 0;


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

                builder.setSingleChoiceItems(zone_array, -1, new DialogInterface.OnClickListener() {
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
                        for (int j = 0; j < selectedzone.length; j++) {
                            selectedzone[j] = false;
                            zone_list.clear();
                            zonetextView.setText("");
                        }
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

                builder.setSingleChoiceItems(soil_array, -1, new DialogInterface.OnClickListener() {
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
                        for (int j = 0; j < selectedsoil.length; j++) {
                            selectedsoil[j] = false;
                            soil_list.clear();
                            soiltextView.setText("");
                        }
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

                builder.setSingleChoiceItems(imp_array, -1, new DialogInterface.OnClickListener() {
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
                        for (int j = 0; j < selectedimp.length; j++) {
                            selectedimp[j] = false;
                            imp_list.clear();
                            imptextView.setText("");
                        }
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

//        System.out.println(ch1_val);

        spectre_text = findViewById(R.id.editTextTextPersonName4);
        fsi_text = findViewById(R.id.editTextTextPersonName2);


        Button button = (Button) findViewById(R.id.postdataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                System.out.println(col_data);
//                System.out.println(zone_data);
//                System.out.println(soil_data);
//                System.out.println(imp_data);
//                System.out.println(ch1.isChecked());
//                System.out.println(ch2_val);
//                System.out.println(ch3_val);
//                System.out.println(ch4_val);
//                System.out.println(ch5_val);
//                System.out.println(ch6_val);

//                saveData(createRequest());

            }
        });
    }}
