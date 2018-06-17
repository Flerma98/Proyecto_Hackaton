package com.tec.fernandoalberto.hackatonsolucionsaludable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.Constantes_web_service;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.EndPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Parametros extends AppCompatActivity {

    public EndPoint endPoint;
    TextView txtPHMax, txtPHMin, txtCEMax, txtCEMin;
    Button btnEnviarPH, btnEenviarCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);
        txtPHMax= findViewById(R.id.txtPHMayor);
        txtPHMin= findViewById(R.id.txtPHMenor);
        txtCEMax= findViewById(R.id.txtCEMayor);
        txtCEMin= findViewById(R.id.txtCEMenor);
        btnEnviarPH= findViewById(R.id.btnEnviarPH);
        btnEenviarCE= findViewById(R.id.btnEnviarCE);

        btnEnviarPH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtPHMax.getText().toString().length()==0||txtPHMin.getText().toString().length()==0) {
                }else{
                    SubirDatosDeJSonPH(txtPHMax.getText().toString(), txtPHMin.getText().toString());
                }
            }
        });
        btnEenviarCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCEMax.getText().toString().length()==0||txtCEMin.getText().toString().length()==0) {
                }else{
                    SubirDatosDeJSonPH(txtCEMax.getText().toString(), txtCEMin.getText().toString());
                }
            }
        });
    }

    public void SubirDatosDeJSonPH(String phmax, String phmin){
        final Retrofit.Builder builder= new Retrofit.Builder().baseUrl(Constantes_web_service.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();
        endPoint = retrofit.create(EndPoint.class);
        Call call = endPoint.guardarPHMax(phmax);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //Toast.makeText(Parametros.this, "Envio exitoso MAX", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //Toast.makeText(Parametros.this, "Error al enviar dato\n \n" +  t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Call call1 = endPoint.guardarPHMin(phmin);
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
               // Toast.makeText(Parametros.this, "Envio exitoso MIN", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
               // Toast.makeText(Parametros.this, "Error al enviar dato\n \n" +  t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(Parametros.this, "Envio exitoso", Toast.LENGTH_SHORT).show();
    }

    public void SubirDatosDeJSonCE(String cemax, String cemin){
        final Retrofit.Builder builder= new Retrofit.Builder().baseUrl(Constantes_web_service.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();
        endPoint = retrofit.create(EndPoint.class);
        Call call = endPoint.guardarCEMax(cemax);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                // Toast.makeText(MainActivity.this, "Envio exitoso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // Toast.makeText(MainActivity.this, "Error al enviar dato\n \n" +  t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Call call1 = endPoint .guardarCEMin(cemin);
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                // Toast.makeText(MainActivity.this, "Envio exitoso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // Toast.makeText(MainActivity.this, "Error al enviar dato\n \n" +  t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(Parametros.this, "Envio exitoso MAX", Toast.LENGTH_SHORT).show();
    }
}
