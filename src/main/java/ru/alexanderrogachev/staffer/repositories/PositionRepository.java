package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
