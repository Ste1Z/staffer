package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();

    void saveRequest(Request request);

    Request getRequest(Long requestId);

    void deleteRequest(Long requestId);

    List<Request> findRequestsByShop(Shop shop);

}
