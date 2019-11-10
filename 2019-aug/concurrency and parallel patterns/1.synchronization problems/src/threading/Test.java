package threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

	public static void main(String[] args) throws Exception {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(3);
		for(int i = 0; i < 3; ++i)
			queue.put(i);
		queue.put(10);
		System.out.println(queue);

	}

}
