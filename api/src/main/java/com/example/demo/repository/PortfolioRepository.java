package com.example.demo.repository;

import com.example.demo.domain.Portfolio;
import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
    List<Portfolio> findAllByUser(User user);

    List<Portfolio> findAllByUserIdentityId(String identityId);

    List<Portfolio> findByUserIdentityIdAndAssetName(String userIdentityId, String assetName);
}
