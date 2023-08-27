package by.pvt.newmultiproject.core.domain;

import by.pvt.newmultiproject.api.enums.TypeStuff;

import java.io.Serializable;

public class Product implements Serializable {
    private Long id;
    private String name;
    private TypeStuff type;
    private Long code;
    private Double price;
    static long nextId = 1L;

    public Product(String name, TypeStuff type, Long code, Double price) {
        id = Long.valueOf(0);
        this.name = name;
        this.type = type;
        this.code = code;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        id = nextId;
        nextId++;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeStuff getType() {
        return type;
    }

    public void setType(TypeStuff type) {
        this.type = type;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", code=" + code +
                ", price=" + price +
                '}';
    }
}
