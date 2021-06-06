package com.copart.repository;

import com.copart.entity.Inventory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {
}
