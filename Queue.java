package GroupProject;
import java.io.Serializable;
import java.util.*;

public class Queue<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Vector<T> jobs;
	
	public Queue() {
		jobs = new Vector<T>();
	}
	
	public void enqueue(T val) {
		jobs.add(val);
	}
	
	public boolean dequeue() {
		if(!jobs.isEmpty()) {
			jobs.remove(0);
			return true;
		}
		else {
			return false;
		}
	}
	
	public T poll() {
		if(!jobs.isEmpty()) {
			T val = jobs.get(0);
			jobs.remove(0);
			return val;
		}
		else {
			return null;
		}
	}
	
	public T peek() {
		if(jobs.isEmpty()) {
			return null;
		}
		else {
			return jobs.get(0);
		}
	}
	
	
	public boolean isEmpty() {
		return jobs.isEmpty();
	}
}
