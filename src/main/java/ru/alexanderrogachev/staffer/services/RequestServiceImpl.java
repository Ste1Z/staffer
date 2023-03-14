package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Request;
import ru.alexanderrogachev.staffer.models.Shop;
import ru.alexanderrogachev.staffer.repositories.RequestRepository;

import java.util.Date;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {


    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId).get();
    }

    @Override
    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    @Override
    public List<Request> findRequestsByShop(Shop shop) {
        return requestRepository.findRequestsByRequestShop(shop);
    }

}
