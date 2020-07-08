package com.app.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.ecommerce.api.ApiUser;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ImageView btnBack;
    Button btnLogin, btnRegister;
    TextInputEditText txtName, txtEmail, txtPassword, txtPasswordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inputs
        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtPasswordConfirmation = findViewById(R.id.passwordConfirmation);

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

        // (Refactoring)
        final String name, email, password, password_confirmation;
        name = Objects.requireNonNull(txtName.getText()).toString();
        email = txtName.getText().toString();
        password = txtName.getText().toString();
        password_confirmation = txtName.getText().toString();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // (Refactoring)
                // Crear un Objeto Usuario
                // Validaciones con el UserController
                // Petición API
                ApiUser.registerUser(RegisterActivity.this, name, email, password, password_confirmation);
            }
        });

    }
}