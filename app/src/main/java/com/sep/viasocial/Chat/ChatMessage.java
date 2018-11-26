package com.sep.viasocial.Chat;

public class ChatMessage {

    private String message;
    private String username;
    private String photoUrl;

    public ChatMessage() {
    }

    public ChatMessage(String message, String name, String photoUrl) {
        this.message = message;
        this.username = name;
        this.photoUrl = photoUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
