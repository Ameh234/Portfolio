package com.exam._5.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Food name cannot be blank")
    @Size(min = 3, max = 20)
    private String foodName;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20)
    private String userName;

    @Min(value = 100)
    private double price;

    private LocalDateTime orderTime = LocalDateTime.now();

    @OneToMany(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", userName='" + userName + '\'' +
                ", price=" + price +
                ", orderTime=" + orderTime +
                ", comments=" + comments +
                '}';
    }

    public FoodOrder(Long id, String foodName, String userName, double price, LocalDateTime orderTime, List<Comment> comments) {
        this.id = id;
        this.foodName = foodName;
        this.userName = userName;
        this.price = price;
        this.orderTime = orderTime;
        this.comments = comments;
    }

    public FoodOrder(){
    }
}
