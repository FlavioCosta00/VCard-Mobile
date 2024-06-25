package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    //from activity_main.xml
    private Button createButton;
    private EditText editPhoneNumber,editPassword ,editDigitCode;
    //from login.xml
    private Button loginButton;
    private EditText editDigitCodeLogin;
    //Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get a reference to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("authenticationStorage",0);
        String userPhoneNumber = sharedPreferences.getString("userPhoneNumber", "");
        if (!userPhoneNumber.isEmpty()) {
            setContentView(R.layout.login);
            loginButton = findViewById(R.id.buttonLogin);
            editDigitCodeLogin = findViewById(R.id.editDigitCodeLogin);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pin = editDigitCodeLogin.getText().toString();
                    if (pin.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill the digit code", Toast.LENGTH_SHORT).show();
                    } else {
                        Query query = FirebaseDatabase.getInstance().getReference("users")
                                .orderByChild("phone_number").equalTo(userPhoneNumber);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // Iterate through the results (there may be multiple if multiple records match)
                                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                        String pinFireBase = childSnapshot.child("pin").getValue().toString();
                                        if (pin.equals(pinFireBase)) {
                                            Toast.makeText(MainActivity.this,"Logged with success !",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                            intent.putExtra("userPhoneNumber", userPhoneNumber);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "PIN is invalid", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                // Handle any errors that occur during the query
                                Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else {
            setContentView(R.layout.activity_main);
            editPhoneNumber = findViewById(R.id.editPhoneNumber);
            editPassword = findViewById(R.id.editPassword);
            editDigitCode = findViewById(R.id.editPin);
            createButton = findViewById(R.id.buttonCreate);
            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = editPhoneNumber.getText().toString();
                    String password = editPassword.getText().toString();
                    String pin = editDigitCode.getText().toString();
                    if (phoneNumber.isEmpty() || password.isEmpty() || pin.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild("phone_number").equalTo(phoneNumber);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                 @Override
                                                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                     if (dataSnapshot.exists()) {
                                                                         for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                                                             String pinFireBase = childSnapshot.child("pin").getValue().toString();
                                                                             String passwordFireBase = childSnapshot.child("password").getValue().toString();
                                                                             if (pin.equals(pinFireBase) && password.equals(passwordFireBase)) {
                                                                                 Toast.makeText(MainActivity.this,"Logged with success !",Toast.LENGTH_SHORT).show();
                                                                                 //Get a reference to SharedPreferences
                                                                                 SharedPreferences sharedPreferences = getSharedPreferences("authenticationStorage",0);
                                                                                 // Get an editor to edit the SharedPreferences
                                                                                 SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                                 // Get the phone number entered by the user (replace "userPhoneNumber" with your variable)
                                                                                 String userPhoneNumber = editPhoneNumber.getText().toString(); // Replace with the actual phone number
                                                                                 // Store the phone number in SharedPreferences
                                                                                 editor.putString("userPhoneNumber", userPhoneNumber);
                                                                                 editor.apply();
                                                                                 Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                                                                 intent.putExtra("userPhoneNumber", userPhoneNumber);
                                                                                 startActivity(intent);
                                                                             }
                                                                             else {
                                                                                 Toast.makeText(getApplicationContext(), "PIN or Password is invalid", Toast.LENGTH_SHORT).show();
                                                                             }
                                                                         }
                                                                     } else {
                                                                         createCard();
                                                                     }
                                                                 }

                                                                 @Override
                                                                 public void onCancelled(@NonNull DatabaseError databaseError) {
                                                                     Toast.makeText(MainActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }
                        );
                    }
                }
            });
        }
    }
    public void createCard(){
        Map<String,Object> map = new HashMap<>();
        map.put("phone_number",editPhoneNumber.getText().toString());
        map.put("password", editPassword.getText().toString());
        map.put("pin", editDigitCode.getText().toString());
        map.put("balance", "500.00");
        map.put("piggybalance", "100.00");
        map.put("notifications", "no");
        map.put("autosavings", "no");
        FirebaseDatabase.getInstance().getReference().child("users").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Get a reference to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("authenticationStorage",0);
                        // Get an editor to edit the SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        // Get the phone number entered by the user (replace "userPhoneNumber" with your variable)
                        String userPhoneNumber = editPhoneNumber.getText().toString(); // Replace with the actual phone number
                        // Store the phone number in SharedPreferences
                        editor.putString("userPhoneNumber", userPhoneNumber);
                        editor.apply();
                        Toast.makeText(MainActivity.this,"Card created sucessfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("userPhoneNumber", userPhoneNumber);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Error creating card",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}