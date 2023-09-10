package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.core.domain.Basket;


import java.util.List;

public interface BasketRepositoryDB {
    List<Basket> getAllBaskets();

    void add(Basket basket);

    void updateBasket(Long id, Basket basket);

    Basket getBasketById(Long id);

    void delete(Long id);

    List<Basket> getBasketsByOrderId(Long orderId);

    Basket getBasketByOrderId(Long orderId);
}
