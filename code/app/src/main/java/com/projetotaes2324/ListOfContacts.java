package com.projetotaes2324;

import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class ListOfContacts extends AppCompatActivity implements ContactCallback {

    private RecyclerView recyclerView;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private List<Contact> contactList = new ArrayList<>();
    private Button buttonAddContactListOfContacts,buttonSendMoneySendTo;
    private ContactAdapter contactAdapter;
    private EditText editTextPhoneNumberSendTo;
    private int queryCounter = 0; // Counter for ongoing queries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_contacts);
        Intent intent = getIntent();
        // Get the variable from the Intent
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        Toolbar toolbar = findViewById(R.id.toolbar);
        buttonAddContactListOfContacts = findViewById(R.id.buttonAddContactListOfContacts);
        buttonSendMoneySendTo = findViewById(R.id.buttonSendMoneySendTo);
        editTextPhoneNumberSendTo = findViewById(R.id.editTextPhoneNumberSendTo);
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

        buttonSendMoneySendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberSendTo =  editTextPhoneNumberSendTo.getText().toString();
                if (phoneNumberSendTo.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please fill phone number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Query query = FirebaseDatabase.getInstance().getReference("users")
                            .orderByChild("phone_number").equalTo(phoneNumberSendTo);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Intent intent = new Intent(getApplicationContext(), SendMoney.class);
                                intent.putExtra("userPhoneNumber", userPhoneNumber);
                                intent.putExtra("phoneNumberSendTo", phoneNumberSendTo);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "That number is not associated to any card", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle onCancelled
                        }
                    });
                }
            }
        });

        buttonAddContactListOfContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click
                Intent intent = new Intent(getApplicationContext(), AddContact.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.contactRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(contactList, userPhoneNumber);
        recyclerView.setAdapter(contactAdapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // Permission already granted, fetch contacts
            fetchContacts();
        }
    }

    private void fetchContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        );
        if (cursor != null) {
            int idColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int nameColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

            while (cursor.moveToNext()) {
                String contactId = (idColumnIndex != -1) ? cursor.getString(idColumnIndex) : null;
                String contactName = (nameColumnIndex != -1) ? cursor.getString(nameColumnIndex) : null;

                if (contactId != null && contactName != null) {
                    String phoneNumber = getContactPhoneNumber(contactId);
                    Contact contact = new Contact(contactName, "", phoneNumber);
                    contactList.add(contact);
                    checkIfContactHasCard(phoneNumber, contact);
                }
            }
            cursor.close();
        }
    }

    private String getContactPhoneNumber(String contactId) {
        String phoneNumber = null;
        Cursor phones = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                null,
                null
        );

        if (phones != null && phones.moveToFirst()) {
            int phoneNumberColumnIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            if (phoneNumberColumnIndex != -1) {
                phoneNumber = phones.getString(phoneNumberColumnIndex);
            }
            phones.close();
        }
        return phoneNumber;
    }

    private void checkIfContactHasCard(String phoneNumber, Contact contact, ContactCallback callback) {
        Query query = FirebaseDatabase.getInstance().getReference("users")
                .orderByChild("phone_number").equalTo(phoneNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // A record with the specified phone number exists
                    contact.setVcard("yes");
                } else {
                    // No record found with the specified phone number
                    contact.setVcard("no");
                }
                // Notify the callback with the updated contact
                callback.onContactUpdated(contact);
                // Decrement the counter when the query is completed
                queryCounter--;
                // Check if all queries are completed, and update the list
                if (queryCounter == 0) {
                    // Ensure that the UI update is done on the main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contactAdapter.notifyDataSetChanged();
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
    private void checkIfContactHasCard(String phoneNumber, Contact contact) {
        Query query = FirebaseDatabase.getInstance().getReference("users")
                .orderByChild("phone_number").equalTo(phoneNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // A record with the specified phone number exists
                    contact.setVcard("yes");
                } else {
                    // No record found with the specified phone number
                    contact.setVcard("no");
                }

                // Check if all contacts have been processed
                if (contactList.size() == recyclerView.getAdapter().getItemCount()) {
                    // Notify the adapter that the data set has changed
                    contactAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
            }
        });
    }

    @Override
    public void onContactUpdated(Contact contact) {
        // No need to update the list here; it will be updated in checkIfContactHasCard
    }
}