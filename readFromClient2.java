package GroupProject;

import java.io.BufferedReader;
import java.io.IOException;

public class readFromClient2 extends Thread{

	private BufferedReader br;
	private String job;
	private Queue<String> jobsQueue;
	
	public readFromClient2(BufferedReader br, Queue<String> jobsQueue) {
		this.br = br;
		this.jobsQueue = jobsQueue;
	}
	
	public void run() {
		
		try {
			while ((job = br.readLine()) == null); //initial spin
			
			while(!(job).equals("client finished")) {
				System.out.println("Client 2 sent job " +job +" to Master.");	
				jobsQueue.enqueue(job);
				
				while ((job = br.readLine()) == null); //check again and spin
			}
		} catch (IOException e) {
			System.out.println("readFromClient2 IOException");
		}
		
	}
	
}
