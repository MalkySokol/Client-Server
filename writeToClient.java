package GroupProject;

import java.io.PrintWriter;

public class writeToClient extends Thread{

	PrintWriter pw1;
	PrintWriter pw2;
	Queue<String> finishedJobs;
	
	public writeToClient(PrintWriter pw1, PrintWriter pw2, Queue<String> finishedJobs) {
		this.pw1 = pw1;
		this.pw2 = pw2;
		this.finishedJobs = finishedJobs;
	}

	public void run() {
		while (finishedJobs.peek() == null);
		
		while (!finishedJobs.peek().equals("client finished")) {
			if ((finishedJobs.peek().charAt(finishedJobs.peek().length()-1))% 2 != 0) {
				System.out.println("Job " +finishedJobs.peek()+ " send to Client1");
				pw1.println(finishedJobs.poll());
			} 
			else {
				System.out.println("Job " +finishedJobs.peek()+ " sent to Client2");
				pw2.println(finishedJobs.poll());
			}
			
			while (finishedJobs.peek() == null);
		}
		
	}
	
}
