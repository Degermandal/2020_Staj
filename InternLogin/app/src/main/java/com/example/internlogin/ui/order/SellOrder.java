package com.example.internlogin.ui.order;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.internlogin.Model.Market;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfRequest.GiveNewOrder;
import com.example.internlogin.modelOfRequest.StockRequest;
import com.example.internlogin.modelOfResponse.GetMarket.GetMarket;
import com.example.internlogin.modelOfResponse.GetOrder.CancelOrderRequest;
import com.example.internlogin.modelOfResponse.GetOrder.GetOrder;
import com.example.internlogin.modelOfResponse.GetStockForOrder.GetStockForOrder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellOrder extends Fragment implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.btn_order_stock_sell)
    Button selectStock;
    @BindView(R.id.spn_orderType_sell)
    Spinner orderType;
    @BindView(R.id.spn_timeType_sell)
    Spinner timeType;
    @BindView(R.id.btn_decrease_sell)
    Button btn_decrease;
    @BindView(R.id.btn_increase_sell)
    Button btn_increase;
    @BindView(R.id.edtxt_price_sell)
    EditText etxt_price;
    @BindView(R.id.edtxt_amount_sell)
    EditText etxt_amount;
    @BindView(R.id.tv_total_sell)
    EditText tv_total;
    @BindView(R.id.tv_stockBuying2)
    TextView tv_buying;
    @BindView(R.id.tv_stockSelling2)
    TextView tv_selling;
    @BindView(R.id.tv_stockLast2)
    TextView tv_last;
    @BindView(R.id.btn_sell)
    Button btn_sell;
    @BindView(R.id.btn_open_sell)
    Button btn_open_sell;

    APIService apiService;
    DecimalFormat formatter;
    SpinnerDialog spinnerDialog;
    ArrayList<String> stockList = new ArrayList<>();
    List<Market> marketList = new ArrayList<>();
    List<String> ordertypes;
    List<String> timetypes;
    ArrayAdapter timeAdapter;
    View root;
    String userIdentitiy;
    CancelOrderRequest cancelOrderRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sell_order, container, false);
        ButterKnife.bind(this, root);
        apiService = ApiUtils.getAPIService();
        //İşlem yapılacak hisseleri sıralamak için api'den çekiyor. (Inter-Apı )
        StockRequest stockRequest = new StockRequest();
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Ocp-Apim-Subscription-Key", "584075b0c41c4d15abd24e89df477d33");
        getStockMarket(header, stockRequest);

        userIdentitiy = getActivity().getIntent().getExtras().getString("userIdentity");

        orderType.setOnItemSelectedListener(this);
        ordertypes = new ArrayList<>(Arrays.asList("Seçiniz", "Limit", "Piyasa", "Piyasadan limite", "Denge", "Orta Nokta (Piyasa)", "Orta Nokta (Limit)", "AOF (Piyasa)"));
        ArrayAdapter orderAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ordertypes);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderType.setAdapter(orderAdapter);
        timeType.setOnItemSelectedListener(this);
        timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "Gün", "KIE", "IKG"));
        timeAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, timetypes);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeType.setAdapter(timeAdapter);

        formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("₺"); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols); // Formatter usage 2430.0 ---->  ₺2.430,0

        etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
        tv_total.setText(calculateTotal());

        btn_decrease.setOnClickListener(view -> {
            if (etxt_price.getText().toString().length() > 0) {
                Double currentPrice = Double.valueOf(etxt_price.getText().toString());
                Double newPrice = currentPrice - 0.01;
                if (newPrice > 0) {
                    etxt_price.setText(String.format("%.2f", newPrice));
                    tv_total.setText(calculateTotal());
                } else {
                    Toast.makeText(getContext(), Html.fromHtml("<font color='#ce2900' ><b>" + "Lütfen geçerli bir sayı giriniz.." + "</b></font>"), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_increase.setOnClickListener(view -> {
            if (etxt_price.getText().toString().length() > 0) {
                Double currentPrice = Double.valueOf(etxt_price.getText().toString());
                Double newPrice = currentPrice + 0.01;
                etxt_price.setText(String.format("%.2f", newPrice));
                tv_total.setText(calculateTotal());
            }

        });

        etxt_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etxt_amount.getText().toString().length() > 0 && Double.valueOf(etxt_amount.getText().toString()) <= 0) {
                    Toast.makeText(getContext(), Html.fromHtml("<font color='#ce2900' ><b>" + "Lütfen geçerli bir sayı giriniz.." + "</b></font>"), Toast.LENGTH_SHORT).show();
                }
                tv_total.setText(calculateTotal());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        showStockSearch();


        btn_open_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(root.getContext(), "Geçici süreliğine hizmet verilememektedir.", Toast.LENGTH_SHORT).show();
            }
        });

        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sat dediğinde satış emri giriyor..
                APIService apiService = ApiUtils.getAPIService();

                String hisse = selectStock.getText().toString();
                String emirTipi = orderType.getSelectedItem().toString();

                String periyot;
                switch (timeType.getSelectedItem().toString()) {
                    case "1. Seans":
                        periyot = "OO"; //Öğleden önce
                        break;
                    case "Günlük":
                        periyot = "GB"; //Gün boyu
                        break;
                    case "2. Seans":
                        periyot = "OS"; //Öğleen sonra
                        break;
                    default:
                        periyot = "GB";
                }
                System.out.println("Tıklandı : " + hisse + " " + emirTipi + " " + periyot);
                GiveNewOrder giveNewOrder = new GiveNewOrder(emirTipi, Double.valueOf(etxt_price.getText().toString()), hisse,
                        userIdentitiy, Integer.valueOf(etxt_amount.getText().toString()), "GB", "s");
                giveSellOrder(apiService, giveNewOrder);
            }
        });

        if (getArguments() != null) {
            String symbol = getArguments().getString("symbol");
            String buying = getArguments().getString("buying");
            String selling = getArguments().getString("selling");
            String last = getArguments().getString("last");
            String amount = getArguments().getString("amount");
            tv_buying.setText(buying);
            tv_selling.setText(selling);
            tv_last.setText(last);
            selectStock.setText(symbol);
            etxt_price.setText(selling);
            tv_total.setText(calculateTotal());
            etxt_amount.setText(amount);
        }
        return root;
    }

    private void giveSellOrder(APIService apiService, GiveNewOrder param) {
        System.out.println("give sell order ");
        apiService.giveNewOrder(param).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                if (response.isSuccessful()) {
                    if (response.body().booleanValue()) {
                        System.out.println("Hisse satma başarılı!");
                        Toast.makeText(getContext(), "Emir girişi başarılı.", Toast.LENGTH_SHORT).show();
                        if (getArguments() != null) {
                            String id = getArguments().getString("id");
                            if (!id.equals("-1")) {
                                cancelOrderRequest = new CancelOrderRequest();
                                cancelOrderRequest.getOrderIds().add(Integer.valueOf(id));
                                cancelOrderRequest.setUserIdentityId(userIdentitiy);
                                giveCancelOrder(cancelOrderRequest);
                            }
                        }
                    } else {
                        System.out.println("Hisse satma başarısız. Bu isimde hisseniz bulunmamakta");
                        Toast.makeText(getContext(), "Emir girişi başarısız. Bu isimde hisseniz bulunmamakta", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("Give Sell Order Fail : " + t.getMessage());
            }
        });
    }

    public void getStockMarket(Map<String, String> header, StockRequest stockRequest) {
        System.out.println("get stock market in sell fragment..");
        APIService interApiService = ApiUtils.getStockOrderService();

        interApiService.marketOnInterGet(header, stockRequest).enqueue(new Callback<GetStockForOrder>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetStockForOrder> call, Response<GetStockForOrder> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        response.body().getData().getIndexList().stream()
                                .forEach(o -> marketList.add(new Market(o.getSymbol(),
                                        Double.valueOf(o.getBuyPrice()),
                                        Double.valueOf(o.getSellPrice()),
                                        Double.valueOf(o.getPrice()),
                                        Double.valueOf(o.getDifference()))));
                        marketList.stream().forEach(a -> stockList.add(a.getSymbol()));

                    } else
                        System.out.println("Stocks For Order : Response body null");
                }
            }

            @Override
            public void onFailure(Call<GetStockForOrder> call, Throwable t) {
                System.out.println("Stocks For Order : " + t.getMessage());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spn_orderType_sell) {
            String selectedOrder = adapterView.getItemAtPosition(i).toString();
            if (selectedOrder.equals("Limit")) {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "Gün"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                enableEditText(etxt_price);
                btn_increase.setClickable(true);
                btn_decrease.setClickable(true);
                enableEditText(etxt_amount);
                disableEditText(tv_total);

                //hisseyi seçtiği anki fiyat gelicek.fiyat düzenlenebilir. adet de girilcek
            } else if (selectedOrder.equals("Piyasa")) {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "KIE"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                disableEditText(etxt_price);
                btn_increase.setClickable(false);
                btn_decrease.setClickable(false);
                enableEditText(etxt_amount);
                disableEditText(tv_total);

                //anlık fiyat gözükecek, fiyat düzenlenemez. sadece adet girilcek
            } else if (selectedOrder.equals("Piyasadan limite")) {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "Gün", "KIE"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                disableEditText(etxt_price);
                btn_increase.setClickable(false);
                btn_decrease.setClickable(false);
                enableEditText(etxt_amount);
                disableEditText(tv_total);

                //fiyat yok, adet giricek
            } else if (selectedOrder.equals("Denge")) {
                timetypes = new ArrayList<>(Arrays.asList("EFG"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(false);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                disableEditText(etxt_price);
                btn_increase.setClickable(false);
                btn_decrease.setClickable(false);
                enableEditText(etxt_amount);
                disableEditText(tv_total);

                //süre seçilmiyo
                //açılış kapanış saatleri, anlık fiyat fiyat girilmicek, adet girilcek /disabled olcak
            } else if (selectedOrder.equals("Orta Nokta (Piyasa)")) {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "Gün"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                disableEditText(etxt_price);
                btn_increase.setClickable(false);
                btn_decrease.setClickable(false);
                enableEditText(etxt_amount);
                enableEditText(tv_total);

                //bist30 dan alış satış, fiyat düznlenemez. adet girlcek max tutar verilcek. tutar düzenleme 100 bin 30 milyon arası
            } else if (selectedOrder.equals("Orta Nokta (Limit)")) {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "Gün"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                enableEditText(etxt_price);
                btn_increase.setClickable(true);
                btn_decrease.setClickable(true);
                enableEditText(etxt_amount);
                disableEditText(tv_total);

                //bist30 dan alış satış. fiyat düzenlenebilir. adet düzenlenebilir. tutar 100bin 30 milyon arası
            } else if (selectedOrder.equals("AOF (Piyasa")) {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "KIE"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                disableEditText(etxt_price);
                btn_increase.setClickable(false);
                btn_decrease.setClickable(false);
                enableEditText(etxt_amount);
                enableEditText(tv_total);

                //fiyat girilemez adet girebilir 100 bin ile 30 milyon olacak. gün ortalamasına göre gün sonunda emir gerçekleşir ortalama aof emirleri arasından hesaplanır
            } else {
                timetypes = new ArrayList<>(Arrays.asList("Seçiniz", "Gün", "KIE", "IKG"));
                timeAdapter.clear();
                timeAdapter.addAll(timetypes);
                timeAdapter.notifyDataSetChanged();
                timeType.setEnabled(true);
                etxt_price.setText(getMarketByName(selectStock.getText().toString()).toString());
                enableEditText(etxt_price);
                btn_increase.setClickable(true);
                btn_decrease.setClickable(true);
                enableEditText(etxt_amount);
                disableEditText(tv_total);

            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private String calculateTotal() {
        if (etxt_price.getText().toString().length() > 0 && etxt_amount.getText().toString().length() > 0) {
            Double p = Double.valueOf(etxt_price.getText().toString());
            Integer a = Integer.valueOf(etxt_amount.getText().toString());
            Double result = p * a;
            return String.format("%.2f", result);
        }

        return "₺ ---";
    }

    private void showStockSearch() {
        spinnerDialog = new SpinnerDialog(getActivity(), stockList, "Hisse Seçiniz.", R.style.DialogAnimations_SmileWindow, "Kapat");
        spinnerDialog.setCancellable(true); // for cancellable
        spinnerDialog.setShowKeyboard(false);// for open keyboard by default


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(getActivity(), item + "  " + position + "", Toast.LENGTH_SHORT).show();
                selectStock.setText(item);
                tv_buying.setText(marketList.get(position).getBuying().toString());
                tv_selling.setText(marketList.get(position).getSelling().toString());
                tv_last.setText(marketList.get(position).getLast().toString());
                etxt_price.setText(marketList.get(position).getSelling().toString());

                tv_total.setText(calculateTotal());
            }
        });
        selectStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    private void enableEditText(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    private Double getMarketByName(String name) {
        for (Market m : marketList) {
            if (m.getSymbol().equals(name)) {
                return m.getSelling();
            }
        }
        return 1.00;
    }

    private void giveCancelOrder(CancelOrderRequest cancelOrderRequest) {
        System.out.println("give cancel order for one item");
        apiService.cancelOrderGet(cancelOrderRequest).enqueue(new Callback<List<GetOrder>>() {
            @Override
            public void onResponse(Call<List<GetOrder>> call, Response<List<GetOrder>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                    } else
                        System.out.println("Give Cancel Order : Response body is null.");
                }
            }

            @Override
            public void onFailure(Call<List<GetOrder>> call, Throwable t) {
                System.out.println("Give cancel order : " + t.getMessage());
            }
        });
    }
}
