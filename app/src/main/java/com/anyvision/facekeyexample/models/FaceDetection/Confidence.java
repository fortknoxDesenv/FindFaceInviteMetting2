package com.anyvision.facekeyexample.models.FaceDetection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Confidence {

    @SerializedName("average_conf")
    @Expose
    public double average_conf;
}
