package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;

public interface StafferRepository extends JpaRepository<Staffer, Long> {
    List<Staffer> findByName(String name);

    Staffer findByStafferId(long id);


}
