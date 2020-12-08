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
    private MediaRecorder mediaRecorder = GetVariables.getInstance().getMediaRecorder();
    private Camera camera = null;
    private FindFaceCameraActivity findFaceCameraActivity;
    private int contador;
    private int centralize = 1;
    private int naoSeMexa = 2;
    private int validando = 3;
    private int instrucaoAtual = 0;
    private int contagem = 3;
    private Bitmap[] listaBitmap = new Bitmap[60];
    private int itemAddLista = 0;

    //teste
    private int contagemColor = 0;
    File file = GetVariables.getInstance().getFileMediaRecord();
    private AndroidSequenceEncoder enc;

        private CountDownTimer countDownTimer = new CountDownTimer(4000, 1500) {
//    private CountDownTimer countDownTimer = new CountDownTimer(4000, 500) {
        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("tempo", String.valueOf(millisUntilFinished));
            findFaceCameraActivity.setContagemGravacao(contagem);
            contagem = contagem - 1;

//            findFaceCameraActivity.iniciaColorScreen(contagemColor);
//            contagemColor++;
//            if (contagemColor == 5)
//                contagemColor = 0;
        }

        @Override
        public void onFinish() {
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
        if (faces.size() == 0)
            centralizarFace();

        for (int i = 0; i < faces.size(); ++i)

            if(faces.get(i).getBoundingBox().left > 30 && faces.get(i).getBoundingBox().left < 140 &&
                faces.get(i).getBoundingBox().right > 280 && faces.get(i).getBoundingBox().right < 450 &&
                    faces.get(i).getBoundingBox().top > 80 && faces.get(i).getBoundingBox().top < 250 &&
                     faces.get(i).getBoundingBox().bottom > 440 && faces.get(i).getBoundingBox().bottom < 530){

            instrucaoNaoSeMexa();

            //if (contador >= 40) {
                if (itemAddLista < 60) {
                    try {
                        listaBitmap[itemAddLista] = originalCameraImage;
                        //if(itemAddLista == 0)
                        //countDownTimer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (itemAddLista == 60) {
                    //findFaceCameraActivity.iniciarConversao(listaBitmap);
                    //teste123(listaBitmap);
//                    countDownTimer.start();
                    findFaceCameraActivity.converteBitmapToVideoMP4(listaBitmap);
                    countDownTimer.start();
                    //findFaceCameraActivity.finishFindFaceCameraActivity();
                }
                itemAddLista = itemAddLista + 1;
            //}

//            if (contador == 40) {
//                contador = 0;
//                if (GetVariables.getInstance().isRecording()) {
//                    prepareMediaRecorder();
//                    GetVariables.getInstance().setIsRecording(false);
//                }
//            }
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
//    public void teste123(Bitmap[] image) {
//        try {
//            File file = GetVariables.getInstance().getFileMediaRecord();
//            AndroidSequenceEncoder enc = AndroidSequenceEncoder.create25Fps(file);
//            for (int i = 0; i < image.length; i++) {
//                enc.encodeNativeFrame(BitmapUtil.fromBitmap(image[i]));
//                Log.d("findEncoder1", "Inserindo no encoder  =>" + String.valueOf(i));
//            }
//            enc.finish();
//            Log.d("findEncoder1", "finishing END");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void centralizarFace() {
        if (instrucaoAtual != centralize) {
            findFaceCameraActivity.setTextInstrucaoCamera(centralize);
            instrucaoAtual = centralize;
        }
    }

    public void instrucaoNaoSeMexa() {
        if (instrucaoAtual != naoSeMexa) {
            findFaceCameraActivity.setTextInstrucaoCamera(naoSeMexa);
            instrucaoAtual = naoSeMexa;
        }
    }




    private void prepareMediaRecorder() {
        try {
            int maxDurationInMs = 180000;
            long maxFileSizeInBytes = 1000000;

            camera = GetVariables.getInstance().getCamera();
            findFaceCameraActivity.setTextInstrucaoCamera(validando);
            instrucaoAtual = validando;
            mediaRecorder = new MediaRecorder();

//            camera.unlock();
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

            findFaceCameraActivity.iniciaColorScreen(6);
            camera.unlock();
            try {
                mediaRecorder.prepare();
                //Thread.sleep(2000);
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


