package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {
    private Button Employee;
    private Button Client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );
      Employee = (Button) findViewById( R.id.btnEmployee );
       Client = (Button) findViewById( R.id.btnClient);
        Employee.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {  Intent intent = new Intent(RegistrationActivity.this,EmployeeActivity.class );
                startActivity(intent);}
        } );
       Client.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) { Intent intent = new Intent(RegistrationActivity.this,ClientActivity.class );
                startActivity(intent); }
        } );
    }


}