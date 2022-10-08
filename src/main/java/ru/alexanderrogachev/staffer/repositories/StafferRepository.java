package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Staffer;

public interface StafferRepository extends JpaRepository<Staffer, Integer> {
}
