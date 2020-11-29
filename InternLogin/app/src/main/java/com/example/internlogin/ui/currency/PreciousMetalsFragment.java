package com.example.internlogin.ui.currency;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internlogin.Adapter.CurrencyAdapter;
import com.example.internlogin.Model.Currency;
import com.example.internlogin.R;
import com.example.internlogin.ui.menu.MenuFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PreciousMetalsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_precious_metals, container, false);
        List<Currency> metalList = new ArrayList<>();
        metalList.add(new Currency("XAU/TRY","468,00","462,72","#4CAF50","#4CAF50","GRAM"));
        metalList.add(new Currency("XAU/USD","62,65","62,66","#DA2E21","#DA2E21","GRAM"));
        metalList.add(new Currency("XAU/USD","1.940,04","1.938,51","#DA2E21","#4CAF50","ONS"));
        metalList.add(new Currency("XAG/TRY","6,52","6,53","#4CAF50","#4CAF50","GRAM"));
        metalList.add(new Currency("XAG/USD","27,44","27,48","#A1BFE3","#A1BFE3","ONS"));

        RecyclerView recyclerView = root.findViewById(R.id.rcv_prmetals);
        CurrencyAdapter adapter = new CurrencyAdapter(metalList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button btn_back = root.findViewById(R.id.btn_back_metals);
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
