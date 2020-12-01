package com.anyvision.facekeyexample.models.FaceDetection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Liveness {

    @SerializedName("alive")
    @Expose
    private boolean alive;

    @SerializedName("average_liveness")
    @Expose
    private double average_liveness;

    @SerializedName("best_face")
    @Expose
    private BestFace best_face;

    public Liveness getLiveness(){
        Liveness liveness = new Liveness();
        String teste = String.valueOf(liveness.alive);
        liveness.alive = alive;
        liveness.average_liveness = average_liveness;
        liveness.best_face = best_face;

        return liveness;
    }

    public boolean getAlive(){
        return alive;
    }

    public String getId(){
        if(best_face != null) {
            return best_face.getDetection_id();
        }
        else {
            return "";
        }
    }
}
