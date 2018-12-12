package com.sep.viasocial.Model;

public class GroupChat {

    private String sender;
    private String receiver;
    private String message;
    private String username;
    private String photoUrl;
    //private boolean isseen;

    public GroupChat(String sender, String receiver, String message, String photoUrl){ //, boolean isseen) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.photoUrl = photoUrl;
        //this.isseen = isseen;
    }

    public GroupChat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /*public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }*/
}
