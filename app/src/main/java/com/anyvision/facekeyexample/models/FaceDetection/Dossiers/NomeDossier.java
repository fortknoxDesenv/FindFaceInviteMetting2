package com.anyvision.facekeyexample.models.FaceDetection.Dossiers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NomeDossier {

    @SerializedName("next_page")
    @Expose
    public Object next_page;

    @SerializedName("prev_page")
    @Expose
    public Object prev_page;

    @SerializedName("results")
    @Expose
    public ArrayList<Result> results;

    public String[] getNamesDossiers() {
        String[] listaNomes = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            listaNomes[i] = results.get(i).name;
        }
        return listaNomes;
    }

    public ArrayList<Result> getResults(){
        return results;
    }
}
