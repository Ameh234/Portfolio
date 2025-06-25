package com.exam._5.Service;

import com.exam._5.Models.FoodOrder;
import com.exam._5.Repository.FoodOrderRepo;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {
    @Autowired
    private FoodOrderRepo foodOrderRepo;

    public FoodOrder saveFoodOrder(FoodOrder foodOrder){
        return foodOrderRepo.save(foodOrder);
    }
    public List<FoodOrder> getAllFoodOrders(){
        return foodOrderRepo.findAll();
    }
    public Page<FoodOrder> getAllFoodOrdersPaginated(Pageable pageable){
        return foodOrderRepo.findAll(pageable);
    }
    public Optional<FoodOrder> getFoodOrderById(Long id){
        return foodOrderRepo.findById(id);
    }
    public List<FoodOrder> searchFoodOrdersByFoodName(String keyword){
        return foodOrderRepo.findByFoodNameContainingKeyword(keyword);
    }
}
