package com.anyvision.facekeyexample.prysm;

import com.anyvision.facekeyexample.models.ChamadoGrafico;
import com.anyvision.facekeyexample.models.FaceDetection.DossierFaces;
import com.anyvision.facekeyexample.models.FaceDetection.Dossiers.NomeDossier;
import com.anyvision.facekeyexample.models.FaceDetection.IDsDossiers;
import com.anyvision.facekeyexample.models.FaceDetection.Liveness;
import com.anyvision.facekeyexample.models.Facilities;
import com.anyvision.facekeyexample.models.GetGroups.Groups;
import com.anyvision.facekeyexample.models.SolicitationExtension;
import com.anyvision.facekeyexample.models.VariableRow;
import com.anyvision.facekeyexample.models.VariableRowChamado;

import java.util.List;
import io.reactivex.Observable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface AuthToken {
    @GET("AppVisionService.svc/Open?clientProductDescription=AppMobile1&clientProductName=AppMobile&clientProductCompany=Prysm&clientProcessName=AppMobile&clientProcessVersion=1.0&clientHostName=local")
    Call<ResponseBody> getToken();

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/Login")
    Call<Void> signIn(@Header("SessionID") String SessionId, @Query("username") String username, @Query("hashpassword") String hashpassword);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/SetVariable?changeOnly=false&severity=-1&quality=-1")
    Call<Void> setVariable(@Header("SessionID") String SessionId, @Query("name") String name, @Query("newValue") String newValue);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/SetVariable?changeOnly=false&severity=-1&quality=-1")
    Call<Void> setVariableWithPulse(@Header("SessionID") String SessionId, @Query("name") String name, @Query("tempo") int tempo, @Query("valStart") String valStart, @Query("valEnd") String valEnd);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/Close")
    Call<Void> closeSession(@Header("SessionID") String SessionId);

    @GET("AppVisionService.svc/GetServerState")
    Call<ResponseBody> getServerStatus();

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/GetVariablesByFilter?filters=$V.App.AGENCIA.POC.AGENCIA0001*")
    Call<VariableRow> GetVariableRow(@Header("SessionID") String SessionID);

    @Headers({"Accept: application/xml "})
    @GET("AppVisionService.svc/GetVariablesByFilter?filters=$V.App.AGENCIA.POC.AGENCIA0001.Chamado*")
    Call<VariableRowChamado> GetVariableRowChamado(@Header("SessionID") String SessionID);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/GetVariableStatesByFilter?filters=$V.App.SOLICITA*")
    Call<SolicitationExtension> GetSolicitationExtension(@Header("SessionID") String SessionID);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/GetVariableStatesByFilter?filters=$V.App.REGIONAL.POC.*")
    Call<SolicitationExtension> GetSolicitHistApprovedReproved(@Header("SessionID") String SessionID);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/GetVariablesByFilter?filters=$V.App.AGENCIA.POC.AGENCIA0001.Facilities*,Type=Enum")
    Call<Facilities> GetFacilities(@Header("SessionID") String SessionID);

    @Headers({"Accept: application/xml"})
    @GET("AppVisionService.svc/GetVariableStatesByFilter?filters=$V.Gestao.Graficos.Chamados*")
    Call<ChamadoGrafico> GetGestaoControleSalas(@Header("SessionID") String SessionId);

    @Headers({"Content-Type: video/mp4"})
    @POST("v1/video-liveness?return_normalized=false&return_photo=false&return_detection=true")
        //Call<ResponseBody> posLiveness(@Body RequestBody video);
    Call<Liveness> posLiveness(@Body RequestBody video);

    @GET("verify")
    Call<DossierFaces> getVerificaFaceDossie(@Query("face1") String face1, @Query("dossier_id") int dossier_id);

    @GET("dossier-faces")
    Call<IDsDossiers> getListaIDsDossiers();

    @GET("dossiers")
    Call<NomeDossier> getNomesDossiers(@Query("name_contains") String name_contains);
}
