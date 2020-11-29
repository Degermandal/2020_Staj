package com.example.internlogin.ui.order_track;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internlogin.Adapter.ExecutedStockListAdapter;
import com.example.internlogin.Adapter.PendingStockListAdapter;
import com.example.internlogin.Model.Asset;
import com.example.internlogin.Model.Market;
import com.example.internlogin.Model.Stock;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.helper.RecyclerItemClickListener;
import com.example.internlogin.helper.RecyclerItemTouchHelper;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfRequest.StockRequest;
import com.example.internlogin.modelOfResponse.GetOrder.CancelOrderRequest;
import com.example.internlogin.modelOfResponse.GetOrder.GetOrder;
import com.example.internlogin.modelOfResponse.GetStockForOrder.GetStockForOrder;
import com.example.internlogin.ui.order.BuyOrder;
import com.example.internlogin.ui.order.SellOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingOrders extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    Button btn_allOrderCancel;
    RecyclerView rv_pendingOrders;
    View root;
    RecyclerView.LayoutManager layoutManager;
    PendingStockListAdapter pendingStockListAdapter;

    APIService apiService;
    List<Stock> stockList;
    CancelOrderRequest cancelOrderRequest;
    String userIdentitiy;
    StockRequest stockRequest;


    //Save verification
    private static final String PREFS_NAME = "preferences";
    private static final Boolean PREF_VERIFICATION = true;

    private final Boolean defaultVerification = true;
    private Boolean verification;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_pending_order, container, false);
        verification = true;

        assign();
        stockList = new ArrayList<>();

        userIdentitiy = getActivity().getIntent().getExtras().getString("userIdentity");

        Map<String, String> header = new HashMap<>();
        header.put("identityId", userIdentitiy);
        apiService = ApiUtils.getAPIService();

        getCancelOrder(header);


        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rv_pendingOrders);

        clickBtnAllOrderCancel();

        recyclerViewClick();

        return root;
    }

    private void recyclerViewClick(){
        rv_pendingOrders.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv_pendingOrders, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        alertDialogOpen(position);
                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void recyclerViewClickOperation(int position){
        stockRequest = new StockRequest(30);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Ocp-Apim-Subscription-Key", "584075b0c41c4d15abd24e89df477d33");

        final Market[] market = new Market[1];
        List<Market> marketList = new ArrayList<>();
        APIService interApiService = ApiUtils.getStockOrderService();

        interApiService.marketOnInterGet(header, stockRequest).enqueue(new Callback<GetStockForOrder>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetStockForOrder> call, Response<GetStockForOrder> response) {
                System.out.println(response + " ???");
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        response.body().getData().getIndexList().stream()
                                .forEach(o -> marketList.add(new Market(o.getSymbol(),
                                        Double.valueOf(o.getBuyPrice()),
                                        Double.valueOf(o.getSellPrice()),
                                        Double.valueOf(o.getPrice()),
                                        Double.valueOf(o.getDifference()))));

                    } else
                        System.out.println("Stocks For Order : Response body null");
                }
                for (Market m : marketList) {
                    if (m.getSymbol().equals(stockList.get(position).getName())) {
                        market[0] = new Market(m.getSymbol(), m.getBuying(), m.getSelling(), m.getLast(), m.getRate());
                    }
                }
                Bundle bundle = new Bundle();
                int dotIndex = String.valueOf(stockList.get(position).getAmount()).indexOf(".");
                bundle.putString("id", String.valueOf(stockList.get(position).getId()));
                bundle.putString("amount", String.valueOf(stockList.get(position).getAmount()).substring(0, dotIndex));
                bundle.putString("symbol", market[0].getSymbol());
                bundle.putString("buying", String.valueOf(market[0].getBuying()));
                bundle.putString("selling", String.valueOf(market[0].getSelling()));
                bundle.putString("last", String.valueOf(market[0].getLast()));
                System.out.println("+++++++++++++++++++"+stockList.get(position).getType());
                Fragment fragment;
                if(stockList.get(position).getType().equals("buy")){
                    fragment = new BuyOrder();
                }else{
                    fragment = new SellOrder();
                }

                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragment)
                        .commit();

            }

            @Override
            public void onFailure(Call<GetStockForOrder> call, Throwable t) {
                System.out.println("Stocks For Order : " + t.getMessage());
            }
        });
    }

    private void assign() {
        //Verification'ı çek..
        loadPreferences();

        rv_pendingOrders = root.findViewById(R.id.rv_pendingList);
        btn_allOrderCancel = root.findViewById(R.id.btn_allOrderCancel);
        layoutManager = new LinearLayoutManager(root.getContext());
        rv_pendingOrders.setLayoutManager(layoutManager);
        rv_pendingOrders.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadPreferences() {
        System.out.println("looooodd");
        SharedPreferences settings = root.getContext().getSharedPreferences("preferences",Context.MODE_PRIVATE);
        // Get value
        verification = settings.getBoolean(String.valueOf(PREF_VERIFICATION), defaultVerification);
        System.out.println("LOAD " + verification);

    }
    private void clickBtnAllOrderCancel() {
        btn_allOrderCancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                //Hepsinin id'sini al ..
                System.out.println("hepsini sil.");
                cancelOrderRequest = new CancelOrderRequest();

                stockList.stream().forEach(o -> cancelOrderRequest.getOrderIds().add(o.getId()));
                cancelOrderRequest.setUserIdentityId(userIdentitiy);
                System.out.println("xxxxx " + verification);
                if(verification){
                    System.out.println("verification is " + verification + " ask !");
                    alertDialogOpenAllCancel(cancelOrderRequest);
                }
                else {
                    System.out.println("onaysız iptal");
                    giveCancelOrder(cancelOrderRequest);
                    stockList.clear();
                    pendingStockListAdapter.notifyDataSetChanged();
                }

            }
        });
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PendingStockListAdapter.ViewHolder) {
            // get the removed item name to display it in snack bar
            String name = stockList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Stock deletedItem = stockList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view

            //Silinecek itemin id'sini ver.
            cancelOrderRequest = new CancelOrderRequest();
            cancelOrderRequest.getOrderIds().add(deletedItem.getId());
            cancelOrderRequest.setUserIdentityId(userIdentitiy);
            System.out.println("kaydır + " + verification);
            if (verification) {
                alertDialogOpenSwipped(viewHolder.getAdapterPosition());
            } else {
                stockList.remove(viewHolder.getAdapterPosition());
                pendingStockListAdapter.notifyDataSetChanged();
                giveCancelOrder(cancelOrderRequest);
            }
        }
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

    private void getCancelOrder(Map<String, String> header) {
        System.out.println("get cancel order..");

        apiService.orderGet(header).enqueue(new Callback<List<GetOrder>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<GetOrder>> call, Response<List<GetOrder>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        response.body().stream().filter(x -> x.getDurum().equals("open")).
                                forEach(o -> stockList.add(new Stock(o.getHisseAdi(), o.getDegisenMiktar(), o.getFiyat(), o.getAlisSatis(), o.getId())));

                        pendingStockListAdapter = new PendingStockListAdapter(stockList, root.getContext());
                        rv_pendingOrders.setAdapter(pendingStockListAdapter);
                    } else
                        System.out.println("Get Order : Response body is null!");
                }
            }

            @Override
            public void onFailure(Call<List<GetOrder>> call, Throwable t) {
                System.out.println("Get Order : " + t.getMessage());
            }
        });
    }

    private void alertDialogOpenSwipped(int position) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_layout_verification, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        Button btn_yes = view.findViewById(R.id.btn_yes);
        Button btn_no = view.findViewById(R.id.btn_no);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockList.remove(position);
                pendingStockListAdapter.notifyDataSetChanged();
                giveCancelOrder(cancelOrderRequest);
                dialog.cancel();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pendingStockListAdapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void alertDialogOpenAllCancel(CancelOrderRequest cancelOrderRequest) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_layout_verification, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        Button btn_yes = view.findViewById(R.id.btn_yes);
        Button btn_no = view.findViewById(R.id.btn_no);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveCancelOrder(cancelOrderRequest);
                stockList.clear();
                pendingStockListAdapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pendingStockListAdapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void alertDialogOpen(int i) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_layout_improvement, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        Button btn_yes = view.findViewById(R.id.btn_yes);
        Button btn_no = view.findViewById(R.id.btn_no);
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClickOperation(i);
                dialog.cancel();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
