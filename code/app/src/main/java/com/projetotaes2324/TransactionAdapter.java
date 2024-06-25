package com.projetotaes2324;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList;
    private Context context;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    // Add this method to update the data in the adapter
    public void setData(List<Transaction> newData) {
        this.transactionList = newData;
        notifyDataSetChanged();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView textUser;
        private TextView textAmount;
        private TextView textDate;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            textUser = itemView.findViewById(R.id.textUser);
            textDate = itemView.findViewById(R.id.textDate);
            textAmount = itemView.findViewById(R.id.textAmount);
        }

        public void bind(Transaction transaction) {
            textDate.setText(transaction.getTime());
            if ("Debit".equals(transaction.getType())) {
                textAmount.setTextColor(ContextCompat.getColor(context, R.color.red));
                textAmount.setText("- " + transaction.getAmount() + "€");
            } else {
                textAmount.setText("+ " + transaction.getAmount() + "€");
                textAmount.setTextColor(ContextCompat.getColor(context, R.color.green));
            }

            // Retrieve the contact name based on the phone number
            String contactName = getContactName(context, transaction.getPhone_number());
            if (contactName != null) {
                textUser.setText(contactName);
            } else {
                textUser.setText(transaction.getPhone_number());
            }
        }

        // Function to retrieve the contact name based on the phone number
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
}