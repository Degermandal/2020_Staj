package com.example.internlogin.ui.order_track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.internlogin.Adapter.OrderTrackAdapter;
import com.example.internlogin.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderTrackFragment extends Fragment {

    @BindView(R.id.tabLayout_order_track)
    TabLayout orderTrackTablayout;
    FCViewPager orderTrackViewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_track, container, false);

        orderTrackViewPager = (FCViewPager) root.findViewById(R.id.view_pager_order_track);
        orderTrackViewPager.setEnableSwipe(false);
        ButterKnife.bind(this, root);
        final OrderTrackAdapter adapter = new OrderTrackAdapter(getChildFragmentManager(), orderTrackTablayout.getTabCount());
        orderTrackViewPager.setAdapter(adapter);
        orderTrackViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(orderTrackTablayout));
        orderTrackTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                orderTrackViewPager.setCurrentItem(tab.getPosition());
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





