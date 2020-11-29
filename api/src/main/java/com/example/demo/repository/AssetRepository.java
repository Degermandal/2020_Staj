package com.example.demo.repository;

import com.example.demo.domain.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, Long> {
    Asset findByName(String assetName);
}
