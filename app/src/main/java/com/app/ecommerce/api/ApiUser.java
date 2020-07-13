package com.app.ecommerce.api;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ApiUser {

    // Routes User API
    private static final String URL_REGISTER_USER = "https://www.codecix.com/api/ecommerce/registro";

    // Register User (Refactoring)
    public static void registerUser(AppCompatActivity activity, final String name, final String email,
                                    final String password, final String password_confirmation) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE", response);
                        //Gson gson = new Gson();
                        //City city = gson.fromJson(response, City.class);
                        //Log.d("RESPONSE MAP", city.getName());
                        //drawData(city);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RESPONSE", "Chale"+error);
                //Toast.makeText(WheaterCityActivity.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("password_confirmation", password_confirmation);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

}
