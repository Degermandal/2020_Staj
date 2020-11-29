package com.example.internlogin.ui.currency;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.internlogin.ui.order.BuyOrder;
import com.example.internlogin.ui.order.SellOrder;

public class CurrencyPageAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public CurrencyPageAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numTabs = tabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CurrencyFragment currency = new CurrencyFragment();
                return currency;
            case 1:
                PreciousMetalsFragment metals = new PreciousMetalsFragment();
                return metals;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
