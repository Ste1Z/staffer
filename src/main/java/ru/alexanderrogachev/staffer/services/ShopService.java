package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> getAllShops();

    public void saveShop(Shop shop);

    public Shop getShop(int id);

    public void deleteShopByName(String name);

    public List<Shop> findShopByName(String name);

}
