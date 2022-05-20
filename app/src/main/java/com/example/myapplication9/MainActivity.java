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

    private EditText spectre_text, fsi_text;
    private Spinner spinner, zone_spinner, ground_spinner,  importance_spinner, site_spinner,loss_spinner;
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15,ch16;


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

                saveData(createRequest());

            }
        });
    }
    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();


//        if(ch1.isChecked())
//            ch1_val = "True";
//        if(ch2.isChecked())
//            ch2_val = "True";
//        if(ch3.isChecked())
//            ch3_val = "True";
//        if(ch4.isChecked())
//            ch4_val = "True";
//        if(ch5.isChecked())
//            ch5_val = "True";
//        if(ch6.isChecked())
//            ch6_val = "True";
//        if(ch7.isChecked())
//            ch7_val = "True";
//        if(ch8.isChecked())
//            ch8_val = "True";
//        if(ch9.isChecked())
//            ch9_val = "True";
//        if(ch10.isChecked())
//            ch10_val = "True";
//        if(ch11.isChecked())
//            ch11_val = "True";
//        if(ch12.isChecked())
//            ch12_val = "True";
//        if(ch13.isChecked())
//            ch13_val = "True";
//        if(ch14.isChecked())
//            ch14_val = "True";
//        if(ch15.isChecked())
//            ch15_val = "True";
//        if(ch16.isChecked())
//            ch16_val = "True";

        userRequest.setCollateral_damage(col_data);
        userRequest.setZone_factor(zone_data);
        userRequest.setGround_shaking(soil_data);
        userRequest.setImportance_i(imp_data);
        userRequest.setSpectre_shape(spectre_text.getText().toString());
        userRequest.setFsi(fsi_text.getText().toString());
        userRequest.setSite1(ch1.isChecked());
        userRequest.setSite2(ch2.isChecked());
        userRequest.setSite3(ch3.isChecked());
        userRequest.setSoil1(ch4.isChecked());
        userRequest.setSoil2(ch5.isChecked());
        userRequest.setArch1(ch6.isChecked());
        userRequest.setArch2(ch7.isChecked());
        userRequest.setStrc1(ch8.isChecked());
        userRequest.setStrc2(ch9.isChecked());
        userRequest.setStrc3(ch10.isChecked());
        userRequest.setCon1(ch11.isChecked());
        userRequest.setCon2(ch12.isChecked());
        userRequest.setCon3(ch13.isChecked());
        userRequest.setLossSite1(ch14.isChecked());
        userRequest.setLossSoil1(ch15.isChecked());
        userRequest.setLossSoil2(ch16.isChecked());
//        userRequest.setSpectre_shape(spectre_text);

        return userRequest;
    }

    public void saveData(UserRequest userRequest){

        Call<UserResponse> userResponseCall = AppClient.getUserService().saveUsers(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                System.out.println(response.code());
                System.out.println(response.body());

                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Saved Succssfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, response.code(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Request Failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
//    private void postDataUsingVolley(String name) {
//        // url to post our data
//        String url = "https://webhook.site/6a1a0260-39a7-4eb8-a071-0857480a1bfd";
//
//        // creating a new variable for our request queue
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//
//        // on below line we are calling a string
//        // request method to post the data to our API
//        // in this we are calling a post method.
//        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // inside on response method we are
//                // hiding our progress bar
//                // and setting data to edit text as empty
//
//
//                // on below line we are displaying a success toast message.
//                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
//                try {
//                    // on below line we are parsing the response
//                    // to json object to extract data from it.
//                    JSONObject respObj = new JSONObject(response);
//
//                    // below are the strings which we
//                    // extract from our json object.
//                    String name = respObj.getString("name");
//
//                    // on below line we are setting this string s to our text view.
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // method to handle errors.
//                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                // below line we are creating a map for
//                // storing our values in key and value pair.
//                Map<String, String> params = new HashMap<String, String>();
//
//                // on below line we are passing our key
//                // and value pair to our parameters.
//                params.put("name", name);
//
//                // at last we are
//                // returning our params.
//                return params;
//            }
//        };
//        // below line is to make
//        // a json object request.
//        queue.add(request);
//    }
//}
//
