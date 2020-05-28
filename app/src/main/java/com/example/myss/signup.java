package com.example.myss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    FirebaseAuth mauth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();
    DatabaseReference ref=reference.child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText validation=(EditText)findViewById(R.id.password);
        Button signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name=name.getText().toString();
                final String Email=email.getText().toString();
                String Validation=validation.getText().toString();
                mauth.createUserWithEmailAndPassword(Email,Validation)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user User=new user(Name,Email);
                                    ref.push().setValue(User);
                                    Toast.makeText(signup.this,"registration sucessfull",Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(signup.this,"registration failed",Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });
    }
}
