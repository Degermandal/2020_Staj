package com.example.demo.service.schedular;

import com.example.demo.domain.Asset;
import com.example.demo.domain.OrderEntry;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.Portfolio;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.OrderEntryRepository;
import com.example.demo.repository.OrderStatusRepository;
import com.example.demo.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class OrderServiceSchedular {

    @Autowired
    private OrderEntryRepository orderEntryRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Scheduled(fixedRate= 30000 )
    public void OrderEntity(){
        System.out.println("schedular job çalıştı");
        List<OrderEntry> orderEntryList = orderEntryRepository.findAllByOrderStatusStatusCode("open");
        List<OrderEntry> alisEmirleri = orderEntryList.stream().filter(OrderEntry::getSellOrBuy).collect(Collectors.toList());
        List<OrderEntry> satisEmirleri = orderEntryList.stream().filter(x-> !x.getSellOrBuy()).collect(Collectors.toList());
        OrderStatus orderDone = orderStatusRepository.findByStatusCode("done");
        Asset nakit = assetRepository.findByName("tl");
        for (OrderEntry alisEmir: alisEmirleri) {
            for (OrderEntry satisEmir : satisEmirleri) {
                if(alisEmir.getAsset().equals(satisEmir.getAsset()) && satisEmir.getOrderStatus().getStatusCode().equals("open") && alisEmir.getOrderStatus().getStatusCode().equals("open")){
                    List<Portfolio> portfoliosAlici = portfolioRepository.findAllByUserIdentityId(alisEmir.getAccountInformation().getUser().getIdentityId());
                    List<Portfolio> portfoliosSatici = portfolioRepository.findAllByUserIdentityId(satisEmir.getAccountInformation().getUser().getIdentityId());
                    List<Portfolio> nakitAlici = portfoliosAlici.stream().filter(x-> x.getAsset().getAssetDescription().getName().equals("nakit")).collect(Collectors.toList());
                    List<Portfolio> toDB = new ArrayList<>();

                    double nakitAliciPara = nakitAlici.stream().map(Portfolio::getAmount).reduce(0.0,(sub, element) -> sub+element);
                    if(alisEmir.getPrice() >= satisEmir.getPrice() && nakitAliciPara >= satisEmir.getQuantity() * satisEmir.getPrice() ){

                        if(alisEmir.getQuantity() > satisEmir.getQuantity()) {
                            Portfolio nakitSatici = portfoliosSatici.stream().filter(x -> x.getAsset().getAssetDescription().getName().equals("nakit")).findFirst().orElse(null);
                            Portfolio nakitPortAlici = new Portfolio(nakitAlici.get(0).getUser(), nakit, -1 * satisEmir.getQuantity() * satisEmir.getPrice(), nakitAlici.get(0).getBank(), 1.0, LocalDateTime.now());
                            Portfolio nakitPortSatici = new Portfolio(nakitSatici.getUser(), nakit, satisEmir.getQuantity() * satisEmir.getPrice(), nakitSatici.getBank(), 1.0, LocalDateTime.now());

                            Portfolio hisseAlici = new Portfolio(nakitAlici.get(0).getUser(), alisEmir.getAsset(), satisEmir.getQuantity(), nakitAlici.get(0).getBank(), satisEmir.getPrice(), LocalDateTime.now());
                            Portfolio hisseSatici = new Portfolio(nakitSatici.getUser(), alisEmir.getAsset(), -1 * satisEmir.getQuantity(), nakitSatici.getBank(), satisEmir.getPrice(), LocalDateTime.now());

                            toDB.add(hisseAlici);
                            toDB.add(nakitPortAlici);
                            toDB.add(hisseSatici);
                            toDB.add(nakitPortSatici);


                            alisEmir.setQuantity(alisEmir.getQuantity() - satisEmir.getQuantity());
                            satisEmir.setQuantity(0);
                            satisEmir.setOrderStatus(orderDone);

                        }else if(alisEmir.getQuantity() < satisEmir.getQuantity()){
                            Portfolio nakitSatici = portfoliosSatici.stream().filter(x -> x.getAsset().getAssetDescription().getName().equals("nakit")).findFirst().orElse(null);
                            Portfolio nakitPortAlici = new Portfolio(nakitAlici.get(0).getUser(), nakit, -1 * alisEmir.getQuantity() * satisEmir.getPrice(), nakitAlici.get(0).getBank(), 1.0, LocalDateTime.now());
                            Portfolio nakitPortSatici = new Portfolio(nakitSatici.getUser(), nakit, satisEmir.getQuantity() * satisEmir.getPrice(), nakitSatici.getBank(), 1.0, LocalDateTime.now());

                            Portfolio hisseAlici = new Portfolio(nakitAlici.get(0).getUser(), alisEmir.getAsset(), alisEmir.getQuantity(), nakitAlici.get(0).getBank(), satisEmir.getPrice(), LocalDateTime.now());
                            Portfolio hisseSatici = new Portfolio(nakitSatici.getUser(), alisEmir.getAsset(), -1 * alisEmir.getQuantity(), nakitSatici.getBank(), satisEmir.getPrice(), LocalDateTime.now());

                            toDB.add(hisseAlici);
                            toDB.add(nakitPortAlici);
                            toDB.add(hisseSatici);
                            toDB.add(nakitPortSatici);

                            satisEmir.setQuantity(satisEmir.getQuantity() - alisEmir.getQuantity());
                            alisEmir.setQuantity(0);
                            alisEmir.setOrderStatus(orderDone);
                        }else{
                            Portfolio nakitSatici = portfoliosSatici.stream().filter(x -> x.getAsset().getAssetDescription().getName().equals("nakit")).findFirst().orElse(null);
                            Portfolio nakitPortAlici = new Portfolio(nakitAlici.get(0).getUser(), nakit, -1 * alisEmir.getQuantity() * satisEmir.getPrice(), nakitAlici.get(0).getBank(), 1.0, LocalDateTime.now());
                            Portfolio nakitPortSatici = new Portfolio(nakitSatici.getUser(), nakit, satisEmir.getQuantity() * satisEmir.getPrice(), nakitSatici.getBank(), 1.0, LocalDateTime.now());

                            Portfolio hisseAlici = new Portfolio(nakitAlici.get(0).getUser(), alisEmir.getAsset(), alisEmir.getQuantity(), nakitAlici.get(0).getBank(), satisEmir.getPrice(), LocalDateTime.now());
                            Portfolio hisseSatici = new Portfolio(nakitSatici.getUser(), alisEmir.getAsset(), -1 * alisEmir.getQuantity(), nakitSatici.getBank(), satisEmir.getPrice(), LocalDateTime.now());

                            toDB.add(hisseAlici);
                            toDB.add(nakitPortAlici);
                            toDB.add(hisseSatici);
                            toDB.add(nakitPortSatici);

                            satisEmir.setQuantity(0);
                            alisEmir.setQuantity(0);
                            alisEmir.setOrderStatus(orderDone);
                            satisEmir.setOrderStatus(orderDone);
                        }
                        portfolioRepository.saveAll(toDB);
                        orderEntryRepository.save(alisEmir);
                        orderEntryRepository.save(satisEmir);
                    }
                }
            }
        }
        // todo : süreye göre iptal etmeye bak

    }
}

/*
*
* açık emir kayıtlarını çek
* AL olarak set edilmiş kayıtlardan SAT olarak set edilmiş olanları match et
* hesaplar arasında para aktar
* var ise intertech api lerine istek at
* portfolio tablolarını güncelle
*
* */






