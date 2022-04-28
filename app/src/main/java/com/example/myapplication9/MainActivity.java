package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

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
    private Spinner spinner, zone_spinner, ground_spinner,  importance_spinner, life_spinner,loss_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colateral_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        zone_spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.zone_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zone_spinner.setAdapter(adapter2);

        ground_spinner = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.ground_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ground_spinner.setAdapter(adapter3);

        importance_spinner = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.importance_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importance_spinner.setAdapter(adapter4);

        life_spinner = findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.life_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        life_spinner.setAdapter(adapter5);

        loss_spinner = findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.loss_factors, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loss_spinner.setAdapter(adapter6);

        spectre_text = findViewById(R.id.editTextTextPersonName4);
        fsi_text = findViewById(R.id.editTextTextPersonName2);



        Button button = (Button) findViewById(R.id.postdataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData(createRequest());
//                postDataUsingVolley(text);
            }
        });
    }
    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();

        userRequest.setCollateral_damage(spinner.getSelectedItem().toString());
        userRequest.setZone_factor(zone_spinner.getSelectedItem().toString());
        userRequest.setGround_shaking(ground_spinner.getSelectedItem().toString());
        userRequest.setImportance_i(importance_spinner.getSelectedItem().toString());
        userRequest.setLife_factor(life_spinner.getSelectedItem().toString());
        userRequest.setLoss_factor(loss_spinner.getSelectedItem().toString());
        userRequest.setSpectre_shape(spectre_text.getText().toString());
        userRequest.setFsi(fsi_text.getText().toString());
//        userRequest.setSpectre_shape(spectre_text);

        return userRequest;
    }

    public void saveData(UserRequest userRequest){

        Call<UserResponse> userResponseCall = AppClient.getUserService().saveUsers(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Saved Succssfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Request Failed",Toast.LENGTH_LONG).show();
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
