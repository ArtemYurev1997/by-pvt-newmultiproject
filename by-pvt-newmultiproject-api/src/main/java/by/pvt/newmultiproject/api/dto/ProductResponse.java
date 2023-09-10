package by.pvt.newmultiproject.api.dto;

import by.pvt.newmultiproject.api.enums.TypeStuff;

public class ProductResponse {
    private Long id;
    private String name;
    private TypeStuff type;
    private Long code;
    private Double price;
    static long nextId = 1L;

    public ProductResponse(String name, TypeStuff type, Long code, Double price) {
        id = Long.valueOf(0);
        this.name = name;
        this.type = type;
        this.code = code;
        this.price = price;
    }

    public ProductResponse() {
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
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", code=" + code +
                ", price=" + price +
                '}';
    }
}
