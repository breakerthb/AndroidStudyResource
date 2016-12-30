import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int PORT = 9999;
		
		System.out.println("Server ...");
		System.out.println("Listen Port : " + PORT);
		
		ServerSocket server;
		try {
			server = new ServerSocket(PORT);
			
			while (true) {
				Socket client = server.accept();
				new Thread(new SSocket(client)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
