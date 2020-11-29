package com.example.internlogin.ui.portfoy;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.internlogin.Model.Asset;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfResponse.GetCustomerPortfolio.GetCustomerPortfolio;
import com.example.internlogin.modelOfResponse.GetCustomerPortfolio.PortfolioBankDTO;
import com.google.android.material.tabs.TabLayout;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssetsFragment extends Fragment {

    private APIService apiService;
    DecimalFormat formatter;
    GarantiBankAccount gb = new GarantiBankAccount();
    @BindView(R.id.account_tabLayout)
    TabLayout accountTabLayout;
    @BindView(R.id.account_view_pager)
    ViewPager accountViewPager;
    @BindView(R.id.tv_all_banks_cash)//önemli
            TextView tv_all_banks_cash;//
    double all_bank_cash;
    String userIdentityId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assets, container, false);
        ButterKnife.bind(this, root);
        final AccountsPageAdapter adapter = new AccountsPageAdapter(getChildFragmentManager(), accountTabLayout.getTabCount());
        accountViewPager.setAdapter(adapter);
        accountViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(accountTabLayout));

        Map<String ,String> param = new HashMap<>();
        userIdentityId = getActivity().getIntent().getExtras().getString("userIdentity");

        param.put("userIdentity",userIdentityId);
        customerPortfolioRequest(param);

        accountTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                accountViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
        return root;
    }


    //toplama burada yapılıyor.
    public void all_banks_money() {
        formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("₺"); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols); // Formatter usage 2430.0 ---->  ₺2.430,0

        tv_all_banks_cash.setText(formatter.format(Double.valueOf(String.format("%.2f", all_bank_cash))));
/*
        double garanti = gb.getterTotalGaranti();
        all_bank_cash += garanti;
        //  System.out.println(all_bank_cash + "-------------------------------------------------------------------------");

        tv_all_banks_cash.setText(formatter.format(Double.valueOf(String.format("%.2f", all_bank_cash))));

        //tv_all_banks_cash.setText(bank_money);
*/
    }

    private void customerPortfolioRequest(Map<String,String > param) {
        all_bank_cash = 0;
        apiService = ApiUtils.getAPIService();
        System.out.print("customer portfoli request");

        List<Asset> stockList = new ArrayList<>();
        List<Asset> cashList = new ArrayList<>();
        List<Asset> preciousMetalList = new ArrayList<>();
        List<Asset> currencyList = new ArrayList<>();

        apiService.customerPortfolioPost(param).enqueue(new Callback<GetCustomerPortfolio>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetCustomerPortfolio> call, Response<GetCustomerPortfolio> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        all_bank_cash = response.body().getToplamBakiye();
                        System.out.println("kdg " + all_bank_cash);
                        all_banks_money();


                        /*
                        PortfolioBankDTO denizbank = response.body().getPortfolioBankDTOS().stream().filter(x-> x.getBankName().equals("DENIZBANK")).findFirst().orElse(null);

                        //hisse
                        denizbank.getHisse().stream().forEach(o -> stockList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));
                        //nakit
                        denizbank.getNakit().stream().forEach(o -> cashList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));

                        //kıymetli maden
                        denizbank.getDegerliMadenler().stream().forEach(o -> preciousMetalList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));
                        //döviz
                        denizbank.getDoviz().stream().forEach(o -> currencyList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));

                        //Calculates total balance for assests
                        double total_stock,total_metal,total_cash,total_currency;
                        total_stock = calculateTotalAssetsValue(stockList);
                        total_cash = calculateTotalAssetsValue(cashList);
                        total_metal = calculateTotalAssetsValue(preciousMetalList);
                        total_currency = calculateTotalAssetsValue(currencyList);

                        double total_balance_denizbank = total_cash + total_stock + total_metal + total_currency;
                        all_bank_cash += total_balance_denizbank;
                        all_banks_money(); */
                    } else
                        System.out.println("Customer Portfolio Method : Response Body null");
                }
            }



            @Override
            public void onFailure(Call<GetCustomerPortfolio> call, Throwable t) {
                System.out.println("Customer Portfolio Method : " + t.getMessage());
            }
        });
    }
    public double calculateTotalAssetsValue(List<Asset> assetList) {
        double total = 0;
        for (Asset a : assetList) {
            total += a.getValue();
        }
        return total;
    }

}