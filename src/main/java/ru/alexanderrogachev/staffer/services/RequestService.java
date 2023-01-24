package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Request;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();

    void saveRequest(Request request);

    Request getRequest(long id);

    void deleteRequest(long id);

    List<Request> findRequestByShopName(String name);

}
