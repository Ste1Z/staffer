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
    public Shop getShop(long id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteShopByName(String name) {
        shopRepository.deleteShopByName(name);
    }

    @Override
    public List<Shop> findShopsByName(String name) {
        return shopRepository.findByName(name);
    }

    @Override
    public Shop findShopByName(String name) {
        return shopRepository.findShopByName(name);
    }

    @Override
    public List<Shop> findShopsByBranch(Branch branch) {
        return shopRepository.findShopsByBranch(branch);
    }
}
