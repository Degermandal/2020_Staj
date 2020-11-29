package com.example.internlogin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.internlogin.Model.Stock;
import com.example.internlogin.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

class CancelledStockListAdapter extends BaseAdapter {

    Context context;
    List<Stock> stockList;

    public CancelledStockListAdapter(Context context, List<Stock> stockList) {
        this.context = context;
        this.stockList = stockList;
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int i) {
        return stockList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.layout_order_row, parent, false);
        TextView tv_stockName = convertView.findViewById(R.id.tv_stockName);
        TextView tv_stockValue = convertView.findViewById(R.id.tv_stockValue);
        TextView tv_stockAmount = convertView.findViewById(R.id.tv_stockAmount);
        TextView tv_stockType = convertView.findViewById(R.id.tv_stockType);

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("₺"); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols);

        tv_stockName.setText(stockList.get(position).getName());
        tv_stockAmount.setText(String.valueOf(stockList.get(position).getAmount().intValue()));
        tv_stockValue.setText(formatter.format(Double.valueOf(stockList.get(position).getValue())));
        if(stockList.get(position).isType().equals("buy")){
            tv_stockType.setText("Alış");
        }
        else {
            tv_stockType.setText("Satış");
        }

        return convertView;
    }
}
