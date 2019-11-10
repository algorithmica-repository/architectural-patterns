package threading;

class Counter {
	private int count = 0;
	public void increment() {
		++count;
	}
	public int get() {
		return count;
	}
}

class CountingThread extends Thread {
	private Counter counter;
	
	public CountingThread(Counter counter) {
		this.counter = counter;
	}
	
	public void run() {
		for(int i = 0; i < 10000; ++i)
			counter.increment();
	}
}

public class RaceCondition {

	public static void main(String[] args) throws Exception {
		Counter counter = new Counter();
		CountingThread ct1 = new CountingThread(counter);
		CountingThread ct2 = new CountingThread(counter);
		ct1.start();
		ct2.start();
		ct1.join();
		ct2.join();
		System.out.println(counter.get());

	}

}
