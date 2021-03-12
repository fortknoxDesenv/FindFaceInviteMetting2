package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.api.Invite;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterInvateActivity extends AppCompatActivity {
    private CalendarView calendarioInvite;
    private ImageView btnCalendarioInvite;
    private EditText txtNomeUserInvite;
    private EditText txtSobrenomeUserInvite;
    private EditText txtDuracaoReuniao;
    private EditText txtEmailInvite;
    private EditText txtDataInvite;
    private Button btnSelecionarData;
    private Button btnEnviarInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register_invate);

            txtEmailInvite = findViewById(R.id.txtEmailInvite);
            txtNomeUserInvite = findViewById(R.id.txtNomeUserInvite);
            txtSobrenomeUserInvite = findViewById(R.id.txtSobrenomeUserInvite);
            txtDataInvite = findViewById(R.id.txtDataInvite);
            txtDuracaoReuniao = findViewById(R.id.txtDuracaoReuniao);
            btnCalendarioInvite = findViewById(R.id.btnCalendarioInvite);
            calendarioInvite = findViewById(R.id.calendarioInvite);
            calendarioInvite.setMinDate(System.currentTimeMillis() - 1000);
            btnEnviarInvite = findViewById(R.id.btnEnviarInvite);
            btnSelecionarData = findViewById(R.id.btnSelecionarData);
            btnSelecionarData.setVisibility(View.GONE);
            calendarioInvite.setVisibility(View.GONE);
            final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);

            btnCalendarioInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        showCalendarAndHideAllOthersButtons();

                        if (getCurrentFocus() == null)
                            hideKeyboardAndroid();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            btnSelecionarData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        view.startAnimation(animScale);
                        hideCalendarAndShowAllOthersButtons();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            calendarioInvite.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    try {
                        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        String date = sdf.format(calendar.getTime());

                        txtDataInvite.setText(date);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            txtDataInvite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    try {
                        showCalendarAndHideAllOthersButtons();

                        if (!hasFocus) {
                            hideKeyboardAndroid();
                            txtDataInvite.setInputType(InputType.TYPE_NULL);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            btnEnviarInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        view.startAnimation(animScale);
                        Invite invite = new Invite();
                        invite.postRegisterInvite(txtNomeUserInvite.getText().toString(),
                                txtSobrenomeUserInvite.getText().toString(), txtEmailInvite.getText().toString(),
                                txtDataInvite.getText().toString(), txtDuracaoReuniao.getText().toString());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCalendarAndHideAllOthersButtons() {
        btnSelecionarData.setVisibility(View.VISIBLE);
        calendarioInvite.setVisibility(View.VISIBLE);
        txtEmailInvite.setVisibility(View.GONE);
        txtNomeUserInvite.setVisibility(View.GONE);
        txtDataInvite.setVisibility(View.GONE);
        txtDuracaoReuniao.setVisibility(View.GONE);
        btnCalendarioInvite.setVisibility(View.GONE);
        btnEnviarInvite.setVisibility(View.GONE);
    }

    public void hideCalendarAndShowAllOthersButtons() {
        btnSelecionarData.setVisibility(View.GONE);
        calendarioInvite.setVisibility(View.GONE);
        txtEmailInvite.setVisibility(View.VISIBLE);
        txtNomeUserInvite.setVisibility(View.VISIBLE);
        txtDataInvite.setVisibility(View.VISIBLE);
        txtDuracaoReuniao.setVisibility(View.VISIBLE);
        btnCalendarioInvite.setVisibility(View.VISIBLE);
        btnEnviarInvite.setVisibility(View.VISIBLE);
    }

    public void hideKeyboardAndroid() {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    public static void feedbackUserResponseApiRegister(String message){

    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, RegisterInvateActivity.class);
        from.startActivity(intent);
    }
}

