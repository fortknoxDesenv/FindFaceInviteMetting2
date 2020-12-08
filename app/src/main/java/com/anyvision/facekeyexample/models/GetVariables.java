package com.anyvision.facekeyexample.models;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.anyvision.facekeyexample.activities.face.AndroidSequenceEncoder;
import com.anyvision.facekeyexample.prysm.Authentication;

import java.io.File;

public class GetVariables {

    private static String localServerUrl;
    private static GetVariables getVariablesInstance;
    private static TextView etLocalServerUrl;
    private static TextView textviewFindFace;
    private static String etFindFaceUrl;
    private static String etUsername;
    private static String etRegisterUsername;
    private static String spTypeAccount;
    private static String nameAgencia;
    private Authentication auth;
    private static String etRegisterName;
    private static String etRegisterCargo;
    private static String etRegisterLocalAgencia;
    private static String txtLabelSliceClickChart;
    private static String txtOpcaoFacilities;

    private static MediaRecorder mediaRecorder;
    private static boolean statusMediaRecord = false;
    private static File fileMediaRecord;
    private static Camera camera;

    private GetVariables() {
        localServerUrl = "";
    }

    public static synchronized GetVariables getInstance() {
        if (getVariablesInstance == null) {
            getVariablesInstance = new GetVariables();
        }

        return getVariablesInstance;
    }

    public void SetTxtLabelSliceClickChart(String txtLabel) {
        txtLabelSliceClickChart = txtLabel;
    }

    public String GetTxtLabelSliceClickChart() {
        return txtLabelSliceClickChart;
    }

    public void setServerUrl(String url) {
        localServerUrl = url;
    }

    public String getServerUrl() {
        return localServerUrl;
    }

    public TextView getEtLocalServerUrl() {
        return etLocalServerUrl;
    }

    public void setEtLocalServerUrl(TextView etLocalServerUrl) {
        GetVariables.etLocalServerUrl = etLocalServerUrl;
    }

    public String getEtUsername() {
        return etUsername;
    }

    public void setEtUsername(String etUsername) {
        GetVariables.etUsername = etUsername;
    }

    public String getEtRegisterUsername() {
        return etRegisterUsername;
    }

    public void setEtRegisterUsername(String etRegisterUsername) {
        GetVariables.etRegisterUsername = etRegisterUsername;
    }

    //teste
    public void setNameRegister(String nameRegister) {
        GetVariables.etRegisterName = nameRegister;
    }

    public String getNameRegister() {
        return etRegisterName;
    }

    public void setCargoRegister(String cargoRegister) {
        GetVariables.etRegisterCargo = cargoRegister;
    }

    public String getCargoRegister() {
        return etRegisterCargo;
    }

    public void setLocalAgenciaRegister(String agenciaLocalRegister) {
        GetVariables.etRegisterLocalAgencia = agenciaLocalRegister;
    }

    public String getLocalRegister() {
        return etRegisterLocalAgencia;
    }

    public String getSpTypeAccount() {
        return spTypeAccount;
    }

    public void setSpTypeAccount(String spTypeAccount) {
        GetVariables.spTypeAccount = spTypeAccount;
    }

    public String getEtFindFaceUrl() {
        return etFindFaceUrl;
    }

    public void setEtFindFace(String etAnyvisionUrl) {
        GetVariables.etFindFaceUrl = etAnyvisionUrl;
    }

    public TextView getTextviewFindFace() {
        return textviewFindFace;
    }

    public void setTextviewFindFace(TextView textviewAnyvision) {
        GetVariables.textviewFindFace = textviewAnyvision;
    }

    public String getNameAgencia() {
        return nameAgencia;
    }

    public void setNameAgencia(String nmAgencia) {
        GetVariables.nameAgencia = nameAgencia;
    }

    public String getOpcaoFacilities() {
        return txtOpcaoFacilities;
    }

    public void setTxtOpcaoFacilities(String opcaoFacilities) {
        GetVariables.txtOpcaoFacilities = opcaoFacilities;
    }

    public boolean setCamera(Camera cam) {
        camera = cam;
        return true;
    }

    public Camera getCamera() {
        return camera;
    }

    public boolean unlockCamera() {
        camera.unlock();
        return true;
    }

    public void lockCamera() {
        camera.lock();
    }

    public MediaRecorder getMediaRecorder() {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
        }
        return mediaRecorder;
    }

    public void setMediaRecorder(MediaRecorder mdRecorder) {
        if (mediaRecorder == null) {
            getMediaRecorder();
        }
        mediaRecorder = mdRecorder;

    }

    public void setFileMediaRecorder(File file) {
        if (mediaRecorder == null)
            mediaRecorder = new MediaRecorder();

        fileMediaRecord = file;
    }

    public File getFileMediaRecord() {
        return fileMediaRecord;
    }

    public void setIsRecording(boolean isRecord) {
        statusMediaRecord = isRecord;
    }

    public boolean isRecording() {
        return statusMediaRecord;
    }
}