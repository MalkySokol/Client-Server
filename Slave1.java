package GroupProject;

import java.io.*;

import java.net.Socket;
import java.net.UnknownHostException;

public class Slave1 {
	
	public static void main(String[]args) throws ClassNotFoundException{
			
		args = new String[] {"127.0.0.1", "30121"};
			
		if(args.length != 2) {
			System.err.println("Usage: java EchoClient <host name> <port number>");
		        System.exit(1);
		}
			
		String hostName = args[0];
	        int portNumber = Integer.parseInt(args[1]);
	        
	        try(
	        Socket slaveSocket = new Socket(hostName, portNumber);
	        PrintWriter writeToMaster = new PrintWriter(slaveSocket.getOutputStream(), true);
	        BufferedReader readFromMaster = new BufferedReader(new InputStreamReader(slaveSocket.getInputStream()));	
	        ){
	        	System.out.println("Slave1 Socket Connected\n");
	        	
	        	Thread rw2M = new slave1RWToMaster(writeToMaster, readFromMaster);
	        	rw2M.start();
	        	
	        	try {
	        		rw2M.join();
	        	}catch (InterruptedException e) {
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
