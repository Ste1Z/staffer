package ru.alexanderrogachev.staffer.service;

import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> getAllShops();

    public void saveShop(Shop shop);

    public Shop getShop(int id);

    public void deleteShop(int id);
}
