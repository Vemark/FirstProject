package com.copart.service;

import com.copart.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {
  Page<Inventory> search(Pageable pageable);

  Inventory findOne(Long id);
}
