package com.anyvision.facekeyexample.models.FaceDetection;

import org.simpleframework.xml.Element;

public class Bbox {

    @Element(name = "left", required = false)
    public int left;

    @Element(name = "top", required = false)
    public int top;

    @Element(name = "right", required = false)
    public int right;

    @Element(name = "bottom", required = false)
    public int bottom;
}
