package com.anyvision.facekeyexample.models.FaceDetection;

import org.simpleframework.xml.Element;

public class BestFace {

    @Element(name = "liveness", required = false)
    private double liveness;

    @Element(name = "quality", required = false)
    private double quality;

    @Element(name = "bbox", required = false)
    private Bbox bbox;

    @Element(name = "detection_id", required = false)
    private String detection_id;

    @Element(name = "photo", required = false)
    private String photo;

    @Element(name = "normalized", required = false)
    private String normalized;

    @Element(name = "frame_no", required = false)
    private int frame_no;

    @Element(name = "frame_ts", required = false)
    private double frame_ts;

    public String getDetection_id() {
        return detection_id;
    }
}
