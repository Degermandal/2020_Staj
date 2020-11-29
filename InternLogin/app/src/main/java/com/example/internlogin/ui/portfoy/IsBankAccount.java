package com.example.internlogin.ui.portfoy;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.internlogin.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class IsBankAccount extends Fragment {

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    int[] colors = {ColorTemplate.rgb("#5BC0EB"), ColorTemplate.rgb("#8E24AA"), ColorTemplate.rgb("#9BC53D"), ColorTemplate.rgb("#E55934")};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        pieChart = root.findViewById(R.id.pieChart);
        openPieChart();
        return root;
    }

    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(5f, "Değerli Madenler"));
        pieEntries.add(new PieEntry(2f, "Döviz"));
        pieEntries.add(new PieEntry(3f, "Hisse"));
        pieEntries.add(new PieEntry(6f, "Nakit"));
    }

    private void openPieChart() {
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterText("B Bank");
        pieChart.setDrawEntryLabels(false);
        pieChart.setCenterTextSize(15);
        pieChart.setHoleRadius(75);
        pieChart.animateY(1000);

        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setSliceSpace(5f);

        pieChart.setUsePercentValues(false);
        pieDataSet.setDrawValues(false);
        pieDataSet.setValueTextSize(12f);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                pieChart.setHoleRadius(65);
                pieChart.setUsePercentValues(true);
                pieDataSet.setDrawValues(true);
                pieDataSet.setValueTextSize(12);
            }

            @Override
            public void onNothingSelected() {
                pieChart.setHoleRadius(75);
                pieChart.setUsePercentValues(false);
                pieDataSet.setDrawValues(false);
                pieDataSet.setValueTextSize(12);
            }
        });
    }
}
