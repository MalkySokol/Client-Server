package GroupProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {
	
	public static void main(String[]args) throws ClassNotFoundException{
		
		args = new String[] {"127.0.0.1", "30121"};
		
		if(args.length != 2) {
			System.err.println(
	                "Usage: java EchoClient <host name> <port number>");
	        System.exit(1);
		}
		
		String hostName = args[0];
                int portNumber = Integer.parseInt(args[1]);
        
        	try(
        	Socket client1 = new Socket(hostName, portNumber);
        	PrintWriter writeToMaster = new PrintWriter(client1.getOutputStream(), true);
        	BufferedReader readFromMaster = new BufferedReader(new InputStreamReader(client1.getInputStream()));	
        	){
			System.out.println("Client1 Socket Connected\n");

			Queue <String> jobsQueue = new Queue<String>();

			Thread write2Master = new client1WriteToMaster(writeToMaster, jobsQueue);
			Thread c1ReadFromMaster = new c1ReadFromMaster(readFromMaster);
			write2Master.start();
			c1ReadFromMaster.start();

			Scanner input = new Scanner(System.in);
			int counter = 1;
			String currJob;

			System.out.println("Enter -1 to quit\n");

			do {
				System.out.println("Enter job type (a/b): ");
				currJob = input.nextLine().toLowerCase();

				if (currJob.equals("-1")) {
					continue;
				}

				while(currJob.equals("a") && currJob.equals("b")) {
					System.out.println("Invalid Entry!\n Enter job type (a/b): ");
					currJob = input.nextLine().toLowerCase();
				}

				currJob += counter;
				jobsQueue.enqueue(currJob);
				counter += 2;

			} while (!currJob.equals("-1"));

			jobsQueue.enqueue("client finished");

			try {
				write2Master.join();
				c1ReadFromMaster.join();
			}
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}        	
        	
        	}catch (UnknownHostException e) {
            	System.err.println("Don't know about host " + hostName);
            	System.exit(1);
		} catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to " +
	                hostName);
	            System.exit(1);
		}
        
	}//close main

}
