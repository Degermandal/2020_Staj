package com.example.internlogin.modelOfRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockRequest {
    Header HeaderObject;
    ArrayList< Object > Parameters;

    public StockRequest() {
        HeaderObject =  new Header();
        Parameters = new ArrayList<>();
        Map<String, Boolean > param  = new HashMap<>();
        param.put("IsZeroFilteringActive",true);
        Parameters.add(param);
    }
    public StockRequest(int limit) {
        HeaderObject =  new Header();
        Parameters = new ArrayList<>();
        Map<String, Integer > param  = new HashMap<>();
        param.put("IsZeroFilteringActive",1);
        param.put("Limit",limit);
        Parameters.add(param);
    }
    public ArrayList<Object> getParameters() {
        return Parameters;
    }

    public void setParameters(ArrayList<Object> parameters) {
        Parameters = parameters;
    }

// Getter Methods

    public Header getHeader() {
        return HeaderObject;
    }

    // Setter Methods

    public void setHeader(Header HeaderObject) {
        this.HeaderObject = HeaderObject;
    }
}
