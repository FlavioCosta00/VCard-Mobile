package com.projetotaes2324;

public class Transaction {

    private String time;
    private String amount;
    private String id;
    private String type;
    private String phone_number;

    public Transaction(String time, String amount, String id, String type, String phone_number) {
        this.time = time;
        this.amount = amount;
        this.id = id;
        this.type = type;
        this.phone_number = phone_number;
    }

    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
