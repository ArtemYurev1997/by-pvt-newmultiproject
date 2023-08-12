package by.pvt.newmultiproject.core.mapper;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.dto.ProductResponse;
import by.pvt.newmultiproject.core.domain.Client;
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
}
