package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {
    private ImageButton menuItemHome, menuItemSettings,menuItemTransactions, menuItemNotifications;
    private Button buttonDeleteCard, buttonCancelDelete, buttonConfirmDelete;
    private String balance, pin,notifications,autosavings;
    private Switch switchNotifications,switchAutoSavings;
    private TextView textView7, textView11,textView21,textView22;
    private CheckBox checkBox;
    private EditText editTextPinDeleteCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Get the variable from the Intent
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        setContentView(R.layout.activity_settings);
        SharedPreferences sharedPreferences = getSharedPreferences("authenticationStorage", 0);
        switchNotifications = findViewById(R.id.switchNotifications);
        switchAutoSavings = findViewById(R.id.switchAutoSavings);
        buttonDeleteCard = findViewById(R.id.buttonDeleteCard);
        buttonCancelDelete = findViewById(R.id.buttonCancelDelete);
        buttonConfirmDelete = findViewById(R.id.buttonConfirmDelete);
        textView7 = findViewById(R.id.textView7);
        textView11 = findViewById(R.id.textView11);
        textView21 = findViewById(R.id.textView21);
        textView22= findViewById(R.id.textView22);
        checkBox = findViewById(R.id.checkBox);
        editTextPinDeleteCard = findViewById(R.id.editTextPinDeleteCard);
        buttonCancelDelete.setVisibility(View.GONE);
        buttonConfirmDelete.setVisibility(View.GONE);
        textView7.setVisibility(View.GONE);
        textView11.setVisibility(View.GONE);
        checkBox.setVisibility(View.GONE);
        editTextPinDeleteCard.setVisibility(View.GONE);

        // Initialize the ImageButtons
        menuItemSettings = findViewById(R.id.menuItemSettings);
        menuItemHome = findViewById(R.id.menuItemHome);
        menuItemTransactions = findViewById(R.id.menuItemTransactions);
        menuItemNotifications = findViewById(R.id. menuItemNotifications);

        menuItemNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListOfNotifications.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });
        menuItemTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListOfTransactions.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        menuItemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        buttonDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float balanceFloat = Float.parseFloat(balance);
                if (balanceFloat > 0.00f) {
                    checkBox.setVisibility(View.GONE);
                    textView7.setVisibility(View.VISIBLE);
                }
                else {
                    checkBox.setVisibility(View.VISIBLE);
                    buttonDeleteCard.setEnabled(false);
                }
                buttonCancelDelete.setVisibility(View.VISIBLE);
                buttonConfirmDelete.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                editTextPinDeleteCard.setVisibility(View.VISIBLE);
            }
        });

        buttonCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelDelete.setVisibility(View.GONE);
                buttonConfirmDelete.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                checkBox.setVisibility(View.GONE);
                editTextPinDeleteCard.setVisibility(View.GONE);
            }
        });

        switchAutoSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference("users")
                        .orderByChild("phone_number").equalTo(userPhoneNumber);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                String notifications = childSnapshot.child("autosavings").getValue(String.class);
                                if (!switchAutoSavings.isChecked()) {
                                    childSnapshot.getRef().child("autosavings").setValue("no");
                                    textView22.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                                    textView22.setText("Disabled");
                                } else {
                                    childSnapshot.getRef().child("autosavings").setValue("yes");
                                    textView22.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                                    textView22.setText("Enabled"); }
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
        });

        switchNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference("users")
                        .orderByChild("phone_number").equalTo(userPhoneNumber);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                String notifications = childSnapshot.child("notifications").getValue(String.class);
                                if (!switchNotifications.isChecked()) {
                                        childSnapshot.getRef().child("notifications").setValue("no");
                                        textView21.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                                        textView21.setText("Disabled");
                                } else {
                                        childSnapshot.getRef().child("notifications").setValue("yes");
                                        textView21.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                                        textView21.setText("Enabled"); }
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
        });

        buttonConfirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextPinDeleteCard.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Insert your PIN", Toast.LENGTH_SHORT).show();
                } else {
                    if (!pin.equals(editTextPinDeleteCard.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Wrong Pin Operation Aborted", Toast.LENGTH_SHORT).show();
                    } else {
                        Query query = FirebaseDatabase.getInstance().getReference("users")
                                .orderByChild("phone_number").equalTo(userPhoneNumber);

                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (checkBox.isChecked()) {
                                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                        userSnapshot.getRef().removeValue();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Failed to delete transactions", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("userPhoneNumber");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Card deleted with success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        menuItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
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
                            pin = childSnapshot.child("pin").getValue().toString();
                            notifications = childSnapshot.child("notifications").getValue().toString();
                            autosavings = childSnapshot.child("autosavings").getValue().toString();
                        }
                    }
                    buttonDeleteCard.setEnabled(true);
                    if (notifications.equals("no")){
                        switchNotifications.setChecked(false);
                        textView21.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        textView21.setText("Disabled");
                    }
                    else {
                        switchNotifications.setChecked(true);
                        textView21.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        textView21.setText("Enabled");
                    }
                    if (autosavings.equals("no")){
                        switchAutoSavings.setChecked(false);
                        textView22.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        textView22.setText("Disabled");
                    }
                    else {
                        switchAutoSavings.setChecked(true);
                        textView22.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        textView22.setText("Enabled");
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
    }