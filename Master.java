package GroupProject;

import java.io.*;
import java.util.*;

import java.net.ServerSocket;
import java.net.Socket;

public class Master {
	
	public static void main(String[]args)throws IOException{
		args = new String[] { "30121" };
		
		if(args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}
		
		int portNumber = Integer.parseInt(args[0]);
		
		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				Socket slaveSocket1 = serverSocket.accept();
				PrintWriter slaveWriter1 = new PrintWriter(slaveSocket1.getOutputStream(), true);
				BufferedReader slaveReader1 = new BufferedReader(new InputStreamReader(slaveSocket1.getInputStream()));
				
				Socket slaveSocket2 = serverSocket.accept();
				PrintWriter slaveWriter2 = new PrintWriter(slaveSocket2.getOutputStream(), true);
				BufferedReader slaveReader2 = new BufferedReader(new InputStreamReader(slaveSocket2.getInputStream()));
				
				Socket client1 = serverSocket.accept();
				PrintWriter clientWriter1 = new PrintWriter(client1.getOutputStream(), true);
				BufferedReader clientReader1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
				
				Socket client2 = serverSocket.accept();
				PrintWriter clientWriter2 = new PrintWriter(client2.getOutputStream(), true);
				BufferedReader clientReader2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
				) {
			
			System.out.println("Master Socket Connected\n");

			Queue<String> jobsQueue = new Queue<String>();
			Queue <String> finishedQueue = new Queue<String>();
			Queue<String> jobs4slave1 = new Queue<String>();
			Queue<String> jobs4slave2 = new Queue<String>();
			Counter aTime = new Counter(0);
			Counter bTime = new Counter (0);
			
			//instantiate and run threads
			Thread readFromClient1 = new readFromClient1 (clientReader1, jobsQueue);
			Thread readFromClient2 = new readFromClient2 (clientReader2, jobsQueue);
			Thread writeToSlave1 = new writeToSlave1Thread(slaveWriter1, jobs4slave1);
			Thread writeToSlave2 = new writeToSlave2Thread(slaveWriter2, jobs4slave2);
			Thread readFromSlave1 = new readFromSlave1Thread(slaveReader1, finishedQueue, aTime);
			Thread readFromSlave2 = new readFromSlave2Thread(slaveReader2, finishedQueue, bTime);
			Thread writeToClient = new writeToClient(clientWriter1, clientWriter2, finishedQueue);
			
			readFromClient1.start();
			readFromClient2.start();
			writeToSlave1.start();
			writeToSlave2.start();			
			readFromSlave1.start();
			readFromSlave2.start();
			writeToClient.start();
			
			
			
			while (jobsQueue.peek() == null);
			while (!jobsQueue.peek().equals("client finished")) {
				
				if(jobsQueue.peek().charAt(0) == 'a'){//if its job type A, check counters
					
					if(aTime.wouldAdd2() <= bTime.wouldAdd10()){//checks if sending opt job to slave1 would increase throughput
					
						System.out.println("adding job " + jobsQueue.peek() + " to jobs4slave1");
						jobs4slave1.enqueue(jobsQueue.poll());
						aTime.add2();
					}
					else {
						
						//if bTime.wouldAdd10 is still less than aTime.wouldAdd2, send to jobs4slave2
						System.out.println("adding job " + jobsQueue.peek() + " to jobs4slave2");
						jobs4slave2.enqueue(jobsQueue.poll());
						bTime.add10();
					}
					
				}
				
				else {//if its job type B, check counters
										
					if(bTime.wouldAdd2() <= aTime.wouldAdd10()){//checks if sending opt job to slave1 would increase throughput
						
						System.out.println("adding job " + jobsQueue.peek() + " to jobs4slave2");
						jobs4slave2.enqueue(jobsQueue.poll());
						bTime.add2();
					}
					else {
						
						System.out.println("adding job " + jobsQueue.peek() + " to jobs4slave1");
						jobs4slave1.enqueue(jobsQueue.poll());
						aTime.add10();
					}		
				}
				
				while (jobsQueue.peek() == null);
			} //end
			
			try {
				readFromClient1.join();
				readFromClient2.join();
				writeToSlave1.join();
				writeToSlave2.join();
				readFromSlave1.join();
				readFromSlave2.join();
				writeToClient.join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

				} catch (IOException e) {
					System.out.println(
							"Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
					System.out.println(e.getMessage());
				}
	}//close main brace 
	
}//end Master Class


