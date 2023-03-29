package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;
import java.util.Optional;

public interface StafferRepository extends JpaRepository<Staffer, Long> {
    List<Staffer> findByStafferName(String stafferName);

    Optional<Staffer> findByStafferId(Long stafferId);


}
