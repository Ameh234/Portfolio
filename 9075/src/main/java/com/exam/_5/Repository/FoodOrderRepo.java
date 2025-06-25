package com.exam._5.Repository;

import com.exam._5.Models.FoodOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface FoodOrderRepo extends JpaRepository<FoodOrder, Long> {
    @Query("SELECT fo FROM FoodOrder fo WHERE fo.foodName LIKE %:keyword%")
    List<FoodOrder> findByFoodNameContainingKeyword(@Param("keyword") String keyword);

    Page<FoodOrder> findAll(Pageable pageable);
}
