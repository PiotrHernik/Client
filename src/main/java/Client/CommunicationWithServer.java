package Client;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

public class CommunicationWithServer {
    private Socket socket;
    private String message;
    private PrintStream output;
    private BufferedReader input;
    private final int squereSize;
    private boolean stop = false;

    public CommunicationWithServer(Socket socket, int squereSize){
        this.socket = socket;
        this.squereSize = squereSize;
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

            while(!stop){
                message = input.readLine();
                System.out.println(message);
            }
        }
        catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
    public void sendToServer(Object object){
        if(object instanceof Point2D) {
            Point2D point = (Point2D) object;
            double x = point.getX();
            double y = point.getY();
            System.out.println(x);
            System.out.println(y);

            int countX = (int) (x / squereSize);
            if(x % squereSize > (double)(squereSize / 2)){
                countX++;
            }
            int countY = (int) (y / squereSize);
            if(y % squereSize > (double)(squereSize / 2)){
                countY++;
            }
            Object point2D = new Point2D(countX, countY);

            output.println(point2D);
        }
        else{

            output.println(object);
        }
    }
    public void stopListen(){
        stop = true;
    }

}
