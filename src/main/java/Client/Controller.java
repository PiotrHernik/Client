package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseDragEvent;

public class Controller {
    private CommunicationWithServer communicationWithServer;

    public void setCommunicationWithServer(CommunicationWithServer communicationWithServer){
        this.communicationWithServer = communicationWithServer;
    }

    @FXML
    void Exit(ActionEvent event) {
        if(communicationWithServer != null){
            communicationWithServer.stopListen();
            communicationWithServer.sendToServer("Exit");
            System.out.println("exit");
            System.exit(0);
        }

    }

    @FXML
    void Wait(ActionEvent event) {
        if(communicationWithServer != null){
            communicationWithServer.sendToServer("Wait");
            System.out.println("wait");
        }

    }

    @FXML
    private void chessboard(MouseDragEvent event) {
        if(communicationWithServer != null) {
            sendCoordinates(event.getX(), event.getY());
        }

    }

    public void sendCoordinates(double x, double y){
        if (communicationWithServer != null) {
            Point2D point2D = new Point2D(x, y);
            communicationWithServer.sendToServer(point2D);
        }
    }

}
