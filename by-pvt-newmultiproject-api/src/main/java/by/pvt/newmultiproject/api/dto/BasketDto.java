package by.pvt.newmultiproject.api.dto;

import java.io.Serializable;
import java.util.List;

public class BasketDto implements Serializable {
    private Long id;
    private List<ProductResponse> products;
    private Long productId;
    private Long orderId;
    private Integer count;

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
                ", products=" + products +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", count=" + count +
                '}';
    }
}
