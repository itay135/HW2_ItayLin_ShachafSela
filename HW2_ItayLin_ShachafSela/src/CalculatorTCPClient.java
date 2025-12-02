import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class CalculatorTCPClient {

	public static void main(String[] args) 
	{
		try(Socket socket=new Socket("127.0.0.1",9090))
		{
			System.out.println("Connected to sever");
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Scanner scan = new Scanner(System.in);
			
			String inputline;
			
			while(true) 
			{
				System.out.println("Pls enter expression in format : num operator num (enter exit to quit)");
				inputline=scan.nextLine();
				out.println(inputline);
				
				if(inputline.equalsIgnoreCase("exit")) {
					System.out.println("Client closed");
					
					break;
				}
				
			       String response = in.readLine();

	                
	                System.out.println(response);
			}

			
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
