package com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.BaseHelper;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.Control_de_datos.BaseHelper;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.R;

import java.util.ArrayList;

public class Ajustes extends Fragment {

    Button btnLimpiar;
    ListView Lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ajustes, container, false);
        btnLimpiar= view.findViewById(R.id.BorrarBD);
        Lista= view.findViewById(R.id.ListaDatos);
        if(MainActivity.Datos.size()==0){
            btnLimpiar.setEnabled(false);
        }else{
            btnLimpiar.setEnabled(true);
        }
        CargardeArray();

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Limpiar();
            }
        });
        return view;
    }

    public void CargardeArray(){
        ArrayList<String> arreglo= new ArrayList<>();
        for (int t=0; t<MainActivity.Datos.size(); t++){
            arreglo.add("PH: [" + MainActivity.Datos.get(t).getPH() + "] CE: [" + MainActivity.Datos.get(t).getCE() + "] Salinidad: [" + MainActivity.Datos.get(t).getSal() + "] Fecha: [" + MainActivity.Datos.get(t).getFecha() + "] Hora: [" + MainActivity.Datos.get(t).getHora() + "]");
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(), R.layout.lista_ittem, arreglo);
        Lista.setAdapter(adapter);
    }

    public void Limpiar(){
        BaseHelper baseHelper = new BaseHelper(getContext(), "DatosAgua", null, 1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
        db.execSQL("delete from Datos");
        Toast.makeText(getContext(), "Tabla limpiada", Toast.LENGTH_SHORT).show();
        MainActivity.Datos.clear();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

}
