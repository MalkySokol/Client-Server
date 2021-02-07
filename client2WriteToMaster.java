package GroupProject;

import java.io.PrintWriter;

public class client2WriteToMaster extends Thread {

	private PrintWriter pw;
	private Queue <String> jobs;
	
	public client2WriteToMaster(PrintWriter pw, Queue<String> jobs) {
		this.pw = pw;
		this.jobs = jobs;
	}
	
	public void run () {
		while (jobs.peek() == null);
		while (!jobs.peek().equals("client finished")) {
//			if (jobs4slave1.peek().equals(null)){
//				jobs4slave1.poll();
//			} else {
				System.out.println("Sending job " + jobs.peek() +" to Master");
				pw.println(jobs.poll());
				while (jobs.peek() == null);
//			}
		}
	}
	
}
