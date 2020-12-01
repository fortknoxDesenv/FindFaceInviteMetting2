package com.anyvision.facekeyexample.utils.mlkit;

import android.graphics.Bitmap;

import java.nio.ByteBuffer;

//import com.google.firebase.ml.common.FirebaseMLException;

/** An inferface to process the images with different ML Kit detectors and custom image models. */
public interface VisionImageProcessor {

    /** Processes the images with the underlying machine learning models. */
    void process(ByteBuffer data, FrameMetadata frameMetadata, GraphicOverlay graphicOverlay)
        throws Exception;
            //throws FirebaseMLException;

    /** Processes the bitmap images. */
    void process(Bitmap bitmap, GraphicOverlay graphicOverlay);

    /** Stops the underlying machine learning model and release resources. */
    void stop();
}
