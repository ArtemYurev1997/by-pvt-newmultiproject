package by.pvt.newmultiproject.api.dto;

import by.pvt.newmultiproject.api.enums.Status;

import java.io.Serializable;

public class OrderRequest implements Serializable {
    private Long id;
    private Long userId;
    private Double cost;
    private Status status;
    static Long nextId = 1L;

    public OrderRequest(Long userId, Double cost, Status status) {
        id = Long.valueOf(0);
        this.userId = userId;
        this.cost = cost;
        this.status = status;
    }

    public OrderRequest(Long id, Long userId, Double cost, Status status) {
        this.id=id;
        this.userId = userId;
        this.cost = cost;
        this.status = status;
    }

    public OrderRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setId() {
        id = nextId;
        nextId++;
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
        return "OrderRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }
}
