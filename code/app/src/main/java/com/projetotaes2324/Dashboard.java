package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class Dashboard extends AppCompatActivity {

    private TextView textViewPhoneNumber, textViewBalance, textViewPiggyBankBalance;
    private TextView textUserDashboard, textDateDashboard, textAmountDashBoard, textView19;
    private ImageView imageViewRefresh;
    private ImageButton menuItemHome, menuItemSettings, menuItemTransactions,menuItemNotifications;
    private ImageButton imageViewPiggyBank, imageViewSendMoney;
    private List<Transaction> transactionList = new ArrayList<>();
    private String balance, piggybankbalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the Intent that started this activity
        Intent intent = getIntent();
        // Get the variable from the Intent
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        setContentView(R.layout.activity_dashboard);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        textViewBalance = findViewById(R.id.textViewBalance);
        textViewPhoneNumber.setText(userPhoneNumber);
        textViewPiggyBankBalance = findViewById(R.id.textViewPiggyBalance);
        textUserDashboard = findViewById(R.id.textUserDashboard);
        textDateDashboard = findViewById(R.id.textDateDashboard);
        textView19 = findViewById(R.id.textView19);
        textAmountDashBoard = findViewById(R.id.textAmountDashBoard);
        textDateDashboard.setVisibility(View.GONE);
        textAmountDashBoard.setVisibility(View.GONE);
        textUserDashboard.setVisibility(View.GONE);
        textView19.setVisibility(View.GONE);
        menuItemHome = findViewById(R.id.menuItemHome);
        menuItemSettings = findViewById(R.id.menuItemSettings);
        imageViewRefresh = findViewById(R.id.imageViewRefresh);
        imageViewPiggyBank = findViewById(R.id.imageViewPiggyBank);
        imageViewSendMoney = findViewById(R.id.imageViewSendMoney);

        menuItemTransactions = findViewById(R.id.menuItemTransactions);
        menuItemNotifications = findViewById(R.id.menuItemNotifications);
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

        menuItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        imageViewPiggyBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PiggyBankActivity.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        imageViewSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListOfContacts.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });


        imageViewRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!userPhoneNumber.isEmpty()) {
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
                            float piggybankbalanceFloat = Float.parseFloat(piggybankbalance);
                            // Check the condition and set the text color accordingly
                            if (balanceFloat > 0.00f) {
                                textViewBalance.setTextColor(Color.GREEN);
                            } else {
                                textViewBalance.setTextColor(Color.RED);
                            }
                            textViewBalance.setText(balance + " €");
                            if (piggybankbalanceFloat > 0.00f) {
                                textViewPiggyBankBalance.setTextColor(Color.GREEN);
                            } else {
                                textViewPiggyBankBalance.setTextColor(Color.RED);
                            }
                            textViewPiggyBankBalance.setText(piggybankbalance + " €");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle any errors that occur during the query
                            Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(Dashboard.this, "nao da", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (!userPhoneNumber.isEmpty()) {
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
                    float piggybankbalanceFloat = Float.parseFloat(piggybankbalance);
                    // Check the condition and set the text color accordingly
                    if (balanceFloat > 0.00f) {
                        textViewBalance.setTextColor(Color.GREEN);
                    } else {
                        textViewBalance.setTextColor(Color.RED);
                    }
                    textViewBalance.setText(balance + " €");
                    if (piggybankbalanceFloat > 0.00f) {
                        textViewPiggyBankBalance.setTextColor(Color.GREEN);
                    } else {
                        textViewPiggyBankBalance.setTextColor(Color.RED);
                    }
                    textViewPiggyBankBalance.setText(piggybankbalance + " €");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle any errors that occur during the query
                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            Query query1 = FirebaseDatabase.getInstance().getReference("transactions")
                    .orderByChild("id")
                    .equalTo(userPhoneNumber);
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Convert each DataSnapshot to a Transaction object
                            String amount = snapshot.child("amount").getValue(String.class);
                            String id = snapshot.child("id").getValue(String.class);
                            String phone_number = snapshot.child("phone_number").getValue(String.class);
                            String time = snapshot.child("time").getValue(String.class);
                            String type = snapshot.child("type").getValue(String.class);
                            Transaction transaction = new Transaction(time, amount, id, type, phone_number);
                            transactionList.add(transaction);
                        }
                        Transaction transaction = findMostRecentTransaction(transactionList);
                        textDateDashboard.setVisibility(View.VISIBLE);
                        textAmountDashBoard.setVisibility(View.VISIBLE);
                        textUserDashboard.setVisibility(View.VISIBLE);
                        textDateDashboard.setText(transaction.getTime());
                        if ("Debit".equals(transaction.getType())) {
                            textAmountDashBoard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                            textAmountDashBoard.setText("- " + transaction.getAmount() + "€");
                        } else {
                            textAmountDashBoard.setText("+ " + transaction.getAmount() + "€");
                            textAmountDashBoard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                        }
                        // Retrieve the contact name based on the phone number
                        String contactName = getContactName(getApplicationContext(), transaction.getPhone_number());
                        if (contactName != null) {
                            textUserDashboard.setText(contactName);
                        } else {
                            textUserDashboard.setText(transaction.getPhone_number());
                        }
                    }
                    else {
                        // Handle the case when dataSnapshot is empty
                        textView19.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    private static Transaction findMostRecentTransaction(List<Transaction> transactions) {
        // Find the maximum date string in the list of transactions
        Transaction mostRecentTransaction = transactions.stream()
                .max((t1, t2) -> parseDate(t1.getTime()).compareTo(parseDate(t2.getTime())))
                .orElse(null);

        if (mostRecentTransaction == null) {
            throw new RuntimeException("List is empty");
        }

        return mostRecentTransaction;
    }

    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date", e);
        }
    }

    private String getContactName(Context context, String phoneNumber) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
        String selection = ContactsContract.CommonDataKinds.Phone.NUMBER + "=?";
        String[] selectionArgs = new String[]{phoneNumber};

        try (Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int displayNameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                if (displayNameColumnIndex != -1) {
                    return cursor.getString(displayNameColumnIndex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}