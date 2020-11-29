package com.example.internlogin.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internlogin.Model.Currency;
import com.example.internlogin.R;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

     private List<Currency> currencyList;

    public CurrencyAdapter(List<Currency> lst){
        currencyList = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View currencyView = inflater.inflate(R.layout.fragment_currency_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(currencyView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           Currency cr = currencyList.get(position);

               holder.currency_name.setText(cr.getName());
               holder.selling_price.setText(cr.getSelling_price());
               holder.selling_price.setBackgroundColor(ColorTemplate.rgb(cr.getSelling_color()));
               holder.sell_btn.setText(cr.getName().substring(0,3)+" SAT");
               holder.buying_price.setText(cr.getBuying_price());
               holder.buying_price.setBackgroundColor(ColorTemplate.rgb(cr.getBuying_color()));
               holder.buy_btn.setText(cr.getName().substring(0,3)+" AL");
               if(cr.getName().contains("XAU")){
                   holder.type.setText(cr.getType());
                   holder.type.setTextColor(ColorTemplate.rgb("#FFD700"));
               }else if(cr.getName().contains("XAG")){
                   holder.type.setText(cr.getType());
                   holder.type.setTextColor(ColorTemplate.rgb("#D6D6D6"));
               }else{
                   holder.type.setText("");
               }


    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView currency_name;
        public TextView selling_price;
        public TextView buying_price;
        public TextView sell_btn;
        public TextView buy_btn;
        public TextView type;

        public ViewHolder(View itemView){
            super(itemView);

            currency_name = (TextView) itemView.findViewById(R.id.rcv_name);
            selling_price = (TextView) itemView.findViewById(R.id.rcv_sell_price);
            buying_price = (TextView) itemView.findViewById(R.id.rcv_buy_price);
            sell_btn = (TextView) itemView.findViewById(R.id.rcv_sell_btn);
            buy_btn = (TextView) itemView.findViewById(R.id.rcv_buy_btn);
            type = (TextView) itemView.findViewById(R.id.rcv_type);
        }
    }
}
