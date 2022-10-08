package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
