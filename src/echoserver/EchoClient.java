package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();

		//Starts the reading server thread
		ReadFromServer reader = new ReadFromServer(socket);
		Thread firstThread = new Thread(reader);
		firstThread.start();

		//Starts the writing server thread
		WriteToServer writer = new WriteToServer(socket);
		Thread secondThread = new Thread(writer);
		secondThread.start();
		
		
	}

	public class ReadFromServer implements Runnable{

		//Declares fields
		private Socket socket;

		private int nextByte;

		InputStream input;

		//Constructs the class
		public ReadFromServer(Socket socket) throws IOException {
			this.input = socket.getInputStream();
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				while ((nextByte = input.read()) != -1) {
					//reads it from the server and writes it out
					System.out.write(nextByte);
					System.out.flush();
				  }
				  socket.close();
			} catch (ConnectException ce) {
				System.out.println("We were unable to connect to localhost");
				System.out.println("You should make sure the server is running.");
			  } catch (IOException ioe) {
				System.out.println("We caught an unexpected exception");
				System.err.println(ioe);
			  }
		
		}



	}

	public class WriteToServer implements Runnable{

		//Declares fields
		private Socket socket;

		private int nextByte;

		OutputStream output;

		//Contructs class
		public WriteToServer(Socket socket) throws IOException {
			this.output = socket.getOutputStream();
			this.socket = socket;
		}

		@Override
		public void run() {

			try {
				while ((nextByte = System.in.read()) != -1) {
					//Sends data to the server
					output.write(nextByte);
					output.flush();
				  }

				  socket.shutdownOutput();
			} catch (IOException ioe) {
				System.out.println("We caught an unexpected exception");
				System.err.println(ioe);
			  }

		

			
		}



	}
}