package com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPoint {
    @GET(Constantes_web_service.GET_REPORTE)
    Call<Object> ObtenerReportes();

    @GET(Constantes_web_service.SAVE_PH)
    Call<Object> guardarPH(@Query("Data") String data);

    @GET(Constantes_web_service.SAVE_CE)
    Call<Object> guardarCE(@Query("Data") String data);
}
