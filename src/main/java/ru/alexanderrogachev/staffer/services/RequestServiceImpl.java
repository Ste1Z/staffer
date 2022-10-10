package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.repositories.RequestRepository;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public Request getRequest(int id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRequest(int id) {
        requestRepository.deleteById(id);
    }
}
