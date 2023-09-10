package by.pvt.newmultiproject.core.mapper;

import by.pvt.newmultiproject.api.dto.*;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.domain.Order;
import by.pvt.newmultiproject.core.domain.Product;

public class MappingUtils {
    //из entity в dto
    public ProductResponse mapToProductDto(Product product){
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setType(product.getType());
        dto.setCode(product.getCode());
        dto.setPrice(product.getPrice());
        return dto;
    }
    //из dto в entity
    public Product mapToProductEntity(ProductRequest dto){
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setCode(dto.getCode());
        entity.setPrice(dto.getPrice());
        return entity;
    }


    public ClientResponse mapToClientDto(Client client){
        ClientResponse dto = new ClientResponse();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setLogin(client.getLogin());
        String fullname = client.getName() + " " + client.getSurname();
        dto.setFullname(fullname);
        dto.setRole(client.getRole());
        return dto;
    }
    //из dto в entity
    public Client mapToClientEntity(ClientRequest dto){
        Client entity = new Client();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }

    public OrderDto mapToOrderDto(Order order){
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCost(order.getCost());
        dto.setUserId(order.getUserId());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public OrderResponse mapToOrderResponse(Order order){
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setCost(order.getCost());
        dto.setUserId(order.getUserId());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public Order mapToOrderEntity(OrderRequest orderRequest){
        Order dto = new Order();
        dto.setId(orderRequest.getId());
        dto.setCost(orderRequest.getCost());
        dto.setUserId(orderRequest.getUserId());
        dto.setStatus(orderRequest.getStatus());
        return dto;
    }

    public Order mapToOrderEntityFromResponse(OrderResponse orderResponse){
        Order dto = new Order();
        dto.setId(orderResponse.getId());
        dto.setCost(orderResponse.getCost());
        dto.setUserId(orderResponse.getUserId());
        dto.setStatus(orderResponse.getStatus());
        return dto;
    }

    public BasketDto mapToBasketDto(Basket basket){
        BasketDto basketDto = new BasketDto();
        basketDto.setId(basket.getId());
        basketDto.setProductId(basket.getProductId());
        basketDto.setOrderId(basket.getOrderId());
        basketDto.setCount(basket.getCount());
        return basketDto;
    }

    public Basket mapToBasket(BasketDto basketDto){
        Basket basket = new Basket();
        basket.setId(basketDto.getId());
        basket.setProductId(basketDto.getProductId());
        basket.setOrderId(basketDto.getOrderId());
        basket.setCount(basketDto.getCount());
        return basket;
    }
}
