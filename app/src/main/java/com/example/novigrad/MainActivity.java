package com.example.novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView register;
    private FirebaseAuth firebaseAuth;//IMPORT LIBRARY
    private  FirebaseAuth.AuthStateListener AuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Name = (EditText) findViewById( R.id.etName );
        Password = (EditText) findViewById( R.id.etPassword );
        Info = (TextView) findViewById( R.id.tinfo );
        Login = (Button) findViewById( R.id.btnLogin );
        register= (TextView)findViewById( R.id.tregister );
        Info.setText( "Attempt 5" );
       firebaseAuth=FirebaseAuth.getInstance();//Instanciation du firebase
       AuthListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser User = firebaseAuth.getCurrentUser();//Check if the user has already logged in the fire base
                           if (User != null)
                {
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity( intent);
                    finish();
                }
                else{Toast.makeText( MainActivity.this,"Log in failed", Toast.LENGTH_SHORT ).show();}

            }
        };


        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid( Name.getText().toString(), Password.getText().toString() );

            }
        } );
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class );
                startActivity(intent);
            }
        } );
    }

    private void valid(String userName, String userPassword)
    {
       firebaseAuth.signInWithEmailAndPassword( userName, userPassword ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {//check if task succesful
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (userName.equals( "Admin" )&&userPassword.equals( "12345" ))
               {
                   Toast.makeText( MainActivity.this,"Operation successed", Toast.LENGTH_SHORT ).show();

                   startActivity( new Intent(MainActivity.this, AdminActivity.class) );
            }
               else if(task.isSuccessful())
               {
                   Toast.makeText( MainActivity.this,"Operation successed", Toast.LENGTH_SHORT ).show();

                   startActivity( new Intent(MainActivity.this, SecondActivity.class) );
               }
               else {
                   Toast.makeText( MainActivity.this,"Operation falied", Toast.LENGTH_SHORT ).show();
                   counter--;
               }
             //  if (counter==0){Login.setEnabled( false ); Info.setText( "Remaining"+counter );}
           }
       } );
    }
}