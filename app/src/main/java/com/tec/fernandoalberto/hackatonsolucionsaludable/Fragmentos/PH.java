package com.tec.fernandoalberto.hackatonsolucionsaludable.Fragmentos;

import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.R;

import java.util.ArrayList;
import java.util.List;


public class PH extends Fragment {
    private static XYPlot xyPlotPH;
    private TextView txt;
    private LineChart mChart;
    private SeekBar mSeekBarX;
    private TextView tvX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ph, container, false);

        //xyPlotPH= view.findViewById(R.id.GraficaPH);
        txt= view.findViewById(R.id.txtPH);
        //TablaPH();
        try {
            txt.setText("PH Actual: " + MainActivity.Datos.get(MainActivity.Datos.size() - 1).getPH());
        }catch (Exception e){ }
        LineChart lineChart = (LineChart) view.findViewById(R.id.LineChartPH);
                // creating list of entry<br />
                ArrayList<Entry> entries= new ArrayList<>();
                entries.add(new Entry(0f,0f));
                entries.add(new Entry(1f,10f));
                entries.add(new Entry(2f,20f));
                entries.add(new Entry(3f,30f));
                entries.add(new Entry(4f,4f));
                entries.add(new Entry(5f,5f));
        LineDataSet dataset = new LineDataSet(entries, "");
        ArrayList<String> labels = new ArrayList<>();
                labels.add("cero");
                labels.add("uno");
                labels.add("dos");
                labels.add("tres");
                labels.add("cuatro");
                labels.add("cinco");
        LineData data = new LineData(dataset, dataset);
                lineChart.setData(data);
                lineChart.setDescription(new Description());
        return view;
    }

    /*public static void TablaPH(){
        List<Integer> list= new ArrayList();
        if(MainActivity.Datos.size()>0) {
            if (MainActivity.Datos.size()>10) {
                for (int i = MainActivity.Datos.size() - 10 ; i < MainActivity.Datos.size(); i++) {
                    list.add(MainActivity.Datos.get(i).getPH());
                }
            } else {
                for (int i = 0; i < MainActivity.Datos.size(); i++) {
                    list.add(MainActivity.Datos.get(i).getPH());
                }
            }
        }
        XYSeries seriesPH= new SimpleXYSeries(
                list,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, //Solo variables Verticales
                "PH" //Nombre de la primera serie
        );
        LineAndPointFormatter series1Format= new LineAndPointFormatter(
                Color.rgb(0,200,0), //Color de la linea
                Color.rgb(0,100,0), //Ccolor del punto
                Color.rgb(150,190,150), null // Relleno
        );
        xyPlotPH.addSeries(seriesPH,series1Format);
    }*/



}
