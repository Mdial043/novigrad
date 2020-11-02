package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private Button S;
    private Button SeeS;
    private Button DeleteS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin );
            S = (Button) findViewById( R.id.servicebtn );
            SeeS = (Button) findViewById( R.id.seeServicebtn);
            DeleteS = (Button) findViewById( R.id.succursalebtn);
            S.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {  Intent intent = new Intent(AdminActivity.this,TotalService.class );
                    startActivity(intent);}
            } );
            SeeS.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) { Intent intent = new Intent(AdminActivity.this,SeeSeriveActivity.class );
                    startActivity(intent); }
            } );
            DeleteS.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) { Intent intent = new Intent(AdminActivity.this,DeleteActivity.class );
                    startActivity(intent); }
            } );
    }
}