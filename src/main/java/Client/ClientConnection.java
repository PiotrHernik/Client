//package Client;
//        import javafx.application.Application;
//        import javafx.scene.control.Alert;
//
//        import java.io.*;
//        import java.net.InetAddress;
//        import java.net.Socket;
//
//public class ClientConnection
//{
//    //private Socket socket;
//    private String string;
//
//
//    public static void main (String[] args) {
//        try{
//            Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 3357);
//            PrintStream output = new PrintStream(socket.getOutputStream());
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println("Jestem przed uruchomieniem aplikacji");
//            ClientHandlerGUI clientHandlerGUI = new ClientHandlerGUI();
//            Application.launch(clientHandlerGUI, args);
//            System.out.println("Jestem po uruchomieniu aplikacji");
//
//
////            output.println("bleh");
////            output.flush();
//
//        }
//        catch(IOException e){
//            System.out.println("IO Exception: " + e.getMessage());
//        }
//    }
//
////        public void closeConnection()
////        {
////            try
////            {
////                input.close();
////                output.close();
////                socket.close();
////            }
////            catch (IOException e) {
////                displayAlert("Error");
////            }
////        }
//
//
//
////    public static void displayAlert(String message) {
////        Alert alert = new Alert(Alert.AlertType.INFORMATION);
////        alert.setTitle("Information");
////        alert.setHeaderText(null);
////        alert.setContentText(message);
////        alert.showAndWait();
////    }
//}