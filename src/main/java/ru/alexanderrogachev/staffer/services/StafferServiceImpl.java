package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.repositories.StafferRepository;

import java.util.List;

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
    public Staffer getStaffer(long id) {
        return stafferRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStaffer(long id) {
        stafferRepository.deleteById(id);
    }

    @Override
    public List<Staffer> findStafferByName(String name) {
        return stafferRepository.findByName(name);
    }

    @Override
    public Staffer findStafferById(long id) {
        return stafferRepository.findByStafferId(id);
    }

}
