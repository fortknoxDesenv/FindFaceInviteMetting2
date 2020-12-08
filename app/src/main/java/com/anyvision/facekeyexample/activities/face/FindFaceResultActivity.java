package com.anyvision.facekeyexample.activities.face;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.activities.logged.MainActivity;
import com.anyvision.facekeyexample.activities.logged.SolicitationExtensionActivity;
import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.prysm.Authentication;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;

public class FindFaceResultActivity extends AppCompatActivity {

    private static View progressBar;
    private static TextView resultText;
    private static ImageView resultImage;
    private static View tryAgainContainer;
    private static View horizontalSeparator;
    private static String typeAccount;
    private static Authentication auth;
    private static Activity findFaceResultActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_face_result);
        progressBar = findViewById(R.id.progress_bar_find_face);
        resultText = findViewById(R.id.result_text_find_face);
        resultImage = findViewById(R.id.result_img_find_face);
        tryAgainContainer = findViewById(R.id.try_again_container_find_face);
        horizontalSeparator = findViewById(R.id.horizontal_separator_find_face);
        typeAccount = GetVariables.getInstance().getSpTypeAccount();
        findFaceResultActivity = this;
        GetVariables.getInstance().setIsRecording(false);
//        File file = new File(getExternalFilesDir(null) + "/faceCamera.mp4");
//        auth.livenessFindFace(file);

        authenticate();

        tryAgainContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FindFaceCameraActivity.startActivity(FindFaceResultActivity.this);
            }
        });
    }

    private void authenticate() {
        resultText.setVisibility(View.GONE);
        resultImage.setVisibility(View.GONE);
        tryAgainContainer.setVisibility(View.GONE);
        horizontalSeparator.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void onSuccess() {
        if (typeAccount.equals("REGIONAL")) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("AGENCIA");
            FirebaseMessaging.getInstance().subscribeToTopic("REGIONAL");

            auth.requestToken("aprovaReprovaExtesao", "geral");
            SolicitationExtensionActivity.startActivity(findFaceResultActivity);
        } else {
            MainActivity.startActivity(findFaceResultActivity);
        }
        findFaceResultActivity.finish();
    }

    public static void onResult(String msg) {
        resultText.setVisibility(View.VISIBLE);
        resultImage.setVisibility(View.VISIBLE);
        tryAgainContainer.setVisibility(View.VISIBLE);
        horizontalSeparator.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        resultText.setText(msg);
//        if (isSuccess) {
//            resultImage.setImageDrawable(getDrawable(R.drawable.success));
//        } else {
//            resultImage.setImageDrawable(getDrawable(R.drawable.failure));
//        }
//        File videoAndImageDir = AppData.getVideo().getParentFile();
//        AppData.getVideo().delete();
//        videoAndImageDir.delete();
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, FindFaceResultActivity.class);
        from.startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        FindFaceCameraActivity.startActivity(FindFaceResultActivity.this);
        finish();
    }
}
