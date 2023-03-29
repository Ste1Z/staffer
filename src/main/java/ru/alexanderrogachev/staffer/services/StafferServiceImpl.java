package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.repositories.StafferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StafferServiceImpl implements StafferService {

    private final StafferRepository stafferRepository;

    @Autowired
    public StafferServiceImpl(StafferRepository stafferRepository) {
        this.stafferRepository = stafferRepository;
    }

    @Override
    public List<Staffer> getAllStaffers() {
        return stafferRepository.findAll();
    }

    @Override
    public void saveStaffer(Staffer staffer) {
        stafferRepository.save(staffer);
    }

    @Override
    public void deleteStaffer(Long stafferId) {
        stafferRepository.deleteById(stafferId);
    }

    @Override
    public List<Staffer> findStafferByStafferName(String stafferName) {
        return stafferRepository.findByStafferName(stafferName);
    }

    @Override
    public Optional<Staffer> findStafferById(Long stafferId) {
        return stafferRepository.findByStafferId(stafferId);
    }

}
