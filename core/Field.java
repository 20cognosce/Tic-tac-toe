package core;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface Field {
    void inputFigure(int cellNumber, int figure) throws InstanceAlreadyExistsException;
    int checkIfGameIsOver();
    void displayFieldInConsole();
    List<Integer> getEmptyCellsList();
}