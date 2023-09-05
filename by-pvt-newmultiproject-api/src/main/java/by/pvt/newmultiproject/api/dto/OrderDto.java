package by.pvt.newmultiproject.api.dto;

import by.pvt.newmultiproject.api.enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDto implements Serializable {
    private Long id;
    private List<ProductResponse> products = new ArrayList<>();
    private Double cost;
    private Long userId;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", products=" + products +
                ", cost=" + cost +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }
}
