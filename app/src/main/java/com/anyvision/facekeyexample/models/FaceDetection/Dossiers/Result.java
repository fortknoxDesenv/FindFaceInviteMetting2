package com.anyvision.facekeyexample.models.FaceDetection.Dossiers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Result {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("active")
    @Expose
    public boolean active;

    @SerializedName("created_date")
    @Expose
    public Date created_date;

    @SerializedName("modified_date")
    @Expose
    public Date modified_date;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("comment")
    @Expose
    public String comment;

    @SerializedName("dossier_lists")
    @Expose
    public List<Integer> dossier_lists;

    @SerializedName("face_count")
    @Expose
    public int face_count;

    @SerializedName("has_faces")
    @Expose
    public boolean has_faces;

    @SerializedName("meta")
    @Expose
    public Meta meta;

    @SerializedName("person_id")
    @Expose
    public String person_id;
}
