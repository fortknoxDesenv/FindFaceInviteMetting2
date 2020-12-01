package com.anyvision.facekeyexample.activities.face;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.activities.LoginActivity;
import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.utils.mlkit.CameraSource;
import com.anyvision.facekeyexample.utils.mlkit.CameraSourcePreview;
import com.anyvision.facekeyexample.utils.mlkit.GraphicOverlay;

import java.io.File;
import java.io.IOException;

public class FindFaceCameraActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUESTS = 1;
    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private static final String TAG = "Image labeling";
    private static Activity findFaceCameraActivity;
    private static TextView txtInstrucaoCamera;
    private static int centralize = 1;
    private static int naoSeMexa = 2;
    private static String txtCentralizeRosto;
    private static String txtNaoSeMexa;
    private static String txtValidandoLiveness;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_find_face_camera);
        preview = findViewById(R.id.firePreview);
        graphicOverlay = findViewById(R.id.fireFaceOverlay);

        findFaceCameraActivity = this;
        txtInstrucaoCamera = findViewById(R.id.txtIntrucaoCamera);

        txtCentralizeRosto = getText(R.string.centralizeRosto).toString();
        txtNaoSeMexa = getText(R.string.naoSeMexa).toString();
        txtValidandoLiveness = getText(R.string.validando).toString();

        createCameraSource();
        startCameraSource();
    }

    private void createCameraSource() {
        //Se não existir camera entao cria uma nova
        if (cameraSource == null)
            cameraSource = new CameraSource(this, graphicOverlay);

        //cameraSource.setMachineLearningFrameProcessor(new FaceDetectionProcessor(getResources()));

        File file = new File(getExternalFilesDir(null) + "/faceCamera.mp4");
        GetVariables.getInstance().setFileMediaRecorder(file);

        cameraSource.setMachineLearningFrameProcessor(new FaceContourDetectorProcessor());
    }

    /**
     * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private void startCameraSource() {
        if (cameraSource != null) {
            try {
                if (preview == null) {
                    Log.d(TAG, "resume: Preview is null");
                }
                if (graphicOverlay == null) {
                    Log.d(TAG, "resume: graphOverlay is null");
                }
                preview.start(cameraSource, graphicOverlay);

            } catch (IOException e) {
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

    public void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
        cameraSource.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, FindFaceCameraActivity.class);
        from.startActivity(intent);
    }

    public static void finishFindFaceCameraActivity() {
        FindFaceResultActivity.startActivity(findFaceCameraActivity);
    }

    public static void setTextInstrucaoCamera(int instrucao) {
        if (instrucao == centralize)
            txtInstrucaoCamera.setText(txtCentralizeRosto);

        else if (instrucao == naoSeMexa) {
            txtInstrucaoCamera.setText(txtNaoSeMexa);
        } else {
            txtInstrucaoCamera.setText(txtValidandoLiveness);
        }
    }

    public void onBackPressed() {
        LoginActivity.startActivity(FindFaceCameraActivity.this);
        findFaceCameraActivity.finish();
    }
}