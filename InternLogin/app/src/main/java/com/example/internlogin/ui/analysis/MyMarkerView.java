package com.example.internlogin.ui.analysis;

import android.content.Context;
import android.widget.TextView;

import com.example.internlogin.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.Map;

public class MyMarkerView extends MarkerView {

    private final TextView tvContent;
    private final TextView tvContent2;
    Map<Float,String> dates;

    public MyMarkerView(Context context, int layoutResource, Map<Float,String> dates) {
        super(context, layoutResource);
        this.dates = dates;
        tvContent = findViewById(R.id.tvContent);
        tvContent2 = findViewById(R.id.tvContent2);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getHigh(), 0, true));
        } else {

            tvContent.setText(String.format("%.2f,",e.getY()));
            tvContent2.setText(String.format("%s",dates.get(e.getX())));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}