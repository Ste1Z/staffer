package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShops();

    void saveShop(Shop shop);

    Shop getShop(Long shopId);

    void deleteShopByShopName(String shopName);

    List<Shop> findShopsByShopName(String shopName);

    List<Shop> findShopsByShopBranch(Branch branch);
}
