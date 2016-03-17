package com.ca.core;

public class SenderThread implements Runnable {
	private Object lock = new Object();

	public Object getSenderLockObject() {
		return lock;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (Core.senderQueue.isEmpty()) {
					synchronized (lock) {
						System.out.println("Queue is empty!!!Sender thread awaiting for queue");
						lock.wait();
					}
				} else {
					Core.senderQueue.poll();
					System.out.println("Process write to beckend done");
				}
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
