package com.example.internlogin.ui.analysis;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.internlogin.R;
import com.example.internlogin.ui.menu.MenuFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PortfoyAnalysisFragment extends Fragment implements AdapterView.OnItemSelectedListener, OnChartValueSelectedListener {
    @BindView(R.id.chart) LineChart chart;
    @BindView(R.id.spn_analysis_time) Spinner spnTime;
    @BindView(R.id.spn_analysis_benchmark) Spinner spnBenchmark;
    @BindView(R.id.ly_datePicker) LinearLayout ly_date;
    @BindView(R.id.etxt_date_start) EditText etxt_start;
    @BindView(R.id.etxt_date_end) EditText etxt_end;
    @BindView(R.id.tv_portfoy_g) TextView tv_portfoy;
    @BindView(R.id.tv_bm_g) TextView tv_bm;
    @BindView(R.id.tv_100_g) TextView tv_100;
    @BindView(R.id.tv_30_g) TextView tv_30;
    DatePickerDialog picker;

    private final int[] colors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };
    ArrayList<Entry> set1;
    ArrayList<Entry> set2;
    ArrayList<Entry> set3;
    ArrayList<Entry> portfoy;
    Map<Float,String> dates;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_analysis, container, false);
        ButterKnife.bind(this,root);


        spnTime.setOnItemSelectedListener(this);
        ArrayList timeList = new ArrayList<>(Arrays.asList("Son 1 ay", "Son 3 ay","Son 6 ay","Tarih Aralığı"));
        ArrayAdapter timeAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, timeList);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTime.setAdapter(timeAdapter);
        spnBenchmark.setOnItemSelectedListener(this);
        ArrayList benchmarkList = new ArrayList<>(Arrays.asList("BIST30+BIST100","BIST100","BIST30","BIST ALL","USD/TRY","XAU/TRY","Kıstas Oluştur"));
        ArrayAdapter bmAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, benchmarkList);
        bmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBenchmark.setAdapter(bmAdapter);

        showDateDialog(etxt_start,etxt_end);

        Button btn_back = root.findViewById(R.id.btn_back_analysis);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment menuFragment = new MenuFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,menuFragment)
                        .commit();
            }
        });
        readGraphData("Son 1 ay");

        tv_portfoy.setText("3.83");
        tv_portfoy.setTextColor(ColorTemplate.rgb("#4CAF50"));
        tv_bm.setText("2.01");
        tv_bm.setTextColor(ColorTemplate.rgb("#4CAF50"));
        tv_100.setText("1.98");
        tv_100.setTextColor(ColorTemplate.rgb("#4CAF50"));
        tv_30.setText("2.05");
        tv_30.setTextColor(ColorTemplate.rgb("#4CAF50"));
        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       String time = "";
       String bm = "";
            if(adapterView.getId() == R.id.spn_analysis_time){
              if(adapterView.getItemAtPosition(i).toString().equals("Tarih Aralığı")) {
                  ly_date.setVisibility(View.VISIBLE);
              }else{
                  ly_date.setVisibility(View.GONE);
              }


            }
            time = spnTime.getSelectedItem().toString();
            bm = spnBenchmark.getSelectedItem().toString();


        readGraphData(time);
        drawLineChart(bm,time);
        if(time.equals("Son 1 ay")){
            tv_portfoy.setText("3.83");
            tv_portfoy.setTextColor(ColorTemplate.rgb("#4CAF50"));
            if(bm.equals("BIST30")){
                tv_bm.setText("2.05");
                tv_bm.setTextColor(ColorTemplate.rgb("#4CAF50"));
            }else if(bm.equals("BIST100")){
                tv_bm.setText("1.98");
                tv_bm.setTextColor(ColorTemplate.rgb("#4CAF50"));
            }else{
                tv_bm.setText("2.01");
                tv_bm.setTextColor(ColorTemplate.rgb("#4CAF50"));
            }
            tv_100.setText("1.98");
            tv_100.setTextColor(ColorTemplate.rgb("#4CAF50"));
            tv_30.setText("2.05");
            tv_30.setTextColor(ColorTemplate.rgb("#4CAF50"));
        }else{
            tv_portfoy.setText("4.93");
            tv_portfoy.setTextColor(ColorTemplate.rgb("#4CAF50"));
            if(bm.equals("BIST30")){
                tv_bm.setText("-7.02");
                tv_bm.setTextColor(ColorTemplate.rgb("#F44336"));
            }else if(bm.equals("BIST100")){
                tv_bm.setText("-4.89");
                tv_bm.setTextColor(ColorTemplate.rgb("#F44336"));
            }else{
                tv_bm.setText("-6.02");
                tv_bm.setTextColor(ColorTemplate.rgb("#F44336"));
            }
            tv_100.setText("-4.89");
            tv_100.setTextColor(ColorTemplate.rgb("#F44336"));
            tv_30.setText("-7.02");
            tv_30.setTextColor(ColorTemplate.rgb("#F44336"));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void showDateDialog(EditText start,EditText end){
        start.setInputType(InputType.TYPE_NULL);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                start.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);

                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date start_date = (Date) formatter.parse("02-07-2020");
                        Date end_date = (Date) formatter.parse("11-09-2020");
                        picker.getDatePicker().setMinDate(start_date.getTime());
                        picker.getDatePicker().setMaxDate(end_date.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                picker.show();
            }
        });

        end.setInputType(InputType.TYPE_NULL);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                end.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);

                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date start_date = (Date) formatter.parse(start.getText().toString());
                    Date end_date = (Date) formatter.parse("11-09-2020");
                    picker.getDatePicker().setMinDate(start_date.getTime()+1);
                    picker.getDatePicker().setMaxDate(end_date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                picker.show();
            }
        });
    }

    private void readGraphData(String time){
        set1 = new ArrayList<>();
        set2 = new ArrayList<>();
        set3 = new ArrayList<>();
        portfoy = new ArrayList<>();
        dates = new HashMap<>();
        String filename = "graphdata.txt";
        if(time.equals("Son 1 ay")){
            filename ="sonAy.txt";
        }
        int index = 1;
        BufferedReader reader;
        try {
            InputStream inputStream = getContext().getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                String[] linestr = line.split(" ");
                dates.put((float) index,linestr[0]);
                if(!line.equals("tatil")){
                    set1.add(new Entry(index,Float.valueOf(linestr[2])));
                    set2.add(new Entry(index,Float.valueOf(linestr[4])));
                    set3.add(new Entry(index,Float.valueOf(linestr[6])));
                    portfoy.add(new Entry(index,Float.valueOf(linestr[7])));
                }
                index++;

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void drawLineChart(String kıstas, String time){

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        //dataset 1

        LineDataSet d = new LineDataSet(set1, "Bist 30");
        d.setLineWidth(2f);
        d.setCircleRadius(2f);

        int color = ColorTemplate.rgb("#FF5EFE");
        d.setColor(color);
        d.setDrawCircles(false);
        d.setDrawValues(false);
        dataSets.add(d);

        //dataset 2

        LineDataSet d2 = new LineDataSet(set2, "Bist 100");
        d2.setLineWidth(2f);
        d2.setCircleRadius(2f);

        int color2 = ColorTemplate.rgb("#01FFFF");
        d2.setColor(color2);
        d2.setDrawCircles(false);
        d2.setDrawValues(false);
        dataSets.add(d2);

        //dataset 3 //kıstas
        LineDataSet d3 = new LineDataSet(set3, "Kıstas");
        if(kıstas.equals("BIST30")){
            d3 = new LineDataSet(set1, "Kıstas");
        }else if (kıstas.equals("BIST100")) {
            d3 = new LineDataSet(set2, "Kıstas");
        }
        d3.setLineWidth(2f);


        int color3 = ColorTemplate.rgb("#F9FE00");
        d3.setColor(color3);
        d3.setDrawCircles(false);
        d3.setDrawValues(false);
        dataSets.add(d3);

        //portfoy
        LineDataSet p = new LineDataSet(portfoy, "Portföy");
        p.setLineWidth(2f);
        p.setCircleRadius(2f);

        int color4 = ColorTemplate.rgb("#FF0000");
        p.setColor(color4);
        p.setDrawCircles(false);
        p.setDrawValues(false);
        dataSets.add(p);

        // background color
        chart.setBackgroundColor(ColorTemplate.rgb("#1C4686"));

        // disable description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // set listeners
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);

        //marker
        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view, dates);

        // Set the marker to the chart
        mv.setChartView(chart);
        chart.setMarker(mv);


        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
         chart.setScaleXEnabled(true);
         chart.setScaleYEnabled(true);

        // force pinch zoom along both axis
        chart.setPinchZoom(true);


    XAxis xAxis;
       // // X-Axis Style // //
        xAxis = chart.getXAxis();

        // vertical grid lines
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

    YAxis yAxis;
       // // Y-Axis Style // //
        yAxis = chart.getAxisLeft();

        // disable dual axis (only use LEFT axis)
        chart.getAxisRight().setEnabled(false);
        yAxis.setTextColor(Color.WHITE);
        // horizontal grid lines
      //  yAxis.enableGridDashedLine(10f, 10f, 0f);
      //  yAxis.setDrawAxisLine(false);

        // axis range
        yAxis.setAxisMaximum(6f);
        if(time.equals("Son 1 ay")){
            yAxis.setAxisMinimum(-2f);
        }else{
            yAxis.setAxisMinimum(-11f);
        }


       // // Create Limit Lines // //
        LimitLine ll1 = new LimitLine(0f, "");
        ll1.setLineWidth(1f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        // ll1.setTextSize(10f);
        ll1.setLineColor(Color.WHITE);
        yAxis.setDrawLimitLinesBehindData(true);
        xAxis.setDrawLimitLinesBehindData(true);

        yAxis.addLimitLine(ll1);

        // draw points over time
        chart.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setTextColor(Color.WHITE);
        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
