package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByShopName(String shopName);

}
