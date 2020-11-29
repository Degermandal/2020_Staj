package com.example.internlogin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.internlogin.ui.order_track.CanceledOrders;
import com.example.internlogin.ui.order_track.ExecutedOrders;
import com.example.internlogin.ui.order_track.PendingOrders;


public class OrderTrackAdapter extends FragmentPagerAdapter {


    private int numTabs;


    public OrderTrackAdapter(@NonNull FragmentManager fm, int tabs) {
        super(fm, tabs);
        this.numTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PendingOrders pendingOrder = new PendingOrders();
                return pendingOrder;
            case 1:
                ExecutedOrders executedOrder = new ExecutedOrders();
                return executedOrder;
            case 2:
                CanceledOrders canceledOrders = new CanceledOrders();
                return canceledOrders;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
