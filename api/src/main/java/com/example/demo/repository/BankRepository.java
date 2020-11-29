package com.example.demo.repository;

import com.example.demo.domain.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {

    List<Bank> findAll();

    Bank findByCode(String code);

    Bank findByCodeAndName(String code, String name);
}
