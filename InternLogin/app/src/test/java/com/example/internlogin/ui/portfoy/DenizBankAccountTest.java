package com.example.internlogin.ui.portfoy;

import com.example.internlogin.Model.Asset;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DenizBankAccountTest {

    private static final double DELTA = 1e-15;

    @Test
    public void calculateTotalAssetsValue() {

        List<Asset> assets = new ArrayList<>();
        assets.add(new Asset("", 0.0, 150.0));
        assets.add(new Asset("", 0.0, 180.0));
        assets.add(new Asset("", 0.0, 170.0));

        DenizBankAccount denizBankAccount = new DenizBankAccount();
        double result = denizBankAccount.calculateTotalAssetsValue(assets);
        assertEquals(500.0, result, DELTA);

    }
}