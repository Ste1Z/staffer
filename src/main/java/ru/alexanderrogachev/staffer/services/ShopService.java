package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShops();

    void saveShop(Shop shop);

    Shop getShop(long id);

    void deleteShopByName(String name);

    List<Shop> findShopsByName(String name);

    Shop findShopByName(String name);

    List<Shop> findShopsByBranch(Branch branch);
}
