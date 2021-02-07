package GroupProject;

import java.io.*;

public class writeToSlave1Thread extends Thread{
	
	PrintWriter pw;
	Queue<String> jobs4slave1;
	
	public writeToSlave1Thread(PrintWriter slaveWriter1, Queue<String> jobs4slave1) {
		pw = slaveWriter1;
		this.jobs4slave1 = jobs4slave1;
	}

	public void run() {
		while (jobs4slave1.peek() == null);
		while (!jobs4slave1.peek().equals("client finished")) {
//			if (jobs4slave1.peek().equals(null)){
//				jobs4slave1.poll();
//			} else {
				System.out.println("Sending job " + jobs4slave1.peek() +" to Slave1");
				pw.println(jobs4slave1.poll());
				while (jobs4slave1.peek() == null);
//			}
		}
		
	}
}
