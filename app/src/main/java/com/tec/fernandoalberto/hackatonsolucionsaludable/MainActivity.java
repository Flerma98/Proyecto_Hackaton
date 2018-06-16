package com.tec.fernandoalberto.hackatonsolucionsaludable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidplot.xy.XYPlot;
import com.google.gson.Gson;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.BaseHelper;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.Constantes_web_service;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.EndPoint;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos.Ajustes;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos.Conductividad;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos.PH;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos.Salinidad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    Fragment fragmentguardado;
    public int fragmentindice;
    public static int SegundosStock= 10000;
    public static ArrayList<com.tec.fernandoalberto.hackatonsolucionsaludable.Datos> Datos= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CargarDatosAlArray();
        ObtenerDatosDeJSon();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("PH");
        loadFragment(new PH());
        CuentaRegresiva(SegundosStock);
    }

    public void CuentaRegresiva(final int Segundos) {
        new CountDownTimer(Segundos, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //Toast.makeText(MainActivity.this, "Acabó", Toast.LENGTH_SHORT).show();
                ObtenerDatosDeJSon();
                CuentaRegresiva(Segundos);
                CargarFrom(fragmentindice);
                //comunicarElCambioAlFragment();
            }
        }.start();

    }

    /*public void comunicarElCambioAlFragment() {
        if(activity==ph) {

        }
    }*/

    public void CargarDatosAlArray(){
        Datos.clear();
        BaseHelper baseHelper = new BaseHelper(this, "DatosAgua", null, 1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
        if (db != null) {
            Cursor c= db.rawQuery("select * from Datos", null);
            if(c.moveToFirst()){
                do{
                    Datos.add(new Datos(c.getFloat(0), c.getFloat(1), c.getFloat(2), c.getString(3), c.getString(4)));
                }while (c.moveToNext());
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentindice = item.getItemId();
            CargarFrom(fragmentindice);
            return true;
        }
    };


    public void CargarFrom(int indice){
        Fragment fragment;
        switch (indice) {
            case R.id.PH:
                toolbar.setTitle("PH");
                Log.e("Mi Log", "CargarFrom: PH");
                fragment = new PH();
                loadFragment(fragment);
                break;
            case R.id.Conductividad:
                Log.e("Mi Log", "CargarFrom: CE");
                toolbar.setTitle("Conductividad");
                fragment = new Conductividad();
                loadFragment(fragment);
                break;
            case R.id.Salinidad:
                Log.e("Mi Log", "CargarFrom: Sal");
                toolbar.setTitle("Salinidad");
                fragment = new Salinidad();
                loadFragment(fragment);
                break;
            case R.id.Ajustes:
                Log.e("Mi Log", "CargarFrom: Ajustes");
                toolbar.setTitle("Ajustes");
                fragment = new Ajustes();
                loadFragment(fragment);
                break;
        }
    }


    public String Fecha(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    public String Hora(){
        Calendar c = Calendar.getInstance();
        String hora="";
        if(c.get(Calendar.HOUR)==0){
            hora= String.valueOf(12) + ":" + String.valueOf(c.get(Calendar.MINUTE) + ":" + String.valueOf(c.get(Calendar.SECOND)));
        }else{
            hora= String.valueOf(c.get(Calendar.HOUR)) + ":" + String.valueOf(c.get(Calendar.MINUTE) + ":" + String.valueOf(c.get(Calendar.SECOND)));
        }

        return hora;
    }

    public void ObtenerDatosDeJSon(){
        final Retrofit.Builder builder= new Retrofit.Builder().baseUrl(Constantes_web_service.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();
        EndPoint endPoint= retrofit.create(EndPoint.class);
        Call call= endPoint.ObtenerReportes();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String gsonCadena= new Gson().toJson(response.body());
                JSONObject json = null;
                try {
                    json = new JSONObject(gsonCadena);
                    JSONArray jArray = json.getJSONArray("Datos");
                    for(int i=0; i<jArray.length(); i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        Datos.add(new Datos(Float.parseFloat(json_data.getString("PH")),Float.parseFloat(json_data.getString("CE")),Float.parseFloat(json_data.getString("Salinidad")),Fecha(),Hora()));
                        GuardarDatos(Float.parseFloat(json_data.getString("PH")),Float.parseFloat(json_data.getString("CE")),Float.parseFloat(json_data.getString("Salinidad")),Fecha(),Hora());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void GuardarDatos(float ph, float ce, float sal, String fecha, String hora) {
        BaseHelper baseHelper = new BaseHelper(this, "DatosAgua", null, 1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
        if (db != null) {
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("PH", ph);
            registronuevo.put("CE", ce);
            registronuevo.put("Salinidad", sal);
            registronuevo.put("Fecha", fecha);
            registronuevo.put("Hora", hora);
            long i = db.insert("Datos", null, registronuevo);
            if (i > 0) {
                Toast.makeText(this, "Registro Nuevo Insertado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}