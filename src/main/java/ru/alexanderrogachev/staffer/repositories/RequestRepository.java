package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findRequestsByRequestShop(Shop requestShop);

}
