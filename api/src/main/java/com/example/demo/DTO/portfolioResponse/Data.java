package com.example.demo.DTO.portfolioResponse;

import java.util.ArrayList;

public class Data {
        ArrayList< Object > stockList = new ArrayList < Object > ();
        ArrayList < Object > metalList = new ArrayList < Object > ();
        ArrayList < Object > currencyList = new ArrayList < Object > ();
        ArrayList < Object > cacheList = new ArrayList < Object > ();


        public ArrayList<Object> getStockList() {
            return stockList;
        }

        public void setStockList(ArrayList<Object> stockList) {
            this.stockList = stockList;
        }

        public ArrayList<Object> getMetalList() {
            return metalList;
        }

        public void setMetalList(ArrayList<Object> metalList) {
            this.metalList = metalList;
        }

        public ArrayList<Object> getCurrencyList() {
            return currencyList;
        }

        public void setCurrencyList(ArrayList<Object> currencyList) {
            this.currencyList = currencyList;
        }

        public ArrayList<Object> getCacheList() {
            return cacheList;
        }

        public void setCacheList(ArrayList<Object> cacheList) {
            this.cacheList = cacheList;
        }
}
