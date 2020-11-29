package com.example.internlogin.ui.portfoy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public PageAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AssetsFragment accounts = new AssetsFragment();
                return accounts;
            case 1:
                AccountsFragment assets = new AccountsFragment();
                return assets;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
