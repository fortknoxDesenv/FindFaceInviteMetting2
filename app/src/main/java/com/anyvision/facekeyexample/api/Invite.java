package com.anyvision.facekeyexample.api;

import android.content.Context;
import android.widget.Toast;

import com.anyvision.facekeyexample.FacekeyApplication;
import com.anyvision.facekeyexample.models.Invite.InviteModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Invite {
    private static Context mContext;

    private enum StatusInvite {ERRO, SUCESS}



    public void postRegisterInvite(final String firstName, String lastName, String email, String dataInvite, String duracaoHorasTotalInvite) {
        String url = "http://192.168.5.233:8093";
        //String url = "https://localhost:44395";
        //client timeou fazer

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IInvite invite = retrofit.create(IInvite.class);

        Call<InviteModel> call = invite.postRegisterInvite(firstName, lastName, email, dataInvite, Integer.parseInt(duracaoHorasTotalInvite));
        call.enqueue(new Callback<InviteModel>() {
            @Override
            public void onResponse(Call<InviteModel> call, Response<InviteModel> response) {
                InviteModel result = response.body();
                if (result.getStatus().toUpperCase().equals(StatusInvite.SUCESS)){

                }

            }

            @Override
            public void onFailure(Call<InviteModel> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    private void showToast(String message) {

        Context context = getContext();
        if (context == null)
            mContext = FacekeyApplication.getAppContext();

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public static Context getContext() {
        return mContext;
    }
}
