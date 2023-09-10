package by.pvt.newmultiproject.api.dto;

import java.io.Serializable;
import java.util.List;

public class BasketDto implements Serializable {
    private Long id;
    private Long productId;
    private Long orderId;
    private Integer count;
    static long nextId = 1L;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BasketDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", count=" + count +
                '}';
    }
}
