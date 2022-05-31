package tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import tictactoe.service.FieldService;
import tictactoe.service.PlayerService;

import javax.management.InstanceAlreadyExistsException;

public interface GuiGameController {
    void fieldPressed (ActionEvent event) throws InstanceAlreadyExistsException;
    void help (ActionEvent event);
    void leave (ActionEvent event);
    void restartGame (ActionEvent event);
    void setPlayerService(PlayerService playerService);
    void setFieldService(FieldService fieldService);
    void setStage(Stage stage);
}
