package com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.R;

import java.util.ArrayList;
import java.util.List;


public class Conductividad extends Fragment {

    private XYPlot xyPlotCE;
    private TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_conductividad, container, false);
        xyPlotCE= view.findViewById(R.id.GraficaCE);
        txt= view.findViewById(R.id.txtCE);
        List<Integer> list= new ArrayList();
        if(MainActivity.Datos.size()>0) {
            if (MainActivity.Datos.size()>10) {
                for (int i = MainActivity.Datos.size() - 10 ; i < MainActivity.Datos.size(); i++) {
                    list.add(MainActivity.Datos.get(i).getCE());
                }
            } else {
                for (int i = 0; i < MainActivity.Datos.size(); i++) {
                    list.add(MainActivity.Datos.get(i).getCE());
                }
            }
        }

        XYSeries seriesCE= new SimpleXYSeries(
                list,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, //Solo variables Verticales
                "CE" //Nombre de la primera serie
        );
        LineAndPointFormatter series1Format= new LineAndPointFormatter(
                Color.rgb(0,200,0), //Color de la linea
                Color.rgb(0,100,0), //Ccolor del punto
                Color.rgb(150,190,150), null // Relleno
        );
        xyPlotCE.addSeries(seriesCE,series1Format);

        try {
            txt.setText("Conductividad Actual: " + MainActivity.Datos.get(MainActivity.Datos.size() - 1).getCE());
        }catch (Exception e){ }
        return view;
    }
}
