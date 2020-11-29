package com.example.demo.repository;

import com.example.demo.domain.OrderEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderEntryRepository extends CrudRepository<OrderEntry, Long> {
    List<OrderEntry> findAllByOrderStatusStatusCode(String code);

    List<OrderEntry> findAllByAccountInformationUserIdentityId(String identityId);

    List<OrderEntry> findAllByIdInAndAccountInformationUserIdentityId(List<Long> ids, String identityId);
}
