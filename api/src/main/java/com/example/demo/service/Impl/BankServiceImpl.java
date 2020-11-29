package com.example.demo.service.Impl;

import com.example.demo.DTO.BankDTO;
import com.example.demo.domain.Bank;
import com.example.demo.repository.BankRepository;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Long createBank(BankDTO bank) {
        Bank bank1 = new Bank(bank);
        return bankRepository.save(bank1).getId();
    }
}
