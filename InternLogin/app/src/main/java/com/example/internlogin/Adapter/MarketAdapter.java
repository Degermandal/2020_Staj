package com.example.internlogin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.internlogin.Model.Market;
import com.example.internlogin.R;

import java.util.List;
import java.util.Locale;

import static com.example.internlogin.R.*;
import static com.example.internlogin.R.drawable.down_arrow;
import static com.example.internlogin.R.drawable.up_arrow;

public class MarketAdapter extends BaseAdapter {

    List<Market> marketList;
    Context context;
    public MarketAdapter(List<Market> marketList, Context context) {
        this.marketList = marketList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return marketList.size();
    }

    @Override
    public Object getItem(int i) {
        return marketList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_market_item,viewGroup,false);
        TextView symbol = layout.findViewById(id.txt_symbol);
        TextView selling = layout.findViewById(id.txt_selling);
        TextView buying = layout.findViewById(id.txt_buying);
        TextView last = layout.findViewById(id.txt_last);
        TextView rate = layout.findViewById(id.txt_rate);

        symbol.setText( marketList.get(i).getSymbol()) ;
        buying.setText( String.valueOf(marketList.get(i).getBuying()));
        selling.setText( String.valueOf(marketList.get(i).getSelling()));
        last.setText( String.valueOf(marketList.get(i).getLast()));


        rate.setTextColor(Color.parseColor("#207325"));

        if(marketList.get(i).getRate() < 0.0) {
            rate.setTextColor(Color.RED);
            Drawable x = context.getResources().getDrawable(down_arrow);
            layout.findViewById(id.iv_arrow).setBackgroundDrawable(x);

        } else {
            Drawable x = context.getResources().getDrawable(up_arrow);
            layout.findViewById(id.iv_arrow).setBackgroundDrawable(x);
        }

        rate.setText( String.format(Locale.US, "%.2f", marketList.get(i).getRate()) );

        return layout;
    }
}
