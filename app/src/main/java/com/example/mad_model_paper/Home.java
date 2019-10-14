package com.example.mad_model_paper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_model_paper.Database.DBHelper;

public class Home extends AppCompatActivity {

    EditText username,password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username=findViewById(R.id.etUserNameH);
        password=findViewById(R.id.etPasswordH);
        login=findViewById(R.id.btnLoginH);
        register=findViewById(R.id.btnRegisterH);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pass=password.getText().toString();

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                Boolean validUser=dbHelper.checkUser(name,pass);


                if (name.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields Cannot be Empty!!!", Toast.LENGTH_SHORT).show();

                }
                else if (validUser==true){
                    Toast.makeText(getApplicationContext(), " Login Successful", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Home.this,EditProfile.class);
                    i.putExtra("name",name);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid User!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
