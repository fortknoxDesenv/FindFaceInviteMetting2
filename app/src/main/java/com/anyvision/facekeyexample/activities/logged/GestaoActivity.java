package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.prysm.Authentication;
import com.anyvision.facekeyexample.R;

public class GestaoActivity extends AppCompatActivity {

    private Button btnFacilitiesGestao;
    private Button btnChamadoGestao;
    private Button btnGraficoGestao;
    private Authentication auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestao);
        btnFacilitiesGestao = findViewById(R.id.btnFacilitiesGestao);
        btnChamadoGestao = findViewById(R.id.btnChamadoGestao);
        btnGraficoGestao = findViewById(R.id.btnGraficoGestao);
        auth = new Authentication(GetVariables.getInstance().getServerUrl());

        //auth.requestTokenGestao();

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);

        btnFacilitiesGestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    view.startAnimation(animScale);
                FacilitiesActivity.startActivity(GestaoActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnChamadoGestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    view.startAnimation(animScale);
                    ChamadoActivity.startActivity(GestaoActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnGraficoGestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    view.startAnimation(animScale);
                    ChamadoGraficoActivity.startActivity(GestaoActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, GestaoActivity.class);
        from.startActivity(intent);
    }

    public void onBackPressed(){
        MainActivity.startActivity(GestaoActivity.this);
    }
}