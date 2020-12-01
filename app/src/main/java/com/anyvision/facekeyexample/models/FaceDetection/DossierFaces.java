package com.anyvision.facekeyexample.models.FaceDetection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DossierFaces {

    @SerializedName("confidence")
    @Expose
    private Confidence confidence;

    public Double getResultado(){
        return confidence.average_conf;
    }

}
