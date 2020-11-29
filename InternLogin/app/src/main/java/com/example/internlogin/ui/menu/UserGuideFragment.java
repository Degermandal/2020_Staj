package com.example.internlogin.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.internlogin.R;

import pl.droidsonroids.gif.GifImageView;


public class UserGuideFragment extends Fragment {

    View root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_userguide, container, false);

        clickBack();
        onClickButtons();
        return root;
    }

    /**
     * Önceki sayfaya dön
     */
    private void clickBack() {
        root.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment menuFragment = new MenuFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, menuFragment)
                        .commit();
            }
        });
    }

    private void onClickButtons() {
        root.findViewById(R.id.btn_cancelOrderClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogOpen(R.drawable.cancel_order_close);
            }
        });
        root.findViewById(R.id.btn_cancelOrderOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogOpen(R.drawable.cancel_order_open);
            }
        });
        root.findViewById(R.id.btn_portfoy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogOpen(R.drawable.portfoy);
            }
        });
        root.findViewById(R.id.btn_fastBuySell).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogOpen(R.drawable.fast_buy_sell);
            }
        });
    }


    private void alertDialogOpen(int src) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_layout_gif, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        GifImageView gifImageView = view.findViewById(R.id.gif);
        Button btn_okey = view.findViewById(R.id.btn_okey);
        gifImageView.setBackgroundResource(src);
        alert.setView(view);
        alert.setCancelable(true);

        final AlertDialog dialog = alert.create();
        btn_okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();

    }


}
