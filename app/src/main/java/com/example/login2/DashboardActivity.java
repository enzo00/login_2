package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    TextView txtemail;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        txtemail=findViewById(R.id.txt_email);
        logout=findViewById(R.id.btnlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkuserstatus();
    }
    void checkuserstatus(){
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        Boolean counter=sharedPreferences.getBoolean("logincounter",Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
        if(counter){
            String email=sharedPreferences.getString("useremail",String.valueOf(MODE_PRIVATE));
            txtemail.setText(email);
        }
        else{
            startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
            finish();
        }
    }
}