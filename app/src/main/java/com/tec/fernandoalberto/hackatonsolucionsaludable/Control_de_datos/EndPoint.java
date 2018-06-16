package com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoint {
    @GET(Constantes_web_service.GET_REPORTE)
    Call<Object> ObtenerReportes();
}
