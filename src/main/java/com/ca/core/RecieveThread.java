package com.ca.core;


public class RecieveThread implements Runnable {
	
	@Override
	public void run() {
		while (true) {
			try {
				for (int i = 0; i < 2; i++) {
					Data data = new Data();
					data.setKey("Key" + i);
					data.setValue("Response from Backed: Value" + i);
					Core.receiverMap.put(data.getKey(), data);
					System.out.println("added "+i);
				}
				Thread.sleep(500000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
