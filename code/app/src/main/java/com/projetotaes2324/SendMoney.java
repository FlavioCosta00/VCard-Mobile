package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class SendMoney extends AppCompatActivity {

    private TextView textView5, textviewbalanceavaliableSendMoney, textView12, textView13,textView23;
    private String balance, piggybankbalance, pin,autosavings;
    private float transactionAmmount;
    private EditText editTextAmmount, editTextPinSendMoney;
    private Button buttonSendMoney, buttonCancelSendMoney, buttonConfirmSendMoney;
    private Float piggyBankBalanceFloat, availableBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        Intent intent = getIntent();

        // Get the variable from the Intent
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        String nameContact = intent.getStringExtra("nameContact");
        String phoneNumberSendTo = intent.getStringExtra("phoneNumberSendTo");
        buttonSendMoney = findViewById(R.id.buttonSendMoney);
        buttonConfirmSendMoney = findViewById(R.id.buttonConfirmSendMoney);
        buttonCancelSendMoney = findViewById(R.id.buttonCancelSendMoney);
        editTextAmmount = findViewById(R.id.editTextAmmount);
        textView5 = findViewById(R.id.textView5);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView23 = findViewById(R.id.textView23);
        editTextPinSendMoney = findViewById(R.id.editTextPinSendMoney);
        textView12.setVisibility(View.GONE);
        textView13.setVisibility(View.GONE);
        textView13.setVisibility(View.GONE);
        textView23.setVisibility(View.GONE);
        editTextPinSendMoney.setVisibility(View.GONE);
        buttonSendMoney.setEnabled(false);
        buttonConfirmSendMoney.setVisibility(View.GONE);
        buttonCancelSendMoney.setVisibility(View.GONE);
        textviewbalanceavaliableSendMoney = findViewById(R.id.textviewbalanceavaliableSendMoney);
        Toolbar toolbar = findViewById(R.id.toolbarSendMoney);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        buttonConfirmSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinEditBox = editTextPinSendMoney.getText().toString();
                if (pinEditBox.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill the PIN", Toast.LENGTH_SHORT).show();
                } else {
                    if (!pin.equals(pinEditBox)) {
                        Toast.makeText(getApplicationContext(), "Wrong Pin. Operation Aborted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ListOfContacts.class);
                        intent.putExtra("userPhoneNumber", userPhoneNumber);
                        startActivity(intent);
                    } else {
                        // Step 1: Update Balances
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                        Query senderQuery = usersRef.orderByChild("phone_number").equalTo(userPhoneNumber);
                        Query receiverQuery = usersRef.orderByChild("phone_number").equalTo(phoneNumberSendTo);

                        senderQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot senderSnapshot : dataSnapshot.getChildren()) {
                                        float senderBalance = Float.parseFloat(senderSnapshot.child("balance").getValue().toString());
                                        float newBalance = senderBalance - transactionAmmount;

                                        if (autosavings.equals("yes")){
                                            float piggybalance = Float.parseFloat(senderSnapshot.child("piggybalance").getValue().toString());
                                            float newpiggybalance = piggybalance+1;
                                            senderSnapshot.getRef().child("piggybalance").setValue(newpiggybalance);
                                        }

                                        // Update sender's balance
                                        senderSnapshot.getRef().child("balance").setValue(newBalance);

                                        // Update receiver's balance
                                        receiverQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot receiverSnapshot : dataSnapshot.getChildren()) {
                                                        float receiverBalance = Float.parseFloat(receiverSnapshot.child("balance").getValue().toString());
                                                        String notifications = receiverSnapshot.child("notifications").getValue().toString();
                                                        receiverSnapshot.getRef().child("balance").setValue(receiverBalance + transactionAmmount);

                                                        Map<String, Object> transactionSender = new HashMap<>();

                                                        long currentTimeMillis = System.currentTimeMillis();
                                                        Date date = new Date(currentTimeMillis);

                                                        // Define the desired date and time format
                                                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

                                                        // Format the date using the specified format
                                                        String formattedDate = sdf.format(date);

                                                        if (notifications.equals("yes")){
                                                            Map<String, Object> notification = new HashMap<>();
                                                            notification.put("id", phoneNumberSendTo);
                                                            notification.put("notification_id",phoneNumberSendTo+formattedDate);
                                                            notification.put("time", formattedDate);
                                                            notification.put("message", "You received " + String.valueOf(transactionAmmount) + "€ from ");
                                                            notification.put("phone_number",userPhoneNumber);
                                                            notification.put("checked", "no");
                                                            FirebaseDatabase.getInstance().getReference().child("notifications").push().setValue(notification)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });

                                                        }

                                                        transactionSender.put("id", userPhoneNumber);
                                                        transactionSender.put("phone_number", phoneNumberSendTo);
                                                        transactionSender.put("amount",  String.valueOf(transactionAmmount));
                                                        transactionSender.put("type", "Debit");
                                                        transactionSender.put("time", formattedDate);
                                                        FirebaseDatabase.getInstance().getReference().child("transactions").push().setValue(transactionSender)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                    }

                                                    Map<String, Object> transactionReceiver = new HashMap<>();
                                                    long currentTimeMillis = System.currentTimeMillis();
                                                    Date date = new Date(currentTimeMillis);

                                                    // Define the desired date and time format
                                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

                                                    // Format the date using the specified format
                                                    String formattedDate = sdf.format(date);

                                                    transactionReceiver.put("id", phoneNumberSendTo);
                                                    transactionReceiver.put("phone_number", userPhoneNumber);
                                                    transactionReceiver.put("amount",  String.valueOf(transactionAmmount));
                                                    transactionReceiver.put("type", "Credit");
                                                    transactionReceiver.put("time", formattedDate);
                                                    FirebaseDatabase.getInstance().getReference().child("transactions").push().setValue(transactionReceiver)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Toast.makeText(getApplicationContext(),"Money sent with sucess !",Toast.LENGTH_SHORT).show();
                                                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                                                    intent.putExtra("userPhoneNumber", userPhoneNumber);
                                                                    startActivity(intent);
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Handle onCancelled
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                // Handle onCancelled
                            }
                        });
                    }
                }
            }
        });

        buttonSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView12.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                editTextPinSendMoney.setVisibility(View.VISIBLE);
                buttonConfirmSendMoney.setVisibility(View.VISIBLE);
                buttonCancelSendMoney.setVisibility(View.VISIBLE);
                editTextAmmount.setEnabled(false);
                buttonSendMoney.setEnabled(false);
                if (nameContact != null && !nameContact.isEmpty()) {
                    textView12.setText("Are you sure you want to send " + editTextAmmount.getText().toString() + "€ to " + nameContact + " ?");
                } else {
                    textView12.setText("Are you sure you want to send " + editTextAmmount.getText().toString() + "€ to " + phoneNumberSendTo + " ?");
                }
            }
        });

        buttonCancelSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click
                Intent intent = new Intent(getApplicationContext(), ListOfContacts.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click
                Intent intent = new Intent(getApplicationContext(), ListOfContacts.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });
        if (!userPhoneNumber.isEmpty()){
            if (nameContact != null && !nameContact.isEmpty()) {
                textView5.setText("Send to: " + nameContact);
            }else {
                textView5.setText("Send to: " + phoneNumberSendTo);
            }
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
                            pin = childSnapshot.child("pin").getValue().toString();
                            autosavings = childSnapshot.child("autosavings").getValue().toString();
                        }
                    }
                    float balanceFloat = Float.parseFloat(balance);
                    piggyBankBalanceFloat = Float.parseFloat(piggybankbalance);
                    if (autosavings.equals("no")){
                        textView23.setVisibility(View.GONE);
                        availableBalance = balanceFloat-piggyBankBalanceFloat;
                    }
                    else {
                        textView23.setVisibility(View.VISIBLE);
                        availableBalance = balanceFloat-piggyBankBalanceFloat-1;
                    }
                    textviewbalanceavaliableSendMoney.setText("Available balance: " + availableBalance +" €");
                    editTextAmmount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            // Not needed for this example
                        }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                            if (!TextUtils.isEmpty( editTextAmmount.getText().toString())){
                                transactionAmmount = Float.parseFloat( editTextAmmount.getText().toString());
                                if (transactionAmmount > 0.00 && transactionAmmount <= availableBalance){
                                    buttonSendMoney.setEnabled(true);
                                }
                                else{
                                    buttonSendMoney.setEnabled(false);
                                }

                            }
                            else{
                                buttonSendMoney.setEnabled(false);
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
}