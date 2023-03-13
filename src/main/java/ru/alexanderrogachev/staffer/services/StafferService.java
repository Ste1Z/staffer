package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;

public interface StafferService {
    List<Staffer> getAllStaffers();

    void saveStaffer(Staffer staffer);

    Staffer getStaffer(Long stafferId);

    void deleteStaffer(Long stafferId);

    List<Staffer> findStafferByStafferName(String stafferName);

    Staffer findStafferById(Long stafferId);

}
