package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class PiggyBankActivity extends AppCompatActivity {

    private TextView textViewpiggybalance1,textViewAvailableBalance;
    private EditText editTextNumberDecimal;
    private Button buttonAdd,buttonRemove;
    private String balance,piggybankbalance;
    private float piggyBankBalanceFloat,availableBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the Intent that started this activity
        Intent intent = getIntent();
        // Get the variable from the Intent
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        setContentView(R.layout.activity_piggy_bank);
        Toolbar toolbar = findViewById(R.id.toolbar);
        textViewpiggybalance1 = findViewById(R.id.textViewpiggybalance1);
        textViewAvailableBalance = findViewById(R.id.textViewAvailableBalance);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonRemove = findViewById(R.id.buttonRemove);
        buttonAdd.setEnabled(false);
        buttonRemove.setEnabled(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextNumberDecimal.getText().toString())){
                    float editvalue = Float.parseFloat(editTextNumberDecimal.getText().toString());
                    float newPiggyBalanceValue= piggyBankBalanceFloat + editvalue;
                    updateUserData(userPhoneNumber, String.valueOf(newPiggyBalanceValue));
                    editTextNumberDecimal.setText("");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Query query = FirebaseDatabase.getInstance().getReference("users")
                                    .orderByChild("phone_number").equalTo(userPhoneNumber);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        // Iterate through the results (there may be multiple if multiple records match)
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            balance = childSnapshot.child("balance").getValue().toString();
                                            piggybankbalance = childSnapshot.child("piggybalance").getValue().toString();
                                        }
                                    }
                                    float balanceFloat = Float.parseFloat(balance);
                                    piggyBankBalanceFloat = Float.parseFloat(piggybankbalance);
                                    availableBalance = balanceFloat-piggyBankBalanceFloat;
                                    textViewAvailableBalance.setText("Available balance: " + availableBalance +" €");
                                    textViewpiggybalance1.setText("PiggyBank balance: " + piggybankbalance +" €");
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Handle any errors that occur during the query
                                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                         }
                    }, 500);
                }
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextNumberDecimal.getText().toString())){
                    float editvalue = Float.parseFloat(editTextNumberDecimal.getText().toString());
                    float newPiggyBalanceValue= piggyBankBalanceFloat - editvalue;
                    updateUserData(userPhoneNumber, String.valueOf(newPiggyBalanceValue));
                    editTextNumberDecimal.setText("");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Query query = FirebaseDatabase.getInstance().getReference("users")
                                    .orderByChild("phone_number").equalTo(userPhoneNumber);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        // Iterate through the results (there may be multiple if multiple records match)
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            balance = childSnapshot.child("balance").getValue().toString();
                                            piggybankbalance = childSnapshot.child("piggybalance").getValue().toString();
                                        }
                                    }
                                    float balanceFloat = Float.parseFloat(balance);
                                    piggyBankBalanceFloat = Float.parseFloat(piggybankbalance);
                                    availableBalance = balanceFloat-piggyBankBalanceFloat;
                                    textViewAvailableBalance.setText("Available balance: " + availableBalance +" €");
                                    textViewpiggybalance1.setText("PiggyBank balance: " + piggybankbalance +" €");
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Handle any errors that occur during the query
                                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }, 500);
                }
            }

        });

        if (!userPhoneNumber.isEmpty()){
            Query query = FirebaseDatabase.getInstance().getReference("users")
                    .orderByChild("phone_number").equalTo(userPhoneNumber);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Iterate through the results (there may be multiple if multiple records match)
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            balance = childSnapshot.child("balance").getValue().toString();
                            piggybankbalance = childSnapshot.child("piggybalance").getValue().toString();
                        }
                    }
                    float balanceFloat = Float.parseFloat(balance);
                    piggyBankBalanceFloat = Float.parseFloat(piggybankbalance);
                    availableBalance = balanceFloat-piggyBankBalanceFloat;
                    textViewAvailableBalance.setText("Available balance: " + availableBalance +" €");
                    textViewpiggybalance1.setText("PiggyBank balance: " + piggybankbalance +" €");
                    editTextNumberDecimal.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            // Not needed for this example
                        }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                            if (!TextUtils.isEmpty(editTextNumberDecimal.getText().toString())){
                                float editValue = Float.parseFloat(editTextNumberDecimal.getText().toString());
                                if (editValue > 0.00 && editValue <= availableBalance){
                                    buttonAdd.setEnabled(true);
                                }
                                else{
                                    buttonAdd.setEnabled(false);
                                }
                                if (editValue > 0.00 && editValue <= piggyBankBalanceFloat){
                                    buttonRemove.setEnabled(true);
                                }
                                else{
                                    buttonRemove.setEnabled(false);
                                }
                            }
                            else{
                                buttonAdd.setEnabled(false);
                                buttonRemove.setEnabled(false);
                            }
                        }
                    });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle any errors that occur during the query
                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateUserData(String userPhoneNumber, String balancepiggybank) {
        Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild("phone_number").equalTo(userPhoneNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Iterate through the results (there may be multiple if multiple records match)
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Update the specific field you want to modify
                        childSnapshot.getRef().child("piggybalance").setValue(balancepiggybank);
                        // You can replace "fieldNameToUpdate" with the actual field name you want to update
                    }
               } else {
                    // Handle the case where no matching record is found
                    Toast.makeText(getApplicationContext(), "No matching record found", Toast.LENGTH_SHORT).show();
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