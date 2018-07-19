package Client;

import java.net.Socket;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Object;
import java.io.Writer;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] arg) throws Exception {
		Socket s = new Socket("145.89.164.55", 4711);
		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine() + "\n";
		pw.write(input + "\n");
		pw.flush();

		while (!input.equals("stop")) {
			sc = new Scanner(System.in);
			input = sc.nextLine();
			pw.write(input + "\n");
			pw.flush();

		}

		sc.close();

	}
}