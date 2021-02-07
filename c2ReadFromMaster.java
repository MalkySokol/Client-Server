package GroupProject;

import java.io.BufferedReader;
import java.io.IOException;

public class c2ReadFromMaster extends Thread {

	private BufferedReader br;
	private String currJob;
	
	public c2ReadFromMaster (BufferedReader br) {
		this.br = br;
	}
	
	public void run() {
		try {
			while (!(currJob = br.readLine()).equals("client finished")) {
				System.out.println("Job " + currJob + " finished");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
