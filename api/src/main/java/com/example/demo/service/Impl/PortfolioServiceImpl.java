package com.example.demo.service.Impl;

import com.example.demo.DTO.PortfolioBankDTO;
import com.example.demo.DTO.PortfolioKayitDetayDTO;
import com.example.demo.DTO.PortfolioListDTO;
import com.example.demo.domain.Bank;
import com.example.demo.domain.Portfolio;
import com.example.demo.domain.User;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.PortfolioRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    private List<PortfolioKayitDetayDTO> getAssets(List<Portfolio> userPortfolio, String type, String bankCode){
        // TODO : kar hesaplanacak.
        return userPortfolio.stream()
                .filter(x-> x.getAsset().getAssetDescription().getName().equals(type) && x.getBank().getCode().equals(bankCode))
                .map(x -> new PortfolioKayitDetayDTO(x.getAsset().getName(), x.getAmount(), x.getAmount()*x.getBuyingPrice(), x.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public PortfolioListDTO getPortfolioByUserIdentity(String identityId) {
        User user = userRepository.findByIdentityId(identityId);
        List<Portfolio> userPortfolio = portfolioRepository.findAllByUser(user);

        PortfolioListDTO portfolioListDTO = new PortfolioListDTO();
        portfolioListDTO.setToplamBakiye(
                userPortfolio.stream()
                        .map(x -> x.getAmount()*x.getBuyingPrice())
                        .reduce(0.0, ((subtotal, element) -> subtotal + element))
        );

        List<Bank> banks = bankRepository.findAll();
        for (Bank bank: banks) {
            PortfolioBankDTO portfolioBankDTO = new PortfolioBankDTO();
            portfolioBankDTO.setBankName(bank.getName());
            portfolioBankDTO.setDegerliMadenler(getAssets(userPortfolio, "maden", bank.getCode()));
            portfolioBankDTO.setDoviz(getAssets(userPortfolio, "doviz", bank.getCode()));
            portfolioBankDTO.setNakit(getAssets(userPortfolio, "nakit", bank.getCode()));
            portfolioBankDTO.setHisse(getAssets(userPortfolio, "hisse", bank.getCode()));
            portfolioBankDTO.setToplamBakiye(
                    userPortfolio.stream()
                            .filter(x -> x.getBank().getCode().equals(bank.getCode()))
                            .map(x -> x.getAmount()*x.getBuyingPrice())
                            .reduce(0.0, ((subtotal, element) -> subtotal + element))
            );

            portfolioListDTO.getPortfolioBankDTOS().add(portfolioBankDTO);
        }
        return portfolioListDTO;
    }
}
