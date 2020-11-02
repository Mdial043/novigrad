package com.example.novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class changerService extends AppCompatActivity {
    DatabaseReference databaseService;
    ListView listViewService;
    List<Service> serviceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer_service );
        listViewService=(ListView)findViewById(R.id.ListViewService  );
        serviceList= new ArrayList<>();
        serviceList = ModifyService.ListeDeService;
        ServiceList adapter = new ServiceList( changerService.this, serviceList );

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
               ServiceList adapter = new ServiceList( changerService.this, serviceList );
                listViewService.setAdapter( adapter );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
       listViewService.setOnItemClickListener( new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Service service=serviceList.get(position);
               update(service.serviceId,service.serviceName, service.documents,service.formulaireRequis );
           }
       } );
    }
    private  void update(String servceId, String serviceName, String Documents, String Formulaire)
    {
        AlertDialog.Builder dialogBuilder= new AlertDialog.Builder( this );
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate( R.layout.update_dialog, null );
        dialogBuilder.setView( dialogView );
        final EditText textViewName = (EditText) dialogView.findViewById( R.id.newview );
        final Button buttonUpdate = (Button) dialogView.findViewById( R.id.upbtn );

        dialogBuilder.setTitle( "Update" + serviceName );

       final  AlertDialog alertDialog= dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = textViewName.getText().toString().trim();
            if (TextUtils.isEmpty( name ))
            {
                textViewName.setError( "Champ invalide" );
                return;
            }
            updateservie( servceId, serviceName,Documents,Formulaire);
            alertDialog.dismiss();
            }
        } );

    }

    private  boolean updateservie(String id, String name, String documents,String formulaire)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Service").child( id );
        Service service = new Service(id,name,documents,formulaire);
        databaseReference.setValue( service);
        Toast.makeText( changerService.this,"Update succesful",Toast.LENGTH_LONG ).show();
        return true ;
    }
    }
