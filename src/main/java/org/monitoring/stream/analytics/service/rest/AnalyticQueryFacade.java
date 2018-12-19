package org.monitoring.stream.analytics.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

@Path("/analytic.query")
public class AnalyticQueryFacade {

	@POST
	@Path("create")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getResult(String textInput){
		
		Response response = Response.status(Response.Status.ACCEPTED).build();
		try {
			String responseBody = textInput + ": Recieved";
			response = Response.status(Response.Status.OK).entity(responseBody).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR").build();
		}
		
		return response;
		
	}
}
