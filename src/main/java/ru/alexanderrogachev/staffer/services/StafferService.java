package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;
import java.util.Optional;

public interface StafferService {
    List<Staffer> getAllStaffers();

    void saveStaffer(Staffer staffer);

    void deleteStaffer(Long stafferId);

    List<Staffer> findStafferByStafferName(String stafferName);

    Optional<Staffer> findStafferById(Long stafferId);

}
