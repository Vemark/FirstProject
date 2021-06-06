package com.copart.web.controller;

import com.copart.entity.Inventory;
import com.copart.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
  @Autowired private InventoryService inventoryService;

  @GetMapping("/search")
  public ResponseEntity<Page<Inventory>> search(Pageable pageable) {
    Page<Inventory> results = inventoryService.search(pageable);
    return ResponseEntity.ok(results);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Inventory> findOne(@PathVariable("id") Long id) {
    Inventory inventory = inventoryService.findOne(id);
    return ResponseEntity.ok(inventory);
  }
}
