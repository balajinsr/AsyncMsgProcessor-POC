package com.ca.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		SenderThread senderThreadIn = new SenderThread();
		Thread senderThread = new Thread(senderThreadIn);
		senderThread.start();
		
		for (int i = 0; i < 2; i++) {
			Data data = new Data();
			data.setKey("Key" + i);
			data.setValue("Value" + i);
			WorkerThread wt = new WorkerThread(data);
			wt.setSenderThread(senderThreadIn);
			Runnable worker = wt;
			executor.execute(worker);
		}

		Thread.sleep(2000);
		int i = 0;
		for (i = 0; i < 2; i++) {
			Data data = new Data();
			data.setKey("Key" + i);
			Object lock = Core.map.get(data.getKey());
			if(lock != null)  {
				synchronized (lock) {
					lock.notify();
				}
			} else {
				System.out.println("Time out exception");
			}
			if(i%100 ==0) {
				Thread.sleep(1000);
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}
}
