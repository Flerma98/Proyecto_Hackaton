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

public class Salinidad extends Fragment {

    private XYPlot xyPlotSal;
    private TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_salinidad, container, false);
        xyPlotSal= view.findViewById(R.id.GraficaSal);
        txt= view.findViewById(R.id.txtSal);
        List<Integer> list= new ArrayList();
        if(MainActivity.Datos.size()>0) {
            if (MainActivity.Datos.size()>10) {
                for (int i = MainActivity.Datos.size() - 10 ; i < MainActivity.Datos.size(); i++) {
                    list.add(MainActivity.Datos.get(i).getSal());
                }
            } else {
                for (int i = 0; i < MainActivity.Datos.size(); i++) {
                    list.add(MainActivity.Datos.get(i).getSal());
                }
            }
        }
        XYSeries seriesSal= new SimpleXYSeries(
                list,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, //Solo variables Verticales
                "Salinidad" //Nombre de la primera serie
        );
        LineAndPointFormatter series1Format= new LineAndPointFormatter(
                Color.rgb(150,0,200), //Color de la linea
                Color.rgb(200,100,200), //Ccolor del punto
                Color.rgb(200,50,50), null // Relleno
        );
        xyPlotSal.addSeries(seriesSal,series1Format);
        try {
            txt.setText("Sanilidad Actual: " + MainActivity.Datos.get(MainActivity.Datos.size() - 1).getSal());
        }catch (Exception e){ }
        return view;
    }

}
