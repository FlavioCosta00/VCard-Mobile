package com.projetotaes2324;

public class Contact {
    private String name;
    private String phone_number;
    private String vcard;

    // Constructor with parameters
    public Contact(String name, String vcard, String phone_number) {
        this.name = name;
        this.vcard = vcard;
        this.phone_number=phone_number;
    }

    public String getName() {
        return name;
    }

    public String getVcard() {
        return vcard;
    }

    public void setVcard(String vcard) {
        this.vcard = vcard;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
