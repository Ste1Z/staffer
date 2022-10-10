package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Staffer;
import ru.alexanderrogachev.staffer.repositories.StafferRepository;

import java.util.List;

@Service
public class StafferServiceImpl implements StafferService {

    @Autowired
    private StafferRepository stafferRepository;

    @Override
    public List<Staffer> getAllStaffers() {
        return stafferRepository.findAll();
    }

    @Override
    public void saveStaffer(Staffer staffer) {
        stafferRepository.save(staffer);
    }

    @Override
    public Staffer getStaffer(int id) {
        return stafferRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStaffer(int id) {
        stafferRepository.deleteById(id);
    }
}
