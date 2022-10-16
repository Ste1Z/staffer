package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Staffer;

import java.util.List;

public interface StafferService {
    public List<Staffer> getAllStaffers();

    public void saveStaffer(Staffer staffer);

    public Staffer getStaffer(int id);

    public void deleteStaffer(int id);

    public List<Staffer> findStafferByName(String name);
}
