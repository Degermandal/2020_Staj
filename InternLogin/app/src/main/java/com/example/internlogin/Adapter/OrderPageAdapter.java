package com.example.internlogin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.internlogin.ui.order.BuyOrder;
import com.example.internlogin.ui.order.SellOrder;

public class OrderPageAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public OrderPageAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BuyOrder buyOrder = new BuyOrder();
                return buyOrder;
            case 1:
                SellOrder sellOrder = new SellOrder();
                return sellOrder;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
