package GroupProject;

import java.io.*;

public class readFromSlave2Thread extends Thread{

	private BufferedReader br;
	private String completedJob;
	private Queue<String> finishedJobs;
	Counter bTime;
	
	public readFromSlave2Thread(BufferedReader br, Queue<String> finishedJobs, Counter bTime) {
		this.br = br;
		this.finishedJobs = finishedJobs;
		this.bTime = bTime;
	}
	
	public void run() {
		
		try {
			while ((completedJob = br.readLine()).equals(null)); //initial spin
			
			while(!(completedJob.equals("client finished"))) {
				System.out.println(completedJob +" complete.");	
				finishedJobs.enqueue(completedJob);
				
				if (completedJob.charAt(0) == 'b') {
					bTime.minus2();
				} else {
					bTime.minus10();
				}
				
				while ((completedJob = br.readLine()).equals(null)); //check again and spin
			}
			
		} catch (IOException e) {
			System.out.println("readfromslave2thread IOException");
		}
		
	}
	
	
}
