package com.anyvision.facekeyexample.activities.face;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.activities.CameraPreview;

public class TesteActivity extends AppCompatActivity {
private Camera mCamera;
private CameraPreview mPreview;
private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        mCamera = getCameraInstance();
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
//
//    private boolean prepareVideoRecorder(){
//        //mCamera = getCameraInstance();
//        mediaRecorder = new MediaRecorder();
//        mCamera.unlock();
//
//        mediaRecorder.setCamera(mCamera);
//
//    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, CustomVideoCameraActivity.class);
        from.startActivity(intent);
    }
}