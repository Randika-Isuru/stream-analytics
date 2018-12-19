package org.monitoring.stream.analytics.service.rest;

import java.util.Map;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class RestServer {
	
	private static final StringBuilder pkgs = new StringBuilder();
	
	private static synchronized void addPkgString(final String pkg){
		if(pkgs.length() > 0){
			pkgs.append(",");
		}
		pkgs.append(pkg);
	}

	public static void start() throws Exception {
		
		addPkgString("org.monitoring.stream.analytics.service.rest");

		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		// TODO make this configurable
		int adminPort = 8080;

		// thread pool configuration
		QueuedThreadPool threadPool = new QueuedThreadPool(5000, 200, 30000);
		// server
		Server jettyServer = new Server(threadPool);
		jettyServer.setHandler(context);

		// Server server = new Server(threadPool);
		// connector configuration
		ServerConnector httpConnector = new ServerConnector(jettyServer);
		httpConnector.setPort(adminPort);
		httpConnector.setIdleTimeout(30000);

		jettyServer.setConnectors(new Connector[] { httpConnector });

		ServletHolder jerseyServlet = context.addServlet(
				org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter(
				"jersey.config.server.provider.packages", pkgs.toString());
		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}

}
