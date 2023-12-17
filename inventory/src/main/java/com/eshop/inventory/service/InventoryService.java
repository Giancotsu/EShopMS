package com.eshop.inventory.service;

import com.eshop.inventory.dto.CheckCartRequest;
import com.eshop.inventory.entity.InventoryEntity;
import com.eshop.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String isbn, int quantity){
        Optional<InventoryEntity> inventory = inventoryRepository.findByIsbn(isbn);
        if(inventory.isPresent() && quantity>0){
            return inventory.get().getQuantity()>=quantity;
        }else{
            return false;
        }
    }

    public String updateInventory(InventoryEntity inventory){
        inventoryRepository.save(inventory);
        return "Inventory updated";
    }

    public List<CheckCartRequest> checkCart(List<CheckCartRequest> cartRequests) {
        List<CheckCartRequest> cartRequestRejected = new ArrayList<>();
        cartRequests.forEach(cartRequest -> {
            if(!isInStock(cartRequest.getIsbn(), cartRequest.getQuantity())){
                cartRequestRejected.add(cartRequest);
            }
        });

        return cartRequestRejected;
    }
}
