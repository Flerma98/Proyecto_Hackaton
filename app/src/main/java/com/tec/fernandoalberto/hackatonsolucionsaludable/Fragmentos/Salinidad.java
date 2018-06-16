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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.MainActivity;
import com.tec.fernandoalberto.hackatonsolucionsaludable.R;

import java.util.ArrayList;
import java.util.List;

public class Salinidad extends Fragment {

    private TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_salinidad, container, false);
        txt= view.findViewById(R.id.txtSal);
        if(MainActivity.Datos.size()>0) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.LineChartSal);
            // creating list of entry<br />
            ArrayList<Entry> entries = new ArrayList<>();
            for (int i = 0, a = 0; i < MainActivity.Datos.size(); i++, a++) {
                entries.add(new Entry(i, MainActivity.Datos.get(a).getSal()));
            }
            LineDataSet dataset = new LineDataSet(entries, "Salinidad");
            ArrayList<String> labels = new ArrayList<>();
            for (int i = 0; i < MainActivity.Datos.size(); i++) {
                labels.add(MainActivity.Datos.get(i).getFecha() + " - " + MainActivity.Datos.get(i).getHora());
            }
            LineData data = new LineData(dataset, dataset);
            lineChart.setData(data);
            lineChart.setBackgroundColor(Color.WHITE);
            lineChart.setDescription(new Description());
        }
        try {
            txt.setText("Sanilidad Actual: " + MainActivity.Datos.get(MainActivity.Datos.size() - 1).getSal());
        }catch (Exception e){ }
        return view;
    }

}
