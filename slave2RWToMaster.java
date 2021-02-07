package GroupProject;

import java.io.*;

public class slave2RWToMaster extends Thread{

	private PrintWriter pw;
	private BufferedReader br;	
	
	public slave2RWToMaster(PrintWriter pw, BufferedReader br) {
		this.pw = pw;
		this.br = br;
	}
	
	public void run() {
		String job;
		try {
			while (! (job = br.readLine()).equals("client finished")) {
				System.out.println("Reading job " + job + " in Slave2wrToMaster");
				if(job.equals(null)) {
					continue;
				} 
				else {
					if (job.charAt(0) == 'b') {
						System.out.println("Sleeping 2 secs");
						sleep(2000); //opt job
						pw.println(job);
						System.out.println("Sending job "+job+" to Master");
					} else if (job.charAt(0) == 'a'){
						System.out.println("Sleeping 10 secs");
						sleep(10000); //non-opt job
						pw.println(job);
						System.out.println("Sending job "+job+" to Master");
					}
				}
			}
		} catch (IOException e) {
			System.out.println("slave2rwtomaster IOException catch");			
		} catch (InterruptedException e) {
			System.out.println("slave2rwtomaster InterruptedException catch");
		}
	}
	
}
