package tictactoe.controller.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import tictactoe.controller.GuiGameController;
import tictactoe.model.Player;
import tictactoe.model.impl.ComputerPlayerImpl;
import tictactoe.model.impl.FieldImpl;
import tictactoe.service.FieldService;
import tictactoe.service.PlayerService;

public class GuiGameControllerImpl implements GuiGameController {
    @Setter
    @Getter
    private PlayerService playerService;
    @Setter
    @Getter
    private FieldService fieldService;
    @Setter
    private Stage stage;

    @FXML
    private Button field1;

    @FXML
    private Button field2;

    @FXML
    private Button field3;

    @FXML
    private Button field4;

    @FXML
    private Button field5;

    @FXML
    private Button field6;

    @FXML
    private Button field7;

    @FXML
    private Button field8;

    @FXML
    private Button field9;

    @FXML
    private Menu helpButton;

    @FXML
    private Menu leaveButton;

    @FXML
    private Menu restartGameButton;

    @FXML
    private Text playerScore1;

    @FXML
    private Text playerScore2;

    @FXML
    private Text moveText;

    @Override
    @FXML
    public void fieldPressed (ActionEvent event) {
        Button eventButton = (Button) event.getSource();
        Player currentPlayer = playerService.getCurrentPlayer();

        moveText.setText(
                playerService.getStepOrder() % 2 == 0 ? "Ход Player1" : "Ход Player2"
        );

        switch (eventButton.getId()) {
            case ("field1") -> makeStepAccordingToField(field1,0, currentPlayer);
            case ("field2") -> makeStepAccordingToField(field2,1, currentPlayer);
            case ("field3") -> makeStepAccordingToField(field3,2, currentPlayer);
            case ("field4") -> makeStepAccordingToField(field4,3, currentPlayer);
            case ("field5") -> makeStepAccordingToField(field5,4, currentPlayer);
            case ("field6") -> makeStepAccordingToField(field6,5, currentPlayer);
            case ("field7") -> makeStepAccordingToField(field7,6, currentPlayer);
            case ("field8") -> makeStepAccordingToField(field8,7, currentPlayer);
            case ("field9") -> makeStepAccordingToField(field9,8, currentPlayer);
        }

        if (playerService.getCurrentPlayer() instanceof ComputerPlayerImpl && !fieldService.fieldIsFull()) {
            currentPlayer = getPlayerService().getCurrentPlayer();
            ((ComputerPlayerImpl) currentPlayer).MakeDecision();
            int cellNumber = ((ComputerPlayerImpl) currentPlayer).getDecision();
            switch (cellNumber) {
                case (0) -> makeStepAccordingToField(field1,0, currentPlayer);
                case (1) -> makeStepAccordingToField(field2,1, currentPlayer);
                case (2) -> makeStepAccordingToField(field3,2, currentPlayer);
                case (3) -> makeStepAccordingToField(field4,3, currentPlayer);
                case (4) -> makeStepAccordingToField(field5,4, currentPlayer);
                case (5) -> makeStepAccordingToField(field6,5, currentPlayer);
                case (6) -> makeStepAccordingToField(field7,6, currentPlayer);
                case (7) -> makeStepAccordingToField(field8,7, currentPlayer);
                case (8) -> makeStepAccordingToField(field9,8, currentPlayer);
            }
        }

        int gameOverStatus = fieldService.checkIfGameIsOver();
        if (gameOverStatus != -2) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            if (gameOverStatus == 0) {
                a.setContentText("Нолики победили!");
                a.showAndWait();
                playerService.incrementPlayer1Score();
                playerScore1.setText(Integer.toString(playerService.getPlayer1Score()));
            }
            if (gameOverStatus == 1) {
                a.setContentText("Крестики победили!");
                a.showAndWait();
                playerService.incrementPlayer2Score();
                playerScore2.setText(Integer.toString(playerService.getPlayer2Score()));
            }
            if (gameOverStatus == -1) {
                a.setContentText("Ничья!");
                a.showAndWait();
            }
            fieldService.clearField();
            playerService.setStepOrder(1);
            clearStage();
        }
    }


    @Override
    @FXML
    public void help (ActionEvent event) {

    }

    @Override
    @FXML
    public void leave (ActionEvent event) {
        this.stage.close();
    }

    @Override
    @FXML
    public void restartGame (ActionEvent event) {
        fieldService.setField(new FieldImpl());
        playerService.restartGame(fieldService.getField());
        clearStage();
    }

    private void makeStepAccordingToField(Button field, int cellNumber, Player currentPlayer) {
        try {
            fieldService.makeStep(currentPlayer, cellNumber);
            playerService.updateStepOrder();
            field.setText(currentPlayer.getFigureStr());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("This cell is already taken. Try selecting another one!");
            a.show();
        }
    }

    private void clearStage() {
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field5.setText("");
        field6.setText("");
        field7.setText("");
        field8.setText("");
        field9.setText("");
    }
}

