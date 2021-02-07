package GroupProject;

import java.io.*;

public class writeToSlave2Thread extends Thread{
	
	PrintWriter pw;
	Queue<String> jobs4slave2;
	
	public writeToSlave2Thread(PrintWriter slaveWriter2, Queue<String> jobs4slave2) {
		pw = slaveWriter2;
		this.jobs4slave2 = jobs4slave2;
	}

	public void run() {
		while (jobs4slave2.peek() == null);
		
//		if (jobs4slave2.peek() == null) {
//			
//		}
		
		while (!jobs4slave2.peek().equals("client finished")) {
//			if (jobs4slave2.peek().equals(null)){
//				jobs4slave2.poll();
//			} else {
				System.out.println("Sending job " + jobs4slave2.peek() +" to Slave2");
				pw.println(jobs4slave2.poll());
				while (jobs4slave2.peek() == null);
//			}
			
		}
	}
}
