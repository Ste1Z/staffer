package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.repositories.ShopRepository;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public void saveShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public Shop getShop(int id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteShop(int id) {
        shopRepository.deleteById(id);
    }
}
