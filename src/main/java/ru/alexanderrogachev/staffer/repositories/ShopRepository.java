package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findByShopName(String shopName);

    void deleteShopByShopName(String shopName);

    List<Shop> findShopsByShopBranch(Branch branch);
}
