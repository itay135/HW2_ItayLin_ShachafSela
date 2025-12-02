import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class QuoteUDPClient {

	public static void main(String[] args) 
	{
		try 
		{
			DatagramSocket socket = new DatagramSocket();
			Scanner scan = new Scanner(System.in);
			byte[] sendBuffer;
			byte[] receiveBuffer = new byte[1024];
			 
;
			while(true) 
			{
				System.out.println("Enter the word 'GET' to recieve a quote, or 'exit' to quit.");
				String message = scan.next();
				
				sendBuffer=message.getBytes();
				InetAddress addr = InetAddress.getByName("localhost");
				DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, addr, 8080);
				socket.send(sendPacket);
				
				 if (message.equalsIgnoreCase("exit")) 
	 				{ 
	 					System.out.println("Client finished.");
	 					break;
	 				}
				 
				 DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
				 socket.receive(receivePacket);
				 String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
				 System.out.println("Quote recieved: " + serverResponse);
				 
			}
			 socket.close();
			 scan.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}
}
