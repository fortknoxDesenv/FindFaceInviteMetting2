package com.anyvision.facekeyexample.models.FaceDetection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IDsDossiers {

    @SerializedName("next_page")
    @Expose
    public Object next_page;

    @SerializedName("count")
    @Expose
    public int count;

    @SerializedName("results")
    @Expose
    public List<Result> results;

    public int[] getListaDossier() {
        int[] lista = new int[count];

        for (int i = 0; i < count; i++) {
            lista[i] = results.get(i).dossier;
        }
        return lista;
    }

    public int getSize(){
        return count;
    }
}
