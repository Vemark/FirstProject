package com.copart.service.impl;

import com.copart.entity.Inventory;
import com.copart.repository.InventoryRepository;
import com.copart.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {
  @Autowired private InventoryRepository inventoryRepository;

  @Override
  public Page<Inventory> search(final Pageable pageable) {
    return inventoryRepository.findAll(pageable);
  }

  @Override
  public Inventory findOne(final Long id) {
    return inventoryRepository.findById(id).orElse(null);
  }
}
