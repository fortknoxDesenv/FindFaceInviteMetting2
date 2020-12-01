package com.anyvision.facekeyexample.activities.face;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.utils.mlkit.CameraImageGraphic;
import com.anyvision.facekeyexample.utils.mlkit.FrameMetadata;
import com.anyvision.facekeyexample.utils.mlkit.GraphicOverlay;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FaceContourDetectorProcessor extends VisionProcessorBase<List<FirebaseVisionFace>> {
    private static final String TAG = "FaceContourDetectorProc";
    private MediaRecorder mediaRecorder;
    private Camera camera = null;
    private FindFaceCameraActivity findFaceCameraActivity;
    private int contador;
    private int centralize = 1;
    private int naoSeMexa = 2;
    private int validando = 3;
    private int instrucaoAtual = 0;
    private int contagem = 3;

    private CountDownTimer countDownTimer = new CountDownTimer(4000, 1500) {

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("tempo", String.valueOf(millisUntilFinished));
            findFaceCameraActivity.setContagemGravacao(contagem);
            contagem = contagem - 1;
        }

        @Override
        public void onFinish() {
            mediaRecorder.stop();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            findFaceCameraActivity.finishFindFaceCameraActivity();
        }
    };

    private final FirebaseVisionFaceDetector detector;

    public FaceContourDetectorProcessor() {
        FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                        .setContourMode(FirebaseVisionFaceDetectorOptions.NO_CONTOURS)
                        .setMinFaceSize(0.95f)
                        .build();

        detector = FirebaseVision.getInstance().getVisionFaceDetector(options);
    }

    @Override
    public void stop() {
        try {
            detector.close();
        } catch (IOException e) {
            Log.e(TAG, "Exception thrown while trying to close Face Contour Detector: " + e);
        }
    }

    @Override
    protected Task<List<FirebaseVisionFace>> detectInImage(FirebaseVisionImage image) {
        return detector.detectInImage(image);
    }

    @Override
    protected void onSuccess(
            @Nullable Bitmap originalCameraImage,
            @NonNull List<FirebaseVisionFace> faces,
            @NonNull FrameMetadata frameMetadata,
            @NonNull GraphicOverlay graphicOverlay) {
        graphicOverlay.clear();
        if (originalCameraImage != null) {
            CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
            graphicOverlay.add(imageGraphic);
        }

        if (faces.size() == 1) {
            contador++;
        } else {
            contador = 0;
        }

        if (faces.size() == 0 && instrucaoAtual != centralize) {
            findFaceCameraActivity.setTextInstrucaoCamera(centralize);
            instrucaoAtual = centralize;
        }

        for (int i = 0; i < faces.size(); ++i) {
            if (instrucaoAtual != naoSeMexa) {
                findFaceCameraActivity.setTextInstrucaoCamera(naoSeMexa);
                instrucaoAtual = naoSeMexa;
            }

            Log.d("contador", String.valueOf(contador));
            if (contador > 40) {
                contador = 0;
                GetVariables.getInstance().setStatusMediaRecord(true);
                if (GetVariables.getInstance().isRecording()) {
                    prepareMediaRecorder();
                    GetVariables.getInstance().setStatusMediaRecord(false);
                }
            }
            FirebaseVisionFace face = faces.get(i);
            FaceContourGraphic faceGraphic = new FaceContourGraphic(graphicOverlay, face);
            graphicOverlay.add(faceGraphic);
            Log.d("tamanhoface", "left=" + face.getBoundingBox().left + "   right=" + face.getBoundingBox().right + "   top=" + face.getBoundingBox().top + "   bottom=" + face.getBoundingBox().bottom);
        }
        graphicOverlay.postInvalidate();
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Face detection failed " + e);
    }

    private void prepareMediaRecorder() {
        try {
            int maxDurationInMs = 180000;
            long maxFileSizeInBytes = 1000000;

            camera = GetVariables.getInstance().getCamera();
            findFaceCameraActivity.setTextInstrucaoCamera(validando);
            instrucaoAtual = validando;
            mediaRecorder = new MediaRecorder();
            camera.unlock();
            mediaRecorder.setCamera(camera);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setMaxDuration(maxDurationInMs);
            File file = GetVariables.getInstance().getFileMediaRecord();
            mediaRecorder.setOutputFile(file.getPath());
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
            mediaRecorder.setMaxFileSize(maxFileSizeInBytes);
            mediaRecorder.setVideoSize(640, 480);
            mediaRecorder.setVideoEncodingBitRate(3000000);
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaRecorder.start();
            countDownTimer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


