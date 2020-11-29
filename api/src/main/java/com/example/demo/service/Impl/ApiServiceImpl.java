package com.example.demo.service.Impl;

import com.example.demo.DTO.marketResponse.Datum;
import com.example.demo.DTO.marketResponse.Hurriyet;
import com.example.demo.DTO.marketResponse.MarketDataDTO;
import com.example.demo.service.ApiService;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public List<MarketDataDTO> getMarket() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");

        HttpEntity httpEntity = new HttpEntity(headers);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://bigpara.hurriyet.com.tr/api/v1/hisse/list", HttpMethod.GET, httpEntity,String.class);
        Gson gson = new Gson();
        Hurriyet hurriyet = gson.fromJson(response.getBody(), Hurriyet.class);

        List<MarketDataDTO> marketDataDTOS = new ArrayList<>();

//        while( marketDataDTOS.size() <= 30) {
            Random rand = new Random();
            List<Datum> wordList = rand.
                    ints(30, 0, hurriyet.getData().size()).
                    mapToObj(i -> hurriyet.getData().get(i)).
                    collect(Collectors.toList());


            for ( Datum datum : wordList ) {

                MarketDataDTO marketDataDTOCheck = marketDataDTOS.stream().filter(x-> x.getData().getHisseYuzeysel() != null).filter(x -> x.getData().getHisseYuzeysel().getSembol().equals(datum.getKod())).findFirst().orElse(null);

                if( marketDataDTOCheck != null )
                    continue;
                try {
                    String s = "http://bigpara.hurriyet.com.tr/api/v1/borsa/hisseyuzeysel/" + datum.getKod();
                    ResponseEntity< String > response2 = restTemplate.exchange(s, HttpMethod.GET, httpEntity, String.class);
                    Gson gson1 = new Gson();
                    MarketDataDTO marketDataDTO = gson1.fromJson(response2.getBody(), MarketDataDTO.class);
                    marketDataDTOS.add(marketDataDTO);
                }catch ( Exception e ){
                    continue;
                }
//                if(marketDataDTOS.size() >= 30){
//                    break;
//                }
            }
//        }
        return marketDataDTOS.stream().filter(x-> x.getData().getHisseYuzeysel() != null).collect(Collectors.toList());

    }
}
