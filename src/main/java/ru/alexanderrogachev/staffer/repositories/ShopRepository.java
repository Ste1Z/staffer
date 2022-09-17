package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Shop;

public interface ShopRepository extends JpaRepository<Integer, Shop> {
}
