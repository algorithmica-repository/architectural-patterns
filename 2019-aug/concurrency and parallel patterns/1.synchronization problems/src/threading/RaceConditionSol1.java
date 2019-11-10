package threading;

class Counter1 {
	private int count = 0;
	public void increment() {
		++count;
	}
	public int get() {
		return count;
	}
}

class CountingThread1 extends Thread {
	private Counter1 counter;
	
	public CountingThread1(Counter1 counter) {
		this.counter = counter;
	}
	
	public void run() {
		for(int i = 0; i < 10000; ++i)
			counter.increment();
	}
}

public class RaceConditionSol1 {

	public static void main(String[] args) throws Exception {
		Counter1 counter = new Counter1();
		CountingThread1 ct1 = new CountingThread1(counter);
		CountingThread1 ct2 = new CountingThread1(counter);
		ct1.start();
		ct2.start();
		ct1.join();
		ct2.join();
		System.out.println(counter.get());

	}

}

/*
Intrinsic locks are convenient but limited.
� There is no way to interrupt a thread that�s blocked as a result of trying
to acquire an intrinsic lock.
� There is no way to time out while trying to acquire an intrinsic lock.
� There�s exactly one way to acquire an intrinsic lock: a synchronized block
*/
