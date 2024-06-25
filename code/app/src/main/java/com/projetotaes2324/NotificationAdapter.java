package com.projetotaes2324;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    private List<Notification> noticationList;
    private Context context;

    public NotificationAdapter(List<Notification> noticationList, Context context) {
        this.noticationList = noticationList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        Notification notification = noticationList.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return noticationList.size();
    }

    // Add this method to update the data in the adapter
    public void setData(List<Notification> newData) {
        this.noticationList = newData;
        notifyDataSetChanged();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private TextView textMessage;
        private TextView textDate,textViewView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.textViewUpper);
            textMessage = itemView.findViewById(R.id.textViewLower);
            textViewView = itemView.findViewById(R.id.textViewView);
            // Set a click listener for the button
        }

        public void bind(Notification notification) {
            textDate.setText(notification.getTime());
            if ("no".equals(notification.getChecked())) {
                textViewView.setVisibility(View.VISIBLE);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Handle the click event here
                        openDetailActivity(notification.getId(), notification.getNotification_id());
                    }
                });
            } else {
                textViewView.setVisibility(View.GONE);
            }

            // Retrieve the contact name based on the phone number
            String contactName = getContactName(context, notification.getSender_number());
            if (contactName != null) {
                textMessage.setText(notification.getMessage() + " " + contactName);
            } else {
                textMessage.setText(notification.getMessage() + " " + notification.getSender_number());
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

        private void openDetailActivity(String userPhoneNumber,String notification_id) {
            // Handle opening the detail activity with the provided data
            // Example: You can use an Intent to pass data to a new activity
            Intent intent = new Intent(itemView.getContext(), ListOfTransactions.class);
            intent.putExtra("userPhoneNumber", userPhoneNumber);
            intent.putExtra("notification_id", notification_id);
            itemView.getContext().startActivity(intent);
        }
    }
}
