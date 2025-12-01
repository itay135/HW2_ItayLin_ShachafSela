import java.net.ServerSocket;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class CalculatorTCPServer {

	public static void main(String[] args) 
	{
		try(ServerSocket server = new ServerSocket(9090))
		{
			System.out.println("Server is listening on port 9090");
			Socket s = server.accept();
			System.out.println("New client connected");
			BufferedReader in = new BufferedReader(new InputStreamReader (s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			String inputline;
			
			while((inputline = in.readLine()) != null) 
			{
				if(inputline.equalsIgnoreCase("exit")) 
				{
					System.out.println("Closing calculator..");
					break;
				}
				System.out.println("Server received: " + inputline);
				
				String [] tar =inputline.split(" ");
				if(tar.length!=3) {
					out.println("Error: Invalid expression");
				}
				
				else
				{
					
				
				try 
				{
					double a = Double.parseDouble(tar[0]);
					String op = tar[1];
					double b = Double.parseDouble(tar[2]);
				
					switch(op) 
					{
				
						case "+":
							out.println(inputline + " = " + (a+b));
							break;
						case "-":
							out.println(inputline + " = " + (a-b));
							break;
						case "*":
							out.println(inputline + " = " + (a*b));
							break;
						case "/":
							if(b==0) 
							{
								out.println("Error: Division by zero");
								break;
							}
							
							out.println(inputline + " = " + (a/b));
							break;
						default:
							out.println("Error: Invalid expression");
					
					}
				}
				catch(ArithmeticException e) {
					out.println("Error: Invalid expression");
				}
				catch(NumberFormatException e)
				{
					out.println("Error: Invalid expression");
				}

				}
			}
			

		}

		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		

	}

}
