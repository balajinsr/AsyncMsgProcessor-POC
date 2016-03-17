package com.ca.core;

import java.util.Queue;

public class WorkerThread implements Runnable {

    private Data data;
    private SenderThread senderThread;

	public WorkerThread(Data data){
        this.data=data;
    }

    @Override
    public void run() {
        Object lock = Thread.currentThread();

			try {
				System.out.println("===Tomcat thread started===");
				Core.map.put(data.getKey(), lock);
				Queue<Data> queueIn = Core.senderQueue;
	        	queueIn.offer(data);
	        	SenderThread senderThread = getSenderThread();
	        	Object senderLock = senderThread.getSenderLockObject();
	        	synchronized (senderLock) {
	        		senderLock.notify();
				}

				synchronized (lock) {
					lock.wait(8000);
				}

				System.out.println("===Tomcat thread end===");
				Core.map.remove(data.getKey());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	public SenderThread getSenderThread() {
		return senderThread;
	}

	public void setSenderThread(SenderThread senderThread) {
		this.senderThread = senderThread;
	}
}