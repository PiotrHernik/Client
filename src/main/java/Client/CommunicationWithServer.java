package Client;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class CommunicationWithServer {
    private Socket socket;
    private String message;
    PrintStream output;
    BufferedReader input;
    public CommunicationWithServer(Socket socket){
        this.socket = socket;
        try{
            this.output = new PrintStream(socket.getOutputStream());
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
    public void startListen(){
        try {

            while(true){
                message = input.readLine();
                System.out.println(message);
            }
        }
        catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
    public void sendToServer(double x, double y){
        System.out.println(x);
        System.out.println(y);

        int countX = 0;
        int countY = 0;
        while(x >= 1){
            x -= 1;
            countX++;
        }
        while(y >= 1){
            y -= 1;
            countY++;
        }
        if(x >= 0.5){
            countX++;
        }
        if(y >= 0.5){
            countY++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(countX);
        stringBuilder.append(countY);

        output.println(stringBuilder);

    }

}
