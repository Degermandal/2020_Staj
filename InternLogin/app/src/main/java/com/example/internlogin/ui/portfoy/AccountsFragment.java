package com.example.internlogin.ui.portfoy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.internlogin.R;
import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfResponse.GetAccountInformation.GetAccountInformation;
import com.example.internlogin.modelOfResponse.GetOrder.CancelOrderRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountsFragment extends Fragment {

    View root;
    APIService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_accounts, container, false);

        apiService = ApiUtils.getAPIService();

        onClickDenizbank();

        return root;
    }

    private void onClickDenizbank() {
        root.findViewById(R.id.txt_denizbank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //User id çek..
                Map<String, String> param = new HashMap<>();
                String item = getActivity().getIntent().getExtras().getString("userIdentity");
                //param yap..
                param.put("kimlikNo", "12345678900");
                param.put("bankCode", "DNZBNK");
                accountInformationRequest(param);
            }
        });
    }


    private void accountInformationRequest(Map<String, String> param) {
        System.out.println("Account info");
        apiService.accountInformationGet(param).enqueue(new Callback<GetAccountInformation>() {
            @Override
            public void onResponse(Call<GetAccountInformation> call, Response<GetAccountInformation> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        System.out.println("*** " + response.body());
                        System.out.println(response.body().getAdi() + " " + response.body().getSoyadi());
                        alertDialogOpenAccountInfo(response.body().getAdi() + " " + response.body().getSoyadi(), response.body().getIban(), response.body().getKimlikNo());
                    } else
                        System.out.println("Account Information : Response body null!");
                }
            }

            @Override
            public void onFailure(Call<GetAccountInformation> call, Throwable t) {
                System.out.println("Account Information : " + t.getMessage());
            }
        });
    }


    private void alertDialogOpenAccountInfo(String name, String iban, String identifyId) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_account_info, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(view);
        alert.setCancelable(true);
        TextView tv_identifyId = view.findViewById(R.id.tv_identifyId);
        TextView tv_userName = view.findViewById(R.id.tv_userName);
        TextView tv_ibanNo = view.findViewById(R.id.tv_ibanNo);
        Button btn_okey = view.findViewById(R.id.btn_okey);
        tv_ibanNo.setText(iban);
        tv_identifyId.setText(identifyId);
        tv_userName.setText(name);
        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.edit_text_shape);
        btn_okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
