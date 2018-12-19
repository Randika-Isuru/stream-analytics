package org.monitoring.stream.analytics.service.rest;

public class RestApplication {

	public static void main(String[] args) {
		
		startRestServices();
	}
	
	private static void startRestServices() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					RestServer.start();
				} catch (Throwable ex) {
					System.out.println("Error Starting Rest Server");
				}
			}
		}).start();
	}

}
