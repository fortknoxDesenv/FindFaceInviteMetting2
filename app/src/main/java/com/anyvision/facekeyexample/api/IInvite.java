package com.anyvision.facekeyexample.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IInvite {
    @POST("api/invite/PostRegisterInvite")
    Call<ResponseBody> postRegisterInvite(@Query("firstName") String firstName,
    @Query("lastName") String lastName, @Query("email") String email,
    @Query("dataInvite") String dataInvite, @Query("duracaoHorasTotalInvite") int duracaoHorasTotalInvite);
}
