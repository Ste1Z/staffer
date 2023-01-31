package ru.alexanderrogachev.staffer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexanderrogachev.staffer.models.Position;
import ru.alexanderrogachev.staffer.repositories.PositionRepository;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public void savePosition(Position position) {
        positionRepository.save(position);
    }

    @Override
    public Position getPosition(long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePosition(long id) {
        positionRepository.deleteById(id);
    }
}
