package com.example.novigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmployeeActivity extends AppCompatActivity {
    private EditText userName,userPassword,userEmail,userMNumber;
    private Button regButton;
    private  FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.employeeactivity );
        setupUIViews();
        firebaseAuth=FirebaseAuth.getInstance();
        regButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
               if (valid())
               {
                   //DATA BASE
                   String user_email= userEmail.getText().toString().trim();
                   String user_password= userPassword.getText().toString().trim();
                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if (task.isSuccessful()){Toast.makeText( EmployeeActivity.this,"Registrstion succesful",Toast.LENGTH_SHORT ).show();
                           startActivity( new Intent(EmployeeActivity.this,MainActivity.class)); }
                           else {Toast.makeText( EmployeeActivity.this,"Regtrstion failed",Toast.LENGTH_SHORT ).show();}
                       }
                   } );
                }

            }
        });
    }


    private void setupUIViews()
    {
        userName=(EditText)findViewById( R.id.tName);
        userPassword=(EditText)findViewById( R.id.tPassword );
        userEmail=(EditText)findViewById( R.id.tEmail );
        userMNumber=(EditText)findViewById( R.id.tMNumber );
        regButton=(Button)findViewById( R.id.btnRegistrer );
    }
    private Boolean valid(){
        Boolean result = false;
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String Email = userPassword.getText().toString();
        String MNumber = userMNumber.getText().toString();
        if(name.isEmpty() || password.isEmpty() || Email.isEmpty() || MNumber.isEmpty())
        {
            Toast.makeText(this,"Enter the right informations", Toast.LENGTH_SHORT).show();
        }
        else
        {return true;}
        return result;
    }
}