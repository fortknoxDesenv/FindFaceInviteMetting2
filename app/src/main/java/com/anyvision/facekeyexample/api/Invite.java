package com.anyvision.facekeyexample.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Invite {

    public void postRegisterInvite(String firstName, String lastName, String email, String dataInvite, String duracaoHorasTotalInvite) {
        String url = "http://192.168.5.233:8093";
        //String url = "https://localhost:44395";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        IInvite invite = retrofit.create(IInvite.class);

        Call<ResponseBody> call = invite.postRegisterInvite(firstName, lastName, email, dataInvite, Integer.parseInt(duracaoHorasTotalInvite));
        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody result = response.body();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
             String teste = t.getMessage();
            }
        });
    }
}
