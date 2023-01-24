package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Branch;

import java.util.List;

public interface BranchService {

    List<Branch> getAllBranches();

    void saveBranch(Branch branch);

    Branch getBranch(long id);

    void deleteBranch(long id);

}
