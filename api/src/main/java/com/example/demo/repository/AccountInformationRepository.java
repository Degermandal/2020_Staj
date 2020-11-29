package com.example.demo.repository;

import com.example.demo.domain.AccountInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountInformationRepository extends CrudRepository<AccountInformation, Long> {

    AccountInformation findByIban(String iban);

    List<AccountInformation> findAllByUserIdentityId(String identityId);

    // +++
    List<AccountInformation> findAllByUserIdentityIdAndBankCode(String identityId, String bankCode);
}