package com.harelmallac.edendale.database;


import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.harelmallac.edendale.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequest {
    public void getDataFromMiddleware(final Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://192.168.85.141:8088/users";
        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<UserModel> userList =new ArrayList<UserModel>();
            DataBaseHelper dB= new DataBaseHelper(context);
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++)
                    {
                        JSONObject obj = response.getJSONObject(i);
                        String id = obj.getString("userId");
                        String password = obj.getString("password");
                        String salesRepId = obj.getString("salesRepId");
                        String salesSiteId = obj.getString("salesSiteId");
                        String role = obj.getString("role");
                        String username = obj.getString("username");
                        String bank = obj.getString("bank");
                        Boolean active = obj.getBoolean("active");
                        String mainsite = obj.getString("mainsite");

                        UserModel user = new UserModel(id,password,salesRepId,salesSiteId,role,username,bank,active,mainsite);
                        dB.addOne(user);
                        userList.add(user);


                        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                        Log.e("Id",id+"");

                    }


                    //String userId = response.getString("userId");
                    //Log.e("response", userId);

                   // String color = obj.getString("colorName");
                    //String desc = obj.getString("description");

                    //results.setText(data);
                }
                // Try and catch are included to handle any errors due to JSON
                catch (JSONException e) {
                    Log.e("error", e.toString());
                    // If an error occurs, this prints the error to the log
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
                //Toast.makeText(MainActivity.this, "onErroe", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","edend@leapp","edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(objectRequest);
    }
}
