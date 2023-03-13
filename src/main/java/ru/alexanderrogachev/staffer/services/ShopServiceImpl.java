package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.repositories.ShopRepository;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {


    private final ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }


    @Override
    public void saveShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public Shop getShop(Long shopId) {
        return shopRepository.findById(shopId).get();
    }

    @Override
    public void deleteShopByShopName(String shopName) {
        shopRepository.deleteShopByShopName(shopName);
    }

    @Override
    public List<Shop> findShopsByShopName(String shopName) {
        return shopRepository.findByShopName(shopName);
    }

    @Override
    public List<Shop> findShopsByShopBranch(Branch branch) {
        return shopRepository.findShopsByShopBranch(branch);
    }
}
