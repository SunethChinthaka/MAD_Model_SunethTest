package com.example.mad_model_paper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mad_model_paper.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,password;
    Button add,updateProfile;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username=findViewById(R.id.etUserNamePM);
        dob=findViewById(R.id.etDobPM);
        password=findViewById(R.id.etPasswordPM);
        add=findViewById(R.id.btnAdd);
        updateProfile=findViewById(R.id.btnUpdateProfile);
        male=findViewById(R.id.radioButton);
        female=findViewById(R.id.radioButton2);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (male.isChecked()){
                    gender="Male";
                }
                else {
                    gender="Female";
                }
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                long newID=dbHelper.addInfo(username.getText().toString(), dob.getText().toString(), password.getText().toString(), gender);
                Toast.makeText(ProfileManagement.this, "User Added. User ID:"+newID, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),Home.class);
                startActivity(intent);

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);
            }
        });

    }
}
