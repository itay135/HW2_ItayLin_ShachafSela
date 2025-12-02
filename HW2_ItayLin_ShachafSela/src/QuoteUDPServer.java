import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;
public class QuoteUDPServer {

	public static void main(String[] args) 
	{
		int port=8080;
		try 
		{
			DatagramSocket ds = new DatagramSocket(port);
			System.out.println("Server is running on port :" + port);
			ArrayList<String> quotes = new ArrayList<String>();
			quotes.add("Believe you can and you're halfway there.");
			quotes.add("Success is the sum of small efforts repeated daily.");
			quotes.add("Your only limit is your mind.");
			quotes.add("Dream big. Start small. Act now.");
			quotes.add("Don't stop when you're tired. Stop when you're done.");
			
			byte[] buffer = new byte[1024];
			byte[] sendBuffer;
			
			while(true) 
			{
				
				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
				ds.receive(receivePacket);
				
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
				
				if(message.equalsIgnoreCase("exit")) 
				{
					 System.out.println("Request recieved: "+message);
					 System.out.println("Server shutting down.");
					 break;
				}
				
				if(message.equalsIgnoreCase("GET")) 
				{
					System.out.println("Request recieved: "+message);
					Random r= new Random();
					int r1 = r.nextInt(5);
					
					sendBuffer = quotes.get(r1).getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendBuffer,sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
					
					ds.send(sendPacket);
				}

			}
		 	ds.close();
		} 
		
		catch (SocketException e) 
		{
			
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

}
