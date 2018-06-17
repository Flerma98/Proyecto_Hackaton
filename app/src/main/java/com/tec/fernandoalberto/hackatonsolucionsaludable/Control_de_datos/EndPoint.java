package com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPoint {
    @GET(Constantes_web_service.GET_REPORTE)
    Call<Object> ObtenerReportes();

    @GET(Constantes_web_service.SAVE_PUTDATA)
    Call<Object> guardarPHMax(@Query("PHMax") String data);

    @GET(Constantes_web_service.SAVE_PUTDATA)
    Call<Object> guardarPHMin(@Query("PHMin") String data);

    @GET(Constantes_web_service.SAVE_PUTDATA)
    Call<Object> guardarCEMax(@Query("CEMax") String data);

    @GET(Constantes_web_service.SAVE_PUTDATA)
    Call<Object> guardarCEMin(@Query("CEMin") String data);
}
