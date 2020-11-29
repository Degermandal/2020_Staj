package com.example.internlogin.ui.portfoy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.internlogin.Adapter.AssetsListAdapter;
import com.example.internlogin.helper.Helper;
import com.example.internlogin.Model.Asset;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfResponse.GetCustomerPortfolio.GetCustomerPortfolio;
import com.example.internlogin.modelOfResponse.GetCustomerPortfolio.PortfolioBankDTO;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenizBankAccount extends Fragment {


    private int balanceType; //0=₺, 1=$, 2=€
    private double usdToTlRate, usdToEurRate, eurToTlRate, eurToUsdRate, tlToUsdRate, tlToEurRate;
    private APIService apiService;
    View root;
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;

    AssetsListAdapter assetsListAdapterMetal, assetsListAdapterCurrency, assetsListAdapterStock;

    DecimalFormat formatter;
    DecimalFormatSymbols symbols;

    ArrayList preciousMetalList, currencyList, stockList, cashList;
    ArrayList pieEntries;
    LinearLayout ly_list_preciousMetals, ly_list_currency, ly_list_stock, ly_list_cash;
    ListView lv_preciousMetals, lv_currency, lv_stock, lv_cash;
    LinearLayout ly_preciousMetals, ly_currency, ly_stock, ly_cash;
    TextView tv_preciousMetals, tv_currency, tv_stock, tv_cash;

    double total_metal, total_currency, total_stock, total_cash;
    double total_balance_denizbank;
    int[] colors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_account, container, false);
        balanceType = 0;
        formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("₺"); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols); // Formatter usage 2430.0 ---->  ₺2.430,0


        assign();
        openPieChart();

        Map<String,String > param = new HashMap<>();
        String item = getActivity().getIntent().getExtras().getString("userIdentity");

        param.put("userIdentity", item);
        customerPortfolioRequest(param);

        generateCurrencyInfo();
        generateStocknfo();
        generatePreciousMetalInfo();
        goneAssetListViews();

        showPreciousMetalInfo();
        showCurrencyInfo();
        showStockInfo();

        //listen buttons
        clickEurButton();
        clickTlButton();
        clickUsdButton();

        return root;
    }

    private void assign() {
        colors = new int[]{ColorTemplate.rgb("#5BC0EB"), ColorTemplate.rgb("#8E24AA"), ColorTemplate.rgb("#9BC53D"), ColorTemplate.rgb("#E55934")};

        pieChart = root.findViewById(R.id.pieChart);
        apiService = ApiUtils.getAPIService();

        ly_preciousMetals = root.findViewById(R.id.ly_preciousMetals);
        lv_preciousMetals = root.findViewById(R.id.lv_preciousMetals);
        tv_preciousMetals = root.findViewById(R.id.tv_preciousMetals);
        ly_list_preciousMetals = root.findViewById(R.id.ly_list_preciousMetals);

        ly_currency = root.findViewById(R.id.ly_currency);
        lv_currency = root.findViewById(R.id.lv_currency);
        tv_currency = root.findViewById(R.id.tv_currency);
        ly_list_currency = root.findViewById(R.id.ly_list_currency);

        ly_stock = root.findViewById(R.id.ly_share);
        lv_stock = root.findViewById(R.id.lv_share);
        tv_stock = root.findViewById(R.id.tv_share);
        ly_list_stock = root.findViewById(R.id.ly_list_share);

        ly_cash = root.findViewById(R.id.ly_cash);
        lv_cash = root.findViewById(R.id.lv_cash);
        tv_cash = root.findViewById(R.id.tv_cash);

        preciousMetalList = new ArrayList<>();
        currencyList = new ArrayList<>();
        stockList = new ArrayList<>();
        cashList = new ArrayList();
        initExchangeRate();

        assetsListAdapterMetal = new AssetsListAdapter(root.getContext(), preciousMetalList,0,getActivity().getSupportFragmentManager());
        assetsListAdapterCurrency = new AssetsListAdapter(root.getContext(), currencyList,0,getActivity().getSupportFragmentManager());
        assetsListAdapterStock = new AssetsListAdapter(root.getContext(), stockList,0,getActivity().getSupportFragmentManager());
    }

    private void showPreciousMetalInfo() {
        System.out.println("show precius metal");
        System.out.println("---- ");
        ly_preciousMetals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ly_list_preciousMetals.getVisibility() == View.GONE) {
                    goneAssetListViews();
                    generatePreciousMetalInfo();
                } else {
                    ly_list_preciousMetals.setVisibility(View.GONE);
                }

            }
        });
    }

    private void generatePreciousMetalInfo() {
        goneAssetListViews();
        ly_list_preciousMetals.setVisibility(View.VISIBLE);
        lv_preciousMetals.setAdapter(assetsListAdapterMetal);
        Helper.getListViewSize(lv_preciousMetals);
    }

    private void showCurrencyInfo() {
        System.out.println("show cuurency");
        System.out.println("---- ");
        ly_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ly_list_currency.getVisibility() == View.GONE) {
                    goneAssetListViews();
                    generateCurrencyInfo();
                } else {
                    ly_list_currency.setVisibility(View.GONE);
                }

            }
        });
    }

    private void generateCurrencyInfo() {
        goneAssetListViews();
        ly_list_currency.setVisibility(View.VISIBLE);
        lv_currency.setAdapter(assetsListAdapterCurrency);
        Helper.getListViewSize(lv_currency);
    }

    private void showStockInfo() {
        System.out.println("show share");
        System.out.println("---- ");
        ly_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ly_list_stock.getVisibility() == View.GONE) {
                    goneAssetListViews();
                    generateStocknfo();
                } else {
                    ly_list_stock.setVisibility(View.GONE);
                }

            }
        });
    }

    private void generateStocknfo() {
        goneAssetListViews();
        ly_list_stock.setVisibility(View.VISIBLE);
        lv_stock.setAdapter(assetsListAdapterStock);
        Helper.getListViewSize(lv_stock);
    }


    private void openPieChart() {
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterText("DenizBank \n" + formatter.format(Double.valueOf(String.format("%.2f", total_balance_denizbank))));
        pieChart.setDrawEntryLabels(false);
        pieChart.setCenterTextSize(15);
        pieChart.setHoleRadius(75);
        pieChart.animateY(1000);
        pieChart.setUsePercentValues(false);

        pieDataSet.setColors(colors);
        //pieDataSet.setSliceSpace(2f);
        pieDataSet.setSliceSpace(5f);

        pieDataSet.setDrawValues(false);
        pieDataSet.setValueTextSize(12f);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                pieChart.setHoleRadius(65);
                pieChart.setUsePercentValues(true);
                pieDataSet.setDrawValues(true);
                if (h.getX() == 0) {
                    generatePreciousMetalInfo();
                } else if (h.getX() == 1) {
                    generateCurrencyInfo();
                } else if (h.getX() == 2) {
                    generateStocknfo();
                } else {
                    System.out.println("Dışarı");
                }
            }

            @Override
            public void onNothingSelected() {
                pieChart.setHoleRadius(75);
                pieChart.setUsePercentValues(false);
                pieDataSet.setDrawValues(false);
                goneAssetListViews();
            }
        });
    }


    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float) total_metal, "Değerli Madenler"));
        pieEntries.add(new PieEntry((float) total_currency, "Döviz"));
        pieEntries.add(new PieEntry((float) total_stock, "Hisse"));
        pieEntries.add(new PieEntry((float) total_cash, "Nakit"));
    }


    private void clickTlButton() {
        root.findViewById(R.id.btn_tl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (balanceType != 0) {
                    double exchangeType = 0;
                    if (balanceType == 1) {
                        exchangeType = usdToTlRate;
                    } else if (balanceType == 2) {
                        exchangeType = eurToTlRate;
                    }
                    balanceType = 0;
                    updateAllAmount(exchangeType, balanceType);
                }

            }
        });
    }

    private void clickUsdButton() {
        root.findViewById(R.id.btn_usd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (balanceType != 1) {
                    double exchangeType = 0;
                    if (balanceType == 0) {
                        exchangeType = tlToUsdRate;
                    } else if (balanceType == 2) {
                        exchangeType = eurToUsdRate;
                    }
                    balanceType = 1;
                    updateAllAmount(exchangeType, balanceType);
                }
            }
        });
    }

    private void clickEurButton() {
        root.findViewById(R.id.btn_eur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (balanceType != 2) {
                    double exchangeType = 2;
                    if (balanceType == 0) {
                        exchangeType = tlToEurRate;
                    } else if (balanceType == 1) {
                        exchangeType = usdToEurRate;
                    }
                    balanceType = 2;
                    updateAllAmount(exchangeType, balanceType);
                }

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

    /**
     * Gets the user's assets and fill the lists  and fills in the lists it is related to.
     */

    private void customerPortfolioRequest(Map<String, String> param) {
        System.out.println("!!! customer portfoli request\n");
        apiService.customerPortfolioPost(param).enqueue(new Callback<GetCustomerPortfolio>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetCustomerPortfolio> call, Response<GetCustomerPortfolio> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PortfolioBankDTO denizbank = response.body().getPortfolioBankDTOS().stream().filter(x -> x.getBankName().equals("DENIZBANK")).findFirst().orElse(null);
                        //hisse
                        int index = denizbank.getHisse().size();
                        double miktar, deger;

                        for (int i = 0; i < denizbank.getHisse().size() ; i++) {
                            miktar = denizbank.getHisse().get(i).getMiktar();
                            deger = denizbank.getHisse().get(i).getDeger();

                            for (int j = i+1; j < denizbank.getHisse().size(); j++) {
                                if (denizbank.getHisse().get(i).getTur().equals(denizbank.getHisse().get(j).getTur())) {
                                    miktar += denizbank.getHisse().get(j).getMiktar();
                                    deger += denizbank.getHisse().get(j).getDeger();
                                }
                            }
                            Asset asset = new Asset( denizbank.getHisse().get(i).getTur(), miktar,deger);
                            //TODO satış için doğru ama alış ayrı ayrı oluyor..
                            if(asset.getAmount() > 0 && asset.getValue() > 0)
                                stockList.add(asset);
                        }
                        //nakit
                        denizbank.getNakit().stream().forEach(o -> cashList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));
                        //kıymetli maden
                        denizbank.getDegerliMadenler().stream().forEach(o -> preciousMetalList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));
                        //döviz
                        denizbank.getDoviz().stream().forEach(o -> currencyList.add(new Asset(o.getTur(), o.getMiktar(), o.getDeger())));

                        //Calculates total balance for assests
                        total_stock = calculateTotalAssetsValue(stockList);
                        total_cash = calculateTotalAssetsValue(cashList);
                        total_metal = calculateTotalAssetsValue(preciousMetalList);
                        total_currency = calculateTotalAssetsValue(currencyList);

                        //update the piechart
                        total_balance_denizbank = denizbank.getToplamBakiye();
                        pieChart.setCenterText(String.valueOf(total_balance_denizbank));
                        openPieChart();

                        //fill the text views on uı
                        tv_stock.setText(formatter.format(total_stock));
                        tv_cash.setText(formatter.format(total_cash));
                        tv_currency.setText(formatter.format(total_currency));
                        tv_preciousMetals.setText(formatter.format(total_metal));

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

    private void goneAssetListViews() {
        ly_list_stock.setVisibility(View.GONE);
        ly_list_currency.setVisibility(View.GONE);
        ly_list_preciousMetals.setVisibility(View.GONE);
    }

    private void convertBalanceType(List<Asset> assetList, int balanceType, double exchangeRate) {
        formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        symbols = formatter.getDecimalFormatSymbols();

        if (balanceType == 0) {
            symbols.setCurrencySymbol("₺");
        } else if (balanceType == 1) {
            symbols.setCurrencySymbol("$");
        } else if (balanceType == 2) {
            symbols.setCurrencySymbol("€");
        }
        formatter.setDecimalFormatSymbols(symbols);
        for (Asset s : assetList) {
            s.setValue(s.getValue() * exchangeRate);
        }
    }

    private void convertTotalBalance(double exchangeRate) {
        total_currency = total_currency * exchangeRate;
        total_metal = total_metal * exchangeRate;
        total_cash = total_cash * exchangeRate;
        total_stock = total_stock * exchangeRate;
        total_balance_denizbank = total_balance_denizbank * exchangeRate;
    }

    private void updateAllAmount(double exchangeType, int balanceType) {
        convertTotalBalance(exchangeType);
        convertBalanceType(stockList, balanceType, exchangeType);
        convertBalanceType(currencyList, balanceType, exchangeType);
        convertBalanceType(preciousMetalList, balanceType, exchangeType);
        convertBalanceType(cashList, balanceType, exchangeType);
        assetsListAdapterStock.balanceType = balanceType;
        assetsListAdapterCurrency.balanceType = balanceType;
        assetsListAdapterMetal.balanceType = balanceType;
        assetsListAdapterCurrency.notifyDataSetChanged();
        assetsListAdapterMetal.notifyDataSetChanged();
        assetsListAdapterStock.notifyDataSetChanged();
        tv_preciousMetals.setText(formatter.format(total_metal));
        tv_cash.setText(formatter.format(total_cash));
        tv_stock.setText(formatter.format(total_stock));
        tv_currency.setText(formatter.format(total_currency));
        openPieChart();
    }

    private void initExchangeRate() {
        usdToEurRate = 0.84341292866175704827791415606635;
        usdToTlRate = 7.4388157405341069701703488804582;
        eurToUsdRate = 1.1856588463573822543658493561475;
        eurToTlRate = 8.8198976891868054330569765390721;
        tlToEurRate = 0.11338;
        tlToUsdRate = 0.13443;
    }

}
