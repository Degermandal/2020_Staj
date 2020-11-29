package com.example.internlogin.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.internlogin.MainActivity;
import com.example.internlogin.R;
import com.example.internlogin.ui.analysis.PortfoyAnalysisFragment;
import com.example.internlogin.ui.currency.CurrencyFragment;
import com.example.internlogin.ui.currency.PreciousMetalsFragment;
import com.example.internlogin.ui.news.NewsFragment;

public class MenuFragment extends Fragment {

    //Save verification
    private static final String PREFS_NAME = "preferences";
    private static final Boolean PREF_VERIFICATION = true;

    private final Boolean defaultVerification = true;
    private Boolean verification;

    //Save switch button
    private static final Boolean PREF_SWITCH = true;

    private final Boolean defaultSwitch = true;
    private Boolean switchButton;
    Switch switchOnOff;

    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_menu, container, false);
        switchOnOff = root.findViewById(R.id.btn_settings_switch);

        clickMetal();
        clickCurrency();
        clickAnalysis();
        clickNews();
        clickLogout();
        clickUserGuide();
        loadPreference();
        clickSwitch();
        return root;
    }

    private void clickMetal() {
        root.findViewById(R.id.btn_menu_prmetals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment pmetalsFragment = new PreciousMetalsFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,pmetalsFragment)
                        .commit();
            }
        });
    }

    private void clickAnalysis() {
        root.findViewById(R.id.btn_menu_analysis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment analysisFragment = new PortfoyAnalysisFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,analysisFragment)
                        .commit();
            }
        });
    }

    private void clickCurrency() {
        root.findViewById(R.id.btn_menu_currency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment currencyFragment = new CurrencyFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,currencyFragment)
                        .commit();
            }
        });
    }

    private void clickUserGuide() {

        root.findViewById(R.id.btn_menu_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment settingsFragment = new UserGuideFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,settingsFragment)
                        .commit();
            }
        });
    }

    public void clickNews() {
        root.findViewById(R.id.btn_menu_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newsFragment = new NewsFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,newsFragment)
                        .commit();
            }
        });
    }

    private void clickLogout() {
        root.findViewById(R.id.btn_menu_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    /**
     * On : Emir iptali için onay istiyor
     * Off: Emir iptali için onay istemiyor.
     */
    private void clickSwitch() {
        root.findViewById(R.id.btn_settings_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Önceki datayı çek..
                SharedPreferences preferences;
                SharedPreferences.Editor editor;

                if( verification ) {
                    // onay sormayı kapa
                    System.out.println("Verification Kapa");

                    preferences = root.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    verification = false;
                    editor.putBoolean(String.valueOf(PREF_VERIFICATION), verification);

                    preferences = root.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    switchButton = false; //off
                    switchOnOff.setChecked(false);

                    editor.putBoolean(String.valueOf(PREF_SWITCH), switchButton);

                } else {
                    // onay sormayı aç
                    System.out.println("Verification Aç.");

                    preferences = root.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    verification = true;
                    editor.putBoolean(String.valueOf(PREF_VERIFICATION), verification);

                    preferences = root.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    switchButton = true; //on
                    switchOnOff.setChecked(true);

                    editor.putBoolean(String.valueOf(PREF_SWITCH), switchButton);
                }
                editor.commit();
            }});
    }

    /**
     * Verification son durumu gör
     */
    private void loadPreference() {
        System.out.println("looooadd ");
        SharedPreferences settings = root.getContext().getSharedPreferences("preferences",Context.MODE_PRIVATE);
        // Get value
        verification = settings.getBoolean(String.valueOf(PREF_VERIFICATION), defaultVerification);
        switchButton = settings.getBoolean(String.valueOf(PREF_VERIFICATION), defaultSwitch);
        if(switchButton){
            //on
            switchOnOff.setChecked(true);
        } else {
            //off
            switchOnOff.setChecked(false);
        }
    }

}
