package com.example.novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeeSeriveActivity extends AppCompatActivity {
        DatabaseReference databaseService;
        ListView listViewService;
        List<Service> serviceList;
        List<Service>u = new ArrayList<>();
        TextView clc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_see_serive );
        listViewService=(ListView)findViewById(R.id.ListViewService  );
        serviceList= new ArrayList<>();
        clc = (TextView) findViewById( R.id.clc );
        u = ModifyService.ListeDeService;
        databaseService= FirebaseDatabase.getInstance().getReference("Services0");
        //ModifyService.update(ModifyService.ListeDeService);
        ServiceList adapter = new ServiceList( SeeSeriveActivity.this, u);
        listViewService.setAdapter( adapter );
        listViewService.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service=serviceList.get(position);

            }
        } );


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseService.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot serviceSnapshot: snapshot.getChildren() )
                {
                    Service service = serviceSnapshot.getValue(Service.class);
                    serviceList.add( service );
                }
             /*   ServiceList adapter = new ServiceList( SeeSeriveActivity.this, serviceList );
                listViewService.setAdapter( adapter );*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }

}
