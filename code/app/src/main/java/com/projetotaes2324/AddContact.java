package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.ContactsContract;
import java.util.ArrayList;

public class AddContact extends AppCompatActivity {

    private EditText editTextNameAddContact,editTextPhoneNumberAddContact;
    private Button buttonAddContact;
    private String[] contactPermissions;
    private static final int WRITE_CONTACT_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Intent intent = getIntent();
        // Get the variable from the Intent
        String userPhoneNumber = intent.getStringExtra("userPhoneNumber");

        contactPermissions = new String[]{Manifest.permission.WRITE_CONTACTS};

        buttonAddContact=findViewById(R.id.buttonAddContact);
        editTextNameAddContact=findViewById(R.id.editTextNameAddContact);
        editTextPhoneNumberAddContact=findViewById(R.id.editTextPhoneNumberAddContact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click
                Intent intent = new Intent(getApplicationContext(), ListOfContacts.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                startActivity(intent);
            }
        });

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isWriteContactPermissionEnabled()){
                   saveContact();
                   // Handle navigation icon click
                   Intent intent = new Intent(getApplicationContext(), ListOfContacts.class);
                   intent.putExtra("userPhoneNumber", userPhoneNumber);
                   startActivity(intent);
               }
               else {
                   requestWriteContactPermission();
               }
            }
        });
}

private boolean isWriteContactPermissionEnabled(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) ==
                (PackageManager.PERMISSION_GRANTED);
        return result;
}

private void requestWriteContactPermission(){
    ActivityCompat.requestPermissions(this,contactPermissions, WRITE_CONTACT_PERMISSION_CODE);
}

@Override
    public void onRequestPermissionsResult(int requestcode, @NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestcode,permissions,grantResults);
        if (grantResults.length > 0){
            if (requestcode == WRITE_CONTACT_PERMISSION_CODE){
                boolean haveWriteContactPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (haveWriteContactPermission){
                    saveContact();
                }
                else {
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT);
                }
            }
        }
}

private void saveContact(){
    String name = editTextNameAddContact.getText().toString();
    String phoneNumber = editTextPhoneNumberAddContact.getText().toString();

    if (phoneNumber.isEmpty() || name.isEmpty()){
        Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
    }
    else {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        int rawContactId = ops.size();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try {
            ContentProviderResult[] results = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(getApplicationContext(), "Contact added successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error adding contact: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

}