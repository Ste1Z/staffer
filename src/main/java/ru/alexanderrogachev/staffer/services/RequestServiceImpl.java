package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Request;
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
    public Request getRequest(int id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRequest(int id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> findRequestByShopName(String shopName) {
        return requestRepository.findByShopName(shopName);
    }

    //Устанавливает текущую дату в дату запроса
    public void autoSetDateOfRequest(Request request) {
        Date now = new Date();
        request.setDateOfRequest(now);
    }
}
