package GroupProject;
import java.io.*;

public class slave1RWToMaster extends Thread {
	
	private PrintWriter pw;
	private BufferedReader br;	
	
	public slave1RWToMaster(PrintWriter pw, BufferedReader br) {
		this.pw = pw;
		this.br = br;
	}
	
	public void run() {
		String job;
		
		try {
			while (! (job = br.readLine()).equals("client finished")) {
				System.out.println("Reading job " + job + " in Slave1wrToMaster");
				if(job.equals(null)) {
					continue;
				} else {
					if (job.charAt(0) == 'a') {
						System.out.println("Sleeping 2 secs");
						sleep(2000); //opt job
						System.out.println("Sending job "+job+" to Master");
						pw.println(job);
					} else if (job.charAt(0) == 'b') {
						System.out.println("Sleeping 10 secs");
						sleep(10000); //non-opt job
						System.out.println("Sending job "+job+" to Master");
						pw.println(job);
					}
//					System.out.println("Sending job "+job+" to Master");
//					pw.println(job);
				}
			}
		} catch (IOException e) {
			System.out.println("slave1rwtomaster IOException catch");			
		} catch (InterruptedException e) {
			System.out.println("slave1rwtomaster InterruptedException catch");
		}
	}

}
