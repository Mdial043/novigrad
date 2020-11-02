package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TotalService extends AppCompatActivity {
    private Button creer;
    private  Button modifier;
    private  Button supprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_total_service );
        creer=(Button)findViewById( R.id.cbtn );
        modifier=(Button)findViewById( R.id.modbtn );
        supprimer=(Button)findViewById( R.id.delbtn );
        creer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalService.this,ModifyService.class);
                startActivity( intent);
            }
        } );
        modifier.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalService.this,changerService.class);
                startActivity( intent);

            }
        } );
        supprimer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalService.this,deleteservice.class);
                startActivity( intent);
            }
        } );
    }
}