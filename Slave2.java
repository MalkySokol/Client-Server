package GroupProject;

import java.io.*;
import java.net.*;

public class Slave2 {

	public static void main(String[]args) {
		
		args = new String[] {"127.0.0.1", "30121"};
		
		if(args.length != 2) {
			System.err.println(
	                "Usage: java EchoClient <host name> <port number>");
	        System.exit(1);
		}
		
		String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        try(
        	Socket slaveSocket = new Socket(hostName, portNumber);
        	PrintWriter writeToMaster = new PrintWriter(slaveSocket.getOutputStream(), true);
        	BufferedReader readFromMaster = new BufferedReader(new InputStreamReader(slaveSocket.getInputStream()));
        ){
        	System.out.println("Slave2 Socket Connected\n");
        	
        	Thread rw2M = new slave2RWToMaster(writeToMaster, readFromMaster);
        	rw2M.start();
        	
        	try {
        		rw2M.join();
        	}catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
        	
//        	String job;
//        	while((job = readFromMaster.readLine()) != null) {
//        		System.out.println("Slave2 recieved job: "+job);
//        		
//        		if(job.charAt(0) == ('b')) {
//        			
//        			//thread.sleep(2000);
//        			System.out.println("Job "+job+" sleeping for 2 seconds.");
//        		}
//        		else { 
//        			
//        			//thread.sleep(10000);
//        			System.out.println("Job "+job+" sleeping for 10 seconds.");
//        			
//        		}
//        		writeToMaster.println(job);
//        		System.out.println("Job "+job+ " compleated by slave2 and sent back to master.");
//        	}
//        	System.out.println("Slave2 reached null");
        	
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
