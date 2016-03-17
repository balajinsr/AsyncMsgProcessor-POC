package com.ca.core;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Core {
	public static Map<String, Object> map = new ConcurrentHashMap<String,Object>();
	public static Map<String, Data> receiverMap = new ConcurrentHashMap<String,Data>();
	public static Queue<Data> senderQueue = new ConcurrentLinkedQueue<Data>();
}
