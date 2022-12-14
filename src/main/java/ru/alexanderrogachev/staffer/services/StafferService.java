package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;

public interface StafferService {
    List<Staffer> getAllStaffers();

    void saveStaffer(Staffer staffer);

    Staffer getStaffer(int id);

    void deleteStaffer(int id);

    List<Staffer> findStafferByName(String name);

    Staffer findStafferById(int id);

}
