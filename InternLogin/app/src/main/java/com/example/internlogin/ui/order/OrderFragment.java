package com.example.internlogin.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.internlogin.Adapter.OrderPageAdapter;
import com.example.internlogin.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends Fragment {

    @BindView(R.id.tabLayout_order)
    TabLayout orderTablayout;
    @BindView(R.id.view_pager_order)
    ViewPager orderViewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, root);
        final OrderPageAdapter adapter = new OrderPageAdapter(getChildFragmentManager(), orderTablayout.getTabCount());
        orderViewPager.setAdapter(adapter);
        orderViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(orderTablayout));

        orderTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                orderViewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    orderTablayout.setBackgroundResource(R.drawable.background_tab_buy);
                }else{
                    orderTablayout.setBackgroundResource(R.drawable.background_tab_sell);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }
}