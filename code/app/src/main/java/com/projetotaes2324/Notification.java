package com.projetotaes2324;

public class Notification {

    private String id;
    private String notification_id;
    private String time;
    private String message;
    private String checked;
    private String sender_number;

    public Notification(String id, String time, String message, String checked,String sender_number,String notification_id) {
        this.id = id;
        this.time = time;
        this.message = message;
        this.checked = checked;
        this.sender_number = sender_number;
        this.notification_id= notification_id;
    }

    public String getNotification_id() {
        return notification_id;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getChecked() {
        return checked;
    }

    public String getSender_number() {
        return sender_number;
    }
}
