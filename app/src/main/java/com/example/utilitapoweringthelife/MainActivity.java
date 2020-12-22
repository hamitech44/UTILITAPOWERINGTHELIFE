package com.example.utilitapoweringthelife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import AdaptersPackage.StatusRecyclerAdapter;
import ModelClass.Status;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Status> statuses;
    private StatusRecyclerAdapter statusRecyclerAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue= Volley.newRequestQueue(this);
        statuses= new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        initRecyclerView();

    }


    //set up the recycler view
    private void initRecyclerView(){

        gridLayoutManager= new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        statusRecyclerAdapter= new StatusRecyclerAdapter(statuses,MainActivity.this);
        recyclerView.setAdapter(statusRecyclerAdapter);
getDataFromUtilityApi();
    }

    private void getDataFromUtilityApi(){

                String url ="http://private-176645-utilita.apiary-mock.com/status";
        StringRequest request= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //to get whole object
                    JSONObject jsonObject = new JSONObject(response);
                    //to get base  objects
                    JSONArray arrayKeyName = jsonObject.names();
                    //loop for base  objects
                    for (int i = 0; i < arrayKeyName.length(); i++) {
                        String Keys = arrayKeyName.getString(i);
                        //to get nested objects
                        JSONObject nestedObject = jsonObject.getJSONObject(Keys);
                        JSONArray nestedKeyNames = nestedObject.names();
                        //loop through the nested objects
                        for (int j = 0; j < nestedKeyNames.length(); j++) {

                            String nestedKey = nestedKeyNames.getString(j);
                            JSONObject nestedObjectData = nestedObject.getJSONObject(nestedKey);
                            statuses.add(new Status(nestedKey,
                                    nestedObjectData.getString("url"),
                                    nestedObjectData.getString("responseCode"),
                                    nestedObjectData.getString("responseTime"),
                                    nestedObjectData.getString("class")));
                            statusRecyclerAdapter.notifyDataSetChanged();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
mQueue.add(request);
    }


}