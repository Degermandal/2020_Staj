package com.example.internlogin.ui.currency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internlogin.Adapter.CurrencyAdapter;
import com.example.internlogin.Model.Currency;
import com.example.internlogin.R;
import com.example.internlogin.ui.menu.MenuFragment;

import java.util.ArrayList;
import java.util.List;

public class CurrencyFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_currency, container, false);
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Currency("USD/TRY","7,58","7,32","#DA2E21","#A1BFE3","döviz"));
        currencyList.add(new Currency("EUR/TRY","8,95","8,65","#4CAF50","#DA2E21","döviz"));
        currencyList.add(new Currency("EUR/USD","1,18","1,14","#DA2E21","#DA2E21","döviz"));
        currencyList.add(new Currency("GBP/USD","1,33","1,33","#A1BFE3","#4CAF50","döviz"));

        RecyclerView recyclerView = root.findViewById(R.id.rcv_currency);
        CurrencyAdapter adapter = new CurrencyAdapter(currencyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button btn_back = root.findViewById(R.id.btn_back_currency);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment menuFragment = new MenuFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,menuFragment)
                        .commit();
            }
        });
        return root;
    }
}
