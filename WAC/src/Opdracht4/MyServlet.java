package Opdracht4;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintWriter;


public class MyServlet implements Runnable {

private Socket socket;
private Server create;


public MyServlet(Socket socket) {
    this.socket = socket;
}

@Override
public void run() {
    System.out.println("Client ontvangen!");

          try {

          InputStream is = socket.getInputStream();
          Scanner Scan = new Scanner(is);
          OutputStream os = socket.getOutputStream();
          PrintWriter pw = new PrintWriter(os);


        while (Scan.hasNextLine()) {
            String message = Scan.nextLine();
            System.out.println(message);
            if (message.isEmpty()) {
                pw.println("<h3> It works! </h3>");
                pw.flush();
            }
        }
          }
          catch(IOException e) {
              e.printStackTrace();
          }
        try {
            socket.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
}


}
