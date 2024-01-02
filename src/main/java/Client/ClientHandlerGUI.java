package Client;

        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXMLLoader;
        import javafx.geometry.Insets;
        import javafx.geometry.Point2D;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.*;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;
        import javafx.stage.Stage;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintStream;
        import java.net.InetAddress;
        import java.net.Socket;
//fdfsfsfsd
public class ClientHandlerGUI extends Application {
    private static final int BOARD_SIZE = 10;
    private static final int SQUERE_SIZE = 50;

    private Rectangle[][] boardCells;

    private CommunicationWithServer communicationWithServer;
    //private Label[][] boardLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {

            new Thread(() -> {
                try  {
                    Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 3357);
                    communicationWithServer = new CommunicationWithServer(socket, SQUERE_SIZE);
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
                communicationWithServer.startListen();

            }).start();



            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.setCommunicationWithServer(communicationWithServer);




            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("stroke.css").toExternalForm());
            stage.setTitle("Go Game");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setResizable(false);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }




//    private GridPane createChessboard(){
//        GridPane chessBoard = new GridPane();
//        this.boardCells = new Rectangle[BOARD_SIZE][BOARD_SIZE];
//
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                boardCells[i][j] = new Rectangle(SQUERE_SIZE, SQUERE_SIZE);
//                boardCells[i][j].setFill(Color.BROWN);
//                boardCells[i][j].setStroke(Color.WHITE);
//                chessBoard.add(boardCells[i][j], i, j);
//            }
//        }
//        return chessBoard;
//    }
//    private VBox createButtonsContainer(){
//        VBox buttonsContainer = new VBox(10);
//        Button exit = new Button("Exit");
//        Button wait = new Button("Wait");
//
//        exit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                communicationWithServer.stopListen();
//                communicationWithServer.sendToServer("Exit");
//                System.out.println("exit");
//                System.exit(0);
//            }
//        });
//
//        wait.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                communicationWithServer.sendToServer("Wait");
//                System.out.println("wait");
//            }
//        });
//        buttonsContainer.getChildren().addAll(exit, wait);
//        return buttonsContainer;
//    }

//    private void updateBoardInGUI(String boardString) {
//        // Aktualizacja GUI zgodnie z planszą otrzymaną od serwera
//        char[][] boardArray = parseBoardString(boardString);
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                int finalI = i;
//                int finalJ = j;
//                Platform.runLater(() -> {
//                    //char cellValue = boardArray[finalI][finalJ];
//                    // Ustaw białe pole z czarną obramówką jako wartość domyślną
//                    //String cellStyle = "-fx-background-color: #ffffff; -fx-border-color: black;";
//
//                    // Odwróć kolory: białe pola będą miały czarną ramkę, a czarne pole białą ramkę
////                    boardCells[finalI][finalJ].setStyle(cellStyle);
////                    boardCells[finalI][finalJ].setFill(cellValue == 'B' ? Color.BLACK : Color.WHITE);
//                });
//            }
//        }
//    }


//    private char[][] parseBoardString(String boardString) {
//        // W tym miejscu należy dostosować parsowanie ciągu znaków do struktury planszy
//        // W przykładzie zakłada się, że plansza ma rozmiar 10x10, ale można dostosować to do rzeczywistej struktury danych
//        char[][] boardArray = new char[BOARD_SIZE][BOARD_SIZE];
//        int index = 0;
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                boardArray[i][j] = boardString.charAt(index++);
//            }
//        }
//        return boardArray;
//    }
}