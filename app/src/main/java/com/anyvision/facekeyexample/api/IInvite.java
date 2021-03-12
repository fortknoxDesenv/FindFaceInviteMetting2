package com.anyvision.facekeyexample.api;

import com.anyvision.facekeyexample.models.Invite.InviteModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IInvite {
    @POST("api/invite/PostRegisterInvite")
    Call<InviteModel> postRegisterInvite(@Query("firstName") String firstName,
                                         @Query("lastName") String lastName, @Query("email") String email,
                                         @Query("dataInvite") String dataInvite, @Query("duracaoHorasTotalInvite") int duracaoHorasTotalInvite);
}
