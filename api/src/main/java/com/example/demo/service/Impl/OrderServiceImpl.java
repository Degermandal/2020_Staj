package com.example.demo.service.Impl;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.OrderEntryCancel;
import com.example.demo.DTO.OrderEntryGet;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountInformationRepository accountInformationRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderPeriotRepository orderPeriotRepository;

    @Autowired
    private OrderEntryRepository orderEntryRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AssetDescriptionRepository assetDescriptionRepository;

    @Override
    public Boolean createOrder(OrderDTO order) {
        OrderEntry orderEntry = new OrderEntry();
        try {
            List<AccountInformation> accountInformationList = accountInformationRepository.findAllByUserIdentityId(order.getKullaniciIdentity());
            if(accountInformationList.size() == 0) {
                System.out.println("adamın hesabı yok .");
                throw new RuntimeException();
            }
            System.out.println("1");
            Asset asset = assetRepository.findByName(order.getHisseAdi());
            if(asset == null){
                AssetDescription assetDescription = assetDescriptionRepository.findByName("hisse");
                asset = new Asset();
                asset.setAssetDescription(assetDescription);
                asset.setName(order.getHisseAdi());
                asset = assetRepository.save(asset);
            }

            // todo : islem tarihi olucak sınıf icinde düzeltirsiniz
            orderEntry.setLocalDateTime(LocalDateTime.now());
            orderEntry.setAccountInformation(accountInformationList.get(0));
            orderEntry.setAsset(asset);
            System.out.println("2");
            OrderPeriot orderPeriot = orderPeriotRepository.findByOrderType(order.getPeriyot());
            if(orderPeriot == null){
                System.out.println("Periyot null ");
                throw new RuntimeException();
            }

            orderEntry.setOrderPeriot(orderPeriot);
            orderEntry.setOrderStatus(orderStatusRepository.findByStatusCode("open"));
//        orderEntry.setOrderType();  // kie ..
            orderEntry.setPrice(order.getFiyat());
            orderEntry.setQuantity(order.getMiktar());

            if(order.getSatisAlis().toLowerCase().equals("a"))
                orderEntry.setSellOrBuy(true);
            else if(order.getSatisAlis().toLowerCase().equals("s")) {

                List<Portfolio> portfolio = portfolioRepository.findByUserIdentityIdAndAssetName(order.getKullaniciIdentity(), order.getHisseAdi());

                if(portfolio.stream().map(Portfolio::getAmount).reduce(0.0, (sub , total) -> sub+total) < order.getMiktar()) {
                    System.out.println("satışta adamın aseti yok.");
                    throw new RuntimeException();
                }
                orderEntry.setSellOrBuy(false);
            }else {
                System.out.println("alış satış hata");
                throw new RuntimeException();

            }
            orderEntry.setOrderQuantity(order.getMiktar());
            orderEntryRepository.save(orderEntry);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<OrderEntryGet> getOrderEntry(String identityId) {
        List<OrderEntry> orderEntries = orderEntryRepository.findAllByAccountInformationUserIdentityId(identityId);

        List<OrderEntryGet> orderEntryGets = new ArrayList<>();
        for (OrderEntry orderEntry: orderEntries) {
            OrderEntryGet orderEntryGet = new OrderEntryGet();
            orderEntryGet.setAlisSatis(orderEntry.getSellOrBuy() ? "buy": "sell");
            orderEntryGet.setDurum(orderEntry.getOrderStatus().getStatusCode());
            orderEntryGet.setFiyat(orderEntry.getPrice());
            orderEntryGet.setHisseAdi(orderEntry.getAsset().getName());
            orderEntryGet.setMiktar(orderEntry.getOrderQuantity());
            orderEntryGet.setId(orderEntry.getId());
            orderEntryGet.setDegisenMiktar(orderEntry.getQuantity());
            orderEntryGets.add(orderEntryGet);
        }

        return orderEntryGets;
    }

    @Override
    public List<OrderEntryGet> cancelOrderEntries(OrderEntryCancel orderEntryCancel) {
        List<OrderEntry> orderEntries = orderEntryRepository.findAllByIdInAndAccountInformationUserIdentityId(orderEntryCancel.getOrderIds(), orderEntryCancel.getUserIdentityId());
        OrderStatus orderCancel = orderStatusRepository.findByStatusCode("canceled");
        for (OrderEntry orderEntry: orderEntries) {
            orderEntry.setOrderStatus(orderCancel);
        }
        orderEntryRepository.saveAll(orderEntries);
        return getOrderEntry(orderEntryCancel.getUserIdentityId());
    }
}
