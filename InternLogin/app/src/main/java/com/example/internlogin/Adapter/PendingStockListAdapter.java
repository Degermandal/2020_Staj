package com.example.internlogin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internlogin.Model.Stock;
import com.example.internlogin.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PendingStockListAdapter extends RecyclerView.Adapter<PendingStockListAdapter.ViewHolder> {

    List<Stock> stockList;
    Context context;

    public PendingStockListAdapter(List<Stock> stockList, Context context) {
        this.stockList = stockList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pending_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("₺"); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols);


        holder.stockValue.setText(formatter.format(stockList.get(position).getValue()));
        holder.stockName.setText(stockList.get(position).getName());
        holder.stockAmount.setText(""+stockList.get(position).getAmount().intValue());
        if(stockList.get(position).isType().equals("buy")){
            holder.stockType.setText("Alış");
            holder.layout.setBackgroundResource(R.drawable.buying_order_shape);
        }
        else{
            holder.stockType.setText("Satış");
            holder.layout.setBackgroundResource(R.drawable.selling_order_shape);
        }
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView stockName;
        TextView stockAmount;
        TextView stockValue;
        TextView stockType;
        public RelativeLayout viewBackground, viewForeground;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.ly_row);
            stockAmount = itemView.findViewById(R.id.tv_stockAmount);
            stockType = itemView.findViewById(R.id.tv_stockType);
            stockName = itemView.findViewById(R.id.tv_stockName);
            stockValue = itemView.findViewById(R.id.tv_stockValue);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

        }
    }
}
