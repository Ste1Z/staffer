package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
