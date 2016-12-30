import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class SSocket implements Runnable {

	private Socket client;

	public SSocket(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		DataInputStream input;
		DataOutputStream output;
		
		try {
			InputStream is = client.getInputStream();
			OutputStream os = client.getOutputStream();
			
			input = new DataInputStream(is);
			output = new DataOutputStream(os);
			
			String listMsg = input.readUTF();
			output.writeUTF("You Say : " + listMsg);
			System.out.println("Receive: " + listMsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
