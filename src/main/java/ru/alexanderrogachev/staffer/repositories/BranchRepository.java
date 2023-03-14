package ru.alexanderrogachev.staffer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexanderrogachev.staffer.models.Branch;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findBranchByBranchName(String branchName);

}
