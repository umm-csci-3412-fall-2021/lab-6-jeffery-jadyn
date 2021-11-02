package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	
	public static final int PORT_NUMBER = 6013; 
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket socket = serverSocket.accept();

			//Creates thread from client connection
			CreateThread client = new CreateThread(socket);
			Thread clientThread = new Thread(client);
			clientThread.start();

		}
	}

	public class CreateThread implements Runnable{


		//Declares fields
		private Socket socket;

		InputStream input;

		OutputStream output;

		//Constructs class
		public CreateThread(Socket socket) throws IOException{
			this.input = socket.getInputStream();
			this.output = socket.getOutputStream();
			this.socket = socket;
		}

		@Override
		public void run() {

			try{ 
	
				int nextByte;
	
				//Run forever, which is common for server-style services
				while(true){
	
					//reads data from the client socket
					while ((nextByte = input.read()) != -1) {
						//writes back to the client 
						output.write(nextByte);
						output.flush();
					  }
	
					  //closes everything that needs to be closed
					  input.close();
					  output.close();
	
					socket.close();
				}
			//very minimal error handling 
			} catch (IOException ioe) {
	
				System.out.println("We caught an unexpected exception");
				System.err.println(ioe);
			}


			
		}

		
	}
}