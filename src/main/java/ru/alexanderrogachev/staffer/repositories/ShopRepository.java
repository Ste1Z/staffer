package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findByName(String name);

    void deleteShopByName(String name);

    List<Shop> findAll();

    Shop findShopByName(String name);

}
