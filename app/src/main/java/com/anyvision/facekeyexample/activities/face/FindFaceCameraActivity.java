package com.anyvision.facekeyexample.activities.face;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.activities.LoginActivity;
import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.prysm.Authentication;
import com.anyvision.facekeyexample.utils.mlkit.CameraSource;
import com.anyvision.facekeyexample.utils.mlkit.CameraSourcePreview;
import com.anyvision.facekeyexample.utils.mlkit.GraphicOverlay;

import org.jcodec.scale.BitmapUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FindFaceCameraActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUESTS = 1;
    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private static final String TAG = "Image labeling";
    private static Activity findFaceCameraActivity;
    private static TextView txtInstrucaoCamera;
    private static TextView contagemGravacao;
    private static int centralize = 1;
    private static int naoSeMexa = 2;
    private static String txtCentralizeRosto;
    private static String txtNaoSeMexa;
    private static String txtValidandoLiveness;
    private static Animation animScale;

    private static RelativeLayout color_screen;

    //teste
    private FocusView focusView;
    private static Bitmap[] arrayBitmap = new Bitmap[60];
    private static int vermelho = 0x33FF0000;
    private static int amarelo = 0x33FFFF00;
    private static int laranja = 0x33ffbf00;
    private static int verde = 0x339FE2BF;
    private static int azul = 0x336495ED;
    private static int azulclaro = 0x33CCCCFF;
    private static Authentication auth;
    private static int contagemColor = 10;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_find_face_camera);
        preview = findViewById(R.id.firePreview);
        graphicOverlay = findViewById(R.id.fireFaceOverlay);

        findFaceCameraActivity = this;
        txtInstrucaoCamera = findViewById(R.id.txtIntrucaoCamera);
        contagemGravacao = findViewById(R.id.contagemGravacao);

        txtCentralizeRosto = getText(R.string.centralizeRosto).toString();
        txtNaoSeMexa = getText(R.string.naoSeMexa).toString();
        txtValidandoLiveness = getText(R.string.validando).toString();

        GetVariables.getInstance().setIsRecording(true);

        createCameraSource();
        startCameraSource();

        animScale = AnimationUtils.loadAnimation(this, R.anim.scale1);

        color_screen = findViewById(R.id.oval_find_face);

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
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
        GetVariables.getInstance().setIsRecording(true);
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

    @SuppressLint("WrongConstant")
    public static void iniciaColorScreen(int i) {
        try{
           final GradientDrawable drawable = new GradientDrawable();
            switch (i) {
                case 1:
                    drawable.setColor(vermelho);
                    drawable.setShape(1);
                    drawable.setCornerRadius(10);
                    color_screen.startAnimation(animScale);
                    color_screen.setBackgroundDrawable(drawable);
//                color_screen.setBackgroundColor(vermelho);
                    break;
                case 2:
                    drawable.setColor(verde);
                    drawable.setShape(1);
                    color_screen.startAnimation(animScale);
                    color_screen.setBackgroundDrawable(drawable);
                    //color_screen.setBackgroundColor(verde);
                    break;
                case 3:
                    drawable.setColor(amarelo);
                    drawable.setCornerRadius(10);
                    drawable.setShape(1);
                    color_screen.startAnimation(animScale);
                    color_screen.setBackgroundDrawable(drawable);
                    //color_screen.setBackgroundColor(amarelo);
                    break;
                case 4:
                    drawable.setColor(azul);
                    drawable.setShape(1);
                    color_screen.setBackgroundDrawable(drawable);
                    color_screen.startAnimation(animScale);
                    //color_screen.setBackgroundColor(azul);
                    break;
                case 5:
                    drawable.setColor(laranja);
                    drawable.setShape(1);
                    color_screen.setBackgroundDrawable(drawable);
                    color_screen.startAnimation(animScale);
                    //color_screen.setBackgroundColor(laranja);
                default:
                    drawable.setColor(azulclaro);
                    drawable.setShape(1);
                    color_screen.setBackgroundDrawable(drawable);
                    color_screen.startAnimation(animScale);
                    //color_screen.setBackgroundColor(azulclaro);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void converteBitmapToVideoMP4(final Bitmap[] image){
        Executor service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Camera camera = GetVariables.getInstance().getCamera();
                    camera.unlock();
                    File file = GetVariables.getInstance().getFileMediaRecord();
                    AndroidSequenceEncoder enc = AndroidSequenceEncoder.create25Fps(file);
                    for (int i = 0; i < image.length; i++) {
                        enc.encodeNativeFrame(BitmapUtil.fromBitmap(image[i]));
                        Log.d("findEncoder1", "Inserindo no encoder  =>" + String.valueOf(i));
                    }
                    enc.finish();
                    Log.d("findEncoder1", "finishing END");
                    auth.livenessFindFace(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onBackPressed() {
        LoginActivity.startActivity(FindFaceCameraActivity.this);
        findFaceCameraActivity.finish();
    }

    public static void setContagemGravacao(int i){
        contagemGravacao.setText(String.valueOf(i));
        contagemGravacao.startAnimation(animScale);
    }

//        public static void setContagemGravacao(int color) {
//        color_screen.setBackgroundColor(color);
//    }
}