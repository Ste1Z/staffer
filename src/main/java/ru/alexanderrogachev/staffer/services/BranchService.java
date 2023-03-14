package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    List<Branch> getAllBranches();

    void saveBranch(Branch branch);

    Branch getBranch(Long branchId);

    void deleteBranch(Long branchId);

    Optional<Branch> findBranchByBranchName(String branchName);

}
