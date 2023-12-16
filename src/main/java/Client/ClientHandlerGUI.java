package Client;

        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.layout.GridPane;
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

    private Rectangle[][] boardCells;

    private CommunicationWithServer communicationWithServer;
    //private Label[][] boardLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        this.boardCells = new Rectangle[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardCells[i][j] = new Rectangle(30, 30);
                boardCells[i][j].setFill(Color.BROWN);
                boardCells[i][j].setStroke(Color.WHITE);
                pane.add(boardCells[i][j], i, j);
            }
        }

        Scene scene = new Scene(pane, 300, 300);
        stage.setTitle("Go Game");
        stage.setScene(scene);
        stage.show();

        pane.setOnMouseClicked(event -> {
            sendCoordinates(event.getX(), event.getY());
        });



        new Thread(() -> {
            try  {
                Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 3357);
                communicationWithServer = new CommunicationWithServer(socket);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
                communicationWithServer.startListen();

        }).start();
    }


    public void sendCoordinates(double x, double y){
        if (communicationWithServer != null) {
            communicationWithServer.sendToServer(x,y);
        }
    }

    private void updateBoardInGUI(String boardString) {
        // Aktualizacja GUI zgodnie z planszą otrzymaną od serwera
        char[][] boardArray = parseBoardString(boardString);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int finalI = i;
                int finalJ = j;
                Platform.runLater(() -> {
                    //char cellValue = boardArray[finalI][finalJ];
                    // Ustaw białe pole z czarną obramówką jako wartość domyślną
                    //String cellStyle = "-fx-background-color: #ffffff; -fx-border-color: black;";

                    // Odwróć kolory: białe pola będą miały czarną ramkę, a czarne pole białą ramkę
//                    boardCells[finalI][finalJ].setStyle(cellStyle);
//                    boardCells[finalI][finalJ].setFill(cellValue == 'B' ? Color.BLACK : Color.WHITE);
                });
            }
        }
    }


    private char[][] parseBoardString(String boardString) {
        // W tym miejscu należy dostosować parsowanie ciągu znaków do struktury planszy
        // W przykładzie zakłada się, że plansza ma rozmiar 10x10, ale można dostosować to do rzeczywistej struktury danych
        char[][] boardArray = new char[BOARD_SIZE][BOARD_SIZE];
        int index = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardArray[i][j] = boardString.charAt(index++);
            }
        }
        return boardArray;
    }
}