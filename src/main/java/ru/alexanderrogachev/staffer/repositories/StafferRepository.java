package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;

public interface StafferRepository extends JpaRepository<Staffer, Integer> {
    List<Staffer> findByName(String name);

    Staffer findByStafferId(int id);
}
