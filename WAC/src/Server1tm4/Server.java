package Server1tm4;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.Object;
import java.util.Scanner;

class Server {
	
  public static void main(String[] arg) throws Exception {
    ServerSocket ss = new ServerSocket(4711);
    Socket s = ss.accept();
    InputStream is = s.getInputStream();
    Scanner scanner = new Scanner(is);
    OutputStream os = s.getOutputStream();
    PrintWriter pw = new PrintWriter(os);

    while(scanner.hasNextLine()) {
        String message = scanner.nextLine();
        System.out.println(message);
        if (message.isEmpty()) {
            pw.println("<h1> It works! </h1>");
            pw.flush();
    }
    }
    scanner.close();  
    s.close();
    ss.close();
  }
}

