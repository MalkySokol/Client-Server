package GroupProject;

public class Counter {

		int number;

		public Counter(int number) {
		this.number = number;
		}

		public void set(int number) {
		this.number = number;
		}

		public int get() {
		return this.number;
		}
		
		public void increment() {
			this.number++;
		}
		
		public void decrement() {
			this.number--;
		}
		
		public int wouldAdd2 () {
			int copy = get();
			return copy+2;
		}	
		
		public int wouldAdd10 () {
			int copy = get();
			return copy+10;
		}	
		
		public void minus2() {
			this.number -= 2;
		}
		
		public void minus10() {
			this.number -= 10;
		}
		
		public void add2() {
			this.number += 2;
		}
		
		public void add10() {
			this.number += 10;
		}

} //end class

