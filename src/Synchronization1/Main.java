package Synchronization1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main
{
	static final int THREADS = 2;
	static final int LOOPS = 1000000;
	static final Semaphore semaphore = new Semaphore(0);
	static final ReentrantLock lock = new ReentrantLock();

	static synchronized void synchronize()
	{
	}

	static void lock()
	{
		lock.lock();
		lock.unlock();
	}

	public static void main(String[] args)
	{
		long millis;
		Thread[] sthreads = new Thread[THREADS];
		Thread[] lthreads = new Thread[THREADS];

		for(int i = 0; i < THREADS; ++i)
		{
			sthreads[i] = new Thread(() ->
			{
				try
				{
					semaphore.acquire();
				}
				catch (InterruptedException e) {}
				for (int j = 0; j < LOOPS; ++j) synchronize();
			});
		}

		for(int i = 0; i < THREADS; ++i)
		{
			lthreads[i] = new Thread(() ->
			{
				try
				{
					semaphore.acquire();
				}
				catch (InterruptedException e) {}
				for (int j = 0; j < LOOPS; ++j) lock();
			});
		}

		for(int i = 0; i < THREADS; ++i) sthreads[i].start();
		millis = System.currentTimeMillis();
		semaphore.release(2);
		try
		{
			for(int i = 0; i < THREADS; ++i) sthreads[i].join();
		}
		catch (InterruptedException e) {}
		millis = System.currentTimeMillis() - millis;
		System.out.println("Time with synchronized: " + millis + " ms");

		for(int i = 0; i < THREADS; ++i) lthreads[i].start();
		millis = System.currentTimeMillis();
		semaphore.release(2);
		try
		{
			for(int i = 0; i < THREADS; ++i) lthreads[i].join();
		}
		catch (InterruptedException e) {}
		millis = System.currentTimeMillis() - millis;
		System.out.println("Time with lock: " + millis + " ms");
	}
}
