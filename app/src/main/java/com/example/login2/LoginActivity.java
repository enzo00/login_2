package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;
    EditText txtEmail,txtpass;
    TextView txthelp,txtloginactivity;
    ArrayList<Users> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbtn=findViewById(R.id.btn_login);
        txtEmail=findViewById(R.id.txt_email);
        txtpass=findViewById(R.id.txt_pass);
        txthelp=findViewById(R.id.txt_help);
        txtloginactivity=findViewById(R.id.txt_registeractivity);
        Users usr1=new Users("abc@gmail.com","12345678");
        Users usr2=new Users("xyz@gmail.com","12345678");
        Users usr3=new Users("abcxyz@gmail.com","password");
        Users usr4=new Users("decoder@gmail.com","password123");
        arrayList.add(usr1);
        arrayList.add(usr2);
        arrayList.add(usr3);
        arrayList.add(usr4);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtEmail.getText().toString().trim();
                String pass=txtpass.getText().toString().trim();
                for(Users user:arrayList){
                    if(user.getEmail().equals(email)){
                        if(user.getPass().equals(pass)){
                            savedata(email);
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Incorrect password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Toast.makeText(LoginActivity.this,"Incorrect id and password",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void savedata(String email){
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("logincounter",true);
        editor.putString("useremail",email);
        editor.apply();
        startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
        finish();
    }
}