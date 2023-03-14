package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.repositories.BranchRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public void saveBranch(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public Branch getBranch(Long branchId) {
        return branchRepository.findById(branchId).get();
    }

    @Override
    public void deleteBranch(Long branchId) {
        branchRepository.deleteById(branchId);
    }

    @Override
    public Optional<Branch> findBranchByBranchName(String branchName) {
        return branchRepository.findBranchByBranchName(branchName);
    }
}
