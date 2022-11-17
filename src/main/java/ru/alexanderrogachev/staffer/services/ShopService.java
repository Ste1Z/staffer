package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShops();

    void saveShop(Shop shop);

    Shop getShop(int id);

    void deleteShopByName(String name);

    List<Shop> findShopsByName(String name);

    Shop findShopByName(String name);
}
