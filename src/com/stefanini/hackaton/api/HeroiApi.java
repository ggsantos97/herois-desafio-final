package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.rest.exceptions.NegocioException;
import com.stefanini.hackaton.service.HeroiService;

@Path("/heroi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroiApi {

	@Inject
	private HeroiService service;

	@GET
	public Response listar() {
		return Response.ok(service.listar()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Integer id) throws NegocioException{
		return Response.ok(service.findById(id)).build();
	}
	
	
}
