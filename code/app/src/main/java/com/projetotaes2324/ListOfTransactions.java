package com.projetotaes2324;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListOfTransactions extends AppCompatActivity {

    private RecyclerView recyclerTransaction;
    private String startDate, endDate;
    private ImageButton menuItemHome, menuItemSettings,menuItemTransactions,  menuItemNotifications ;
    private List<Transaction> transactionList = new ArrayList<>();
    private List<Transaction> filteredList = new ArrayList<>();
    private Button buttonReset;
    private String userPhoneNumber;
    private RadioButton radioButtonDebit,radioButtonCredit,radioButtonAll;
    private TransactionAdapter transactionAdapter;
    private TextView startDateTextView,endDateTextView, textViewMoneyAsc,textViewDateAsc,textViewMoneyDesc,textViewDateDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_transactions);
        Intent intent = getIntent();
        // Get the variable from the Intent
        userPhoneNumber = intent.getStringExtra("userPhoneNumber");
        startDateTextView = findViewById(R.id.textView16);
        endDateTextView = findViewById(R.id.textView17);
        textViewMoneyAsc=findViewById(R.id.textViewMoneyAsc);
        textViewMoneyDesc=findViewById(R.id.textViewMoneyDesc);
        textViewDateDesc=findViewById(R.id.textViewDateDesc);
        textViewDateAsc=findViewById(R.id.textViewDateAsc);

        buttonReset = findViewById(R.id.buttonReset);
        radioButtonDebit = findViewById(R.id.radioButtonDebit);
        radioButtonCredit = findViewById(R.id.radioButtonCredit);
        radioButtonAll = findViewById(R.id.radioButtonAll);
        radioButtonAll.setChecked(true);
        // Initialize the ImageButtons
        menuItemSettings = findViewById(R.id.menuItemSettings);
        menuItemHome = findViewById(R.id.menuItemHome);
        menuItemTransactions = findViewById(R.id.menuItemTransactions);
        menuItemNotifications = findViewById(R.id.menuItemNotifications);
        recyclerTransaction = findViewById(R.id.recyclerNotification);
        recyclerTransaction.setLayoutManager(new LinearLayoutManager(this));
        String notificationId = intent.getStringExtra("notification_id");
        SharedPreferences sharedPreferences = getSharedPreferences("authenticationStorage", 0);

        if (notificationId!= null){

            Query query = FirebaseDatabase.getInstance().getReference("notifications")
                    .orderByChild("notification_id").equalTo(notificationId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            childSnapshot.getRef().child("checked").setValue("yes");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle any errors that occur during the query
                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("notification_id");
            editor.apply();
        }

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

        textViewMoneyDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your sort method here
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.GREEN);
                textViewDateAsc.setTextColor(Color.BLACK);
                sortTransactionsByAmountAndTypeDesc();
            }
        });

        textViewMoneyAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your sort method here
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.GREEN);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.BLACK);
                sortTransactionsByAmountAndTypeAsc();
            }
        });

        textViewDateDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your sort method here
                textViewDateDesc.setTextColor(Color.GREEN);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.BLACK);
                sortTransactionsByDateDesc();
            }
        });

        textViewDateAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your sort method here
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.GREEN);
                sortTransactionsByDateAsc();
            }
        });

        radioButtonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.BLACK);
                radioButtonCredit.setChecked(false);
                radioButtonDebit.setChecked(false);
                if (startDate == null || endDate == null) {
                    startDate = null;
                    endDate = null;
                    startDateTextView.setText("Start Date");
                    endDateTextView.setText("End Date");
                    filteredList.clear();
                    filteredList.addAll(transactionList);
                    // Update the RecyclerView with the filtered data
                    if (transactionAdapter == null) {
                        transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                        recyclerTransaction.setAdapter(transactionAdapter);
                    } else {
                        transactionAdapter.setData(transactionList);
                        transactionAdapter.notifyDataSetChanged();
                    }
                }
                if (startDate != null && endDate != null){
                    filteredList.clear();
                    // Verify that endDate is not earlier than startDate
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        Date startDateObj = sdf.parse(startDate);
                        Date endDateObj = sdf.parse(endDate);
                        if (endDateObj.before(startDateObj)) {
                            Toast.makeText(getApplicationContext(), "End date cannot be earlier than start date", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return;
                    }
                    try {
                        Date startDateObj = sdf.parse(startDate);
                        Date endDateObj = sdf.parse(endDate);
                        for (Transaction transaction : transactionList) {
                            Date transactionDate = sdf.parse(transaction.getTime().split(" ")[1]);
                            // Check if the transaction date is within the selected range
                            if (transactionDate != null && isDateBetween(transactionDate, startDateObj, endDateObj)
                                    ) {
                                filteredList.add(transaction);
                            }
                        }
                        // Update the RecyclerView with the filtered data
                        if (transactionAdapter == null) {
                            transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                            recyclerTransaction.setAdapter(transactionAdapter);
                        } else {
                            transactionAdapter.setData(filteredList);
                            transactionAdapter.notifyDataSetChanged();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        radioButtonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.BLACK);
                radioButtonDebit.setChecked(false);
                radioButtonAll.setChecked(false);
                if (startDate == null || endDate == null) {
                    startDate = null;
                    endDate = null;
                    startDateTextView.setText("Start Date");
                    endDateTextView.setText("End Date");
                    filteredList.clear();
                    for (Transaction transaction : transactionList) {
                            // Check if the transaction date is within the selected range
                            if ( transaction.getType().equals("Credit")) {
                                filteredList.add(transaction);
                            }
                        }
                        // Update the RecyclerView with the filtered data
                        if (transactionAdapter == null) {
                            transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                            recyclerTransaction.setAdapter(transactionAdapter);
                        } else {
                            transactionAdapter.setData(filteredList);
                            transactionAdapter.notifyDataSetChanged();
                        }
                    }
                if (startDate != null && endDate != null){
                    filteredList.clear();
                    // Verify that endDate is not earlier than startDate
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        Date startDateObj = sdf.parse(startDate);
                        Date endDateObj = sdf.parse(endDate);
                        if (endDateObj.before(startDateObj)) {
                            Toast.makeText(getApplicationContext(), "End date cannot be earlier than start date", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return;
                    }
                    try {
                        Date startDateObj = sdf.parse(startDate);
                        Date endDateObj = sdf.parse(endDate);
                        for (Transaction transaction : transactionList) {
                            Date transactionDate = sdf.parse(transaction.getTime().split(" ")[1]);
                            // Check if the transaction date is within the selected range
                            if (transactionDate != null && isDateBetween(transactionDate, startDateObj, endDateObj)
                                    && transaction.getType().equals("Credit")) {
                                filteredList.add(transaction);
                            }
                        }
                        // Update the RecyclerView with the filtered data
                        if (transactionAdapter == null) {
                            transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                            recyclerTransaction.setAdapter(transactionAdapter);
                        } else {
                            transactionAdapter.setData(filteredList);
                            transactionAdapter.notifyDataSetChanged();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        radioButtonDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.BLACK);
                radioButtonCredit.setChecked(false);
                radioButtonAll.setChecked(false);
                if (startDate == null || endDate == null) {
                    startDate = null;
                    endDate = null;
                    startDateTextView.setText("Start Date");
                    endDateTextView.setText("End Date");
                    filteredList.clear();
                    for (Transaction transaction : transactionList) {
                        // Check if the transaction date is within the selected range
                        if ( transaction.getType().equals("Debit")) {
                            filteredList.add(transaction);
                        }
                    }
                    // Update the RecyclerView with the filtered data
                    if (transactionAdapter == null) {
                        transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                        recyclerTransaction.setAdapter(transactionAdapter);
                    } else {
                        transactionAdapter.setData(filteredList);
                        transactionAdapter.notifyDataSetChanged();
                    }
                }
                if (startDate != null && endDate != null){
                    filteredList.clear();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        Date startDateObj = sdf.parse(startDate);
                        Date endDateObj = sdf.parse(endDate);
                        if (endDateObj.before(startDateObj)) {
                            Toast.makeText(getApplicationContext(), "End date cannot be earlier than start date", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return;
                    }
                    try {
                        Date startDateObj = sdf.parse(startDate);
                        Date endDateObj = sdf.parse(endDate);
                        for (Transaction transaction : transactionList) {
                            Date transactionDate = sdf.parse(transaction.getTime().split(" ")[1]);
                            // Check if the transaction date is within the selected range
                            if (transactionDate != null && isDateBetween(transactionDate, startDateObj, endDateObj)
                                    && transaction.getType().equals("Debit")) {
                                filteredList.add(transaction);
                            }
                        }

                        // Update the RecyclerView with the filtered data
                        if (transactionAdapter == null) {
                            transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                            recyclerTransaction.setAdapter(transactionAdapter);
                        } else {
                            transactionAdapter.setData(filteredList);
                            transactionAdapter.notifyDataSetChanged();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate=null;
                endDate=null;
                startDateTextView.setText("Start Date");
                endDateTextView.setText("End Date");
                radioButtonDebit.setChecked(false);
                radioButtonCredit.setChecked(false);
                radioButtonAll.setChecked(true);
                textViewDateDesc.setTextColor(Color.BLACK);
                textViewMoneyAsc.setTextColor(Color.BLACK);
                textViewMoneyDesc.setTextColor(Color.BLACK);
                textViewDateAsc.setTextColor(Color.BLACK);
                // Clear the data in transactionList
                transactionList.clear();
                filteredList.clear();
                // Notify the adapter that the data has changed
                transactionAdapter.notifyDataSetChanged();
                Query query = FirebaseDatabase.getInstance().getReference("transactions")
                        .orderByChild("id")
                        .equalTo(userPhoneNumber);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Convert each DataSnapshot to a Transaction object
                            String amount = snapshot.child("amount").getValue(String.class);
                            String id = snapshot.child("id").getValue(String.class);
                            String phone_number = snapshot.child("phone_number").getValue(String.class);
                            String time = snapshot.child("time").getValue(String.class);
                            String type = snapshot.child("type").getValue(String.class);
                            Transaction transaction = new Transaction(time,amount,id,type,phone_number);
                            transactionList.add(transaction);
                        }
                        filteredList.addAll(transactionList);
                        transactionAdapter = new TransactionAdapter(getApplicationContext(),filteredList);
                        recyclerTransaction.setAdapter(transactionAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        Query query = FirebaseDatabase.getInstance().getReference("transactions")
                .orderByChild("id")
                .equalTo(userPhoneNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Convert each DataSnapshot to a Transaction object
                    String amount = snapshot.child("amount").getValue(String.class);
                    String id = snapshot.child("id").getValue(String.class);
                    String phone_number = snapshot.child("phone_number").getValue(String.class);
                    String time = snapshot.child("time").getValue(String.class);
                    String type = snapshot.child("type").getValue(String.class);
                    Transaction transaction = new Transaction(time,amount,id,type,phone_number);
                    transactionList.add(transaction);
                }
                filteredList.addAll(transactionList);
                transactionAdapter = new TransactionAdapter(getApplicationContext(),filteredList);
                recyclerTransaction.setAdapter(transactionAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void showDatePickerDialog(View v) {
        final TextView selectedTextView = (TextView) v;
        // Get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the TextView with the selected date
                        String formattedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                        selectedTextView.setText(formattedDate);
                        // Store the selected dates
                        if (selectedTextView.getId() == R.id.textView16) {
                            startDate = formattedDate;
                        } else if (selectedTextView.getId() == R.id.textView17) {
                            endDate = formattedDate;
                        }
                    }
                }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    public void filterDataByDate(View view) {
        textViewDateDesc.setTextColor(Color.BLACK);
        textViewMoneyAsc.setTextColor(Color.BLACK);
        textViewMoneyDesc.setTextColor(Color.BLACK);
        textViewDateAsc.setTextColor(Color.BLACK);
        filteredList.clear();
        // Check if both startDate and endDate are set
        if (startDate == null || endDate == null) {
            Toast.makeText(this, "Please select both start and end dates", Toast.LENGTH_SHORT).show();
            return;
        }
        // Verify that endDate is not earlier than startDate
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date startDateObj = sdf.parse(startDate);
            Date endDateObj = sdf.parse(endDate);
            if (endDateObj.before(startDateObj)) {
                Toast.makeText(this, "End date cannot be earlier than start date", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        try {
            Date startDateObj = sdf.parse(startDate);
            Date endDateObj = sdf.parse(endDate);
            for (Transaction transaction : transactionList) {
                Date transactionDate = sdf.parse(transaction.getTime().split(" ")[1]);
                if (radioButtonCredit.isChecked()) {
                    if((transactionDate != null && isDateBetween(transactionDate, startDateObj, endDateObj))
                    && transaction.getType().equals("Credit"))
                        filteredList.add(transaction);
                } else if (radioButtonDebit.isChecked()) {
                    if((transactionDate != null && isDateBetween(transactionDate, startDateObj, endDateObj))
                            && transaction.getType().equals("Debit"))
                        filteredList.add(transaction);
                }
                // Check if the transaction date is within the selected range
                else  {
                    if((transactionDate != null && isDateBetween(transactionDate, startDateObj, endDateObj)))
                        filteredList.add(transaction);
                }
            }
            // Update the RecyclerView with the filtered data
            if (transactionAdapter == null) {
                transactionAdapter = new TransactionAdapter(getApplicationContext(), filteredList);
                recyclerTransaction.setAdapter(transactionAdapter);
            } else {
                transactionAdapter.setData(filteredList);
                transactionAdapter.notifyDataSetChanged();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean isDateBetween(Date dateToCheck, Date startDate, Date endDate) {
        // Check if dateToCheck is between startDate and endDate (inclusive)
        return dateToCheck.after(startDate) && dateToCheck.before(endDate) || dateToCheck.equals(startDate) || dateToCheck.equals(endDate);
    }

    private void sortTransactionsByDateAsc() {
        Collections.sort(filteredList, new Comparator<Transaction>() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                try {
                    Date date1 = sdf.parse(transaction1.getTime());
                    Date date2 = sdf.parse(transaction2.getTime());
                    if (date1 != null && date2 != null) {
                        return date1.compareTo(date2);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0; // Default return value if parsing fails
            }
        });

        // Notify the adapter that the data has changed
        if (transactionAdapter != null) {
            transactionAdapter.setData(filteredList);
            transactionAdapter.notifyDataSetChanged();
        }
    }

    private void sortTransactionsByDateDesc() {
        Collections.sort(filteredList, new Comparator<Transaction>() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());

            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                try {
                    Date date1 = sdf.parse(transaction1.getTime());
                    Date date2 = sdf.parse(transaction2.getTime());
                    if (date1 != null && date2 != null) {
                        // For descending order, reverse the order of the comparison
                        return date2.compareTo(date1);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0; // Default return value if parsing fails
            }
        });

        // Notify the adapter that the data has changed
        if (transactionAdapter != null) {
            transactionAdapter.setData(filteredList);
            transactionAdapter.notifyDataSetChanged();
        }
    }

    private void sortTransactionsByAmountAndTypeDesc() {
        Collections.sort(filteredList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                // Compare by type first
                int typeComparison = transaction1.getType().compareTo(transaction2.getType());

                if (typeComparison == 0) {
                    // If types are the same, compare by amount
                    // Parse amounts as double for numerical comparison
                    double amount1 = Double.parseDouble(transaction1.getAmount());
                    double amount2 = Double.parseDouble(transaction2.getAmount());

                    // For debit type, sort in descending order (highest amount first)
                    if (transaction1.getType().equals("Credit")) {
                        return Double.compare(amount2, amount1);
                    } else {
                        // For credit type, sort in ascending order (lowest amount first)
                        return Double.compare(amount1, amount2);
                    }
                } else {
                    // If types are different, use typeComparison result
                    return typeComparison;
                }
            }
        });

        // Notify the adapter that the data has changed
        if (transactionAdapter != null) {
            transactionAdapter.setData(filteredList);
            transactionAdapter.notifyDataSetChanged();
        }
    }

    private void sortTransactionsByAmountAndTypeAsc() {
        Collections.sort(filteredList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                // Compare by type first
                int typeComparison = transaction1.getType().compareTo(transaction2.getType());

                if (typeComparison == 0) {
                    // If types are the same, compare by amount
                    // Parse amounts as double for numerical comparison
                    double amount1 = Double.parseDouble(transaction1.getAmount());
                    double amount2 = Double.parseDouble(transaction2.getAmount());

                    // For debit type, sort in descending order (highest amount first)
                    if (transaction1.getType().equals("Credit")) {
                        return Double.compare(amount2, amount1);
                    } else {
                        // For credit type, sort in ascending order (lowest amount first)
                        return Double.compare(amount1, amount2);
                    }
                } else {
                    // If types are different, use typeComparison result
                    return typeComparison;
                }
            }
        });
        Collections.reverse(filteredList);
        // Notify the adapter that the data has changed
        if (transactionAdapter != null) {
            transactionAdapter.setData(filteredList);
            transactionAdapter.notifyDataSetChanged();
        }
    }


}