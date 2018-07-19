package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.OutputStream;
import java.io.PrintWriter;


class Server {

     public static void main(String[] args) {
            new Server().startServer();
        }
    public void startServer() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                     ServerSocket ss = new ServerSocket(4711);
                     System.out.println("wachten op een client...");

                     while(true) {
                         Socket s = ss.accept();
                         clientProcessingPool.submit(new MyServlet(s));
                     }
                } catch (IOException e) {
                    System.err.println("kan client niet verwerken.");
                    e.printStackTrace();
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

 
}

