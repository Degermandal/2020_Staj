package com.example.internlogin.ui.order_track;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.internlogin.Adapter.ExecutedStockListAdapter;
import com.example.internlogin.MainActivity;
import com.example.internlogin.Model.Asset;
import com.example.internlogin.Model.Stock;
import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfResponse.GetCustomerPortfolio.PortfolioBankDTO;
import com.example.internlogin.modelOfResponse.GetOrder.GetOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HeaderMap;

public class CanceledOrders extends Fragment {


    ListView lv_cancelledOrders;
    View root;
    APIService apiService;
    List<Stock> stockList;
    String userIdentitiy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Cancel Order");

        root = inflater.inflate(R.layout.fragment_cancelled_order, container, false);
        assign();
        stockList = new ArrayList<>();
        userIdentitiy = getActivity().getIntent().getExtras().getString("userIdentity");

        Map<String,String> header = new HashMap<>();
        header.put("identityId",userIdentitiy);
        apiService = ApiUtils.getAPIService();

        getCancelOrder(header);

        return root;
    }

    private void assign(){
        lv_cancelledOrders = root.findViewById(R.id.lv_cancelledOrders);
    }


    private void getCancelOrder(Map<String,String> header ) {
        System.out.println("get cancel order..");

        apiService.orderGet(header).enqueue(new Callback<List<GetOrder>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<GetOrder>> call, Response<List<GetOrder>> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {

                        response.body().stream().filter(x -> x.getDurum().equals("canceled")).
                                forEach(o ->stockList.add(new Stock( o.getHisseAdi(),o.getMiktar(),o.getFiyat(),o.getAlisSatis(),o.getId() )));

                        ExecutedStockListAdapter executedStockListAdapter = new ExecutedStockListAdapter(root.getContext(),stockList);
                        lv_cancelledOrders.setAdapter(executedStockListAdapter);
                    }
                    else
                        System.out.println("Get Order : Response body is null!");
                }
            }

            @Override
            public void onFailure(Call<List<GetOrder>> call, Throwable t) {
                System.out.println("Get Order : " + t.getMessage());
            }
        });

    }

}
