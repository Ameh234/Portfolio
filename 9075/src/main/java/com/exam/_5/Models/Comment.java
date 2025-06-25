package com.exam._5.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_order_id")
    private FoodOrder foodOrder;

    @NotBlank(message = "Comment statement cannot be blank")
    private String statement;

    public Comment(Long id, FoodOrder foodOrder, String statement) {
        this.id = id;
        this.foodOrder = foodOrder;
        this.statement = statement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", foodOrder=" + foodOrder +
                ", statement='" + statement + '\'' +
                '}';
    }

    public Comment(){}
}
