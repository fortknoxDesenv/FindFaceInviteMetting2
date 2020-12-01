package com.anyvision.facekeyexample.models.FaceDetection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Result {

    @SerializedName("created_date")
    @Expose
    public Date created_date;

    @SerializedName("modified_date")
    @Expose
    public Date modified_date;

    @SerializedName("dossier")
    @Expose
    public int dossier;

    @SerializedName("source_photo_name")
    @Expose
    public String source_photo_name;

    @SerializedName("source_photo")
    @Expose
    public String source_photo;

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

    @SerializedName("source_coords_left")
    @Expose
    public int source_coords_left;

    @SerializedName("source_coords_top")
    @Expose
    public int source_coords_top;

    @SerializedName("source_coords_right")
    @Expose
    public int source_coords_right;

    @SerializedName("source_coords_bottom")
    @Expose
    public int source_coords_bottom;

    @SerializedName("id")
    @Expose
    public String id;

    public void getTeste(){

    }
}
