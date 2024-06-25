package com.projetotaes2324;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

        private List<Contact> contactList;
        private String userPhoneNumber;

        public ContactAdapter(List<Contact> contactList,String userPhoneNumber) {
            this.contactList = contactList;
            this.userPhoneNumber = userPhoneNumber;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contact contact = contactList.get(position);
            holder.bind(contact, userPhoneNumber);
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        public static class ContactViewHolder extends RecyclerView.ViewHolder {

            private TextView textName;
            private TextView textPhoneNumber;
            private TextView textVCard;

            public ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                textName = itemView.findViewById(R.id.textName);
                textPhoneNumber = itemView.findViewById(R.id.textPhoneNumber);
                textVCard = itemView.findViewById(R.id.textVCard);
            }

            public void bind(Contact contact,String userPhoneNumber) {
                textName.setText(contact.getName());
                textPhoneNumber.setText(contact.getPhone_number());
                if ("yes".equals(contact.getVcard())) {
                    // If yes, show the "VCard" message
                    textVCard.setVisibility(View.VISIBLE);
                    textVCard.setText("VCard");
                    // Make the itemView clickable
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Handle the click event here
                            openDetailActivity(userPhoneNumber,contact.getName(),contact.getPhone_number());
                        }
                    });
                } else {
                    // If no, hide the textVCard TextView
                    textVCard.setText("");
                }
            }

            private void openDetailActivity(String userPhoneNumber,String name,String phoneNumberSendTo) {
                // Handle opening the detail activity with the provided data
                // Example: You can use an Intent to pass data to a new activity
                Intent intent = new Intent(itemView.getContext(), SendMoney.class);
                intent.putExtra("userPhoneNumber", userPhoneNumber);
                intent.putExtra("phoneNumberSendTo", phoneNumberSendTo);
                intent.putExtra("nameContact", name);
                itemView.getContext().startActivity(intent);
            }

        }
    }