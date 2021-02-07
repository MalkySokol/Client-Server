package GroupProject;

import java.io.BufferedReader;
import java.io.IOException;

public class c1ReadFromMaster extends Thread {

	private BufferedReader br;
	private String currJob;
	
	public c1ReadFromMaster (BufferedReader br) {
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
