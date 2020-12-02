//package com.anyvision.facekeyexample.activities.face;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.hardware.Camera;
//import android.media.MediaRecorder;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import com.anyvision.facekeyexample.R;
//import com.anyvision.facekeyexample.activities.CameraPreview;
//
//import java.io.File;
//import java.io.IOException;
//
//public class CustomVideoCameraActivity extends Activity implements SurfaceHolder.Callback {
//    private static final String TAG = "CAMERA_TURTURIAL";
//    private SurfaceView surfaceView;
//    private SurfaceHolder surfaceHolder;
//    private Camera camera;
//    //    private CameraPreview mPreView;
//    private CameraPreview mPreView;
//
//    private boolean previewRunning;
//
//    private MediaRecorder mediaRecorder;
//    //    private final int maxDurationInMs = 20000;
//    private final int maxDurationInMs = 20000;
//    private final long maxFileSizeInBytes = 500000;
//    private final int videoFramesPerSecond = 30;
//    private File tempFile;
//
//    private FrameLayout preview;
//    private Camera.Face[] mFaces;
//    FaceDetectionListener faceDetectionListener;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom_video_camera);
//        surfaceView = findViewById(R.id.surface_camera2);
//        surfaceHolder = surfaceView.getHolder();
//        surfaceHolder.addCallback(this);
//        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        preview = (FrameLayout) findViewById(R.id.camera_preview);
//
//        camera = getCameraInstance();
//
//
//        mPreView = new CameraPreview(this, camera);
//        preview.addView(mPreView);
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        camera.startPreview();
//        Log.d("teste", String.valueOf(camera.getParameters().getMaxNumDetectedFaces()));
//
//        boolean temCamera = checkCameraHardware(getApplicationContext());
//        int qtdCamera = Camera.getNumberOfCameras();
//
//        //startRecording();
//
//          faceDetectionListener = new FaceDetectionListener() {
//            @Override
//            public void onFaceDetection(Camera.Face[] faces, Camera camera) {
//
//                Log.d("onFaceDetection", "Number of Faces:" + faces.length);
//                // Update the view now!
//                // mFaceView.setFaces(faces);
//            }
//        };
//
//    }
//
//    public boolean startRecording() {
//        try {
//            camera.getParameters();
//            camera.unlock();
//
//            mediaRecorder = new MediaRecorder();
//            mediaRecorder.setCamera(camera);
//            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//            //mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mediaRecorder.setMaxDuration(maxDurationInMs);
//
//            tempFile = new File(getExternalFilesDir(null) + "/faceClient1.mp4");
//
//            mediaRecorder.setOutputFile(tempFile.getPath());
//
//            //mediaRecorder.setVideoFrameRate(videoFramesPerSecond);
//            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
//            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
////            mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
//            mediaRecorder.setMaxFileSize(maxFileSizeInBytes);
//
//            //   try {
//            mediaRecorder.prepare();
////            } catch (IllegalStateException e) {
////                return false;
////            }
//            mediaRecorder.start();
//
//            return true;
////        } catch (IllegalStateException e) {
////            e.printStackTrace();
////            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private boolean checkCameraHardware(Context context) {
//        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static Camera getCameraInstance() {
//        Camera c = null;
//        try {
//            c = Camera.open(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//            camera.setFaceDetectionListener(faceDetectionListener);
//            startFaceDetection();
//
//            camera.setPreviewDisplay(holder);
//            camera.startPreview();
//
//            //startRecording();
//        } catch (IOException e) {
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        if (surfaceHolder.getSurface() == null) {
//            return;
//        }
//
//        try {
//            camera.stopPreview();
//            camera.startFaceDetection();
//            startFaceDetection();
//        } catch (Exception e) {
//        }
//
//        //set preview size and make any resize, rotate of reformatting changes here
//        // start preview with new settings
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//            camera.startPreview();
//        } catch (Exception e) {
//        }
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//
//    }
//
//    public static void startActivity(Context from) {
//        Intent intent = new Intent(from, CustomVideoCameraActivity.class);
//        from.startActivity(intent);
//    }
//
//    public void onResume() {
//        super.onResume();
//    }
//
//    public void onDestroy() {
//        super.onDestroy();
//        camera.release();
//    }
//
//    public void onPause() {
//        super.onPause();
//        releaseMediaRecorder();
//        releaseCamera();
//    }
//
//    private void releaseMediaRecorder() {
//        if (mediaRecorder != null) {
//            //mediaRecorder.stop();
//            mediaRecorder.reset();
//            mediaRecorder.release();
//            mediaRecorder = null;
//            camera.lock();
//        }
//    }
//
//    private void releaseCamera() {
//        if (camera != null)
//            camera.release();
//        camera = null;
//    }
//
//    public void startFaceDetection() {
//        //try starting face detection
//        Camera.Parameters params = camera.getParameters();
//
//        //start face detection only after preview has started
//        if (params.getMaxNumDetectedFaces() > 0) {
//            camera.startFaceDetection();
//        }
//    }
//}
//
//
//
//
//
