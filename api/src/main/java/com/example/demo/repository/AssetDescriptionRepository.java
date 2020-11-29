package com.example.demo.repository;

import com.example.demo.domain.AssetDescription;
import org.springframework.data.repository.CrudRepository;

public interface AssetDescriptionRepository extends CrudRepository<AssetDescription, Long> {
    AssetDescription findByName(String name);
}
