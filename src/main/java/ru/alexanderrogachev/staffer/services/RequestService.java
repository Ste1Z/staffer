package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Request;

import java.util.List;

public interface RequestService {
    public List<Request> getAllRequests();

    public void saveRequest(Request request);

    public Request getRequest(int id);

    public void deleteRequest(int id);

    public List<Request> findRequestByShopName(String name);
}
