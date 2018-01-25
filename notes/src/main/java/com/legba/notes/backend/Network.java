package com.legba.notes.backend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class Network {

	public Network(){
		
	}
	
	@GET
	@Path("/app/files/list")
    @Produces(MediaType.APPLICATION_JSON)
	public String[] getDocumentList(){
		return new String[] {"Document1", "Document2", "Document3"};
	}
	
	@GET
	@Path("/app/files/get/{id}")
	public String getFileList(@PathParam("id") int id){
		return "Document " + id + " Contents";
	}
	
}
