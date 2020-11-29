package com.example.internlogin.ui.portfoy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AccountsPageAdapter extends FragmentPagerAdapter {
    private int numTabs;

    public AccountsPageAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DenizBankAccount denizBankAccount = new DenizBankAccount();
                return denizBankAccount;
            case 1:
                YapiKrediAccount yapiKrediAccount = new YapiKrediAccount();
                return yapiKrediAccount;
            case 2:
                IsBankAccount isBankAccount = new IsBankAccount();
                return isBankAccount;
            case 3:
                GarantiBankAccount garantiBankAccount = new GarantiBankAccount();
                return garantiBankAccount;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
