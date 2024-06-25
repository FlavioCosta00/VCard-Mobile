package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListOfNotifications extends AppCompatActivity {

    private RecyclerView recyclerNotification;
    private TextView textView30;
    private Button buttonDismissAll,buttonRefreshNotifications;

    private ImageButton menuItemHome, menuItemSettings,menuItemTransactions,menuItemNotifications;

    private List<Notification> notificationList = new ArrayList<>();
    private List<Notification> copyList = new ArrayList<>();

    private NotificationAdapter notificationAdapter;
    private String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notifications);
        Intent intent = getIntent();
        // Get the variable from the Intent
        userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        textView30=findViewById(R.id.textView30);
        buttonDismissAll=findViewById(R.id.buttonDismissAll);
        buttonRefreshNotifications=findViewById(R.id.buttonRefreshNotifications);

        recyclerNotification = findViewById(R.id.recyclerNotification);
        recyclerNotification.setLayoutManager(new LinearLayoutManager(this));


        // Initialize the ImageButtons
        menuItemSettings = findViewById(R.id.menuItemSettings);
        menuItemHome = findViewById(R.id.menuItemHome);
        menuItemTransactions = findViewById(R.id.menuItemTransactions);
        menuItemNotifications= findViewById(R.id.menuItemNotifications);

        menuItemNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListOfNotifications.class);
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
        menuItemTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListOfTransactions.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        buttonDismissAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationList.clear();
                if (notificationAdapter != null) {
                    notificationAdapter.notifyDataSetChanged();
                }
                Query query = FirebaseDatabase.getInstance().getReference("notifications")
                        .orderByChild("id")
                        .equalTo(userPhoneNumber);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                snapshot.getRef().child("checked").setValue("yes");

                            }
                            refreshNotifications();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }
        });

        buttonRefreshNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshNotifications();
            }
        });

        // Call the method to load notifications on activity creation
        refreshNotifications();
    }

    private void refreshNotifications() {
        notificationList.clear();
        if (notificationAdapter != null) {
            notificationAdapter.notifyDataSetChanged();
        }

        Query query = FirebaseDatabase.getInstance().getReference("notifications")
                .orderByChild("id")
                .equalTo(userPhoneNumber);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Convert each DataSnapshot to a Transaction object
                        String id = snapshot.child("id").getValue(String.class);
                        String time = snapshot.child("time").getValue(String.class);
                        String phone_number = snapshot.child("phone_number").getValue(String.class);
                        String message = snapshot.child("message").getValue(String.class);
                        String checked = snapshot.child("checked").getValue(String.class);
                        String notification_id = snapshot.child("notification_id").getValue(String.class);

                        // Check for null values before creating a Notification object
                        if (id != null && time != null && phone_number != null && message != null && checked != null) {
                            Notification notification = new Notification(id, time, message, checked, phone_number,notification_id);
                            notificationList.add(notification);
                        }
                    }

                    // Create the adapter only once
                    if (notificationAdapter == null) {
                        notificationAdapter = new NotificationAdapter(notificationList, getApplicationContext());
                        recyclerNotification.setAdapter(notificationAdapter);
                    } else {
                        notificationAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}