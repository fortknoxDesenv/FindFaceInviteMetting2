package com.anyvision.facekeyexample.models.Invite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InviteModel {

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("Message")
    @Expose
    private String message;

    public String getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}
