package ru.alexanderrogachev.staffer.service;

import ru.alexanderrogachev.staffer.models.Request;

import java.util.List;

public interface RequestService {
    public List<Request> getAllRequests();

    public void saveRequest(Request request);

    public Request getRequest(int id);

    public void deleteRequest(int id);
}
