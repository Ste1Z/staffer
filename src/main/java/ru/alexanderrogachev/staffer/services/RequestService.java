package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Request;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();

    void saveRequest(Request request);

    Request getRequest(int id);

    void deleteRequest(int id);

    List<Request> findRequestByShopName(String name);

}
