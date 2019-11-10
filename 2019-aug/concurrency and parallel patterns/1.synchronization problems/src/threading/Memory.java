package threading;

class Dummy {
	private int count = 0;
	private boolean countReady = false;
	public void update() {
		count  = 10;
		countReady = true;
	}
	public int get() {
		return count;
	}
	public boolean isReady() {
		return countReady;
	}
}

class DummyThread1 extends Thread {
	private Dummy dummy;
	
	public DummyThread1(Dummy dummy) {
		this.dummy = dummy;
	}
	
	public void run() {
		dummy.update();
	}
}

class DummyThread2 extends Thread {
	private Dummy dummy;
	
	public DummyThread2(Dummy dummy) {
		this.dummy = dummy;
	}
	
	public void run() {
		if(dummy.isReady())
			System.out.println("The counter value:" + dummy.get());
		else
			System.out.println("Dont know counter value");
	}
}

public class Memory {
	public static void main(String[] args) throws Exception {
		Dummy dummy = new Dummy();
		DummyThread1 dt1 = new DummyThread1(dummy);
		DummyThread2 dt2 = new DummyThread2(dummy);
		dt1.start();
		dt2.start();
		dt1.join();
		dt2.join();
	}
}
