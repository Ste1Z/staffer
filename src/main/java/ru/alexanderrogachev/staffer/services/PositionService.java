package ru.alexanderrogachev.staffer.services;

import ru.alexanderrogachev.staffer.models.Position;

import java.util.List;

public interface PositionService {

    List<Position> getAllPositions();

    void savePosition(Position position);

    Position getPosition(long id);

    void deletePosition(long id);

}
