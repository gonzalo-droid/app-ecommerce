package com.app.ecommerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.app.ecommerce.R;
import com.app.ecommerce.api.RegistroRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ImageView btnBack;
    Button btnLogin, btnRegister;
    TextInputLayout txtName, txtEmail, txtPassword, txtPasswordConfirmation;
    RegistroRequest loginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inputs
        txtName = findViewById(R.id.contentName);
        txtEmail = findViewById(R.id.contentEmail);
        txtPassword = findViewById(R.id.contentPassword);
        txtPasswordConfirmation = findViewById(R.id.contentPasswordConfirmation);

        // Buttons
        //btnBack = findViewById(R.id.imgBack);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Objects.requireNonNull(txtName.getEditText().getText()).toString().trim();
                String email = txtName.getEditText().getText().toString().trim();
                String password = txtName.getEditText().getText().toString().trim();
                String password_confirmation = txtName.getEditText().getText().toString().trim();


                Log.d("tagito",name+"--"+email+"--"+password+"--"+password_confirmation);
                Response.Listener<String> respuesta = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonRespuesta = new JSONObject(response);
                            String ok = jsonRespuesta.getString("message");

                            Toast.makeText(RegisterActivity.this,"id: "+ok,Toast.LENGTH_SHORT).show();
                            /*) if (ok.equals("")) {
                                //getCarga();
                                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                                alert.setMessage("Los datos son inválidos")
                                        .setNegativeButton("Reintentar",null)
                                        .create()
                                        .show();
                            }else{

                                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                                alert.setMessage("Los datos son inválidos")
                                        .setNegativeButton("Reintentar",null)
                                        .create()
                                        .show();
                            }*/


                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }
                };

                loginRequest = new RegistroRequest(name, email, password, password_confirmation,respuesta);
                RequestQueue cola = Volley.newRequestQueue(RegisterActivity.this);
                cola.add(loginRequest);
            }
        });

    }
}