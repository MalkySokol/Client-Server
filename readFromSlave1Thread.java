package GroupProject;

import java.io.*;

public class readFromSlave1Thread extends Thread{

	private BufferedReader br;
	private String completedJob;
	private Queue<String> finishedJobs;
	Counter aTime;
	
	public readFromSlave1Thread(BufferedReader br, Queue<String> finishedJobs, Counter aTime) {
		this.br = br;
		this.finishedJobs = finishedJobs;
		this.aTime = aTime;
	}
	
	public void run() {
		
		try {
			while ((completedJob = br.readLine()).equals(null)); //initial spin
			
			while(!(completedJob).equals("client finished")) {
				System.out.println(completedJob +" complete.");	
				finishedJobs.enqueue(completedJob);
				
				if (completedJob.charAt(0) == 'a') {
					aTime.minus2();
				} else {
					aTime.minus10();
				}
				
				while ((completedJob = br.readLine()).equals(null)); //check again and spin
			}
		} catch (IOException e) {
			System.out.println("readfromslave1thread IOException");
		}
		
	}
	
	
}
