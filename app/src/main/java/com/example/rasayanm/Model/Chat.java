package com.example.rasayanm.Model;

import java.util.ArrayList;

public class Chat {
    String id;
    ArrayList<String> person;
    String userToken;
    String msg;

    Chat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getPerson() {
        return person;
    }

    public void setPerson(ArrayList<String> person) {
        this.person = person;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Chat(String id, ArrayList<String> person, String userToken, String msg) {
        this.id = id;
        this.person = person;
        this.userToken = userToken;
        this.msg = msg;
    }
}
