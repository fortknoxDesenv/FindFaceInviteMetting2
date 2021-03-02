package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;

public class RegisterInvateActivity extends AppCompatActivity {
    private CalendarView calendarioInvite;
    private ImageView btnCalendarioInvite;
    private EditText txtNomeUserInvite;
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
            txtDataInvite = findViewById(R.id.txtDataInvite);
            txtDuracaoReuniao = findViewById(R.id.txtDuracaoReuniao);
            btnCalendarioInvite = findViewById(R.id.btnCalendarioInvite);
            calendarioInvite = findViewById(R.id.calendarioInvite);
            calendarioInvite.setMinDate(System.currentTimeMillis() - 1000);
            btnEnviarInvite = findViewById(R.id.btnEnviarInvite);
            btnSelecionarData = findViewById(R.id.btnSelecionarData);
            btnSelecionarData.setVisibility(View.GONE);
            calendarioInvite.setVisibility(View.GONE);

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
                        String date = getMonthSelectedInPortuguese(dayOfMonth, month, year);
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

    public String getMonthSelectedInPortuguese(int dayOfMonth, int month, int year) {
        String[] meses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String mesSelecionado = meses[month];
        String date = dayOfMonth + "/" + (mesSelecionado) + "/" + year;
        return date;
    }

    public void hideKeyboardAndroid() {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, RegisterInvateActivity.class);
        from.startActivity(intent);
    }
}